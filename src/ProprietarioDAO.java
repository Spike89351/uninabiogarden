import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class ProprietarioDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
    
    
    public void inserisiciProprietario(String Username, String email, String partitaIva) {
    	String sql = "INSERT INTO prguninabiogarden.Proprietario (Username, email, partita_iva) VALUES(?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {

               
                psmt.setString(1, Username);
                psmt.setString(2, email);
                psmt.setString(3, partitaIva);
                
                
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Proprietario! (CLASSE ProprietarioDAO), funzione: inserisciProprietario" + e);
    	}    	    	
    }
    
    public int trovaCodiceProprietario(String username) {
    	int idProprietario = -1;
    	
    	String sql = "SELECT Id_proprietario FROM prguninabiogarden.Proprietario WHERE Username = ?";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
                psmt.setString(1, username);
                
                
                try (ResultSet rs = psmt.executeQuery()) {
                    // Controlla se è stata trovata una riga
                    if (rs.next()) {
                        idProprietario = rs.getInt("Id_proprietario");
                    }
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Proprietario! (CLASSE ProprietarioDAO), funzione: inserisciProprietario" + e);
    	}   
    	return idProprietario;
    }
    
    
//    public Proprietario ritornaProprietario(String username) {
//    	String sql = "SELECT nome, cognome, data_nascita, genere, username, passwd, id_proprietario, email, partita_iva, id_proprietario, id_terreno, superfice, tipo_terreno, fertilità FROM prguninabiogarden.Proprietario AS P JOIN "
//    			+ "	prguninabiogarden.Terreno AS T ON P.username = T.username  WHERE Username = ?";
//    	
//    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
//    			PreparedStatement psmt = conn.prepareStatement(sql)) {
//    		
//    		psmt.setString(1, username);
//    			
//    		 ResultSet rs = psmt.executeQuery();
//    		 	
//    		if (rs.next()) {
//         	   String nome = rs.getString(1);
//         	   String cognome = rs.getString(2);
//         	   Date dataNascita = rs.getDate(3);
//         	   String genere = rs.getString(4);
//         	   String password = rs.getString(6); //GLI DIAMO LA PASSWORD HASHATA
//         	   int idProp = rs.getInt(7);
//         	   String email = rs.getString(8);
//         	   String partitaIva = rs.getString(9);
//         	   int id_proprietario = rs.getInt(10);
//         	   int id_terreno = rs.getInt(11);
//         	   double superfice = rs.getDouble(12);
//         	   String tipoTerrString = rs.getString(13);
//         	   String fertString = rs.getString(14);
//         	   
//         	   //CAST:
//         	   Genere genCast = Genere.valueOf(genere);
//         	   TipoTerreno tipTerra = TipoTerreno.valueOf(tipoTerrString);
//         	   Fertilità fertTerr = Fertilità.valueOf(fertString);
//         	   
//         	   
//         	   
//         	  Proprietario p = new Proprietario(nome, cognome, dataNascita, genCast, username, password, idProp, email, partitaIva);
//         	   
//         	   return p;
//         	   
//            }
//    	}catch(Exception e) {
//    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Proprietario! (CLASSE ProprietarioDAO), funzione: inserisciProprietario" + e);
//    	}   
//    	
//    }
    
    
}
