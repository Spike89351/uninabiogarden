public class Coltura {
    private String nome;
    private int tempoMaturazione;
    private String colore;
    private String stagione;
    private String tipoOrtaggio; //ORESA DAL DB
    private String parteUtilizzabile;
    private boolean disp;


    Coltura(String nome, int tempoMat, String colore, String stagione, String tipoOrtaggio, String parteUtilizzabile, boolean disp){
        this.nome = nome;
        this.tempoMaturazione = tempoMat;
        this.colore = colore;
        this.stagione = stagione;
        this.tipoOrtaggio = tipoOrtaggio;
        this.parteUtilizzabile = parteUtilizzabile;
        this.disp = disp;
    }



    //GETTERS:
    public String getNome() {
        return nome;
    }
    public int getTempoMaturazione() {
        return tempoMaturazione;
    }
    public String getColore() {
        return colore;
    }
    public String getStagione() {
        return stagione;
    }
    public String getTipoOrtaggio() {
        return tipoOrtaggio;
    }
    public String getParteUtilizzabile() {
        return parteUtilizzabile;
    }
    public boolean isDisp() {
        return disp;
    }




    //SETTER:
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTempoMaturazione(int tempoMaturazione) {
        this.tempoMaturazione = tempoMaturazione;
    }
    public void setColore(String colore) {
        this.colore = colore;
    }
    public void setStagione(String stagione) {
        this.stagione = stagione;
    }
    public void setTipoOrtaggio(String tipoOrtaggio) {
        this.tipoOrtaggio = tipoOrtaggio;
    }
    public void setParteUtilizzabile(String parteUtilizzabile) {
        this.parteUtilizzabile = parteUtilizzabile;
    }
    public void setDisp(boolean disp) {
        this.disp = disp;
    }

    //SERVE PER AUMENTARE IL TEMPO DI MATURAZIONE:
    public void aumentaTempoMaturazione(int ore) {
        this.tempoMaturazione = this.tempoMaturazione + ore;
    }



}
