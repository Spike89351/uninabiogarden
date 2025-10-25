import java.util.ArrayList;
import java.util.Date;

public class Progetto {
	private int codeProgetto;
    private String NomeProgetto;
    private java.sql.Date DataFine;
    private java.sql.Date DataInizio;
    private String DescrizioneProgetto;
    private ArrayList<Notifica> Notify = new ArrayList<Notifica>();
    private Terreno Terreno;
    private StatoProgetto StatoProgetto;
    
    
    
	public Progetto(String nomeProgetto,  java.sql.Date dataInizio, Terreno terreno) {
		NomeProgetto = nomeProgetto;
		DataInizio = dataInizio;
		Terreno = terreno;
		}
	
	
//GETTERS:    
	public int getCodeProgetto() {
		return codeProgetto;
	}
	public String getNomeProgetto() {
		return NomeProgetto;
	}
	public Date getDataFine() {
		return DataFine;
	}
	public Date getDataInizio() {
		return DataInizio;
	}
	public String getDescrizioneProgetto() {
		return DescrizioneProgetto;
	}
	public ArrayList<Notifica> getNotify() {
		return Notify;
	}
	public Terreno getTerreno() {
		return Terreno;
	}
	public StatoProgetto getStatoProgetto() {
		return StatoProgetto;
	}
	
	
//SETTERS:
	public void setCodeProgetto(int code) {
		codeProgetto = code;
	}
	public void setNomeProgetto(String nomeProgetto) {
		NomeProgetto = nomeProgetto;
	}
	public void setDataFine(java.sql.Date dataFine) {
		DataFine = dataFine;
	}
	public void setDataInizio(java.sql.Date dataInizio) {
		DataInizio = dataInizio;
	}
	public void setDescrizioneProgetto(String descrizioneProgetto) {
		DescrizioneProgetto = descrizioneProgetto;
	}
	public void setNotify(ArrayList<Notifica> notify) {
		Notify = notify;
	}
	public void setTerreno(Terreno terreno) {
		Terreno = terreno;
	}
	public void setStatoProgetto(StatoProgetto statoProgetto) {
		StatoProgetto = statoProgetto;
	}

    
    
}
