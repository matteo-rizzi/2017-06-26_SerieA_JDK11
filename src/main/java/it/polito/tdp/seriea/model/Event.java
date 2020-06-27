package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{

	private LocalDate time;
	private Match match;

	public Event(LocalDate time, Match match) {
		super();
		this.time = time;
		this.match = match;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.getTime());
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", match=" + match + "]";
	}

}
