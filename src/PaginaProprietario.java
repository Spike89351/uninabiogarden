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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class PaginaProprietario extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel elencoAttributiPrg;
	private JTextField txtNomeProgetto;
	private JTextField txtIdTerreno;
	private int idProprietario;
	
	
	public PaginaProprietario(String username, Controller c) {
		theController = c;
		
//		idProprietario = theController
		
		setTitle("La tua pagina - Proprietario ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 326);
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
		
		JPanel panel = new JPanel();
		
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
		
		JDateChooser dataInizioChooser = new JDateChooser();
		dataInizioChooser.setToolTipText("Inserisci la data di inizio del progetto");
		
		JTextArea txtAreaDescrizione = new JTextArea();
		txtAreaDescrizione.setToolTipText("Inserisci la descrizione del progetto (NON E' OBBLIGATORIO)");
		
		JButton btnCreaProgetto = new JButton("Crea progetto");
		btnCreaProgetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FUNZIONE CHE PERMETTE LA CREAZIONE DI UN PROGETTO:
				theController.inserisciProgetto(idTerreno, username, null, username);
			}
		});
		
		JLabel lblIdTerreno = new JLabel("id terreno");
		lblIdTerreno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtIdTerreno = new JTextField();
		txtIdTerreno.setToolTipText("Inserisci l'id del terreno dove vuoi che il progetto si svolga");
		txtIdTerreno.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
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
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(txtIdTerreno, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(txtAreaDescrizione, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(69)
					.addComponent(lblAggiungiProgetto)
					.addPreferredGap(ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
					.addComponent(lblElencoProgetti)
					.addGap(152))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(168)
					.addComponent(btnCreaProgetto)
					.addPreferredGap(ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
					.addComponent(btnVisualizzaProgetto)
					.addGap(123))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblElencoProgetti)
						.addComponent(lblAggiungiProgetto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomeProgetto)
								.addComponent(txtNomeProgetto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescrizione, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblIdTerreno, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtIdTerreno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(dataInizioChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataInizioProgetto, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
					.addGap(28)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVisualizzaProgetto)
						.addComponent(btnCreaProgetto))
					.addContainerGap())
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(39)
					.addComponent(txtAreaDescrizione, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addGap(125))
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		DefaultTableModel elencoAttributiPrg  = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "id progetto", "Nome progetto", "id terreno", "data inizio"}
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
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LOGOUT
				
			}
		});
		panelBottom.add(btnBack);

	}
}
