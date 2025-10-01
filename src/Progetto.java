import java.util.ArrayList;
import java.util.Date;

public class Progetto {
    private String NomeProgetto;
    private Date DataFine;
    private Date DataInizio;
    private String DescrizioneProgetto;
    private ArrayList<Notifica> Notify = new ArrayList<Notifica>();
    private Terreno Terreno;
    private StatoProgetto StatoProgetto;
}
