import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
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
	
	//MI SERVE PER POPOLARE LA TABELLA CON TUTTI I PROGETTI/ATTIVITA' SU CUI STA LAVORANDO IL COLTIVATORE:
	public void tutteLeAttività(int idColt, DefaultTableModel model){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Coltivatore AS C "
				+ "JOIN prguninabiogarden.Attività AS A ON C.id_attività = A.id_attività "
				+ "WHERE C.id_coltivatore = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
               
                psmt.setInt(1, idColt);
                
                ResultSet rs = psmt.executeQuery();
                
            while(rs.next()) {
				model.addRow(new Object[]{rs.getString("id_attività"), rs.getInt("tipo_attività"), rs.getDouble("data_inizio"), rs.getString("data_fine")});
            }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con le attività del coltivatore! (CLASSE ColtivatoreDAO), funzione: tutteLeAttività" + e);
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
	
	//MI SERVE A CAMBIARE LA DISPONIIBLITA' DI UN COLTIVATORE:
	//DEVO ANCHE CONTROLLARE QUANTI COLTIVATORI CI SONO SU QUELL'ATTIVITA', USARE UNA FUNZIONE ESTERNA
	public boolean dissociaColtivatoreDaAttività(int idAttività) {
		
		return false;
	}
}
