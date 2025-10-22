import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Controller {
	
	//INIZIALIZZAZIONI DELLE CLASSI COME ATTIBUTI:
	private Utente utente;
	private UtenteDAO utenteDAO;
	private Genere genere;
	private Proprietario proprietario;
	private ProprietarioDAO proprietarioDAO;
	private Coltivatore coltivaotore;
	private ColtivatoreDAO coltivatoreDAO;
	private Progetto progetto;
	private Terreno terreno;
	private TerrenoDAO terrenoDAO;
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
	public PaginaRegistraTerreno paginaRegistraTerreno;
	public PaginaProprietario paginaProprietario;
	public PaginaColtivatore paginaColtivatore;
	
	//FINESTRE:
	private FinestraVisualizzaEModificaDatiProprietario finestraDatiProprietario;
	//MAIN:
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
	
	//INSERIMENTO DEL PROPRIETARIO:
	public void inserisciPropreitario(Utente u, String email, String partitaIva, double superfice, TipoTerreno tipoTerreno, Fertilità tipoFertilità) {
		utenteDAO = new UtenteDAO();
		utenteDAO.inserisicUtente(u);
		proprietarioDAO = new ProprietarioDAO();
		proprietarioDAO.inserisiciProprietario(u.getUsername(), email, partitaIva);
		int idProprietario = proprietarioDAO.trovaCodiceProprietario(u.getUsername());
		if(idProprietario > 0) {
			terrenoDAO = new TerrenoDAO();
			terrenoDAO.inserisciTerreno(idProprietario, superfice, tipoTerreno, tipoFertilità);
		}else {
			JOptionPane.showMessageDialog(null, "Errore nella classe controller nella funzione InserisciProprietario!");
		}
	}
	
	//INSERIMENTO DEL COLTIVATORE:
	public void inserisciColtivatore(Utente u) {
		utenteDAO = new UtenteDAO();
		utenteDAO.inserisicUtente(u);//INSERIMENTO NELLA TABELLA UTENTE;
		coltivatoreDAO = new ColtivatoreDAO();
		coltivatoreDAO.inserisciColtivatore(u.getUsername());//INSERIMENTO NELLA TABELLA COLTIVATORE;
	}
	
	
	//QUESTO METODO SERVE PER IL LOGIN:
	public void accediAllaPiattaforma(String username, String password) {
		utenteDAO = new UtenteDAO();
		if(utenteDAO.ctrlUsername(username)) {
			if(utenteDAO.ctrlPassword(username, password)) {
				if(utenteDAO.controlloTipoUtente(username) == 1) {
					//VAI ALLA PAGINA DEL PROPRIETARIO:
					daHomePageAccessoAProprietario(username);
				}else if(utenteDAO.controlloTipoUtente(username) == 2) {
					//VAI ALLA PAGINA DEL COLTIVATORE:
					daHomePageAccessoAColtivatore(username);
				}else if(utenteDAO.controlloTipoUtente(username) == 0){
					//MESSAGGIO DI ERRORE!
					JOptionPane.showMessageDialog(null, "Errore nella funzione tipoUtente classe Controller");
				}
			}else {
				JOptionPane.showMessageDialog(null, "L'account non esiste, RIPROVA!");
				homePage.setVisible(true);
			}
		}else {
			JOptionPane.showMessageDialog(null, "L'username che hai inserito non esiste!");
			homePage.setVisible(true);
		}
	}
	

	//PASSAGGIO DALLA PAGINA CREAZIONE UTENTE A QUELLA DELLA REGISTRAZIONE PER IL PROPRIETARIO:
	public void daPaginaRegistratiAProprietario(Utente u) {
		paginaRegistrati.setVisible(false);
		
		paginaRegistraProp = new PaginaRegistraProprietario(u, this);
		paginaRegistraProp.setVisible(true);
	}
	
	public void daPaginaProprietarioARegistraUtente(Utente u) {
		u = null;
		paginaRegistraProp.setVisible(false);
		paginaRegistrati.setVisible(true);
	}
	
	//PASSAGGIO DALLA PAGINA DELLA REGISTRAZIONE DEL PROPRIETARIO ALLA REGISTRAZIONE DEL TERRENO:
	public void daPaginaRegistraProprietarioATerreno(Utente u, String email, String partitaIva) {
		paginaRegistraProp.setVisible(false);
		
		paginaRegistraTerreno = new PaginaRegistraTerreno(u, email, partitaIva, this);
		paginaRegistraTerreno.setVisible(true);		
	}
	
	//PASSAGGIO DALLA REGISTRAZIONE DEL TERRENO ALLA PAGINA REGISTRA PROPRIETARIO:
	public void daRegistraTerrenoARegistraProprietario() {
		paginaRegistraTerreno.setVisible(false);
		paginaRegistraProp.setVisible(true);
	}
	
	//UNA VOLTA COMPLETATO LA REGISTRAZIONE VAI ALLA HOME PAGE:
	public void daTerrenoAHomePage() {
		paginaRegistraTerreno.setVisible(false);
		homePage.setVisible(true);
	}
	
	//UNA VOLTA FATTO L'ACCESSO IL PROPRIETARIO ENTRERA' NELLA SUA PAGINA:
		//VENGONO UTILIZZATE NEL METODO ALLA LINEA DI CODICE 73;
	private void daHomePageAccessoAProprietario(String username) {
		homePage.setVisible(false);
		
		paginaProprietario = new PaginaProprietario(username, this);
		paginaProprietario.setVisible(true);
	}
	
	private void daHomePageAccessoAColtivatore(String username) {
		homePage.setVisible(false);
		
		paginaColtivatore = new PaginaColtivatore(username, this);
		paginaColtivatore.setVisible(true);
	}

//METODO CHE MI SERVE PER LA FINSETRA DI DIALOGO PER VISUALIZZARE I DATI DI UN UTENTE:
	public Utente prendiDatiUtente(String username) {
		utenteDAO = new UtenteDAO();
		Utente u = null;
		return u = utenteDAO.prendiDatiUtente(username);
	}
	
	//SERVE PER LA FINESTRA DEI DATI DELL'UTENTE:
	public void daPaginaProprietarioAFinestraDatiUtente(Utente u) {
		finestraDatiProprietario = new FinestraVisualizzaEModificaDatiProprietario(this, u);
		finestraDatiProprietario.setVisible(true);
		finestraDatiProprietario.requestFocus();
		paginaProprietario.setEnabled(false);
		
	}
	
}
