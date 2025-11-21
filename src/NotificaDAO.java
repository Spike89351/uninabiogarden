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
	
	public void visualizzaNotifiche(int idColt, DefaultTableModel model, String tipoNot) {
		if(tipoNot.isBlank()) {
			visualizzaNotificheNonLette(idColt, model);
		}else {
			visualizzaNotificheLette(idColt, model);
		}
	}
	
	//MI SERVE PER VISUALIZZARE LE NOTIFICHE NON VISUALIZZATE:
	public void visualizzaNotificheNonLette(int idColt, DefaultTableModel model) {
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
					model.addRow(new Object[]{rs.getInt("id_notifica"), rs.getString("tipo_notifica"), rs.getString("Descrizione"), rs.getDate("data_creazione")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con le notifiche del coltivatore, nella CLASSE NotificaDAO, funzione: visualizzaNotifiche" + e);
    	}
	}
	
	//MI SERVE PER VISUALIZZARE LE NOTIFICHE GIA' VISTE:
	public void visualizzaNotificheLette(int idColt, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Notifica AS N "
				+ "JOIN prguninabiogarden.Coltivatore AS C ON N.id_coltivatore = C.id_coltivatore "
				+ "WHERE C.id_coltivatore = ? AND visualizzata = 'TRUE' "
				+ "ORDER BY N.data_creazione DESC ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(1, idColt);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					model.addRow(new Object[]{rs.getInt("id_notifica"), rs.getString("tipo_notifica"), rs.getString("Descrizione"), rs.getDate("data_creazione")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolare la tabella con le notifiche già lette del coltivatore, nella CLASSE NotificaDAO, funzione: visualizzaNotificheLette" + e);
    	}
	}
	
	//MI SERVE A VISUALIZZARE LA NOTIFICA, CAMBIO FALSE->TRUE:
	public boolean cambiaVisualNotifica(int idNotifica) {
		String sql = "UPDATE prguninabiogarden.Notifica "
				+ "SET visualizzata = 'TRUE' "
				+ "WHERE id_notifica = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
				
				psmt.setInt(2, idNotifica);
				
				int result = psmt.executeUpdate();
				
				return result > 0;				
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel cambaire lo stato della notifica da (FALSE -> TRUE) nella CLASSE NotificaDAO, funzione: cambiaVisualNotifica" + e);
    		return false;
    	}
	}
}
