package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	
	private SerieADAO dao;
	private Graph<Team, DefaultWeightedEdge> grafo;
	private Simulator sim;
	
	public Model() {
		this.dao = new SerieADAO();
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<Team, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.listTeams());
		
		// aggiungo gli archi
		for(Adiacenza a : this.dao.getAdiacenze()) {
			if(!this.grafo.containsEdge(this.grafo.getEdge(a.getCasa(), a.getOspite()))) {
				Graphs.addEdge(this.grafo, a.getCasa(), a.getOspite(), a.getPeso());
			} else {
				DefaultWeightedEdge e = this.grafo.getEdge(a.getCasa(), a.getOspite());
				this.grafo.setEdgeWeight(e, this.grafo.getEdgeWeight(e) + a.getPeso());
			}
		}
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Team> listTeams() {
		return this.dao.listTeams();
	}
	
	public List<SquadraPartite> getConnessioniSquadra(Team squadra) {
		List<SquadraPartite> squadre = new ArrayList<>();
		for(Team team : Graphs.neighborListOf(this.grafo, squadra)) {
			SquadraPartite s = new SquadraPartite(team, (int) this.grafo.getEdgeWeight(this.grafo.getEdge(squadra, team)));
			squadre.add(s);
		}
		
		Collections.sort(squadre);
		return squadre;
	}
	
	public List<Match> listMatches(Season season) {
		return this.dao.listMatches(season);
	}
	
	public List<Team> getTeamsBySeason(Season season) {
		return this.dao.getTeamsBySeason(season);
	}
	
	public List<SquadraStagione> simula(Season season) {
		sim = new Simulator(this);
		
		sim.setSeason(season);
		sim.init();
		sim.run();
		List<SquadraStagione> result = sim.getResult();
		Collections.sort(result);
		return result;
	}
	
	public List<Season> listSeasons() {
		return this.dao.listSeasons();
	}

}
