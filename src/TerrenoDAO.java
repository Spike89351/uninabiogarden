import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class TerrenoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	public void inserisciTerreno(int codeProprietario, double superficie, TipoTerreno TipologiaTerreno) {
		String tipoTerreno = String.valueOf(TipologiaTerreno);
String sql = "INSERT INTO prguninabiogarden.Terreno (Id_proprietario, Superfice, Tipo_terreno) VALUES(?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
                psmt.setInt(1, codeProprietario);
                psmt.setDouble(2, superficie);
                psmt.setObject(3, TipologiaTerreno);
                
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Terreno! (CLASSE TerrenoDAO), funzione: inserisciTerreno" + e);
    	}    	
    	
	}
	
	
	
	
}
