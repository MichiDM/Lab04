package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		cdao.getCorsoPerNome("Economia aziendale");
		
		Corso corso = new Corso("01KSUPG", 8, "Gestione dell'innovazione e sviluppo prodotto", 2);
		
		cdao.getStudentiIscrittiAlCorso(corso);
	}

}
