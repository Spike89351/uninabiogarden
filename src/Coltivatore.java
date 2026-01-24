import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class Coltivatore extends Utente{
    private int codiceId;
    private String indirizzo;
    private ArrayList<Attività> elencoAttività = new ArrayList<Attività>();
    private ArrayList<Notifica> elencoNotifiche = new ArrayList<Notifica>();
    private Notifica notifica;
    private boolean disponibilità;



    //COSTRUTTORE:
    Coltivatore(String nome, String cognome, java.sql.Date dataNascita, Genere gen, String username, String password, String indirizzo){
        super(nome, cognome, dataNascita, gen, username, password);
    }

    //GETTERS:
    public int getCodiceId() {
        return codiceId;
    }
    public ArrayList<Attività> getElencoAttività() {
        return elencoAttività;
    }
    public ArrayList<Notifica> getElencoNotifiche() {
        return elencoNotifiche;
    }
    public Notifica getNotifica() {
    	return notifica;
    }
    public boolean isDisponibilità() {
        return disponibilità;
    }
    public String getIndirizzo() {
    	return indirizzo;
    }

    //SETTERS:
    public void setCodiceId(int codiceId) {
        this.codiceId = codiceId;
    }
    public void setElencoAttività(ArrayList<Attività> elencoAttività) {
        this.elencoAttività = elencoAttività;
    }
    public void setElencoNotifiche(ArrayList<Notifica> elencoNotifiche) {
        this.elencoNotifiche = elencoNotifiche;
    }
    public void setNotifica(Notifica notifica) {
    	this.notifica = notifica;
    }
    public void setDisponibilità(boolean disponibilità) {
        this.disponibilità = disponibilità;
    }
    public void setIndirizzo(String indirizzo) {
    	this.indirizzo = indirizzo;
    }
}