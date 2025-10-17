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
	private PaginaRegistraTerreno paginaRegistraTerreno;
	
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
	
	
	//QUESTO METODO SERVE PER IL LOGIN:
	public void accediAllaPiattaforma(String username, String password) {
		System.out.println("Sto nella funzione che si trova nel controller:");
		utenteDAO = new UtenteDAO();
		if(utenteDAO.ctrlUsername(username)) {
			System.out.println("Sto nel primo if, cioè quello del controllo dell'username:");
			if(utenteDAO.ctrlPassword(username, password)) {
				JOptionPane.showMessageDialog(null, "Il tuo account esiste!");
			}else {
				JOptionPane.showMessageDialog(null, "L'account non esiste, RIPROVA!");
			}
		}else {
			JOptionPane.showMessageDialog(null, "L'username che hai inserito non esiste!");
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
	
	public void daRegistraTerrenoARegistraProprietario() {
		paginaRegistraTerreno.setVisible(false);
		paginaRegistraProp.setVisible(true);
	}
	
	public void daTerrenoAHomePage() {
		paginaRegistraTerreno.setVisible(false);
		homePage.setVisible(true);
	}
	
}
