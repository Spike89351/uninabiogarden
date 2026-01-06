import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TerrenoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	//SERVE PER INSERIRE UN TERRENO:
	public void inserisciTerreno(int codeProprietario, double superficie, TipoTerreno TipologiaTerreno, Fertilità tipoFertilità, int idDep, String indirizzo) {
		String sql = "INSERT INTO prguninabiogarden.Terreno (Id_proprietario, id_deposito, Superfice, Tipo_terreno, Fertilità, indirizzo) VALUES(?, ?, ?, ?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		//FACCIO IL CAST:
    		String tipoTerreno = String.valueOf(TipologiaTerreno);
    		String Fertilità = String.valueOf(tipoFertilità);
    		
                psmt.setInt(1, codeProprietario);
                psmt.setInt(2, idDep);
                psmt.setDouble(3, superficie);
                psmt.setString(4, tipoTerreno);
                psmt.setString(5, Fertilità);
                psmt.setString(6, indirizzo);
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Terreno! (CLASSE TerrenoDAO), funzione: inserisciTerreno" + e);
    	}    	
	}
	
	//MI SERVE PER TROVARE IL TERRENO:
	public Terreno trovaTerreno(String idTerreno) {
		String sql = "SELECT superfice, tipo_terreno, fertilità, indirizzo FROM prguninabiogarden.Terreno WHERE id_terreno = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
			
			psmt.setInt(1, Integer.valueOf(idTerreno.trim()));
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				Deposito dep = null;
				Terreno terProv = new Terreno(rs.getDouble("superfice"), TipoTerreno.valueOf(rs.getString("tipo_terreno")), Fertilità.valueOf(rs.getString("fertilità")), dep, rs.getString("indirizzo"));
				return terProv;
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione trovaTerreno, nella classe TerrenoDAO" + e);
    		return null;
    	}  
		return null;
	}
	
//SERVE PER RISALIRE ALLA LISTA DEI TERRENI DI UN PROPRIETARIO: 
	public void risaliTerreni(String username, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Terreno AS T "
				+ "JOIN prguninabiogarden.Proprietario AS P ON T.id_proprietario = P.id_proprietario "
				+ "WHERE P.Username = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setString(1, username);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					model.addRow(new Object[]{rs.getInt("id_terreno"), rs.getString("indirizzo"), rs.getDouble("superfice"), rs.getString("tipo_terreno"), rs.getString("fertilità")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella CLASSE TerrenoDAO, funzione: risaliTerreni" + e);
    	} 
	}
	
	//SERVE A RISALIRE AL PROPRIETARIO DEL TERRENO TRAMITE L'ID DEL TERRENO:
	public int trovaProprietarioTramiteTerreno(int idTerreno) {
		String sql = "SELECT T.id_proprietario "
				+ "FROM prguninabiogarden.Terreno AS T "
				+ "JOIN prguninabiogarden.Proprietario AS P ON T.id_proprietario = P.id_proprietario "
				+ "WHERE T.id_terreno = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idTerreno);
				
				ResultSet rs = psmt.executeQuery();
    		
               if(rs.next()) {
                	int val = rs.getInt("id_proprietario");
                	return val;
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella CLASSE TerrenoDAO, funzione: risaliTerreni" + e);
    		return 0;
    	} 
		return 0;
	}
	
	//MI SERVE PER POPOLARE LA TABELLA CON I TERRENI LIBERI PER POI SCEGLIERNE UNO SU CUI FARE UN NUOVO PROGETTO:
	public void terreniDisponibiliPerUnNuovoProgetto(String username, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Proprietario AS P "
				+ "JOIN prguninabiogarden.Terreno AS T ON P.id_proprietario = T.id_proprietario "
				+ "WHERE P.username = ? AND T.codice_prg IS null ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setString(1, username);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					model.addRow(new Object[]{rs.getInt("id_terreno"), rs.getString("indirizzo"), rs.getDouble("superfice"), rs.getString("tipo_terreno"), rs.getString("fertilità")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella CLASSE TerrenoDAO, funzione: terreniDisponibiliPerUnNuovoProgetto" + e);
    	} 
	}
}