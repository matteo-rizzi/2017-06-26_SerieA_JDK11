package it.polito.tdp.seriea.model;

public class Adiacenza {

	private Team casa;
	private Team ospite;
	private Integer peso;

	public Adiacenza(Team casa, Team ospite, Integer peso) {
		super();
		this.casa = casa;
		this.ospite = ospite;
		this.peso = peso;
	}

	public Team getCasa() {
		return casa;
	}

	public void setCasa(Team casa) {
		this.casa = casa;
	}

	public Team getOspite() {
		return ospite;
	}

	public void setOspite(Team ospite) {
		this.ospite = ospite;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
