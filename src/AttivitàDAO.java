import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AttivitàDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	//MI SERVE AD INSERIRE I DATI NELLA DB:
	public boolean inserisci(int idProgetto, int idTerr, String tipoAttività, String statoAttività, java.sql.Date dataInizio, java.sql.Date dataFine) {
		String sql = "INSERT INTO prguninabiogarden.Attività(codice_prg, id_terreno, tipo_attività, Stato_attività, Data_Inizio, Data_Fine) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
				psmt.setInt(1, idProgetto);
                psmt.setInt(2, idTerr);
                psmt.setString(3, tipoAttività);
                psmt.setString(4, statoAttività);
                psmt.setDate(5, dataInizio);
                psmt.setDate(6, dataFine);
                
           int x = psmt.executeUpdate();
           
           return x > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'attività! funzione (CLASSE AttivitàDAO), funzione: inserisci" + e);
    		return false;
    	}
	}
	
	//MI SERVE A PRENDERE L'ATTIVITA' ULTIMA IN CORSO:
	private boolean prendiUltimaAttività(int idTerr) {
		String sql = "SELECT 1 "
				+ "FROM prguninabiogarden.Attività AS A "
				+ "WHERE id_terreno = ? "
				+ "AND tipo_attività <> 'Raccolta' "
				+ "AND Stato_attività <> 'Completata' "
				+ "AND (Data_Fine IS NULL OR Data_Fine > CURRENT_DATE) ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
                psmt.setInt(1, idTerr);
                
          ResultSet rs  =  psmt.executeQuery();
           
          return rs.next(); //RESTITUISCE TRUE SE C'E' UN'ATTIVITA' IN CORSO;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'attività! (CLASSE AttivitàDAO), funzione: inserisci" + e);
    		return false;
    	} 
	}

	//POPOLA TABELLA CON TUTTE LE ATTIVITA' (RICORDA CHE NON TI FARA VEDERE TUTTE LE FASI DI UN'ATTIVITA'!):
	public ArrayList<Attività> popolaTabella(int idTerreno, int idProg) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Terreno AS T "
				+ "JOIN prguninabiogarden.Progetto AS PRO ON T.id_terreno = PRO.id_terreno "
				+ "JOIN prguninabiogarden.Attività AS A ON A.codice_prg = PRO.codice_prg "
				+ "WHERE T.id_terreno = ? AND PRO.codice_prg = ? ";
		
		ArrayList<Attività> elenco = new ArrayList<Attività>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idTerreno);
				psmt.setInt(2, idProg);
				
				ResultSet rs = psmt.executeQuery();
				
				String tempStato;
				
				while(rs.next()) {
					if(rs.getString("stato_attività").equalsIgnoreCase("in corso")) {
						tempStato = String.valueOf(Stato.in_Corso);
					}else {
						tempStato = rs.getString("stato_attività");
					}
					Attività at = new Attività(CondizioneRaccolto.valueOf(rs.getString("tipo_attività")), Stato.valueOf(tempStato), rs.getDate("Data_inizio"), rs.getDate("Data_fine"), rs.getString("indirizzo"));
					at.setIdAttività(rs.getInt("Id_attività"));
					elenco.add(at);
                }
				return elenco;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella nella CLASSE AttivitàDAO, funzione: popolaTabella" + e);
    		return null;
    	} 
	}
	
	//MI SERVE AD INSERIRE UNA DATA ODIERNA NELL'ATTIVITA' QUANDO SI E' COMPLETATA:
	public boolean inserisciStatoAttivitàCompleta(int idAttività, String stato) {
		String sql = "UPDATE prguninabiogarden.Attività "
				+ "SET stato_attività = ? "
				+ "WHERE id_attività = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setString(1, stato);
				psmt.setInt(2, idAttività);
				
				int result = psmt.executeUpdate();
				
				return result > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dello stato completato nell'attività scelta, CLASSE AttivitàDAO, funzione: inserisciDataOdiernaAlCompletamento" + e);
    		return false;
    	} 
	}
	
	//MI SERVE PER MODIFICARE LO STATO E AGGIUNGERE UNA QUANTITA' DEL RACCOLTO:
	public boolean modificaStatoEAggiungiQuantità(String stato, double raccoltoQuant, int idAtt) {
		String sql = "UPDATE prguninabiogarden.Attività "
				+ "SET stato_attività = ?, "
				+ "quantità_raccolto = ?, "
				+ "tipo_attività = ? "
				+ "WHERE id_attività = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setString(1, stato);
				psmt.setDouble(2, raccoltoQuant);
				psmt.setString(3, "Raccolta");
				psmt.setInt(4, idAtt);
				
				int ret = psmt.executeUpdate();
				
				return ret > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella modifica dello stato + aggiunta di raccolta la tabella nella CLASSE AttivitàDAO, funzione: modificaStatoEAggiungiQuantità" + e);
    		return false;
    	} 
	}
	
	//MI SERVE PER POPOLARE LA TABELLA CON LE ATTIVITA' COMPLETATE SUL TERRENO:
	public void popolaTabellaConQuantitàRaccolto(int idTerreno, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Attività AS A "
				+ "JOIN prguninabiogarden. Terreno AS T ON A.id_terreno = T.id_terreno "
				+ "WHERE T.id_terreno = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idTerreno);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					model.addRow(new Object[]{rs.getInt("Id_attività"), rs.getDouble("quantità_raccolto"), rs.getDate("Data_inizio"), rs.getDate("Data_fine")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella nella CLASSE AttivitàDAO, funzione: popolaTabellaConQuantitàRaccolto" + e);
    	} 
	}
	
	//MI SERVE A INSERIRE I COLTIVATORI NELLA TABELLA PER VISUALIZZARLI:
	public ArrayList<Coltivatore> coltivatoreAttività(int idAttività, String statoAtt) {
		if(statoAtt.equals("Completata")) {
			return popolaTabellaConColtivatoriAttivitàCompleta(idAttività);
		}else {
			return popolaTabellaConColtivatoriAttivitàNonCompleta(idAttività);
		}
	}
	
	//MI SERVE PER LA FUNZIONE SOPRA, E SERVE PER INSERIRE NELLA TABELLA I COLTIVATORI CHE STANNO LAVORANDO ALL'ATTIVITA' CHE SI STA SVOLGENDO ORA:
	private ArrayList<Coltivatore> popolaTabellaConColtivatoriAttivitàNonCompleta(int idAttività) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Attività AS A "
				+ "JOIN prguninabiogarden.Coltivatore AS C ON A.id_attività = C.id_attività "
				+ "JOIN prguninabiogarden.Utente AS U ON C.username = U.username "
				+ "WHERE A.id_attività = ? ";
		
		ArrayList<Coltivatore> elenco = new ArrayList<Coltivatore>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idAttività);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					Coltivatore c = new Coltivatore(rs.getString("nome"), rs.getString("cognome"), rs.getDate("data_nascita"), Genere.valueOf(rs.getString("genere")), rs.getString("username"), rs.getString("passwd"), rs.getString("indirizzo"));
					c.setCodiceId(rs.getInt("id_coltivatore"));
					elenco.add(c);
                }
				return elenco;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con i coltivatori che lavorano su quel campo, nella CLASSE AttivitàDAO, funzione: popolaTabellaConQuantitàRaccolto" + e);
    		return null;
    	}
	}
	
	//MI SERVE NELLA FUNZIONE SOPRA, E SERVE PER INSERIRE NELLA TABELLA TUTTI I COLTIVATORI CHE HANNO LAVORATO A QUELL'ATTIVITA' ORAMAI COMPLETATA:
	private ArrayList<Coltivatore> popolaTabellaConColtivatoriAttivitàCompleta(int idAttività) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Attività AS A "
				+ "JOIN prguninabiogarden.logStoricoColtivatore AS STC ON A.id_attività = STC.id_attività "
				+ "JOIN prguninabiogarden.Coltivatore AS C ON STC.id_coltivatore = C.id_coltivatore "
				+ "JOIN prguninabiogarden.Utente AS U ON C.username = U.username "
				+ "WHERE STC.id_attività = ? ";
		
		ArrayList<Coltivatore> elenco = new ArrayList<Coltivatore>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idAttività);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					Coltivatore c = new Coltivatore(rs.getString("nome"), rs.getString("cognome"), rs.getDate("data_nascita"), Genere.valueOf(rs.getString("genere")), rs.getString("username"), rs.getString("passwd"), rs.getString("indirizzo"));
					c.setCodiceId(rs.getInt("id_coltivatore"));
					elenco.add(c);
                }
				return elenco;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con i coltivatori che lavorano su quel campo, nella CLASSE AttivitàDAO, funzione: popolaTabellaConQuantitàRaccolto" + e);
    		return null;
    	}
	}
	
	//PRENDI DATI ATTIVITA':
	public ArrayList<Object> prendiDatiAttività(int idAttività) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Attività "
				+ "WHERE id_attività = ? ";
		
		ArrayList<Object> elencoDati = new ArrayList<Object>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idAttività);
				
				ResultSet rs = psmt.executeQuery();
				
				if(rs.next()) {
					elencoDati.add(rs.getDate("data_inizio"));
					elencoDati.add(rs.getString("tipo_attività"));
					elencoDati.add(rs.getString("stato_attività"));
                }
				return elencoDati;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione, nella CLASSE AttivitàDAO, funzione: prendiDatiAttività" + e);
    		return elencoDati;
    	}
	}
	
	//MI SERVE PER CAMBIARE LO STATO DI UN'ATTIVITA'
	public boolean cambiaStato(int idAtt, String newState) {
		String sql = "UPDATE prguninabiogarden.Attività "
				+ "SET stato_attività = ? "
				+ "WHERE id_attività = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setString(1, newState);
				psmt.setInt(2, idAtt);
				
				int result = psmt.executeUpdate();
				
				return result > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione, nella CLASSE AttivitàDAO, funzione: cambiaStato" + e);
    		return false;
    	}
	}
}
