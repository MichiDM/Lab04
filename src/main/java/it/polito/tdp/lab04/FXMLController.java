package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbElencoCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextArea txtArea;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	
    	txtArea.clear();
    	
    	int matricola = 0;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
			// TODO: handle exception
    		txtArea.setText("Inserted Value is not an integer value");
    		return;
		}
    	
    	
    	Studente s = model.getStudente(matricola);
    	
    	if (s == null) {
    		txtArea.setText("Matricola non esistente nel DB");
    		return;
    	}
    	
    	List<Corso> corsiStudente = model.getCorsiSeguitiDaStudente(s);
    	
    	for (Corso c : corsiStudente)
    		
    		txtArea.appendText("" + c + "\n");
    	

    }

    @FXML
    void cercaIscrittiCorso(ActionEvent event) {
    	
    	txtArea.clear();
    	
    	String nomeCorso = cmbElencoCorsi.getValue();
    	
    	if (nomeCorso == null || nomeCorso ==" ") {
    		txtArea.setText("Selezionare un corso dal menù a tendina");
    		return;
    	}
    	
    	Corso corso = model.getCorsoPerNome(nomeCorso);	
    	
    	List<Studente> studentiAlCorso = model.getStudentiIscrittiAlCorso(corso);
    	
    	
    	if (studentiAlCorso.size() == 0) {
    		txtArea.setText("Nessuno studente iscritto al corso di: " + corso.getNome());
    	}
    	for (Studente s : studentiAlCorso)
    		
    		txtArea.appendText("" + s + "\n");

    }

    @FXML
    void checkCompletamentoAutomatico(ActionEvent event) {
    	
    	int matricola = 0;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
			// TODO: handle exception
    		txtArea.setText("Inserted Value is not an integer value");
    		return;
		}
    	
    	
    	Studente s = model.getStudente(matricola);
    	
    	if (s == null) {
    		txtArea.setText("Matricola non esistente nel DB");
    		return;
    	}
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	
    }
    
    @FXML
    void studenteIscrittoCorso(ActionEvent event) {
    	
    	txtArea.clear();
    	
    	
    	String nomeCorso = cmbElencoCorsi.getValue();
    	
    	if (nomeCorso == null || nomeCorso ==" ") {
    		txtArea.setText("Selezionare un corso dal menù a tendina");
    		return;
    	}
    	
    	Corso corso = model.getCorsoPerNome(nomeCorso);
    	
    	int matricola = 0;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
			// TODO: handle exception
    		txtArea.setText("Inserted Value is not an integer value");
    		return;
		}
    	
    	
    	Studente s = model.getStudente(matricola);
    	
    	if (s == null) {
    		txtArea.setText("Matricola non esistente nel DB");
    		return;
    	}
    	
    	Map <Integer, String> result = model.getStudenteIscrittoCorsoSpecifico(s, corso);
    	
    	if (result.size() != 0) {
    		txtArea.setText("studente " + s.getNome() + " " + s.getCognome() + " già iscritto a " + corso.getNome());
    	}
    	else {
    		txtArea.setText("studente " + s.getNome() + " " + s.getCognome() + " NON iscritto a " + corso.getNome());
    	}
    	
    	
    	
    	

    }

    @FXML
    void iscrizioneStudenteCorso(ActionEvent event) {
    	
    	txtArea.clear();
    	
    	String nomeCorso = cmbElencoCorsi.getValue();
    	
    	if (nomeCorso == null || nomeCorso ==" ") {
    		txtArea.setText("Selezionare un corso dal menù a tendina");
    		return;
    	}
    	
    	Corso corso = model.getCorsoPerNome(nomeCorso);
    	
    	int matricola = 0;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
			// TODO: handle exception
    		txtArea.setText("Inserted Value is not an integer value");
    		return;
		}
    	
    	
    	Studente s = model.getStudente(matricola);
    	
    	if (s == null) {
    		txtArea.setText("Matricola non esistente nel DB");
    		return;		
    	}
    	
    	boolean iscrizione = model.inscriviStudenteACorso(s, corso);
    	
    	if(iscrizione == true) {
    		txtArea.setText("Studente iscritto al corso");
    	}
    	
    	else {
    		txtArea.setText("Impossibile aggiungere studente al corso");
    	}
    	
    	

    }

    @FXML
    void reset(ActionEvent event) {
    	
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtArea.clear();
    	

    }

    @FXML
    void initialize() {
        assert cmbElencoCorsi != null : "fx:id=\"cmbElencoCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";      

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		List<String> nomiCorsi = new ArrayList<String>();
		for (Corso c : model.getTuttiICorsi())
			nomiCorsi.add(c.getNome());
		
		cmbElencoCorsi.getItems().add(" ");
		cmbElencoCorsi.getItems().addAll(nomiCorsi);
		this.model = model;
		
	}

}
