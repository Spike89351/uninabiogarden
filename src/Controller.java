import java.sql.SQLException;

public class Controller {
	
	//INIZIALIZZAZIONI DELLE CLASSI COME ATTIBUTI:
	private Utente utente;
	private Genere genere;
	private Proprietario proprietario;
	private Coltivatore coltivaotore;
	private Progetto progetto;
	private Terreno terreno;
	private CondizioneRaccolto condizioneRaccolto;
	private Stato stato;
	private TipoAttivita tipoAttività;
	private Attività attività;
	private Notifica notifica;
	private StatoNotifica statoNotifica;
	private Importanza importanza;
	private Deposito depposito;
	private Fertilizzante fertilizzanti;
	private Coltura coltura;
	private Attrezzo attrezzo;
	private StatoAttrezzo statoAttrezzo;
	
	//INIZIALIZZAZIONE DELLE PAGINE: (ALCUNE)
	public HomePage homePage;
	public PaginaRegistrati paginaRegistrati;
	public PaginaRegistraProprietario paginaRegistraProp;
	
	//MAIN
	public static void main(String[] args) throws SQLException {
			Controller theController = new Controller();
	}
	
	//COSTRUTTORE
	public Controller() throws SQLException{
		
		homePage = new HomePage(this);
		homePage.setVisible(true);
		
		paginaRegistrati = new PaginaRegistrati(this);
		
		
	}
	
	
//METODI:
	
	public void creaUtente() {
		
	}
	
	public void daPaginaRegistratiAProprietario(Utente u) {
		//ALL'INTERNO DEVI INSERIRE LA PAGINA DEL COLTIVATORE + GLI PASSI L'UTENTE COSI' DA PRENDERE I DATI
		paginaRegistrati.setVisible(false);
		
		paginaRegistraProp = new PaginaRegistraProprietario(u, this);
		paginaRegistraProp.setVisible(true);
	}
	
}
