import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public ArrayList<Terreno> risaliTerreni(String username) {
		ArrayList<Terreno> listaTerreni = new ArrayList<Terreno>();
		
		String sql = "SELECT * FROM prguninabiogarden.Terreno AS T JOIN prguninabiogarden.Proprietario AS P ON T.id_proprietario = P.id_proprietario WHERE P.Username = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setString(1, username);
				
				ResultSet rs = psmt.executeQuery();
    		
                psmt.setString(1, username);

               while(rs.next()) {
                	Terreno terreno = new Terreno(rs.getDouble(4), TipoTerreno.valueOf(rs.getString(5)), Fertilità.valueOf(rs.getString(6)));
                	terreno.setID_Terreno(rs.getInt(3));
                	listaTerreni.add(terreno);
                }
                
                return listaTerreni;
            
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella CLASSE TerrenoDAO, funzione: risaliTerreni" + e);
    		return null;
    	} 
	}
	
	
	
	
}
