import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JTextArea;

public class PaginaProprietario extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel elencoAttributiPrg;
	private JTextField txtNomeProgetto;
	private JTextField txtIdTerreno;
	private JDateChooser dataInizioChooser;
	private int idProprietario;
	private JComboBox comboBoxStatoProgetto;
	private JTextField txtDescrizione;
	
	public PaginaProprietario(String username, Controller c) {
		theController = c;
		
		idProprietario = theController.trovaProprietarioTramiteUsername(username);
		
		setTitle("La tua pagina - Proprietario ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1026, 329);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menùTerreni = new JMenu("Terreni");
		menuBar.add(menùTerreni);
		
		JMenuItem menùItemAggiungiTerreni = new JMenuItem("Aggiungi e visualizza terreni");
		menùItemAggiungiTerreni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PAGINA DOVE POTER AGGIUNGERE UN TERRENO:
				Utente u = theController.prendiDatiUtente(username);
				theController.daPaginaProprietarioAFinestraTerreni(u);
			}
		});
		menùTerreni.add(menùItemAggiungiTerreni);
		
		JMenu menùDatiUtente = new JMenu("Dati utente");
		menuBar.add(menùDatiUtente);
		
		JMenuItem menuItemisualizzaDati = new JMenuItem("Visualizza e modifica dati utente");
		menuItemisualizzaDati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PRENDO I DATI DELL'UTENTE E LI PORTO NELL'ALTRA FINESTRA COSS' DA POTER INSERIRLI NEI CAMPI DI TESTO
				theController.daPaginaProprietarioAFinestraDatiUtente(theController.prendiDatiUtente(username));
			}
		});
		menùDatiUtente.add(menuItemisualizzaDati);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Questa è la tua pagina");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblElencoProgetti = new JLabel("Elenco progetti");
		lblElencoProgetti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblAggiungiProgetto = new JLabel("Aggiungi progetto");
		lblAggiungiProgetto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panelTable = new JPanel();
		
		JButton btnVisualizzaProgetto = new JButton("Visualizza progetto");
		btnVisualizzaProgetto.setEnabled(false);
		btnVisualizzaProgetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VISUALIZZA PROGETTO:
				
			}
		});
		
		JLabel lblNomeProgetto = new JLabel("Nome progetto");
		lblNomeProgetto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDataInizioProgetto = new JLabel("Data inizio");
		lblDataInizioProgetto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtNomeProgetto = new JTextField();
		txtNomeProgetto.setToolTipText("Inserisici il nome del progetto");
		txtNomeProgetto.setColumns(10);
		
		dataInizioChooser = new JDateChooser();
		dataInizioChooser.setToolTipText("Inserisci la data di inizio del progetto");
		
		JButton btnCreaProgetto = new JButton("Crea progetto");
		btnCreaProgetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CONTROLLO CHE I CAMPI SIANO OK:
				if(ctrlFields()){
					//SE LA DESCRIIZION E' VUOTA ALLORA SET A NULL:
					if(txtDescrizione.getText().isBlank()) {
						txtDescrizione.setText(null);
					}
					//FUNZIONE CHE PERMETTE LA CREAZIONE DI UN PROGETTO:
					try {
						//CAST: 
						java.sql.Date sqlDate = new java.sql.Date(dataInizioChooser.getDate().getTime());
						
						//CONTROLLO CHE IL PROPIETARIO DEL TERRENO SIA LO STESSO:
						 int varId_proprietario_Trovato = theController.ctrlSulProprietarioDelTerreno(Integer.valueOf(txtIdTerreno.getText()));
						 if(varId_proprietario_Trovato == idProprietario) {
							//CREAZIONE DEL PROGETTO:
							if(theController.inserisciProgetto(idProprietario, Integer.valueOf(txtIdTerreno.getText()), txtNomeProgetto.getText().trim(), sqlDate, txtDescrizione.getText())) {
								//AGGIORNA TABELLA:
								theController.popolaTabellaProgetti(idProprietario, String.valueOf(comboBoxStatoProgetto.getSelectedItem()), elencoAttributiPrg);
								JOptionPane.showMessageDialog(null, "Il progetto è stato inserito correttamente!");
								clearFields();
							}else {
								JOptionPane.showMessageDialog(null, "Il progetto NON è stato inserito correttamente!");							
							}
						 }else {
							 JOptionPane.showMessageDialog(null, "Mi dispiace ma NON è stato trovato nessun terreno con i codice "+Integer.valueOf(txtIdTerreno.getText()));
						 }
					}catch(Exception ss) {
						JOptionPane.showMessageDialog(null, "Errore nel cast della data");
					}
				}
			}
		});
		
		JLabel lblIdTerreno = new JLabel("id terreno");
		lblIdTerreno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtIdTerreno = new JTextField();
		txtIdTerreno.setToolTipText("Inserisci l'id del terreno dove vuoi che il progetto si svolga");
		txtIdTerreno.setColumns(10);
		
		String[] statoProgetto = {"", "Pianificato", "In corso", "Completato"};
		
		comboBoxStatoProgetto = new JComboBox(statoProgetto);
		comboBoxStatoProgetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.popolaTabellaProgetti(idProprietario, String.valueOf(comboBoxStatoProgetto.getSelectedItem()), elencoAttributiPrg);
			}
		});
		
		JLabel lblCercaPerStatoProgetto = new JLabel("Cerca per stato del progetto");
		lblCercaPerStatoProgetto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtDescrizione = new JTextField();
		txtDescrizione.setToolTipText("Inserisci una breve descrizione");
		txtDescrizione.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelCentral.createSequentialGroup()
					.addGap(168)
					.addComponent(btnCreaProgetto)
					.addPreferredGap(ComponentPlacement.RELATED, 395, Short.MAX_VALUE)
					.addComponent(btnVisualizzaProgetto)
					.addGap(217))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblNomeProgetto)
									.addGap(18)
									.addComponent(txtNomeProgetto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblDataInizioProgetto, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(dataInizioChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescrizione, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIdTerreno, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtDescrizione)
								.addComponent(txtIdTerreno, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(69)
							.addComponent(lblAggiungiProgetto)))
					.addGap(27)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblElencoProgetti)
							.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
							.addComponent(lblCercaPerStatoProgetto)
							.addGap(18)
							.addComponent(comboBoxStatoProgetto, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAggiungiProgetto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblElencoProgetti)
							.addComponent(comboBoxStatoProgetto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCercaPerStatoProgetto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomeProgetto)
								.addComponent(txtNomeProgetto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescrizione, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDescrizione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblIdTerreno, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtIdTerreno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(dataInizioChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataInizioProgetto, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addGap(28)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreaProgetto)
						.addComponent(btnVisualizzaProgetto))
					.addContainerGap())
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		elencoAttributiPrg  = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "Id progetto", "Nome progetto", /*"Id terreno",*/ "Data inizio", "Data fine", "Stato progetto"}
			);;
		
		table = new JTable(elencoAttributiPrg);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// una volta cliccato su un progetto si sblocca il tasto visualizza progetto:
				btnVisualizzaProgetto.setEnabled(true);
				
			}
		});
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Logout");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PULISCI I CAMPI:
				clearFields();
				
				//FAI IL LOGOUT:
				theController.logout();
			}
		});
		panelBottom.add(btnBack);

		//POPOLIAMO LA TABELLA CON TUTTI I PROGETTI:
		try {
			theController.popolaTabellaProgetti(idProprietario, String.valueOf(comboBoxStatoProgetto.getSelectedItem()), elencoAttributiPrg);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella nell'apertura della pagina" + e);
		}
		
	}
	
//METODI:
	private boolean ctrlFields() {
		if(txtNomeProgetto.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma il campo nome progetto non può essere vuoto!");
			return false;
		}
		if(txtIdTerreno.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma il campo id terreno non può essere vuoto!");
			return false;
		}
		//COVERSIONE + CONTROLLO CHE LA DATA DI INIZIO NON SIA PRIMA DELLA DATA CORRENTE:
		try {
			LocalDate oggi = LocalDate.now();
			
		    LocalDate dataScelta = dataInizioChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		    if (dataScelta.isBefore(oggi)) {
				JOptionPane.showMessageDialog(null, "La data di inizio del progetto non può essere una data precedente alla data corrente");
				return false;
			}else {
				return true;
			}
		}catch(Exception x) {
			JOptionPane.showMessageDialog(null, "Errore nella conversione della data");
			return false;
		}
	}
	
	//SERVE PER PULIRE I CAMPI UNA VOLTA CHE IL PROGETTO E' STATO INSERITO:
	private void clearFields() {
		txtNomeProgetto.setText(null);
		txtIdTerreno.setText(null);
		txtDescrizione.setText(null);
		dataInizioChooser.setDate(null);
		
	}
}
