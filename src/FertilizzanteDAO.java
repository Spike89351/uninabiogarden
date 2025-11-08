import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class FertilizzanteDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	//MI SERVE PER INSERIRE I FERTILIZZANTI NEL DB:
	public boolean inserisciFertilizzanti(int idDep, double letame, double compost, double granulari, double liquidi) {
		String sql = "INSERT INTO prguninabiogarden.Fertilizzante(id_deposito, letame, compost, granulari, liquidi)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idDep);
                psmt.setDouble(2,  letame);
                psmt.setDouble(3, compost);
                psmt.setDouble(4, granulari);
                psmt.setDouble(5, liquidi);
                
            psmt.executeUpdate();
            return true;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Fertilizzante! (CLASSE FertilizzanteDAO), funzione: inserisciFertilizzante" + e);
    		return false;
    	}  		
	}
	
	//MI SERVE PER POPOLARE LA TABELLA CON I FERTILIZZANTI PER DEPOSITO:
	public void popolaTabella(int idDep) {
		
	}
	
}
