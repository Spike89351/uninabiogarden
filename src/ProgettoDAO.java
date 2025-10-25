import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProgettoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	//FUNZIONE CHE SERVE PER LA CREAZIONE DI UN PROGETTO:
	public void inserisciProgetto(int idProprietario, int idTerreno, String nomePrg, java.sql.Date dataInizio, String desc) {
		String sql = "INSERT INTO prguninabiogarden.Progetto (id_proprietario, id_terreno, nome_prg, data_inizio, desc_prg)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProprietario);
			psmt.setInt(2, idTerreno);
			psmt.setString(3, nomePrg);	
			psmt.setDate(4, dataInizio);
			psmt.setString(5, desc);			
			
			psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione trovaTerreno, nella classe TerrenoDAO" + e);
    	}  
	}
	
	//FUNZIONE CHE SERVE PER RISALIRE AI PROGETTI:
	public ArrayList<Progetto> listaDiProgettiPerTerreno(int idTerreno, Terreno terr){
		String sql = "SELECT    "
				+ "FROM prguninabiogarden.Progetto AS P"
				+ "JOIN prguninabiogarden.Terreno AS T ON P.codice_prg = T.codice_prg"
				+ "WHERE id_terreno = ?";
		
		ArrayList<Progetto> listaProvProgetti = new ArrayList<Progetto>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idTerreno);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				Progetto prgProv = new Progetto(rs.getString("Nome_prg"), rs.getDate("data_inizio"), terr);
				prgProv.setDescrizioneProgetto(rs.getString("desc_prg"));
				prgProv.setDataFine(rs.getDate("data_fine"));
				prgProv.setCodeProgetto(rs.getInt("codice_prg"));
				prgProv.setStatoProgetto(StatoProgetto.valueOf(rs.getString("stato_prg")));
				
				listaProvProgetti.add(prgProv);
			} 
			return listaProvProgetti;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione trovaTerreno, nella classe TerrenoDAO" + e);
    		return null;
    	}  
	}
	
}
