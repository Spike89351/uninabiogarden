import java.util.ArrayList;
import java.util.Date;

public class Proprietario extends Utente {
    private String codiceId;
    private String email;
    private String partitaIva;
    private ArrayList<Terreno> lottiPosseduti = new ArrayList<Terreno>();
    private ArrayList<Progetto> elencoProgetti = new ArrayList<Progetto>();


    Proprietario(String nome, String cognome, Date dataNascita, Genere gen, String username, String password, String codiceId, String email, String partitaIva, Terreno lotto) {
        super(nome, cognome, dataNascita, gen, username, password);
        this.codiceId = codiceId;
        this.email = email;
        this.partitaIva = partitaIva;
        this.lottiPosseduti.add(lotto);
    }

    //GETTERS:
    public String getCodiceId() {
        return codiceId;
    }
    public String getEmail() {
        return email;
    }
    public String getPartitaIva() {
        return partitaIva;
    }
    public ArrayList<Terreno> getLottiPosseduti() {
        return lottiPosseduti;
    }
    public ArrayList<Progetto> getElencoProgetti() {
        return elencoProgetti;
    }


    //SETTERS:
    public void setCodiceId(String codiceId) {
        this.codiceId = codiceId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }
    public void setLottiPosseduti(ArrayList<Terreno> lottiPosseduti) {
        this.lottiPosseduti = lottiPosseduti;
    }
    public void setElencoProgetti(ArrayList<Progetto> elencoProgetti) {
        this.elencoProgetti = elencoProgetti;
    }




}
