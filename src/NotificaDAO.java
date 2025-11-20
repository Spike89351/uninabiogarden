import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

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
}
