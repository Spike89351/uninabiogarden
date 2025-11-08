import java.util.*;

import javax.swing.JOptionPane;

public class Attrezzo {
    private String nome;
    private int  idAttrezzo;
    private TipoAttrezzo tipo;
    private ArrayList<Date> elencoManutenzioni = new ArrayList<Date>();
    private StatoAttrezzo statoAttrezzo;
    private ArrayList<String> elencoManStraordinaria = new ArrayList<String>();
    private boolean disp;

    Attrezzo(String nome, TipoAttrezzo typ, StatoAttrezzo stato){
        this.nome = nome;
        this.tipo = typ;
        this.statoAttrezzo = stato;
        this.disp = true;
    }


    //GETTERS:
    public String getNome() {
        return nome;
    }
    public int getIdAttrezzo() {
        return idAttrezzo;
    }
    public TipoAttrezzo getTipo() {
        return tipo;
    }
    public ArrayList<Date> getElencoManutenzioni() {
        return elencoManutenzioni;
    }
    public StatoAttrezzo getStatoAttrezzo() {
        return statoAttrezzo;
    }
    public ArrayList<String> getElencoManStraordinaria() {
        return elencoManStraordinaria;
    }
    public boolean isDisp() {
        return disp;
    }



    //SETTERS:
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdAttrezzo(int idAttrezzo) {
        this.idAttrezzo = idAttrezzo;
    }
    public void setTipo(TipoAttrezzo tipo) {
        this.tipo = tipo;
    }
    public void setElencoManutenzioni(Date Manutenzione) {
        //MI SERVE PER CONTROLLARE SE ESISTE LA DATA:
        boolean ctrl = true;
        //CONTROLLO SE LA DATA GIA' E' ESISTENTE:
        for(int i=0; i<elencoManutenzioni.size(); i++) {
            if(elencoManutenzioni.get(i).equals(Manutenzione)) {
                ctrl = false;
            }
        }
        if(! ctrl) {
            JOptionPane.showMessageDialog(null, "La data che stai inserendo è già presente nell'elenco della manutenzione dell'attrezzo!");
        }else {
            //ALTRIMENTI INSERISCI:
            elencoManutenzioni.add(Manutenzione);
        }
    }
    public void setStatoAttrezzo(StatoAttrezzo statoAttrezzo) {
        this.statoAttrezzo = statoAttrezzo;
    }
    public void setElencoManStraordinaria(String manutenzioneStrao) {
        elencoManStraordinaria.add(manutenzioneStrao);
    }
    public void setDisp(boolean disp) {
        this.disp = disp;
    }



}
