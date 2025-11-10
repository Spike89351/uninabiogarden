public class Coltura {
    private String nome;
    private String colore;
    private String stagione;
    private String tipoOrtaggio; //ORESA DAL DB
    private boolean disp;


    Coltura(String nome, String colore, String stagione, String tipoOrtaggio){
        this.nome = nome;
        this.colore = colore;
        this.stagione = stagione;
        this.tipoOrtaggio = tipoOrtaggio;
    }



    //GETTERS:
    public String getNome() {
        return nome;
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
    public boolean isDisp() {
        return disp;
    }




    //SETTER:
    public void setNome(String nome) {
        this.nome = nome;
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
    public void setDisp(boolean disp) {
        this.disp = disp;
    }

    //SERVE PER AUMENTARE IL TEMPO DI MATURAZIONE:
    public void aumentaTempoMaturazione(int ore) {
        this.tempoMaturazione = this.tempoMaturazione + ore;
    }



}
