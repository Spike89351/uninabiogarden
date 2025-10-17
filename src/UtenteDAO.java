import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;




public class UtenteDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
    private Utente utente;
    
    public void inserisicUtente(Utente utente) {
    	String sql = "INSERT INTO prguninabiogarden.Utente (Nome, Cognome, Data_nascita, Genere, Username, Passwd) VALUES(?, ?, ?, ?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		//CASTO IL TIPO GENERE IN STRING PERCHE' IL DB NON ACCETTA IL TIPO Object:
    		String gen = String.valueOf(utente.getGenere());
    		
              String hashedPassword = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt());
              // Salva `hashedPassword` nel database
    		
                psmt.setString(1, utente.getNome());
                psmt.setString(2, utente.getCognome());
                psmt.setDate(3, utente.getDataNascita());
               	psmt.setString(4, gen);
                psmt.setString(5, utente.getUsername());
                psmt.setString(6, hashedPassword);
                                
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		System.out.println(e);
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'utente! (CLASSE UtenteDAO), funzione: inserisciUtente" + e);
    	}    	
    }
    
    //MI SERVE PER CAPIRE SE L'USERNAME INSERITO E' CORRETTO:
    public boolean ctrlUsername(String Username) {
    	String sql = "SELECT 1 FROM prguninabiogarden.Utente WHERE username = ?";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		
    		psmt.setString(1, Username);
            ResultSet rs = psmt.executeQuery();

            return rs.next(); // Se c'è almeno una riga, l'username esiste
    		
    	}catch(Exception e) {
    		System.out.println(e);
    		JOptionPane.showMessageDialog(null, "Errore nella classe UteneteDAO, funzione: ctrlUsrname");
    		return false;
    	}
    }
    	
    
    //MI SERVE PER CONTROLLARE LA PASSWORD INSERITA SE E' CORRETTA:
    public boolean ctrlPassword(String username, String password) {
    	String sql = "SELECT 1 FROM prguninabiogarden.Utente WHERE username = ? AND Passwd = ?";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		
    		String hashedPassword = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt());
    		
    		psmt.setString(1, username);
    		psmt.setString(2, hashedPassword);
            ResultSet rs = psmt.executeQuery();

            return rs.next(); // Se c'è almeno una riga, l'username esiste
    		
    	}catch(Exception e) {
    		System.out.println(e);
    		JOptionPane.showMessageDialog(null, "Errore nella classe UteneteDAO, funzione: ctrlUsrname");
    		return false;
    	}
    	
    }
    
}

