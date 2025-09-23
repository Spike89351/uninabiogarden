import java.util.Date;

public class Utente {
    private String nome;
    private String cognome;
    private Date dataNascita;
    private Genere genere;
    private String username;
    private String password;



    Utente(String nome, String cognome, Date dataNascita, Genere gen, String username, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.genere = gen;
        this.username = username;
        this.password = password;
    }


    //GETTERS:
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public Date getDataNascita() {
        return dataNascita;
    }
    public Genere getGenere() {
        return genere;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    //SETTERS:
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
    public void setGenere(Genere genere) {
        this.genere = genere;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}