import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class Coltivatore extends Utente{
    private String codiceId;
    private ArrayList<Attività> elencoAttività = new ArrayList<Attività>();
    private ArrayList<Notifica> elencoNotifiche = new ArrayList<Notifica>();
    private Date dataInizioContratto;
    private ArrayList<Attività> elencoCosaSaFare = new ArrayList<Attività>();
    private Notifica notifica;
    private boolean disponibilità;



    //COSTRUTTORE:
    Coltivatore(String nome, String cognome, java.sql.Date dataNascita, Genere gen, String username, String password){
        super(nome, cognome, dataNascita, gen, username, password);
    }

    //GETTERS:
    public String getCodiceId() {
        return codiceId;
    }
    public ArrayList<Attività> getElencoAttività() {
        return elencoAttività;
    }
    public ArrayList<Notifica> getElencoNotifiche() {
        return elencoNotifiche;
    }
    public Date getDataInizioContratto() {
        return dataInizioContratto;
    }
    public ArrayList<Attività> getElencoCosaSaFare(){
        return elencoCosaSaFare;
    }
    public Notifica getNotifica() {
    	return notifica;
    }
    public boolean isDisponibilità() {
        return disponibilità;
    }
    

    //SETTERS:
    public void setCodiceId(String codiceId) {
        this.codiceId = codiceId;
    }
    public void setElencoAttività(ArrayList<Attività> elencoAttività) {
        this.elencoAttività = elencoAttività;
    }
    public void setElencoNotifiche(ArrayList<Notifica> elencoNotifiche) {
        this.elencoNotifiche = elencoNotifiche;
    }
    public void setDataInizioContratto(Date dataInizioContratto) {
        this.dataInizioContratto = dataInizioContratto;
    }
    public void setElencoCosaSoFare(Attività at) {
        boolean ctrl = true;
        for(int i=0; i<elencoCosaSaFare.size(); i++) {
            if(elencoCosaSaFare.get(i).equals(at)) {
                ctrl = false;
            }
        }
        if(! ctrl) {
            JOptionPane.showMessageDialog(null, "Mi dispiace, ma stai cercando di inserire una mansione che già hai!");
        }else {
            elencoCosaSaFare.add(at);
        }
    }
    public void setNotifica(Notifica notifica) {
    	this.notifica = notifica;
    }
    public void setDisponibilità(boolean disponibilità) {
        this.disponibilità = disponibilità;
    }





}