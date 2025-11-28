import java.util.ArrayList;

public class Terreno {
    private int ID_Terreno;
    private Proprietario Prop;
    private double superficie;
    private String indirizzo;
    private TipoTerreno TipologiaTerreno;
    private Fertilità fertTerreno;
    private ArrayList<Progetto> Project = new ArrayList<Progetto>();
    private ArrayList<Attività> TipoAttivita = new ArrayList<Attività>();
    private Deposito deposito;

    //COSTRUTTORE:
    Terreno(double superficie, TipoTerreno TipologiaTerreno, Fertilità fertTerr, Deposito dep, String ind) {
        this.superficie = superficie;
        this.TipologiaTerreno = TipologiaTerreno;
        this.fertTerreno = fertTerr;
        this.deposito = dep;
        this.indirizzo = ind;
    }

    //GETTERS:
    public int getID_Terreno() {
    	return ID_Terreno;
    }
    public Proprietario getProp() {
        return Prop;
    }
    public double getSuperficie() {
        return superficie;
    }
    public String getIndirizzo() {
    	return indirizzo;
    }
    public TipoTerreno getTipologiaTerreno() {
        return TipologiaTerreno;
    }
    public Fertilità getfertTerreno() {
    	return fertTerreno;
    }
    public ArrayList<Progetto> getProject() {
        return Project;
    }
    public ArrayList<Attività> getTipoAttivita() {
        return TipoAttivita;
    }

    //SETTERS:
    public void setID_Terreno(int idTerr) {
    	this.ID_Terreno = idTerr;
    }
    public void setProp(Proprietario Prop) {
        this.Prop = Prop;
    }
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
    public void setIndirizzo(String ind) {
    	this.indirizzo = ind;
    }
    public void setTipologiaTerreno(TipoTerreno TipologiaTerreno) {
        this.TipologiaTerreno = TipologiaTerreno;
    }
    public void setfertTerreno(Fertilità ferTerr) {
    	this.fertTerreno = ferTerr;
    }
    public void setProject(ArrayList<Progetto> Project) {
        this.Project = Project;
    }
    public void setTipoAttivita(ArrayList<Attività> TipoAttivita) {
        this.TipoAttivita = TipoAttivita;
    }
}