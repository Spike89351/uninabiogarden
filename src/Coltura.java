public class Coltura {
	private int idColtura;
    private String nome;
    private String colore;
    private String stagione;
    private String tipoOrtaggio;
    private boolean disp;


    Coltura(String nome, String colore, String stagione, String tipoOrtaggio){
        this.nome = nome;
        this.colore = colore;
        this.stagione = stagione;
        this.tipoOrtaggio = tipoOrtaggio;
    }


    //GETTERS:
    public int getIdColtura() {
    	return idColtura;
    }
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
    public void setIdColtura(int idColt) {
    	this.idColtura = idColt;
    }
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

}
