import java.sql.Date;
import java.time.LocalDate;

public class Notifica {
	
	private int idNotifica;
	private String descrizione;
	private java.sql.Date dataCreazione;
	private TipoNotifica tipoNotifica;
	
	
	public Notifica(String descrizione, TipoNotifica tipoNotifica) {
		LocalDate now = LocalDate.now();
		this.descrizione = descrizione;
		this.tipoNotifica = tipoNotifica;
		this.dataCreazione = java.sql.Date.valueOf(now);
	}
	
//GETTERS:
	public int getIdNotifica() {
		return idNotifica;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public java.sql.Date getDataCreazione() {
		return dataCreazione;
	}
	public TipoNotifica getTipoNotifica() {
		return tipoNotifica;
	}
	
//SETTERS:
	public void setIdNotifica(int idNotifica) {
		this.idNotifica = idNotifica;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void getTipoNotifica(java.sql.Date nowDat) {
		 this.dataCreazione = nowDat;
	}
	public void setTipoNotifica(TipoNotifica tipoNotifica) {
		this.tipoNotifica = tipoNotifica;
	}
	
}
