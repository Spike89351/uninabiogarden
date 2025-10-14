import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;


public class UtenteDAO {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USER = "postgres";
  private static final String PASSWORD = "Informatica1";
    
    
    public void inserisicUtente(Utente utente) {
    	String sql = "INSERT INTO prguninabiogarden.Utente (Nome, Cognome, Data_nascita, Genere, Username, Passwd) VALUES(?, ?, ?, ?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {

                psmt.setString(1, utente.getNome());
                psmt.setString(2, utente.getCognome());
                psmt.setDate(3, (Date) utente.getDataNascita());
                psmt.setObject(4, utente.getGenere());
                psmt.setString(5, utente.getUsername());
                psmt.setString(6, utente.getPassword());
                
                
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del veicolo! (CLASSE AutoDAO), funzione: inserisciAuto" + e);
    	}    	
    	
    }
    
    
    
    
}

