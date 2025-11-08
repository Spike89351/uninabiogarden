import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class AttrezzoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	public void inserisciAttrezzo(int idDep, String nomeAttrezzo, String tipoAttrezzo, String statoAttrezzo) {
		String sql = "INSERT INTO prguninabiogarden.Attrezzo(Id_deposito, nome_attrezzo, Tipo, Stato_attrezzo) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idDep);
                psmt.setString(2,  tipoAttrezzo);
                psmt.setString(3, tipoAttrezzo);
                psmt.setString(4, statoAttrezzo);
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'attrezzo! (CLASSE AttrezzoDAO), funzione: inserisciAttrezzo" + e);
    	} 
	}
	
	
}
