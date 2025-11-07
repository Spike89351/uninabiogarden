import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

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
	private ProgettoDAO progettoDAO;
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
	private DepositoDAO depositoDAO;
	private Fertilizzante fertilizzanti;
	private Coltura coltura;
	private Attrezzo attrezzo;
	private StatoAttrezzo statoAttrezzo;
	
	//VARIABILI CHE SERVONO A MEMORIZZARE I DATI DAL DB COSI' DA AVERLI A PORTATA DI MANO:
	//private ArrayList<Terreno> listaTerre;
	
	//INIZIALIZZAZIONE DELLE PAGINE: (ALCUNE)
	public HomePage homePage;
	public PaginaRegistrati paginaRegistrati;
	public PaginaRegistraProprietario paginaRegistraProp;
	public PaginaRegistraDeposito paginaRegistraDeposito;
	public PaginaRegistraTerreno paginaRegistraTerreno;
	public PaginaProprietario paginaProprietario;
	public PaginaColtivatore paginaColtivatore;
	public VisualizzaTerrenoInModoSpecifico paginaTerrenoSpecifico;
	public PaginaVisualizzaDettagliProgetto paginaDettagliProgetto;
	public PaginaDeposito paginaDeposito;
	public PaginaDettagliDeposito paginaDettagliDeposito;
	
	
	//FINESTRE:
	public FinestraVisualizzaEModificaDatiProprietario finestraDatiProprietario;
	public AggiungiEVisualizzaTerreno AggEVisualizzaTerre;
	
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
	public void inserisciPropreitario(Utente u, String email, String partitaIva, Deposito dep, double superfice, TipoTerreno tipoTerreno, Fertilità tipoFertilità) {
		utenteDAO = new UtenteDAO();
		utenteDAO.inserisicUtente(u);
		proprietarioDAO = new ProprietarioDAO();
		proprietarioDAO.inserisiciProprietario(u.getUsername(), email, partitaIva);
		int codeProp = proprietarioDAO.trovaCodiceProprietario(u.getUsername());
		creaDeposito(codeProp, dep.getIndirizzo(), dep.getDimDeposito());
		depositoDAO = new DepositoDAO();
		int idDep = depositoDAO.trovaIdDeposito(codeProp);
		aggiungiTerreno(u.getUsername(), superfice, tipoTerreno, tipoFertilità, idDep);
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
	
	//SERVE A TORANARE INDIETRO:
	public void daPaginaProprietarioARegistraUtente(Utente u) {
		u = null;
		paginaRegistraProp.setVisible(false);
		paginaRegistrati.setVisible(true);
	}
	
	//PSSAGGIO DA PROPRIETARIO A DEPOSITO:
	public void daPaginaRegistraProprietarioAPaginaRegistraDeposito(Utente u, String email, String pIva) {
		paginaRegistraProp.setVisible(false);
		
		paginaRegistraDeposito = new PaginaRegistraDeposito(u, email, pIva, this);
		paginaRegistraDeposito.setVisible(true);
	}
	
	//PASSAGGIO DALLA PAGINA DELLA REGISTRAZIONE DEL PROPRIETARIO ALLA REGISTRAZIONE DEL TERRENO:
	public void daPaginaRegistraDepositoARegistraTerreno(Utente u, String email, String partitaIva, Deposito dep) {
		paginaRegistraDeposito.setVisible(false);
		
		paginaRegistraTerreno = new PaginaRegistraTerreno(u, email, partitaIva, dep, this);
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
		//VENGONO UTILIZZATE NEL METODO ALLA LINEA DI CODICE 99;
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
	public Utente prendiDatiUtente(String username) {//DA MODIFICARE:
		utenteDAO = new UtenteDAO();
		Utente u = null;
		return u = utenteDAO.prendiDatiUtente(username);
	}
	
	//SERVE PER LA MODIFICA DEI DATI DI UN UTENTE:
	public void modificaDati(Utente u, String nome, String cognome, java.sql.Date data, Genere genere) {
		//CONTROLLO SE I CAMPI DI TESTO SONO UGUALI AI DATI DELL'UTENTE, IN TAL CASO NON MODIFICO QUEL DATO:
		try {
			if(! u.getNome().equals(nome)) {
				u.setNome(nome);
				utenteDAO.modificaNome(u.getUsername(), nome);
			}
			if(! u.getCognome().equals(cognome)) {
				u.setCognome(cognome);
				utenteDAO.modificaCognome(u.getUsername(), cognome);
			}
			if(! u.getDataNascita().equals(data)) {
				u.setDataNascita(data);
				utenteDAO.modificaDataNascita(u.getUsername(), data);
			}
			if(! u.getGenere().equals(genere)) {
				u.setGenere(genere);
				utenteDAO.modificaGenere(u.getUsername(), genere);
			}
		}catch(Exception x){
			JOptionPane.showMessageDialog(null, "Errore nella funzione nella classe controller, funzione modificaDati");
		}
	}
	
	//SERVE PER LA FINESTRA DEI DATI DELL'UTENTE:
	public void daPaginaProprietarioAFinestraDatiUtente(Utente u) {
		finestraDatiProprietario = new FinestraVisualizzaEModificaDatiProprietario(this, u);
		finestraDatiProprietario.setVisible(true);
		paginaProprietario.setEnabled(false);
	}
	
//MI SERVE PER LA PAGINA DEI TERRENI IN PROPRIETARIO:
	
	//SERVE PER LA FINESTRA DEI TERRENI DEL PROPRIETARIO:
	public void daPaginaProprietarioAFinestraTerreni(Utente u) {
		AggEVisualizzaTerre	= new AggiungiEVisualizzaTerreno(this, u);
		AggEVisualizzaTerre.setVisible(true);
		paginaProprietario.setEnabled(false);
	}
	
	//SERVE PER POPOLARE LA TABELLA CON I VARI TERRENI CHE APPARTENGONO ALL'UTENTE:
	public void popolaTabellaTerreni(String username, DefaultTableModel modelTab) {
		modelTab.setRowCount(0);
		terrenoDAO = new TerrenoDAO();
		terrenoDAO.risaliTerreni(username, modelTab);
	}
	
	//SERVE AD AGGIUNGERE UN TERRENO AL PROPRIETARIO: (NEL CASO BISOGNEREBBE AGGIORNARE ANCHE L'ARRAYLIST)
	public void aggiungiTerreno(String username, double superfice, TipoTerreno tipoTerreno, Fertilità tipoFertilità, int idDep) {
		//TROVA IL CODICE DEL PROPRIETARIO:
		proprietarioDAO = new ProprietarioDAO();
		int codeProp = proprietarioDAO.trovaCodiceProprietario(username);
		if(codeProp > 0) {
			//CONTROLLA SE L'ID DEL DEPOSITO INSERITO E' IL SUO: 
			depositoDAO = new DepositoDAO();
			if(depositoDAO.ctrlAppDeposito(codeProp, idDep)) {
				//CREA UN NUOVO TERRENO:
				terrenoDAO = new TerrenoDAO();
				terrenoDAO.inserisciTerreno(codeProp, superfice, tipoTerreno, tipoFertilità, idDep);
			}else {
				JOptionPane.showMessageDialog(null, "Mi dispiace ma il codice del deposito non esiste, l'operazione non è andata a buon fine!");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Errore nel codice del proprietario, funzione aggiungiTerreno (Controller)");
		}
	}
	
	
	//SERVE PER ANDARE NELLA PAGINA TERRENO E VISUALIZZARE UN TERRENO SELEZIONATO PI' APPROFONDITAMENTE:
	public void daPaginaAggiungiEVisualizzaTerrenoAVisualizzaTerrenoSpecifico(String idTerreno) {
		paginaTerrenoSpecifico = new VisualizzaTerrenoInModoSpecifico(this, idTerreno);
		paginaTerrenoSpecifico.setVisible(true);
		
		AggEVisualizzaTerre.setVisible(false);
		paginaProprietario.setVisible(false);
	}
	
//METODI CHE SERVONO PER LA PAGINA VISUALIZZA TERRENO SPECIFICO:
	public Terreno trovaTerreno(String idTerreno) {
		terrenoDAO = new TerrenoDAO();
		Terreno ter = terrenoDAO.trovaTerreno(idTerreno);
		return ter;
	}
	
	//SERVE PER VISUALIZZARE TUTTI I PROGETTI PER UN TERRENO: (DEVI PRIMA POTERLI INSERIRE)
	public void popolaTabellaProgettiPerTerreno(String idTerreno) {
		Terreno ter = trovaTerreno(idTerreno);
		progettoDAO = new ProgettoDAO();
		progettoDAO.listaDiProgettiPerTerreno(Integer.valueOf(idTerreno), ter);
		
	}

	//METODO CHE SERVE PER PASSARE DALLA VISUALIZZAZIONE DEL TERRENO SPECIFICO ALLA PAGINA 'ALTRI DETTAGLI':
	public void daPaginaVisualizzaDettagliTerrenoToAltriDettagli(int idTerreno) {
		
	}
	
//METODI CHE SERVONO PER LA PAGINA DEL PROPRIETARIO PER INSERIRE UN PROGETTO:
	public boolean inserisciProgetto(int codiceProp, int idTerreno, String nomePrg, java.sql.Date dataInizio, String desc) {
		//AGGIUNGI IL PROGETTO:
		progettoDAO = new ProgettoDAO();
		 return progettoDAO.inserisciProgetto(codiceProp, idTerreno, nomePrg, dataInizio, desc);
	}
	
	//MI SERVE PER LA PAGINA PROPRIETARIO PER TROVARE IL CODICE TRAMITE USERNAME:
	public int trovaProprietarioTramiteUsername(String username) {
		proprietarioDAO = new ProprietarioDAO();
		return proprietarioDAO.trovaCodiceProprietario(username);
	}
	
	//MI SERVE PER CONTROLLARE SE PRIMA DI INSERIRE IL PROGETTO IL TERRENO E' DEL PROPRIETARIO CHE HA FATTO L'ACCESSO:
	public int ctrlSulProprietarioDelTerreno(int idTerreno) {
		terrenoDAO = new TerrenoDAO();
		 return terrenoDAO.trovaProprietarioTramiteTerreno(idTerreno);
	}
	
	//SERVE PER VISUALIZZARE, IN BASE ALLO STATO DI UN PROGETTO, I PROGETTI DI UN PROPRIETARIO:
	public void  popolaTabellaProgetti(int idProp, String statoPrg, DefaultTableModel modelTab){
		modelTab.setRowCount(0);
		progettoDAO = new ProgettoDAO();
		progettoDAO.listaProgettiPerProprietario(idProp, statoPrg, modelTab);
	}
	
	//MI SERVE PER ANDARE NELLA PAGINA DOVE POSSO VISUALIZZARE UN PROGETTO IN MODO SPECIFICO E FARE MODIFICHE NEL CASO:
	public void daProprietarioToPaginaVisualizzaProgetto(int idProgetto) {
		paginaProprietario.setVisible(false);
		
		paginaDettagliProgetto = new PaginaVisualizzaDettagliProgetto(this, idProgetto);
		paginaDettagliProgetto.setVisible(true);
	}

//METODI CHE SERVONO PER LA PAGINA DOVE SI VUOLE MODIFICARE UN PROGETTO:
	//POPOLA TABELLA:
	public void inserisciInTabellaLaTuplaDaVisualizzare(int idProgetto, DefaultTableModel model) {
		model.setRowCount(0);
		progettoDAO = new ProgettoDAO();
		progettoDAO.tuplaDettagliprogetto(idProgetto, model);
	}
	
	//MODIFICA DI ALCUNI DATI DEL PROGETTO:
	public void modificaDatiProgetto(int codiceProgetto, String newNomeProgetto, java.sql.Date dataFinePrg, String statoProgetto) {
		progettoDAO = new ProgettoDAO();
		//MODIFICA DEL NOME DEL PROGETTO SE E SOLO SE LA STRINGA INSERITA NON E' VUOTA:
		if(! newNomeProgetto.isBlank()) {
			progettoDAO.modificaNomeProgetto(newNomeProgetto, codiceProgetto);
		}
		//INSERIMENTO ALTRI DATI GIA' CONTROLLATI:
		//DATA:
		progettoDAO.inserisciDataFine(dataFinePrg, codiceProgetto);
		//STATO:
		progettoDAO.modificaStatoProgetto(statoProgetto, codiceProgetto);
	}
	
	//CONTROLLO LO STATO DEL PROGETTO:
	public String ctrlStatoProgetto(int idTerreno) {
		progettoDAO = new ProgettoDAO();
		return progettoDAO.ctrlStatoProgetto(idTerreno);
	}
	
//PER IL LOGOUT:
	public void logout() {
		paginaProprietario.setVisible(false);
		homePage.setVisible(true);
	}
	
//METODI CHE SERVONO PER LA PAGINA DEPOSITO:
	public void daPaginaProprietarioAPaginaDeposito(int idProprietario) {
		paginaProprietario.setVisible(false);

		paginaDeposito = new PaginaDeposito(this, idProprietario);
		paginaDeposito.setVisible(true);
	}
	
	//MI SERVE PER LA CREAZIONE DI UN DEPOSITO:
	public void creaDeposito(int idProp, String indirizzo, double dimensione) {
		depositoDAO = new DepositoDAO();
		depositoDAO.creaDeposito(idProp, indirizzo, dimensione);
	}
	
	//POPOLA TABELLA CON I DEPOSITI:
	public void popolaTabellaDepositi(int idPropr, DefaultTableModel model) {
		model.setRowCount(0);
		depositoDAO = new DepositoDAO();
		depositoDAO.popolaTabellaDepositi( idPropr, model);
	}
	
	//TROVA L'ID DEL DEPOSITO:
	public int trovaIdDeposito(int idProp) {
		depositoDAO = new DepositoDAO();
		return depositoDAO.trovaIdDeposito(idProp);
	}
	
	//SERVE PER PASSARE DA paginaDeposito A paginaDettagliDeposito:
	public void daPaginaDepositoAPaginaDettagliDeposito(int idDeposito) {
		paginaDeposito.setVisible(false);
		
		paginaDettagliDeposito = new PaginaDettagliDeposito(this, idDeposito);
		paginaDettagliDeposito.setVisible(true);
	}
	
	//MI SERVE PER POPOLARE LA TABELLA CON UN DEPOSITO:
	public void popolaTabellaDepositoConUnaTupla(int idDep, DefaultTableModel model) {
		depositoDAO = new DepositoDAO();
		depositoDAO.popolaTabellaConUnDeposito(idDep, model);
	}
	
//SERVE PER LA PAGINA DETTAGLI DEPOSITO:
	public void modificaDeposito(int idDep, String newIndirizzo, double newQuantRaccolto, double newDim) {
		depositoDAO = new DepositoDAO();
		//MODIFICA DEI DATI:
		if(newIndirizzo.isBlank()) {
			depositoDAO.modificaIndirizzo(idDep, newIndirizzo);
		}
//		if(newQuantRaccolto) {
//			depositoDAO.modificaDatiRaccolto(idDep, newQuantRaccolto);
//		}
		depositoDAO.modificaDatiRaccolto(idDep, newQuantRaccolto);
		depositoDAO.modificaDimensione(idDep, newDim);
	}
//METODI CHE SERVONO PER IL TIPO DI ATTIVITA' DI UN TERRENO:
	
}
