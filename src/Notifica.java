import java.sql.Date;

public class Notifica {
	
	private int idNotifica;
	private String descrizione;
	private TipoNotifica tipoNotifica;
	private java.sql.Date dataCreazione;
	
	
	public Notifica(String descrizione, TipoNotifica tipoNotifica, Date dataCreazione) {
		this.descrizione = descrizione;
		this.tipoNotifica = tipoNotifica;
		this.dataCreazione = dataCreazione;
	}
	
//GETTERS:
	public int getIdNotifica() {
		return idNotifica;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public TipoNotifica getTipoNotifica() {
		return tipoNotifica;
	}
	public java.sql.Date getDataCreazione() {
		return dataCreazione;
	}
	
//SETTERS:
	public void setIdNotifica(int idNotifica) {
		this.idNotifica = idNotifica;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setTipoNotifica(TipoNotifica tipoNotifica) {
		this.tipoNotifica = tipoNotifica;
	}
	public void setDataCreazione(java.sql.Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	
	
	
}
