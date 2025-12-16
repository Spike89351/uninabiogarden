import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AttrezzoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	//MI SERVE PER INSERIRE L'ATTREZZO NEL DB:
	public boolean inserisciAttrezzo(int idDep, String nomeAttrezzo, String tipoAttrezzo, String statoAttrezzo) {
		String sql = "INSERT INTO prguninabiogarden.Attrezzo(Id_deposito, nome_attrezzo, Tipo, Stato_attrezzo) "
				+ "VALUES(?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idDep);
                psmt.setString(2,  nomeAttrezzo);
                psmt.setString(3, tipoAttrezzo);
                psmt.setString(4, statoAttrezzo);
                
            psmt.executeUpdate();
            return true;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento dell'attrezzo! (CLASSE AttrezzoDAO), funzione: inserisciAttrezzo" + e);
    		return false;
    	} 
	}
	
	//MI SERVE PER POPOLARE LA TABELLA CON TUTTI GLI ATTREZZI DI QUEL DEPOSITO:
	public void popolaTabellaAttrezzoPerDeposito(int idDep, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Deposito AS D "
				+ "JOIN prguninabiogarden.Attrezzo AS A ON D.id_deposito = A.id_deposito "
				+ "WHERE D.id_deposito = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idDep);
                
                ResultSet rs = psmt.executeQuery();
                
                while(rs.next()) {
                	model.addRow(new Object[]{rs.getString("id_attrezzo"), rs.getString("nome_attrezzo"), rs.getString("tipo"), rs.getString("stato_attrezzo")});
                }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella con gli attrezzi del deposito! (CLASSE AttrezzoDAO), funzione: popolaTabellaAttrezzoPerDeposito" + e);
    	} 
	}
	
	//ELIMINA ATTREZZO:
	public boolean elimina(int idAttrezzo) {
		String sql = "DELETE FROM prguninabiogarden.Attrezzo WHERE id_attrezzo = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idAttrezzo);
                
                int riuscito = psmt.executeUpdate();
                
                return riuscito > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'eliminazione dell'attrezzo! (CLASSE AttrezzoDAO), funzione: elimina" + e);
    		return false;
    	} 
	}
	
	//AGGIUNGI STATO MANUTENZIONE ATTREZZO E CAMBIA DISPONIBILITA' NEL CASO:
	public boolean manutenzione(int idAttrezzo, String statoMan, boolean disp) {
		String sql = "UPDATE prguninabiogarden.Attrezzo "
				+ "SET "
				+ "stato_manutenzione = ?, "
				+ "disponibilità = ? "
				+ "WHERE id_attrezzo = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setString(1, statoMan);
                psmt.setBoolean(2, disp);
                psmt.setInt(3, idAttrezzo);
                                
                int riuscito = psmt.executeUpdate();
                return riuscito > 0;
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'eliminazione dell'attrezzo! (CLASSE AttrezzoDAO), funzione: elimina" + e);
    		return false;
    	} 		
	}
	
	//POPOLA LA TABELLA CON GLI ATTREZZI TRAMITE IL SUO STATO DI MANUTENZIONE E E ID DEPOSITO:
	public void popolaTabellaConIdDepositoEStatoManutenzione(int idDep, String statoManutenzione, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Deposito AS D "
				+ "JOIN prguninabiogarden.Attrezzo AS A ON D.id_deposito = A.id_deposito "
				+ "WHERE D.id_deposito = ? AND A.stato_manutenzione = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idDep);
                psmt.setString(2, statoManutenzione);
                
                ResultSet rs = psmt.executeQuery();
                		
	    		while(rs.next()) {
	    			model.addRow(new Object[]{rs.getString("id_attrezzo"), rs.getString("nome_attrezzo"), rs.getString("stato_manutenzione")});
	            }
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella con gli attrezzi tramite stato! (CLASSE AttrezzoDAO), funzione: popolaTabellaConIdDepositoEStatoManutenzione" + e);
    	} 
	}
	
	
}
