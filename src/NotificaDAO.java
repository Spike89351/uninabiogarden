import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NotificaDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	//SERVE AD INVIARE UNA NOTIFICA AL COLTIVATORE:
	public boolean inviaDiPresaInCarico(int idColtivatore) {
		String sql = "INSERT INTO prguninabiogarden.Notifica(id_coltivatore, Descrizione, tipo_notifca) "
				+ "VALUES(?, ?, ?) ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
    		
				psmt.setInt(1, idColtivatore);
                psmt.setString(2, "Presa in carico");
                psmt.setString(3, "Attività imminente");
                
           int x =  psmt.executeUpdate();
           
           return x > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella creazione della notifica! funzione (CLASSE NotificaDAO), funzione: invia" + e);
    		return false;
    	}
	}
	
	//POSSIBILIA' DI INVIARE UN'ALTRA NOTIFICA AL COLTIVAOTORE:
	public boolean inviaNotifica() {
		
		return false;
	}
	
	//MI SERVE PER VISUALIZZARE LE NOTIFICHE:
	public void visualizzaNotifiche(int idColt, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Notifica AS N "
				+ "JOIN prguninabiogarden.Coltivatore AS C ON N.id_coltivatore = C.id_coltivatore "
				+ "WHERE C.id_coltivatore = ? AND visualizzata = 'FALSE' "
				+ "ORDER BY N.data_creazione DESC "; //POTRESTI ANCHE FARLA IN ORDINE TRAMITE ID_ATTIVITA'
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idColt);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					model.addRow(new Object[]{rs.getString("Descrizione"), rs.getString("tipo_notifica"), rs.getDate("data_creazione")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con le notifiche del coltivatore, nella CLASSE NotificaDAO, funzione: visualizzaNotifiche" + e);
    	}
	}
}
