import java.util.ArrayList;
import java.util.Date;

public class Attività {
	private ArrayList<Coltivatore> elencoColt = new ArrayList<Coltivatore>();
    private CondizioneRaccolto condizioneRaccolto;
    private Stato statoEsecuzione;
    private Date dataRaccolto;
    private Terreno terreno;
    private int quantitàRaccolto;
    
    Attività(Terreno ter){
    	
    }

//GETTERS:
	public ArrayList<Coltivatore> getElencoColt() {
		return elencoColt;
	}
	public CondizioneRaccolto getCondizioneRaccolto() {
		return condizioneRaccolto;
	}
	public Stato getStatoEsecuzione() {
		return statoEsecuzione;
	}
	public Date getDataRaccolto() {
		return dataRaccolto;
	}
	public Terreno getTerreno() {
		return terreno;
	}
	public int getQuantitàRaccolto() {
		return quantitàRaccolto;
	}
	
	
	
	
	
//SETTERS:    
	public void setElencoColt(ArrayList<Coltivatore> elencoColt) {
		this.elencoColt = elencoColt;
	}
	public void setCondizioneRaccolto(CondizioneRaccolto condizioneRaccolto) {
		this.condizioneRaccolto = condizioneRaccolto;
	}
	public void setStatoEsecuzione(Stato statoEsecuzione) {
		this.statoEsecuzione = statoEsecuzione;
	}
	public void setDataRaccolto(Date dataRaccolto) {
		this.dataRaccolto = dataRaccolto;
	}
	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}
	public void setQuantitàRaccolto(int quantitàRaccolto) {
		this.quantitàRaccolto = quantitàRaccolto;
	}
    
    
}
