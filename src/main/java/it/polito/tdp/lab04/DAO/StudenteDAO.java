package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	
	public Studente getStudente(int matricola) {
		
		
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
						 + "FROM studente s "
						 + "WHERE s.matricola = ?";

		Studente match = null;

		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				match = new Studente( rs.getInt("matricola"), rs.getString("cognome"),
						rs.getString("nome"), rs.getString("CDS") );
				
				// print usata per verificare la giusta connessione tra DB e Java, testando ciò in TestDB
				// System.out.println(s.getMatricola() + " " + s.getCognome() + " " + s.getNome() + " " + s.getCDS());
				
			}
			
			st.close();
			rs.close();
			conn.close();
			return match;
			
		} catch(SQLException e) {
			System.out.println("Error in Corso DAO");
			e.printStackTrace();
			return null;		
			
		}
	}
	
	public List<Corso> getCorsiSeguitiDaStudente(Studente studente) {
		
		String sql = "SELECT c.* " 
				   + "FROM corso c, iscrizione i "
				   + "WHERE c.codins = i.codins AND i.matricola = ?";
		
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
				
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				Corso corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(corso);
				
				// print usata per verificare la giusta connessione tra DB e Java, testando ciò in TestDB
				// System.out.println(s.getMatricola() + " " + s.getCognome() + " " + s.getNome() + " " + s.getCDS());
				
			}
			
			st.close();
			rs.close();
			conn.close();
			return corsi;
			
		} catch(SQLException e) {
			System.out.println("Error in Corso DAO");
			e.printStackTrace();
			return null;		
			
		}
	
		
		
	}
	
	
	public Map <Integer, String> getStudenteIscrittoCorsoSpecifico(Studente studente, Corso corso) {
		
		String sql = "SELECT i.* " 
				   + "FROM iscrizione i "
				   + "WHERE i.codins = ? AND i.matricola = ?";
		
		Map<Integer, String> mappa = new HashMap<Integer, String>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
				
				String codins = rs.getString("codins");
				int matricola = rs.getInt("matricola");
				mappa.put(matricola, codins);
					
				
				// print usata per verificare la giusta connessione tra DB e Java, testando ciò in TestDB
				// System.out.println(s.getMatricola() + " " + s.getCognome() + " " + s.getNome() + " " + s.getCDS());
				
			}
			
			st.close();
			rs.close();
			conn.close();
			return mappa;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);	
			
		}
	
		
		
	}
	

	
	

}
