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
            
            return rs.next();
              		
    	}catch(Exception e) {
    		System.out.println(e);
    		JOptionPane.showMessageDialog(null, "Errore nella classe UteneteDAO, funzione: ctrlUsrname");
    		return false;
    	}
    }
    	
    
    //MI SERVE PER CONTROLLARE LA PASSWORD INSERITA SE E' CORRETTA:
    public boolean ctrlPassword(String username, String password) {
        // 1.RECUPER L'HASH SALVATO PER QUESTO UTENTE:
        String sql = "SELECT Passwd FROM prguninabiogarden.Utente WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                String hashedPasswordFromDB = rs.getString("Passwd");
                // 2. CONFRONTA LA PASSWORD(IN HASH) CON LA PASSOWRD PRESA DAL DB(IN HASH) BCrypt.checkpw()
                boolean isPasswordCorrect = BCrypt.checkpw(password, hashedPasswordFromDB);
                if (isPasswordCorrect) {
                    return true;
                } else {
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username non trovato.");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Errore nel controllo della password.");
            return false;
        }
    }

    //CONTROLLO SE E' UN PROPRIETARIO O COLTIVATORE:
    public int controlloTipoUtente(String username) {
    	String sql = "SELECT\r\n"
    			+ "  CASE\r\n"
    			+ "    WHEN P.username IS NOT NULL THEN 1  -- È un Proprietario\r\n"
    			+ "    WHEN C.username IS NOT NULL THEN 2  -- È un Coltivatore\r\n"
    			+ "    ELSE 0                                -- (Non dovrebbe mai accadere, ma gestito per sicurezza)\r\n"
    			+ "  END AS tipo_utente\r\n"
    			+ "FROM prguninabiogarden.Utente AS U\r\n"
    			+ "LEFT JOIN prguninabiogarden.Proprietario AS P ON U.username = P.username\r\n"
    			+ "LEFT JOIN prguninabiogarden.Coltivatore AS C ON U.username = C.username\r\n"
    			+ "WHERE U.username = ?";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement psmt = conn.prepareStatement(sql)) {

               psmt.setString(1, username);
               ResultSet rs = psmt.executeQuery();

               if (rs.next()) {
                   int tipoUtente = rs.getInt("tipo_utente");
                   // tipoUtente sarà 1 (Proprietario), 2 (Cliente) o 0 (Errore)
                   return tipoUtente;
               } else {
                   return 0; // Utente non trovato
               }
               
           } catch (Exception e) {
               System.out.println(e);
               JOptionPane.showMessageDialog(null, "Errore nel controllo della tipologia di utente! funzione controlloTipoUtente, classe UtenteDAO");
               return 0;
           }
    	
    }
    
    //MI SERVE PER PRENDERE I DATI DELL'UTENTE:
    public Utente prendiDatiUtente(String Username) {
    	String sql = "SELECT Nome, Cognome, data_nascita, genere, username, passwd FROM prguninabiogarden.Utente WHERE username = ?";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement psmt = conn.prepareStatement(sql)) {

               psmt.setString(1, Username);
               ResultSet rs = psmt.executeQuery();

               if (rs.next()) {
            	   String nome = rs.getString(1);
            	   String cognome = rs.getString(2);
            	   Date dataNascita = rs.getDate(3);
            	   String genere = rs.getString(4);
            	   String username = rs.getString(5);
            	   String password = rs.getString(6);
            	   
            	   //CAST DEL GENERE:
            	   Genere genCast = Genere.valueOf(genere);
            	   
            	   Utente u = new Utente(nome, cognome, dataNascita, genCast, username, password);
            	   
            	   return u;
            	   
               } else {
            	   JOptionPane.showMessageDialog(null, "Mi dispiace ma c'è stato un'errore nella ricerca dell'utente");
               }
               
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Errore nell'estrazione dei dati dell'utente! funzione prendiDatiUtente, classe UtenteDAO");
               return null;
           }
    	return null;    	
    }
    
//QUESTI METODI SERVIRANNO PER LA MODIFICA DEI DATI DI UN UTENTE:
    
    //MODIFICA IL NOME:
    public void modificaNome(String username, String newNome) {
    	String sql = "UPDATE prguninabiogarden.Utente SET Nome = ? WHERE Username = ?";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		
    		psmt.setString(1, newNome);
            psmt.setString(2, username);
               
            psmt.executeUpdate();
           } catch (Exception e) {
               System.out.println(e);
               JOptionPane.showMessageDialog(null, "Errore nella modifica del nome utente! funzione modificaNome, classe UtenteDAO");
           }    	
    }
    
    //MODIFICA IL COGNOME:
    public void modificaCognome(String username, String newCognome) {
    	String sql = "UPDATE prguninabiogarden.Utente SET Cognome = ? WHERE Username = ?";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		
    		psmt.setString(1, newCognome);
            psmt.setString(2, username);
               
            psmt.executeUpdate();
           } catch (Exception e) {
               System.out.println(e);
               JOptionPane.showMessageDialog(null, "Errore nella modifica del cognome utente! funzione modificaNome, classe UtenteDAO");
           }     	
    }
    
    
}

