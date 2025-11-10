import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ColturaDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	public boolean inserisci(String nome, String colore, String stagione, String tipo) {
		String sql = "INSERT INTO prguninabiogarden.Coltura(nome, colore, stagione, tipo) "
				+ "VALUES(?, ?, ?, ?) ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setString(1, nome);
                psmt.setString(2,  colore);
                psmt.setString(3, stagione);
                psmt.setString(4, tipo);
                
            int x  = psmt.executeUpdate();
            return x > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento della coltura! (CLASSE ColturaDAO), funzione: inserisci" + e);
    		return false;
    	} 
	}
	
	//MI SERVE PER POPOLARE LA TABELLA SIA CON TUTTE LE COLTURE SIA CON LE COLTURE DISPONIBILI CHE NON:
	public void popolaTabella(int idDep, DefaultTableModel model, boolean disp) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Coltura "
				+ "WHERE id_deposito = ? AND disponibilità = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idDep);
			psmt.setBoolean(2, disp);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object[]{rs.getString("nome"), rs.getString("colore"), rs.getString("stagione"), rs.getString("tipo"), rs.getString("disponibilità")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione popolataTabella, nella classe ColturaDAO " + e);
    	} 
	}
	
	//MI SERVE PER ELIMINARE UNA COLTURA:
	public boolean elimina(String nome, String colore, String stagione, String tipo) {
		String sql = "DELETE FROM prguninabiogarden.Coltura "
				+ "WHERE "
				+ "nome = ?, "
				+ "colore = ?, "
				+ "stagione = ?, "
				+ "tipo = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setString(1, nome);
			psmt.setString(2, colore);
			psmt.setString(3, stagione);
			psmt.setString(4, tipo);
			
			int x = psmt.executeUpdate();
			
			return x > 0; 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione elimina, nella classe ColturaDAO " + e);
    		return false;
    	} 
	}
	
}
