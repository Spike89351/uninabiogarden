import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	
	//SERVE A POPOLARE LA TABELLA CON I DEPOSITI:
	public void popolaTabellaDepositi(int idProp, DefaultTableModel model) {
		String sql = "SELECT indirizzo_deposito, dim_Deposito "
				+ "FROM prguninabiogarden.Deposito "
				+ "WHERE id_Proprietario = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object[]{rs.getInt("indirizzo_deposito"), rs.getString("dim_Deposito")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione popolataTabellaDepsoiti, nella classe DepositoDAO " + e);
    	} 
	}
	
	
	
	
}
