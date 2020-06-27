package it.polito.tdp.seriea.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		Season season = new Season(2005, "2004/2005");
		List<SquadraStagione> result = m.simula(season);
		for(SquadraStagione ss : result) {
			System.out.println(ss);
		}
	}

}
