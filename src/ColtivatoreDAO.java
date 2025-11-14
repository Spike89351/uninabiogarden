import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

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
	
	
}
