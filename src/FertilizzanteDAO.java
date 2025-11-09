import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FertilizzanteDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	//MI SERVE PER INSERIRE I FERTILIZZANTI NEL DB:
	public boolean inserisciFertilizzanti(int idDep, double letame, double compost, double granulari, double liquidi) {
	    String sql = "INSERT INTO prguninabiogarden.Fertilizzante(id_deposito, letame, compost, granulari, liquidi) "
	               + "VALUES (?, ?, ?, ?, ?) "
	               + "ON CONFLICT (id_deposito) "
	               + "DO UPDATE SET "
	               + "letame = Fertilizzante.letame + EXCLUDED.letame, "
	               + "compost = Fertilizzante.compost + EXCLUDED.compost, "
	               + "granulari = Fertilizzante.granulari + EXCLUDED.granulari, "
	               + "liquidi = Fertilizzante.liquidi + EXCLUDED.liquidi";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement psmt = conn.prepareStatement(sql)) {

	        psmt.setInt(1, idDep);
	        psmt.setDouble(2, letame);
	        psmt.setDouble(3, compost);
	        psmt.setDouble(4, granulari);
	        psmt.setDouble(5, liquidi);

	        psmt.executeUpdate();
	        return true;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Fertilizzante: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//MI SERVE PER SOTTRARRE IL FERTILIZZANTE:
	public boolean sottraiFertilizzanti(int idDep, double letame, double compost, double granulari, double liquidi) {
	    String sql = "UPDATE prguninabiogarden.Fertilizzante " +
	                 "SET letame = GREATEST(letame - ?, 0), " +
	                     "compost = GREATEST(compost - ?, 0), " +
	                     "granulari = GREATEST(granulari - ?, 0), " +
	                     "liquidi = GREATEST(liquidi - ?, 0) " +
	                 "WHERE id_deposito = ?";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement psmt = conn.prepareStatement(sql)) {

	        psmt.setDouble(1, letame);
	        psmt.setDouble(2, compost);
	        psmt.setDouble(3, granulari);
	        psmt.setDouble(4, liquidi);
	        psmt.setInt(5, idDep);

	        int rowsAffected = psmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Errore nell'aggiornamento del Fertilizzante: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	
	//MI SERVE PER POPOLARE LA TABELLA CON I FERTILIZZANTI PER DEPOSITO:
	public boolean popolaTabella(int idDep, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Fertilizzante "
				+ "WHERE id_deposito = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idDep);
               
                ResultSet rs = psmt.executeQuery();
    			
    			while(rs.next()) {
    				model.addRow(new Object[]{rs.getDouble("Letame"), rs.getDouble("Compost"), rs.getDouble("Granulari"), rs.getDouble("Liquidi")});
    			}    
    			return true;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella (CLASSE FertilizzanteDAO), funzione: popolaTabella" + e);
    		return false;
    	}
	}
	
}
