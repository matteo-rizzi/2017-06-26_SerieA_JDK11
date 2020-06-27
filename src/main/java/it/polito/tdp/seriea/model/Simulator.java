package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.seriea.model.SquadraStagione.Risultato;


public class Simulator {
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	// PARAMETRI DI SIMULAZIONE
	private Season season;
	
	// MODELLO DEL MONDO
	private Model model;
	private final int P = 10;
	
	// VALORI DA CALCOLARE
	private Map<Team, SquadraStagione> result;

	public List<SquadraStagione> getResult() {
		List<SquadraStagione> ss = new ArrayList<SquadraStagione>(result.values());
		return ss;
	}

	public void setSeason(Season season) {
		this.season = season;
	}
	
	public Simulator(Model model) {
		this.model = model;
	}
	
	public void init() {
		this.queue = new PriorityQueue<>();
		this.result = new HashMap<Team, SquadraStagione>();
		
		for(Team team : this.model.getTeamsBySeason(season)) {
			SquadraStagione ss = new SquadraStagione(team, 1000, 0);
			result.put(team, ss);
		}
		
		for(Match match : this.model.listMatches(season)) {
			Event e = new Event(match.getDate(), match);
			this.queue.add(e);
		}
	}
	
	public void run() {
		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			System.out.println(e);
			this.processEvent(e);
		}
	}

	private void processEvent(Event e) {
		int goalCasa = e.getMatch().getFthg();
		int goalOspite = e.getMatch().getFtag();
		
		SquadraStagione casa = this.result.get(e.getMatch().getHomeTeam());
		SquadraStagione ospite = this.result.get(e.getMatch().getAwayTeam());
		
		if(casa.getNumeroTifosi() > ospite.getNumeroTifosi()) {
			double probabilita = 1 - ((double)ospite.getNumeroTifosi() /(double) casa.getNumeroTifosi());
			double random = Math.random();
			System.out.println("Probabilita: " + probabilita);
			System.out.println("Random: " + random);
			if(random < probabilita && goalOspite > 0) {
				goalOspite = goalOspite - 1;
			}
			System.out.println("Goal ospite: " + goalOspite);
		}
		else if(casa.getNumeroTifosi() < ospite.getNumeroTifosi()) {
			double probabilita = 1 - ((double) casa.getNumeroTifosi() / (double) ospite.getNumeroTifosi());
			double random = Math.random();
			System.out.println("Probabilita: " + probabilita);
			System.out.println("Random: " + random);
			if(random < probabilita && goalCasa > 0) {
				goalCasa = goalCasa - 1;
			}
			System.out.println("Goal casa: " + goalCasa);
		}
		
		if(goalCasa > goalOspite) {
			// HA VINTO LA SQUADRA DI CASA
			int scarto = goalCasa - goalOspite;
			double percDaSpostare = (scarto * P) / 100.0;
			double numeroTifosiSpostati = percDaSpostare * ospite.getNumeroTifosi();
			System.out.println("Tifosi spostati: " + numeroTifosiSpostati);
			ospite.setNumeroTifosi((int) (ospite.getNumeroTifosi() - numeroTifosiSpostati));
			casa.setNumeroTifosi((int) (casa.getNumeroTifosi() + numeroTifosiSpostati));
			casa.assegnaPunti(Risultato.VITTORIA);
		}
		else if(goalCasa < goalOspite) {
			// HA VINTO LA SQUADRA OSPITE
			int scarto = goalOspite - goalCasa;
			double percDaSpostare = (scarto * P) / 100.0;
			double numeroTifosiSpostati = percDaSpostare * casa.getNumeroTifosi();
			System.out.println("Tifosi spostati: " + numeroTifosiSpostati);
			ospite.setNumeroTifosi((int) (ospite.getNumeroTifosi() + numeroTifosiSpostati));
			ospite.assegnaPunti(Risultato.VITTORIA);
			casa.setNumeroTifosi((int) (casa.getNumeroTifosi() - numeroTifosiSpostati));
		} else {
			// HANNO PAREGGIATO
			casa.assegnaPunti(Risultato.PAREGGIO);
			ospite.assegnaPunti(Risultato.PAREGGIO);
		}
		
		System.out.println("Casa : " + casa);
		System.out.println("Ospite: " + ospite);
		
	}
	
	

}
