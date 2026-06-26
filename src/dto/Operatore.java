package dto;

public class Operatore {
	private String nome;
	private String cognome;
	private String username;
	private String password;
	
	
	public Operatore(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}
	
//GETTERS:
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
//SETTERS:
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}