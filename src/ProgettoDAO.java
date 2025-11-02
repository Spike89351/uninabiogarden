import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    		JOptionPane.showMessageDialog(null, "Errore nella funzione InserisciProgetto, nella classe ProgettoDAO " + e);
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
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaDiPorgettiPerTerreno, nella classe ProgettoDAO " + e);
    		return null;
    	}  
	}
	
	//SERVE PER VISUALIZZARE I PROGETTI PER PROPRIETARIO:
	public void listaProgettiPerProprietario(int idProprietario, String statoProgetto, DefaultTableModel model) {
		if(statoProgetto.equals("Tutti")) {
			listaTuttiProgetti(idProprietario, model);
		}
		if(statoProgetto.equals("Pianificato")) {
			listaProgettiPianificati(idProprietario, model);
		}
		if(statoProgetto.equals("In corso")) {
			listaProgettiInCorso(idProprietario, model);
		}
		if(statoProgetto.equals("Completato")) {
			listaProgettiCompletati(idProprietario, model);
		}
	}
	
//METODI CHE SERVONO PER RICERCARE I PROGETTI IN BASE ALLO STATO DEL PROGETTO: 
	//TUTTI I PROGETTI:
	private void listaTuttiProgetti(int idProprietario, DefaultTableModel model){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Terreno AS T ON PROG.id_terreno = T.id_terreno "
				+ "WHERE T.id_proprietario = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProprietario);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object[]{rs.getInt("codice_prg"), rs.getString("Nome_prg"), rs.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("stato_prg"), rs.getInt("Id_terreno")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaTuttiProgetti, nella classe ProgettoDAO " + e);
    	} 		
	}
	
	//PIANIFCATO: 
	private void listaProgettiPianificati(int idProp, DefaultTableModel model){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Terreno AS T ON PROG.id_terreno = T.id_terreno "
				+ "WHERE T.id_proprietario = ? AND PROG.stato_prg = 'Pianificato' ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[]{rs.getInt("codice_prg"), rs.getString("Nome_prg"), rs.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("stato_prg"), rs.getInt("Id_terreno")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaProgettiPianificati, nella classe ProgettoDAO " + e);
    	} 
	}
	
	//IN CORSO:
	private void listaProgettiInCorso(int idProp, DefaultTableModel model){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Terreno AS T ON PROG.id_terreno = T.id_terreno "
				+ "WHERE T.id_proprietario = ? AND PROG.stato_prg = 'In corso' ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[]{rs.getInt("codice_prg"), rs.getString("Nome_prg"), rs.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("stato_prg"), rs.getInt("Id_terreno")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaProgettiInCorso, nella classe ProgettoDAO " + e);
    	} 		
	}
	
	//COMPLETATO:
	private void listaProgettiCompletati(int idProp, DefaultTableModel model){
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS PROG "
				+ "JOIN prguninabiogarden.Terreno AS T ON PROG.id_terreno = T.id_terreno "
				+ "WHERE T.id_proprietario = ? AND PROG.stato_prg = 'Completato' ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[]{rs.getInt("codice_prg"), rs.getString("Nome_prg"), rs.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("stato_prg"), rs.getInt("Id_terreno")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione listaProgettiCompletati, nella classe ProgettoDAO " + e);
    	} 	
	}
	
	
//MI SERVE PER LA 'PaginaVisualizzaDettagliProgetto':
	public void tuplaDettagliprogetto(int idProgetto, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Progetto AS P "
				+ "JOIN prguninabiogarden.Terreno AS T ON P.id_terreno = T.id_terreno "
				+ "WHERE P.codice_prg = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProgetto);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[]{rs.getInt("codice_prg"), rs.getString("Nome_prg"), rs.getDate("data_inizio"), rs.getDate("data_fine"), rs.getString("stato_prg"), rs.getInt("Id_terreno"), rs.getDouble("superfice"), rs.getString("tipo_terreno"), rs.getString("fertilità")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione tuplaDettagliprogetto, nella classe ProgettoDAO " + e);
    	} 
	}
	
	//AGGIUNTA DELLA DATA DI FINE:
	public void inserisciDataFine(java.sql.Date dataFine, int codicePrg) {
		String sql = "UPDATE prguninabiogarden.Progetto "
				+ "SET data_fine = ? "
				+ "WHERE codice_prg = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setDate(1, dataFine);
			psmt.setInt(2, codicePrg);
			
			psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione inserisciDataFine, nella classe ProgettoDAO " + e);
    	} 
		
	}
	
//MODIFICA DELLO STATO DEL PROGETTO:
	//RICORDA CHE NEL DB VA' SEGNATO in corso, INVECE NELLA CLASSE ENUM E' in_corso;
	
	
	
}
