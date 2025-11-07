import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DepositoDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Informatica1";
	
	
	//SERVE PER LA CREAZIONE DI UN DEPOSITO:
	public void creaDeposito(int idProprietario, String indirizzo, Double dimensione) {
		String sql = "INSERT INTO prguninabiogarden.Deposito(id_proprietario, indirizzo_deposito, dim_deposito)"
				+ "VALUES(?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
            
                psmt.setInt(1, idProprietario);
                psmt.setString(2,  indirizzo.toLowerCase());
                psmt.setDouble(3, dimensione);
                
            psmt.executeUpdate();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nell'inserimento del Deposito! (CLASSE DepositoDAO), funzione: inserisciDeposito" + e);
    	}    	    	
    }
	
	//SERVE A POPOLARE LA TABELLA CON I DEPOSITI:
	public void popolaTabellaDepositi(int idProp, DefaultTableModel model) {
		String sql = "SELECT id_deposito, indirizzo_deposito, dim_Deposito "
				+ "FROM prguninabiogarden.Deposito "
				+ "WHERE id_Proprietario = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object[]{rs.getString("id_deposito"), rs.getString("indirizzo_deposito"), rs.getString("dim_Deposito")});
			} 
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione popolataTabellaDepsoiti, nella classe DepositoDAO " + e);
    	} 
	}
	
	//TROVA L'ID DEL DEPOSITO:
	public int trovaIdDeposito(int idProp) {
		String sql = "SELECT id_deposito "
				+ "FROM prguninabiogarden.Deposito "
				+ "WHERE id_proprietario = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);

			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id_deposito");
			}
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione trovaIdDeposito, nella classe DepositoDAO " + e);
    		return 0;
    	} 
		return 0;
	}
	
	//CONTROLLO SE L'ID DEL DEPOSITO APPARTIENE AL PROPRIETARIO:
	public boolean ctrlAppDeposito(int idProp, int idDeposito) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Proprietario AS P "
				+ "JOIN prguninabiogarden.Deposito AS D  ON P.id_proprietario = D.id_proprietario "
				+ "WHERE P.id_proprietario = ? AND D.id_deposito = ? ";
			
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idProp);
			psmt.setInt(2, idDeposito);

			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("id_deposito") > 0) {
					return true;
				}else {
					JOptionPane.showMessageDialog(null, "Non esiste alcun deposito con l'id inserito: "+idDeposito);
					return false;
				}
			}
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione ctrlAppDeposito, nella classe DepositoDAO " + e);
    		return false;
    	} 
		return false;
	}
	
	//POPOLA TABELLA CON I DATI DI UN SINGOLO DEPOSITO:
	public void popolaTabellaConUnDeposito(int idDep, DefaultTableModel model) {
		String sql = "SELECT * "
				+ "FROM prguninabiogarden.Deposito "
				+ "WHERE id_deposito = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idDep);

			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				model.addRow(new Object[]{rs.getString("id_deposito"), rs.getString("indirizzo_deposito"), rs.getString("dim_Deposito"), rs.getDouble("quantità_raccolto")});
			}
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione popolaTabellaConUnDeposito, nella classe DepositoDAO " + e);
    	} 
	}
	
//MODIFICA DATI DEL DEPOSITO:
	//MODIFICA DATI DEL RACCOLTO:
	public void modificaDatiRaccolto(int idDep, double newRaccolto) {
		String sql = "UPDATE prguninabiogarden.Deposito "
				+ "SET quantità_raccolto = ? "
				+ "WHERE id_deposito = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(1, idDep);
			psmt.setDouble(2, newRaccolto);
			
			psmt.executeQuery();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione modificaDatiRaccolto, nella classe DepositoDAO " + e);
    	} 
	}
	
	//MODIFICA DELL'INDIRIZZO:
	public void modificaIndirizzo(int idDep, String newIndirizzo) {
		String sql = "UPDATE prguninabiogarden.Deposito "
				+ "SET indirizzo_deposito = ? "
				+ "WHERE id_deposito = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(2, idDep);
			psmt.setString(1, newIndirizzo);
			
			psmt.executeQuery();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione modificaIndirizzo, nella classe DepositoDAO " + e);
    	} 
	}
	
	//MODIFICA DELLA DIMENSIONE DEL DEPOSITO:
	public void modificaDimensione(int idDep, double newDimensione) {
		String sql = "UPDATE prguninabiogarden.Deposito "
				+ "SET dim_deposito = ? "
				+ "WHERE id_deposito = ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
    			PreparedStatement psmt = conn.prepareStatement(sql)) {
		
			psmt.setInt(2, idDep);
			psmt.setDouble(1, newDimensione);
			
			psmt.executeQuery();
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Errore nella funzione modificaDimensione, nella classe DepositoDAO " + e);
    	}
	}
	
}
