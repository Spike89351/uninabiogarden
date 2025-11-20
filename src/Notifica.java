import java.sql.Date;

public class Notifica {
	
	private int idNotifica;
	private String descrizione;
	private TipoNotifica tipoNotifica;
	
	
	public Notifica(String descrizione, TipoNotifica tipoNotifica) {
		this.descrizione = descrizione;
		this.tipoNotifica = tipoNotifica;
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
	
}
