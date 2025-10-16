import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class UtenteDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
    private Utente utente;
    
    public void inserisicUtente(Utente utente) {
    	System.out.println("Sono nella funzione inserisci utente:");
    	String sql = "INSERT INTO prguninabiogarden.Utente (Nome, Cognome, Data_nascita, Genere, Username, Passwd) VALUES(?, ?, ?, ?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    			
    		System.out.println("Sto nel try-catch");
    		
                psmt.setString(1, utente.getNome());
                psmt.setString(2, utente.getCognome());
                System.out.println((Date) utente.getDataNascita());
                psmt.setDate(3, (Date) utente.getDataNascita());
                psmt.setObject(4, utente.getGenere());
                psmt.setString(5, utente.getUsername());
                psmt.setString(6, utente.getPassword());
                
                
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		System.out.println(e);
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'utente! (CLASSE UtenteDAO), funzione: inserisciUtente" + e);
    	}    	
    	
    	
    }
    
}

