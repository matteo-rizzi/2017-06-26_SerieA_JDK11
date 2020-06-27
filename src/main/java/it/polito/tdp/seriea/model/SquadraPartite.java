package it.polito.tdp.seriea.model;

public class SquadraPartite implements Comparable<SquadraPartite>{

	private Team team;
	private Integer peso;

	public SquadraPartite(Team team, Integer peso) {
		super();
		this.team = team;
		this.peso = peso;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(SquadraPartite other) {
		return other.getPeso().compareTo(this.peso);
	}

	@Override
	public String toString() {
		return "Squadra: " + this.team + ", numero di partite giocate: " + this.peso;
	}

}
