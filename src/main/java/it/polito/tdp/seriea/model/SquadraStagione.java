package it.polito.tdp.seriea.model;

public class SquadraStagione implements Comparable<SquadraStagione>{
	
	public enum Risultato {
		VITTORIA, PAREGGIO
	}

	private Team squadra;
	private int numeroTifosi;
	private int punti;

	public SquadraStagione(Team squadra, int numeroTifosi, int punti) {
		super();
		this.squadra = squadra;
		this.numeroTifosi = numeroTifosi;
		this.punti = punti;
	}

	public Team getSquadra() {
		return squadra;
	}

	public void setSquadra(Team squadra) {
		this.squadra = squadra;
	}

	public int getNumeroTifosi() {
		return numeroTifosi;
	}

	public void setNumeroTifosi(int numeroTifosi) {
		this.numeroTifosi = numeroTifosi;
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}
	
	public void assegnaPunti(Risultato risultato) {
		switch(risultato) {
		case VITTORIA:
			punti = punti + 3;
			break;
		case PAREGGIO:
			punti = punti + 1;
			break;
		}
	}

	@Override
	public String toString() {
		return "Squadra: " + squadra + ", numero tifosi: " + numeroTifosi + ", punti: " + punti;
	}

	@Override
	public int compareTo(SquadraStagione other) {
		return other.punti - this.punti;
	}


	

}
