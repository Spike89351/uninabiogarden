
public class Fertilizzante {
    private double letame;
    private double compost;
    private double granulari;
    private double liquidi;

    //COSTRUTTORE:
    Fertilizzante(double letame, double compost, double granulari, double liquidi){
        this.letame = letame;
        this.compost = compost;
        this.granulari = granulari;
        this.liquidi = liquidi; 
    }


    //GETTERS:
    public double getLetame() {
        return letame;
    }
    public double getCompost() {
        return compost;
    }
    public double getGranulari() {
        return granulari;
    }
    public double getLiquidi() {
        return liquidi;
    }


    //SETTERS:
    public void setLetame(double letame) {
        this.letame = this.letame + letame ;
    }
    public void setCompost(double compost) {
        this.compost = this.compost + compost;
    }
    public void setGranulari(double granulari) {
        this.granulari = this.granulari + granulari;
    }
    public void setLiquidi(double liquidi) {
        this.liquidi = this.liquidi + liquidi;
    }


}
