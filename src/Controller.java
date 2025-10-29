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
	public PaginaRegistraTerreno paginaRegistraTerreno;
	public PaginaProprietario paginaProprietario;
	public PaginaColtivatore paginaColtivatore;
	public VisualizzaTerrenoInModoSpecifico paginaTerrenoSpecifico;
	
	
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
	public void inserisciPropreitario(Utente u, String email, String partitaIva, double superfice, TipoTerreno tipoTerreno, Fertilità tipoFertilità) {
		utenteDAO = new UtenteDAO();
		utenteDAO.inserisicUtente(u);
		proprietarioDAO = new ProprietarioDAO();
		proprietarioDAO.inserisiciProprietario(u.getUsername(), email, partitaIva);
		aggiungiTerreno(u.getUsername(), superfice, tipoTerreno, tipoFertilità);
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
		ArrayList<Terreno> listaTerre = terrenoDAO.risaliTerreni(username);
		if(listaTerre.size() <= 0) {
			JOptionPane.showMessageDialog(null, "La tua lista di terre è vuote");//IMPOSSIBILE!
		}else {
			for(Terreno terr : listaTerre) {
				modelTab.addRow(new Object[]{ terr.getID_Terreno(), terr.getSuperficie(), terr.getTipologiaTerreno(), terr.getfertTerreno()});
			}
		}
	}
	
	//SERVE AD AGGIUNGERE UN TERRENO AL PROPRIETARIO: (NEL CASO BISOGNEREBBE AGGIORNARE ANCHE L'ARRAYLIST)
	public void aggiungiTerreno(String username, double superfice, TipoTerreno tipoTerreno, Fertilità tipoFertilità) {
		//TROVA IL CODICE DEL PROPRIETARIO:
		proprietarioDAO = new ProprietarioDAO();
		int codeProp = proprietarioDAO.trovaCodiceProprietario(username);
		if(codeProp > 0) {
			//CREA UN NUOVO TERRENO:
			terrenoDAO = new TerrenoDAO();
			terrenoDAO.inserisciTerreno(codeProp, superfice, tipoTerreno, tipoFertilità);
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
		ArrayList<Progetto> listaProgetti;
		listaProgetti = progettoDAO.listaProgettiPerProprietario(idProp, statoPrg);
		if(! listaProgetti.isEmpty()) {
			for(Progetto prg : listaProgetti) {
				modelTab.addRow(new Object[]{prg.getCodeProgetto(), prg.getNomeProgetto(), /*prg.getIdTerreno(),*/ prg.getDataInizio(), prg.getDataFine(), prg.getStatoProgetto()});
			}
		}else {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma non ci sono progetti con lo stato progetto che ha inserito");
		}
	}
	
}
