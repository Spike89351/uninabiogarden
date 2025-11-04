import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class DepositoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	//SERVE PER LA CREAZIONE DI UN DEPOSITO:
	public void creaDeposito(int idProprietario, String indirizzo, Double dimensione) {
		String sql = "INSERRT INTO prguninbiogarden.Deposito(id_terreno, indirizzo_deposito, dim_deposito)"
				+ "VALUES(?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idProprietario);
                psmt.setString(2,  indirizzo.toLowerCase());
                psmt.setDouble(3, dimensione);
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Deposito! (CLASSE DepositoDAO), funzione: inserisciDeposito" + e);
    	}    	    	
    }
	
	
	
	
	
}
