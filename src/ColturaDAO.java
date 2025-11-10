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
	
	
	public boolean inserisci(int idDep, String nome, String colore, String stagione, String tipo) {
		String sql = "INSERT INTO prguninabiogarden.Coltura(id_deposito, nome, colore, stagione, tipo) "
				+ "VALUES(?, ?, ?, ?, ?) ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idDep);
                psmt.setString(2, nome);
                psmt.setString(3,  colore);
                psmt.setString(4, stagione);
                psmt.setString(5, tipo);
                
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
				model.addRow(new Object[]{rs.getString("nome"), rs.getString("colore"), rs.getString("stagione"), rs.getString("tipo"), rs.getBoolean("disponibilità")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione popolataTabella, nella classe ColturaDAO " + e);
    	} 
	}
	
	//MI SERVE PER ELIMINARE UNA COLTURA:
	public boolean elimina(int idDep, String nome, String colore, String stagione, String tipo) {
		String sql = "DELETE FROM prguninabiogarden.Coltura "
				+ "WHERE "
				+ "id_deposito = ? "	
				+ "AND nome = ? "
				+ "AND colore = ? "
				+ "AND stagione = ? "
				+ "AND tipo = ? ";
		System.out.println("Sto dopo la query");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
			System.out.println("Sto nel try catch");
			psmt.setInt(1, idDep);
			psmt.setString(2, nome);
			psmt.setString(3, colore);
			psmt.setString(4, stagione);
			psmt.setString(5, tipo);
			System.out.println("Sto dopo i vari psmt.");
			int x = psmt.executeUpdate();
			System.out.println("Sto dopo l'execute query");
			System.out.println("la x è uguale a: "+ x);
			return x > 0; 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione elimina, nella classe ColturaDAO " + e);
    		return false;
    	} 
	}
	
}
