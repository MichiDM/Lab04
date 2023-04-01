package it.polito.tdp.lab04.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {
		
		
		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			// il while prende tutte le righe del risultato della query "final String sql"
			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				// print usata per verificare la giusta connessione tra DB e Java, testando ciò in TestDB
				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Corso corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(corso);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codice) {
		
		final String sql = "SELECT * "
						+ "FROM corso c "
						+ "WHERE c.codins = ?";


		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);

			ResultSet rs = st.executeQuery();
			
			Corso corso = null;

			// il while prende tutte le righe del risultato della query "final String sql"
			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				 
				 // print usata per verificare la giusta connessione tra DB e Java, testando ciò in TestDB
				 //System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
			}

			conn.close();
			return corso;
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	/*
	 * Dato un nome di un corso, ottengo il corso
	 */
	public Corso getCorsoPerNome(String nomeCorso) {
		
		final String sql = "SELECT * "
						+ "FROM corso c "
						+ "WHERE c.nome = ?";


		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeCorso);

			ResultSet rs = st.executeQuery();
			
			Corso corso = null;

			// il while prende tutte le righe del risultato della query "final String sql"
			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				 
				 // print usata per verificare la giusta connessione tra DB e Java, testando ciò in TestDB
				 // System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
			}

			conn.close();
			return corso;
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
					+ "FROM studente s, iscrizione i "
					+ "WHERE s.matricola = i.matricola AND i.codins = ?";
		
		List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				Studente s = new Studente( rs.getInt("matricola"), rs.getString("cognome"),
						rs.getString("nome"), rs.getString("CDS") );
				studenti.add(s);
				
				// print usata per verificare la giusta connessione tra DB e Java, testando ciò in TestDB
				// System.out.println(s.getMatricola() + " " + s.getCognome() + " " + s.getNome() + " " + s.getCDS());
				
			}
			
			st.close();
			rs.close();
			conn.close();
			return studenti;
			
		} catch(SQLException e) {
			System.out.println("Error in Corso DAO");
			e.printStackTrace();
			return null;		
			
		}
	
		
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		
		String sql = "INSERT INTO iscrizione (matricola, codins) "
					+ "VALUES (?, ?);";
	
		
	
		try {
		
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
		
			int rs = st.executeUpdate();
		
			if (rs == 1) {
			
			st.close();
			conn.close();
			return true;
			}
		
		} catch(SQLException e) {
			System.out.println("Error in Corso DAO");
			e.printStackTrace();
					
		
	}
		
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
