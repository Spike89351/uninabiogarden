import java.util.ArrayList;

public class Terreno {
    private String ID_Terreno;
    private Proprietario Prop;
    private double superficie;
    private TipoTerreno TipologiaTerreno;
    private ArrayList<Progetto> Project = new ArrayList<Progetto>();
    private ArrayList<TipoAttivita> TipoAttivita = new ArrayList<TipoAttivita>();
    private ArrayList<String> StoricoColtura = new ArrayList<String>();


    //COSTRUTTORE:
    Terreno(Proprietario Prop, double superficie, TipoTerreno TipologiaTerreno) {
        this.Prop = Prop;
        this.superficie = superficie;
        this.TipologiaTerreno = TipologiaTerreno;
    }

    //GETTERS:

   
    public Proprietario getProp() {
        return Prop;
    }
    public double getSuperficie() {
        return superficie;
    }
    public TipoTerreno getTipologiaTerreno() {
        return TipologiaTerreno;
    }
    public ArrayList<Progetto> getProject() {
        return Project;
    }
    public ArrayList<String> getStoricoColtura() {
        return StoricoColtura;
    }
    public ArrayList<TipoAttivita> getTipoAttivita() {
        return TipoAttivita;
    }

    //SETTERS:
    public void setProp(Proprietario Prop) {
        this.Prop = Prop;
    }
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
    public void setTipologiaTerreno(TipoTerreno TipologiaTerreno) {
        this.TipologiaTerreno = TipologiaTerreno;
    }
    public void setProject(ArrayList<Progetto> Project) {
        this.Project = Project;
    }
    public void setStoricoColtura(ArrayList<String> StoricoColtura) {
        this.StoricoColtura = StoricoColtura;
    }
    public void setTipoAttivita(ArrayList<TipoAttivita> TipoAttivita) {
        this.TipoAttivita = TipoAttivita;
    }
}