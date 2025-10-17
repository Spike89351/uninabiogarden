import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class TerrenoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	public void inserisciTerreno(int codeProprietario, double superficie, TipoTerreno TipologiaTerreno, Fertilità tipoFertilità) {
		String sql = "INSERT INTO prguninabiogarden.Terreno (Id_proprietario, Superfice, Tipo_terreno, Fertilità) VALUES(?, ?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		//FACCIO IL CAST:
    		String tipoTerreno = String.valueOf(TipologiaTerreno);
    		String Fertilità = String.valueOf(tipoFertilità);
    		
                psmt.setInt(1, codeProprietario);
                psmt.setDouble(2, superficie);
                psmt.setString(3, tipoTerreno);
                psmt.setString(4, Fertilità);
                
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Terreno! (CLASSE TerrenoDAO), funzione: inserisciTerreno" + e);
    	}    	
    	
	}
	
	
	
	
}
