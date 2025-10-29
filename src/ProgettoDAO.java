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
	public boolean inserisciProgetto(int idProprietario, int idTerreno, String nomePrg, java.sql.Date dataInizio, String desc) {
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
			return true;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione InserisciProgetto, nella classe ProgettoDAO" + e);
    		return false;
    	}  
	}
	
	//FUNZIONE CHE SERVE PER RISALIRE AI PROGETTI:
	public ArrayList<Progetto> listaDiProgettiPerTerreno(int idTerreno, Terreno terr){
		String sql = "SELECT * "
		           + "FROM prguninabiogarden.Progetto AS P "
		           + "JOIN prguninabiogarden.Terreno AS T ON P.codice_prg = T.codice_prg "
		           + "WHERE T.id_terreno = ?";
		
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
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaDiPorgettiPerTerreno, nella classe TerrenoDAO" + e);
    		return null;
    	}  
	}
	
	//SERVE PER VISUALIZZARE I PROGETTI PER PROPRIETARIO:
	public ArrayList<Progetto> listaProgettiPerProprietario(int idProprietario, String statoProgetto) {
		if(statoProgetto.isBlank()) {
			return listaTuttiProgetti(idProprietario);
		}
		if(statoProgetto.equalsIgnoreCase("Pianificato")) {
			return listaProgettiPianificati(idProprietario);
		}
		if(statoProgetto.equalsIgnoreCase("in_corso")) {
			return listaProgettiInCorso(idProprietario);
		}
		if(statoProgetto.equalsIgnoreCase("Completato")) {
			return listaProgettiCompletati(idProprietario);
		}
		return null;
	}
	
//METODI CHE SERVONO PER RICERCARE I PROGETTI IN BASE ALLO STATO DEL PROGETTO:
	//TUTTI I PROGETTI:
	private ArrayList<Progetto> listaTuttiProgetti(int idProprietario){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Proprietario AS P "
				+ "WHERE PROG.id_proprietario = ? ";
		
		ArrayList<Progetto> listaProvProgetti = new ArrayList<Progetto>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProprietario);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Progetto prgProv = new Progetto(rs.getString("Nome_prg"), rs.getDate("data_inizio"), null);
				prgProv.setDescrizioneProgetto(rs.getString("desc_prg"));
				prgProv.setDataFine(rs.getDate("data_fine"));
				prgProv.setCodeProgetto(rs.getInt("codice_prg"));
				if(rs.getString("stato_prg").equalsIgnoreCase("in corso")) {
					prgProv.setStatoProgetto(StatoProgetto.In_Corso);
				}else {
					prgProv.setStatoProgetto(StatoProgetto.valueOf(rs.getString("stato_prg")));
				}
				
				
				listaProvProgetti.add(prgProv);
			} 
			return listaProvProgetti;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaTuttiProgetti, nella classe TerrenoDAO" + e);
    		return null;
    	} 		
	}
	
	//PIANIFCATO:
	private ArrayList<Progetto> listaProgettiPianificati(int idProp){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Proprietario AS P "
				+ "WHERE PROG.id_proprietario = ? AND PROG.stato_prg = 'Pianificato' ";
		
		ArrayList<Progetto> listaProvProgetti = new ArrayList<Progetto>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Progetto prgProv = new Progetto(rs.getString("Nome_prg"), rs.getDate("data_inizio"), null);
				prgProv.setDescrizioneProgetto(rs.getString("desc_prg"));
				prgProv.setDataFine(rs.getDate("data_fine"));
				prgProv.setCodeProgetto(rs.getInt("codice_prg"));
				prgProv.setStatoProgetto(StatoProgetto.valueOf(rs.getString("stato_prg")));
				
				listaProvProgetti.add(prgProv);
			} 
			return listaProvProgetti;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaProgettiPianificati, nella classe TerrenoDAO" + e);
    		return null;
    	} 
	}
	
	//IN CORSO:
	private ArrayList<Progetto> listaProgettiInCorso(int idProp){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Proprietario AS P "
				+ "WHERE PROG.id_proprietario = ? AND PROG.stato_prg = 'In corso' ";
		
		ArrayList<Progetto> listaProvProgetti = new ArrayList<Progetto>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Progetto prgProv = new Progetto(rs.getString("Nome_prg"), rs.getDate("data_inizio"), null);
				prgProv.setDescrizioneProgetto(rs.getString("desc_prg"));
				prgProv.setDataFine(rs.getDate("data_fine"));
				prgProv.setCodeProgetto(rs.getInt("codice_prg"));
				prgProv.setStatoProgetto(StatoProgetto.In_Corso);
				
				listaProvProgetti.add(prgProv);
			} 
			return listaProvProgetti;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaProgettiInCorso, nella classe TerrenoDAO" + e);
    		return null;
    	} 		
	}
	
	//COMPLETATI:
	private ArrayList<Progetto> listaProgettiCompletati(int idProp){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Proprietario AS P "
				+ "WHERE PROG.id_proprietario = ? AND PROG.stato_prg = 'Completato' ";
		
		ArrayList<Progetto> listaProvProgetti = new ArrayList<Progetto>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Progetto prgProv = new Progetto(rs.getString("Nome_prg"), rs.getDate("data_inizio"), null);
				prgProv.setDescrizioneProgetto(rs.getString("desc_prg"));
				prgProv.setDataFine(rs.getDate("data_fine"));
				prgProv.setCodeProgetto(rs.getInt("codice_prg"));
				prgProv.setStatoProgetto(StatoProgetto.valueOf(rs.getString("stato_prg")));
				
				listaProvProgetti.add(prgProv);
			} 
			return listaProvProgetti;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaProgettiCompletati, nella classe TerrenoDAO" + e);
    		return null;
    	} 	
	}
	
//MODIFICA DELLO STATO DEL PROGETTO:
	//RICORDA CHE NEL DB VA' SEGNATO in corso, INVECE NELLA CLASSE ENUM E' in_corso;
	
	
	
}
