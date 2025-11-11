import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class AttivitàDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	//MI SERVE PER INSERIRE NELLA DB I DATI DELL'ATTIVITA'
	public boolean inserisci(int idTerr, String tipoAttività, String statoAttività, java.sql.Date dataInizio) {
		String sql = "INSERT INTO prguninabiogarden.Attività(id_terreno, tipo_attività, Stato_attività, data_inizio) "
				+ "VALUES(?, ?, ?, ?) ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
    		
                psmt.setInt(1, idTerr);
                psmt.setString(2, tipoAttività);
                psmt.setString(3, statoAttività);
                psmt.setDate(4, dataInizio);
                
           int x =  psmt.executeUpdate();
           
           return x > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'attività! (CLASSE AttivitàDAO), funzione: inserisci" + e);
    		return false;
    	}    
	}
}
