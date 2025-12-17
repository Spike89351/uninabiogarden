import java.util.*;

import javax.swing.JOptionPane;

public class Attrezzo {
    private String nome;
    private int  idAttrezzo;
    private TipoAttrezzo tipo;
    private StatoAttrezzo statoAttrezzo;
    private Stato statoAtt;
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
    public StatoAttrezzo getStatoAttrezzo() {
        return statoAttrezzo;
    }
    public Stato getStatoAtt() {
    	return statoAtt;
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
    public void setStatoAttrezzo(StatoAttrezzo statoAttrezzo) {
        this.statoAttrezzo = statoAttrezzo;
    }
    public void setStatoAtt(Stato statoAtt) {
    	this.statoAtt = statoAtt;
    }
    public void setDisp(boolean disp) {
        this.disp = disp;
    }



}
