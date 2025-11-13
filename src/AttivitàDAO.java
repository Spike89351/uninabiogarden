import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AttivitàDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	//MI SERVE PER INSERIRE NELLA DB I DATI DELL'ATTIVITA'
	public boolean inserisciOmodifica(int idProgetto, int idTerr, String tipoAttività, String statoAttività, java.sql.Date dataInizio, java.sql.Date dataFine) {
		//CONTROLLO SE ESISTE UN'ATTIVITA' IN CORSO (TUPLA):
		if(prendiUltimaAttività(idTerr)) {
			//SE ESSITE UNA TUPLA DELL'ATTIVITA': AGGIORNA LA TUPLA ESISTENTE
			String sql = "INSERT INTO prguninabiogarden.Attività(codice_prg, id_terreno, tipo_attività, Stato_attività, Data_Inizio, data_fine) "
			           + "VALUES (?, ?, ?, ?, ?, ?) "
			           + "ON CONFLICT (codice_prg) "
			           + "DO UPDATE SET "
			           + "tipo_attività = EXCLUDED.tipo_attività, "
			           + "Stato_attività = EXCLUDED.Stato_attività, "
			           + "Data_Inizio = EXCLUDED.Data_Inizio, "
			           + "Data_Fine = EXCLUDED.Data_Fine ";
			
			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
	    			PreparedStatement psmt = conn.prepareStatement(sql)) {
	    			
					psmt.setInt(1, idProgetto);
	                psmt.setInt(2, idTerr);
	                psmt.setString(3, tipoAttività);
	                psmt.setString(4, statoAttività);
	                psmt.setDate(5, dataInizio);
	                psmt.setDate(6, dataFine);
	                
	           int x =  psmt.executeUpdate();
	           
	           return x > 0;
	    	}catch(Exception e) {
	    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'attività! Prima parte della funzione (CLASSE AttivitàDAO), funzione: inserisciOmodifica" + e);
	    		return false;
	    	}
		}else {
			//SE NON ESISTE:
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
	                
	           int x =  psmt.executeUpdate();
	           
	           return x > 0;
	    	}catch(Exception e) {
	    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'attività! Seconda parte della funzione (CLASSE AttivitàDAO), funzione: inserisciOmodifica" + e);
	    		return false;
	    	}
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
	public void popolaTabella(int idTerreno, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Attività AS A "
				+ "JOIN prguninabiogarden.Terreno AS T ON A.id_terreno = T.id_terreno "
				+ "WHERE T.id_terreno = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idTerreno);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					model.addRow(new Object[]{rs.getInt("Id_attività"), rs.getString("Tipo_attività"), rs.getString("Stato_attività"), rs.getDate("Data_inizio"), rs.getDate("Data_fine")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella nella CLASSE AttivitàDAO, funzione: popolaTabella" + e);
    	} 
	}
}
