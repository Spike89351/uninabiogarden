
public class StoricoColtivatore {	
	
	private int idStorico;
	private int idColtivatore;
	private int idAttvità;
	private String stato;
	
	
	public StoricoColtivatore(int idStorico, int idColtivatore, int idAttvità, String stato) {
		super();
		this.idStorico = idStorico;
		this.idColtivatore = idColtivatore;
		this.idAttvità = idAttvità;
		this.stato = stato;
	}
	
//GETTERS:
	public int getIdStorico() {
		return idStorico;
	}
	public int getIdColtivatore() {
		return idColtivatore;
	}
	public int getIdAttvità() {
		return idAttvità;
	}
	public String getStato() {
		return stato;
	}

//SETTERS:
	public void setIdStorico(int idStorico) {
		this.idStorico = idStorico;
	}
	public void setIdColtivatore(int idColtivatore) {
		this.idColtivatore = idColtivatore;
	}
	public void setIdAttvità(int idAttvità) {
		this.idAttvità = idAttvità;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
}
