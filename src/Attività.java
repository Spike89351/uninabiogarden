import java.util.ArrayList;
import java.util.Date;

public class Attività {
	private int idAttività;
	private ArrayList<Coltivatore> elencoColt = new ArrayList<Coltivatore>();
    private CondizioneRaccolto condizioneRaccolto;
    private Stato statoEsecuzione;
    private java.sql.Date dataInizio;
    private java.sql.Date dataFine;
    private java.sql.Date dataRaccolto;
    private String indirizzo;
    private int quantitàRaccolto;
    
    Attività(CondizioneRaccolto cond, Stato stat, java.sql.Date dataInizio, java.sql.Date dataFine, String indirizzo){
    	this.condizioneRaccolto = cond;
    	this.statoEsecuzione = stat;
    	this.dataInizio = dataInizio;
    	this.dataFine = dataFine;
    	this.indirizzo = indirizzo;
    }

//GETTERS:
    public int getIdAttività() {
    	return idAttività;
    }
	public ArrayList<Coltivatore> getElencoColt() {
		return elencoColt;
	}
	public CondizioneRaccolto getCondizioneRaccolto() {
		return condizioneRaccolto;
	}
	public Stato getStatoEsecuzione() {
		return statoEsecuzione;
	}
	public java.sql.Date getDataInizio(){
		return dataInizio;
	}
	public java.sql.Date getDataFine(){
		return dataFine;
	}
	public java.sql.Date getDataRaccolto() {
		return dataRaccolto;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public int getQuantitàRaccolto() {
		return quantitàRaccolto;
	}
	
//SETTERS:    
	public void setIdAttività(int idAtt) {
		this.idAttività = idAtt;
	}
	public void setElencoColt(Coltivatore colt) {
		elencoColt.add(colt);
	}
	public void setCondizioneRaccolto(CondizioneRaccolto condizioneRaccolto) {
		this.condizioneRaccolto = condizioneRaccolto;
	}
	public void setStatoEsecuzione(Stato statoEsecuzione) {
		this.statoEsecuzione = statoEsecuzione;
	}
	public void setDataInizio(java.sql.Date dataIn) {
		this.dataInizio = dataIn;
	}
	public void setDataFine(java.sql.Date dataFin) {
		this.dataFine = dataFin;
	}
	public void setDataRaccolto(java.sql.Date dataRaccolto) {
		this.dataRaccolto = dataRaccolto;
	}
	public void setIndirizzo(String indir) {
		this.indirizzo = indir;
	}
	public void setQuantitàRaccolto(int quantitàRaccolto) {
		this.quantitàRaccolto = quantitàRaccolto;
	}
    
    
}
