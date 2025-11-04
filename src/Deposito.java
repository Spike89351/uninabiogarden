import java.util.ArrayList;

public class Deposito {
    private int idDeposito;
    private String indirizzo;
    private double dimDeposito;
    private ArrayList<String> elencoRaccoltoPoss = new ArrayList<String>();
    private ArrayList<String> note = new ArrayList<String>();
    private Fertilizzante fertilizzante;
    private ArrayList<Coltura> elencoColture = new ArrayList<Coltura>();
    private ArrayList<Attrezzo> elencoAttrezzi = new ArrayList<Attrezzo>();

    Deposito(String indirizzo, Double dimensione){
    	this.indirizzo = indirizzo;
    	this.dimDeposito = dimensione;
    }

//GETTERS:
	public int getIdDeposito() {
		return idDeposito;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public double getDimDeposito() {
		return dimDeposito;
	}
	public ArrayList<String> getElencoRaccoltoPoss() {
		return elencoRaccoltoPoss;
	}
	public ArrayList<String> getNote() {
		return note;
	}
	public Fertilizzante getFertilizzante() {
		return fertilizzante;
	}
	public ArrayList<Coltura> getElencoColture() {
		return elencoColture;
	}
	public ArrayList<Attrezzo> getElencoAttrezzi() {
		return elencoAttrezzi;
	}

	
//SETTERS:	
	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public void setDimDeposito(double dimDeposito) {
		this.dimDeposito = dimDeposito;
	}
	public void setElencoRaccoltoPoss(String RaccoltoPoss) {
		this.elencoRaccoltoPoss.add(RaccoltoPoss);
	}
	public void setNote(String note) {
		this.note.add(note);
	}
	public void setFertilizzante(Fertilizzante fertilizzante) {
		this.fertilizzante = fertilizzante;
	}
	public void setElencoColture(Coltura elencoColture) {
		this.elencoColture.add(elencoColture);
	}
	public void setElencoAttrezzi(Attrezzo Attrezzo) {
		this.elencoAttrezzi.add(Attrezzo);
	}
    


}