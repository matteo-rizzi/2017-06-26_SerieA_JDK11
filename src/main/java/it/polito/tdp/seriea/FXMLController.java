package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.SquadraPartite;
import it.polito.tdp.seriea.model.SquadraStagione;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Team> boxSquadra;

    @FXML
    private ChoiceBox<Season> boxStagione;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaTifosi;

    @FXML
    private Button btnAnalizzaSquadre;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaSquadre(ActionEvent event) {
    	this.txtResult.clear();
    	
    	this.model.creaGrafo();
    	this.txtResult.appendText("Grafo creato!\n");
    	this.txtResult.appendText("# VERTICI: " + this.model.nVertici() + "\n");
    	this.txtResult.appendText("# ARCHI: " + this.model.nArchi() + "\n\n");

    }

    @FXML
    void doCalcolaConnessioniSquadra(ActionEvent event) {
    	this.txtResult.clear();
    	
    	if(this.boxSquadra.getValue() == null) {
    		this.txtResult.appendText("Errore! Devi selezionare una squadra dall'apposito menu a tendina");
    		return;
    	}
    	
    	Team squadra = this.boxSquadra.getValue();
    	
    	List<SquadraPartite> result = this.model.getConnessioniSquadra(squadra);
    	this.txtResult.appendText("Elenco connessioni della squadra " + squadra + ":\n");
    	
    	for(SquadraPartite sp : result) {
    		this.txtResult.appendText(sp + "\n");
    	}

    }

    @FXML
    void doSimulaTifosi(ActionEvent event) {
    	this.txtResult.clear();
    	

    	if(this.boxStagione.getValue() == null) {
    		this.txtResult.appendText("Errore! Devi selezionare una stagione dall'apposito menu a tendina");
    		return;
    	}
    	
    	Season season = this.boxStagione.getValue();
    	List<SquadraStagione> result = this.model.simula(season);
    	
    	for(SquadraStagione ss : result) {
    		this.txtResult.appendText(ss + "\n");
    	}

    }

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxStagione != null : "fx:id=\"boxStagione\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaTifosi != null : "fx:id=\"btnSimulaTifosi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaSquadre != null : "fx:id=\"btnAnalizzaSquadre\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxSquadra.getItems().addAll(this.model.listTeams());
		this.boxStagione.getItems().addAll(this.model.listSeasons());
	}
}