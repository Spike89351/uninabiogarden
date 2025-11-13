import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class PaginaAttività extends JFrame {
	private Controller theController;
	
	private int idAttivitàSelezionata;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel panelCentral;
	private JComboBox comboBoxTipoAttività;
	private JComboBox comboBoxStato;
	private JDateChooser dateChooserInizio;
	private JDateChooser dateChooserFine;
	private JPanel panelBottom;
	private JLabel lblWelcome;
	private JButton btnBack;
	private JButton btnVisualizzaDettagli;
	private JButton btnAggiungi;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnRimuovi;
	
	public PaginaAttività(int idTerreno, int idProgetto, Controller c) {
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
			@Override
			public void windowActivated(WindowEvent e) {
//				theController.popolaTabellaAttività(idTerreno, model);
			}
		});
		
		theController = c;
		
		setTitle("Pagina attività");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650, 340);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		lblWelcome = new JLabel("Aggiungi il tipo di attività sul terreno con id "+ idTerreno +" e al progetto con id "+ idProgetto);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblTipoAttività = new JLabel("Tipo attività");
		lblTipoAttività.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDataInizio = new JLabel("Dara inizio");
		lblDataInizio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		String[] elecoCondizioni = {"", "Riposo", "Rinnovo", "Preparazione", "Semina", "Germinazione", "Irrigazione", "Nutrizione", "Fioritura", "Crescita", "Maturazione", "Fruttificazione"};
		
		comboBoxTipoAttività = new JComboBox(elecoCondizioni);
		comboBoxTipoAttività.setToolTipText("Se lasci vuoto il database inserira il parametro 'Preprazione come default'");
		
		String[] elencoStato = {"", "Nessuna", "In Corso", "Pianificata", "Completata"};
		
		comboBoxStato = new JComboBox(elencoStato);
		comboBoxStato.setToolTipText("Se lasci vuoto questo camp il database compilerà questo campo di default con il valore 'Nessuno'");
		
		dateChooserInizio = new JDateChooser();
		dateChooserInizio.setToolTipText("Questo camapo non può essere vuoto");
		
		JPanel panelTable = new JPanel();
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CONTROLLA I PARAMETRI:
				if(ctrlFields()) {
					try {
						//CAST DEI PARAMETRI:
						LocalDate dataInizioLocalDate = dateChooserInizio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						java.sql.Date castDataInizio = java.sql.Date.valueOf(dataInizioLocalDate);
						
						LocalDate dataFineLocalDate = dateChooserFine.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						java.sql.Date castDataFine = java.sql.Date.valueOf(dataFineLocalDate);
						
						//AGGIUNGI ATTIVITA':
						if(theController.inserisciOModificaAttività(idProgetto, idTerreno, comboBoxTipoAttività.getSelectedItem().toString(), comboBoxStato.getSelectedItem().toString(), castDataInizio, castDataFine)) {
							//PULISCI CAMPI:
							clearFields();
							JOptionPane.showMessageDialog(null, "Complimenti l'azione è andata a buon fine!");
						}else {
							JOptionPane.showMessageDialog(null, "Errore, l'azione non è andata a buon fine!");
						}
					}catch(Exception x) {
						JOptionPane.showMessageDialog(null, "Errore nel cast del tipo di dato");
					}
				}
			}
		});
		
		JLabel lblDataFine = new JLabel("Data fine");
		lblDataFine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		dateChooserFine = new JDateChooser();
		dateChooserFine.setToolTipText("Questo camapo non può essere vuoto");
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipoAttività)
								.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
									.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBoxTipoAttività, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(51)
							.addComponent(btnAggiungi)))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipoAttività)
								.addComponent(comboBoxTipoAttività, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnAggiungi)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "Id attività", "Tipo", "Stato", "Data inizio", "Data fine"}
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
		btnVisualizzaDettagli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VISUALIZZA DETTAGLI E LI' PUOI AGGIUNGERE ALTRE COSE:
				theController.daPaginaAttivitàAFinestraDettagliAttività(idTerreno);
			}
		});
		panelBottomoCentral.add(btnVisualizzaDettagli);
		
		btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RIMUOVI ATTIVITA':
				
				
			}
		});
		panelBottom.add(btnRimuovi, BorderLayout.EAST);
		
	}
//METODI:
	//SERVE PER PULIRE I CAMPI:
	private void clearFields() {
		comboBoxTipoAttività.setSelectedItem(null);
		comboBoxStato.setSelectedItem(null);
		dateChooserInizio.setDate(null);
		dateChooserFine.setDate(null);
	}
	//SERVE PER CONTROLLARE SE I CAMPI SONO CORRETTAMENTE COMPILATI:
	private boolean ctrlFields() {
		if(comboBoxTipoAttività.getSelectedItem().toString().isBlank()){
			JOptionPane.showMessageDialog(null, "Il campo tipo attività è vuoto, pertanto verrà settato con un valore du default 'Preparazione'! ");
		}else {
			//FAI ALTRO:
			
		}
		if(comboBoxStato.getSelectedItem().toString().isBlank()) {
			JOptionPane.showMessageDialog(null, "Il campo stato è vuoto, pertanto verrà settato con un valore du default 'Nessuno'! ");
		}else {
			//FAI ALTRO:
			
		}
		try {
			LocalDate dataOdiernaLocalDate = LocalDate.now();
			java.sql.Date dataOdierna = java.sql.Date.valueOf(dataOdiernaLocalDate);
			if(dateChooserInizio.getDate().before(dataOdierna)) {
				JOptionPane.showMessageDialog(null, "La data di inizio di un'attività non può essere prima della data odierna!");
				return false;
			}
			if(dateChooserFine.getDate().equals(dateChooserInizio.getDate()) || dateChooserFine.getDate().before(dateChooserInizio.getDate())) {
				JOptionPane.showMessageDialog(null, "La data di fine non può essere uguale, o minore, alla data di inizio di un'attività!");
				return false;
			}
		}catch(ClassCastException x) {
			JOptionPane.showMessageDialog(null, "Errore nel cast della data!");
		}
		
		return true;
	}
}
