package it.polito.tdp.lab04.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	// bisogna istanziare il DAO poichè la classe modello non ha una istanza di questo oggetto
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model(){
		
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	       
	}
	
	// scritto un metodo nel modello che chiede al DAO di interrogare il database
	public List<Corso> getTuttiICorsi(){
		
		return this.corsoDAO.getTuttiICorsi();
	}
	
	
	public Corso getCorso(String codice){
		
		return this.corsoDAO.getCorso(codice);
	}
	
	public Corso getCorsoPerNome(String nomeCorso) {
		
		return this.corsoDAO.getCorsoPerNome(nomeCorso);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getStudente(int matricola) {
		
		return this.studenteDAO.getStudente(matricola);
	}
	
	public List<Corso> getCorsiSeguitiDaStudente(Studente studente) {
		
		return this.studenteDAO.getCorsiSeguitiDaStudente(studente);
	}
	
	public Map <Integer, String> getStudenteIscrittoCorsoSpecifico(Studente studente, Corso corso) {
		
		return this.studenteDAO.getStudenteIscrittoCorsoSpecifico(studente, corso);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		return this.corsoDAO.inscriviStudenteACorso(studente, corso);
	}

}
