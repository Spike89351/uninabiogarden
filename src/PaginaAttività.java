import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaginaAttività extends JFrame {
	private Controller theController;
	
	private int idAttivitàSelezionata;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel panelCentral;
	private JComboBox comboBoxCondizione;
	private JComboBox comboBoxStato;
	private JDateChooser dateChooserInizio;
	private JPanel panelBottom;
	private JLabel lblWelcome;
	private JButton btnBack;
	private JButton btnVisualizzaDettagli;
	private JButton btnAggiungi;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnRimuovi;
	
	public PaginaAttività(int idTerreno, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//PULISCI CAMPI:
				clearFields();
				//RENDO IL PULSANTE NON DISPONIBILE:
				btnVisualizzaDettagli.setEnabled(false);
				//LA VARIABILE A CUI PASSO L'ID DELL'ATTIVITA' LA SETTO  A 0:
				idAttivitàSelezionata = 0;
			}
		});
		
		theController = c;
		
		setTitle("Pagina attività");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(605, 318);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		lblWelcome = new JLabel("Aggiungi il tipo di attività sul terreno con id 0");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblCondizione = new JLabel("Condizione");
		lblCondizione.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDataInizio = new JLabel("Dara inizio");
		lblDataInizio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		String[] elecoCondizioni = {"", "Preparazione", "Semina", "Germinazione", "Irrigazione", "Nutrizione", "Fioritura", "Crescita", "Raccolta", "Maturazione", "Fruttificazione", "Riposo", "Rinnovo"};
		
		comboBoxCondizione = new JComboBox(elecoCondizioni);
		
		String[] elencoStato = {"", "Nessuna", "In Corso", "Pianificata", "Completata"};
		
		comboBoxStato = new JComboBox(elencoStato);
		
		dateChooserInizio = new JDateChooser();
		
		JPanel panelTable = new JPanel();
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AGGIUNGI ATTIVITA':
				
				//PULISCI CAMPI:
				clearFields();
			}
		});
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(dateChooserInizio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblCondizione)
									.addGap(18)
									.addComponent(comboBoxCondizione, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(51)
							.addComponent(btnAggiungi)))
					.addGap(18)
					.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panelTable, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelCentral.createSequentialGroup()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCondizione)
								.addComponent(comboBoxCondizione, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnAggiungi)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "Id attività", "Condizione", "Stato", "Data inizio"}
			);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.rowAtPoint(e.getPoint());
				if(selectedRow != -1) {
					//TRADUCO CIO' CHE HO SELEZIONATO:
					String idAttivitàString= String.valueOf(table.getValueAt(selectedRow, 0));
					idAttivitàSelezionata = Integer.valueOf(idAttivitàString);
					
					//SBLOCCO IL PULSANTE 'DETTAGLI':
					btnVisualizzaDettagli.setEnabled(true);
				}
			}
		});
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///TORNA INDIETRO:
				setVisible(false);
				theController.paginaTerrenoSpecifico.setVisible(true);
				
				
				//PULISCI CAMPI:
				clearFields();
			}
		});
		panelBottom.setLayout(new BorderLayout(0, 0));
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		JPanel panelBottomoCentral = new JPanel();
		panelBottom.add(panelBottomoCentral, BorderLayout.CENTER);
		
		btnVisualizzaDettagli = new JButton("Visualizza Dettagli");
		btnVisualizzaDettagli.setEnabled(false);
		panelBottomoCentral.add(btnVisualizzaDettagli);
		
		btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RIMUOVI ATTIVITA':
				
				
			}
		});
		panelBottom.add(btnRimuovi, BorderLayout.EAST);
		btnVisualizzaDettagli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VISUALIZZA DETTAGLI E LI' PUOI AGGIUNGERE ALTRE COSE:
				
				//NON CREDO CI SIA BISOGNO DI SETTARE IL PULSANTE FALSE PER RENDERLO NON CLICCABILE:
//				btnVisualizzaDettagli.setEnabled(false);
			}
		});
	}
//METODI:
	//SERVE PER PULIRE I CAMPI:
	private void clearFields() {
		comboBoxCondizione.setSelectedItem(null);
		comboBoxStato.setSelectedItem(null);
		dateChooserInizio.setDate(null);
	}
	//SERVE PER CONTROLLARE SE I CAMPI SONO CORRETTAMENTE COMPILATI:
	private boolean ctrlFields() {
		
		return true;
	}
}
