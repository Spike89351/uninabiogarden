import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ColtivatoreDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
    
	//MI SERVE PER INSERIRE UN COLTIVATORE:
	public void inserisciColtivatore(String username) {
		String sql = "INSERT INTO prguninabiogarden.Coltivatore (Username) VALUES(?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {

               
                psmt.setString(1, username);
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Coltivatore! (CLASSE ColtivatoreDAO), funzione: inserisciColtivatore" + e);
    	}   
	}
	
	//MI SERVE A TROVARE L'ID DEL COLTIVATORE TRAMITE USERNAME:
	public int trovaId(String username) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Utente AS U "
				+ "JOIN prguninabiogarden.Coltivatore AS C ON U.username = C.username "
				+ "WHERE U.username = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
               
                psmt.setString(1, username);
                
                ResultSet rs = psmt.executeQuery();
                
            if(rs.next()) {
            	return rs.getInt("id_coltivatore");
            }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel trovare l'id del Coltivatore! (CLASSE ColtivatoreDAO), funzione: trovaId" + e);
    		return -1;
    	}
		return -1;
	}
	
	public void trovaCredenziali(String username, JTextField nome, JTextField cognome) {
		String sql = "SELECT nome, cognome "
				+ "FROM prguninabiogarden.Utente AS U "
				+ "WHERE U.username = ? ";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
               
                psmt.setString(1, username);
                
                ResultSet rs = psmt.executeQuery();
                
            if(rs.next()) {
            	nome.setText(rs.getString("nome"));
            	cognome.setText(rs.getString("cognome"));
            }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel trovare le credenziali del Coltivatore! (CLASSE ColtivatoreDAO), funzione: trovaCredenziali" + e);
    	}
	}
	
	//SERVE A POPOLARE LA TABELLA CON TUTTI I COLTIVATORI:
	public void popolaTabella(DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Utente AS U "
				+ "JOIN prguninabiogarden.Coltivatore AS C ON U.username = C.username "
				+ "WHERE C.disponibilità = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
               
				psmt.setBoolean(1, true);
			
                ResultSet rs = psmt.executeQuery();
                
            while(rs.next()) {
				model.addRow(new Object[]{rs.getInt("id_coltivatore"), rs.getString("Nome"), rs.getString("Cognome"), rs.getDate("data_nascita")});
            }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con le attività del coltivatore! (CLASSE ColtivatoreDAO), funzione: popolaTabella" + e);
    	}
	}
	
	//MI SERVE PER ASSOCIARE UN COLTIVATORE A UN'ATTIVITA':
	public boolean associaAttivitàAlColtivatore(int idAttività, int idColtivatore) {
		String sql = "UPDATE prguninabiogarden.Coltivatore "
				+ "SET id_attività = ?, "
				+ "disponibilità = ? "
				+ "WHERE id_coltivatore = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
	               
				psmt.setInt(1, idAttività);
				psmt.setBoolean(2, false);
				psmt.setInt(3, idColtivatore);
			
                int result = psmt.executeUpdate();
                
            return result > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione che serve per associare un coltivatore a un'attività! (CLASSE ColtivatoreDAO), funzione: associaAttivitàAlColtivatore" + e);
    		return false;
    	}
	}
	
//MI SERVE PER POPOLARE LA TABELLA CON TUTTI I PROGETTI/ATTIVITA' SU CUI STA LAVORANDO IL COLTIVATORE:
	//POTREI CERCARE PER STATO DELL'ATTIVITA:
	public void tutteLeAttività(int idColt, DefaultTableModel model, String cercaAtt){
		if(cercaAtt.isBlank()) {
			allAttività(idColt, model);
		}else if(cercaAtt.equals("Completata")){
			statoCompletato(idColt, model, cercaAtt);
		}
	}
	
	//MI SERVE PER LA FUNZIONE 'tutteLeAttività': 
		//CERCA TUTTE LE ATTIVITA':
	private void allAttività(int idColt, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Coltivatore AS C "
				+ "JOIN prguninabiogarden.Attività AS A ON C.id_attività = A.id_attività "
				+ "JOIN prguninabiogarden.Terreno AS T ON A.id_terreno = T.id_terreno "
				+ "WHERE C.id_coltivatore = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
               
                psmt.setInt(1, idColt);
                
                ResultSet rs = psmt.executeQuery();
                
            while(rs.next()) {
				model.addRow(new Object[]{rs.getString("id_attività"), rs.getString("tipo_attività"), rs.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("stato_attività"), rs.getString("indirizzo")});
            }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con le attività del coltivatore! (CLASSE ColtivatoreDAO), funzione: allAttività" + e);
    	}
	}
	
	//CERCA IN BASE A UN PARAMETRO (COMPLETATO): (Non funziona)
	private void statoCompletato(int idColt, DefaultTableModel model, String tipoStato) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Coltivatore AS C "
				+ "JOIN prguninabiogarden.logStoricoColtivatore AS STC ON C.id_coltivatore = STC.id_coltivatore "
				+ "JOIN prguninabiogarden.Attività AS A ON STC.id_attività = A.id_attività "
				+ "JOIN prguninabiogarden.Terreno AS T ON A.id_terreno = T.id_terreno "
				+ "WHERE C.id_coltivatore = ? AND STC.stato = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
               
                psmt.setInt(1, idColt);
                psmt.setString(2, tipoStato);
                
                ResultSet rs = psmt.executeQuery();
                
            while(rs.next()) {
				model.addRow(new Object[]{rs.getString("id_attività"), rs.getString("tipo_attività"), rs.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("stato"), rs.getString("indirizzo")});
            }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con le attività del coltivatore! (CLASSE ColtivatoreDAO), funzione: attivitàPianificate" + e);
    	}
	}
	
}
