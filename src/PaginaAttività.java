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
import java.awt.FlowLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private JButton btnCompleta;
	private JButton btnAggiungi;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnAggiungiColtivatore;
	private String tipoAttività;
	private String statoAttivitàSelezionata;
	private JButton btnCambiaStato;
	private boolean ctrlDate;
	
	public PaginaAttività(int idTerreno, int idProgetto, String statoPrg, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//PULISCI CAMPI:
				clearFields();
				//RENDO IL PULSANTE NON DISPONIBILE:
				btnCompleta.setEnabled(false);
				//LA VARIABILE A CUI PASSO L'ID DELL'ATTIVITA' LA SETTO  A 0:
				idAttivitàSelezionata = 0;
			}
			@Override
			public void windowActivated(WindowEvent e) {
				if(statoPrg.equals("Completato")) {
					btnAggiungi.setEnabled(false);
					btnCompleta.setEnabled(false);
					btnCambiaStato.setEnabled(false);
				}
				theController.popolaTabellaAttività(idTerreno, idProgetto, model);
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
		
		String[] elecoCondizioni = {"", "Riposo", "Rinnovo", "Preparazione", "Semina", "Germinazione", "Irrigazione", "Nutrizione", "Fioritura", "Crescita", "Maturazione", "Fruttificazione", "Raccolta"};
		
		comboBoxTipoAttività = new JComboBox(elecoCondizioni);
//		comboBoxTipoAttività.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				//UNA VOLTA CAMBIATO STATO E SELEZIONATO 'RACCOLTA':
//				if(comboBoxTipoAttività.getSelectedItem().toString().equals("Raccolta")) {
//					ctrlDate = false;
//					dateChooserFine.setEnabled(false);
//				}else {
//					ctrlDate = true;
//					dateChooserFine.setEnabled(true);
//				}
//			}
//		});
		comboBoxTipoAttività.setToolTipText("Se lasci vuoto il database inserira il parametro 'Preprazione come default'");
		
		String[] elencoStato = {"", "Nessuna", "Pianificata", "In Corso"};
		
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
						
						java.sql.Date castDataFine = null;
						if(ctrlDate){
							LocalDate dataFineLocalDate = dateChooserFine.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
							castDataFine = java.sql.Date.valueOf(dataFineLocalDate);
						}
						
						//AGGIUNGI ATTIVITA':
						if(theController.inserisciAttività(idProgetto, idTerreno, comboBoxTipoAttività.getSelectedItem().toString(), comboBoxStato.getSelectedItem().toString(), castDataInizio, castDataFine)) {
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
		dateChooserFine.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
                    // CONTROLLA SE LA NUOVA DATA E' DIVERSA DA NULL:
                    if (dateChooserFine.getDate() != null) {
                    	ctrlDate = true; 
                    } else {
                    	ctrlDate = false;
                    }    
				}
			}
		});
		dateChooserFine.setToolTipText("Questo camapo non può essere vuoto");
		
		btnAggiungiColtivatore = new JButton("Seleziona coltivatore");
		btnAggiungiColtivatore.setEnabled(false);
		btnAggiungiColtivatore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RIMUOVI ATTIVITA':
				theController.daPaginaAttivitàAPaginaSceltaColtivatore(idAttivitàSelezionata, statoAttivitàSelezionata);
			}
		});
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
								.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTipoAttività, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(51)
							.addComponent(btnAggiungi)
							.addPreferredGap(ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
							.addComponent(btnAggiungiColtivatore)))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAggiungiColtivatore))
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
					.addContainerGap(37, Short.MAX_VALUE))
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
					tipoAttività = String.valueOf(table.getValueAt(selectedRow, 1));
					statoAttivitàSelezionata = String.valueOf(table.getValueAt(selectedRow, 2));
					
					if(!statoAttivitàSelezionata.equals("Completata")) {
						//SBLOCCO IL PULSANTE 'DETTAGLI':
						btnAggiungiColtivatore.setEnabled(true);
						btnCambiaStato.setEnabled(true);
						btnCompleta.setEnabled(true);
						btnAggiungiColtivatore.setEnabled(true);
					}else {
						btnAggiungiColtivatore.setEnabled(false);
						btnCambiaStato.setEnabled(false);
						btnCompleta.setEnabled(false);
					}
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
		
		JPanel panelBottomCentral = new JPanel();
		panelBottom.add(panelBottomCentral, BorderLayout.CENTER);
		panelBottomCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnCambiaStato = new JButton("Cambia stato");
		btnCambiaStato.setEnabled(false);
		btnCambiaStato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MI SERVE PER CAMBIARE STATO DELL'ATTIVIA':
				theController.daPaginaAttivitàAFinestraCambiaStatoAttività(idAttivitàSelezionata, statoAttivitàSelezionata);
				disattivaPulsanti();
			}
		});
		panelBottomCentral.add(btnCambiaStato);
		
		btnCompleta = new JButton("Completa");
		panelBottom.add(btnCompleta, BorderLayout.EAST);
		btnCompleta.setToolTipText("Questo pulsante aggiunge una data fine all'attività selezioanta ");
		btnCompleta.setEnabled(false);
		btnCompleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SE L'ATTIVITà E' DIVERSA DALLA RACCOLTA ALLORA INSERISCI SOLO LA DATA ALTRIMENTI VAI NELLA PAGINA E INSERISCI LA QUANTITA' RACCOLTA:
				if(! tipoAttività.equals("Raccolta")) {
					//INSERISCI LO STATO 'COMPLETATA':
					if(theController.inserisciDataFIneAttività(idAttivitàSelezionata, "Completata")) {
						JOptionPane.showMessageDialog(null, "Complimenti l'attività è stata completata");
					}else {
						JOptionPane.showMessageDialog(null, "Errore, non è stato cambiato lo stato dell'attività");
					}
					theController.popolaTabellaAttività(idTerreno, idProgetto, model);
				}else {
					//VISUALIZZA DETTAGLI E LI' PUOI AGGIUNGERE ALTRE COSE:
					theController.daPaginaAttivitàAFinestraDettagliAttività(idTerreno, idAttivitàSelezionata);
				}
				disattivaPulsanti();
			}
		});
		
	}
//METODI:
	//MI SERVE PER DISATTIVARE I PULSANTI:
	private void disattivaPulsanti() {
		btnAggiungiColtivatore.setEnabled(false);
		btnCompleta.setEnabled(false);
		btnCambiaStato.setEnabled(false);
	}
	
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
			if(ctrlDate) {
				if(dateChooserFine.getDate().equals(dateChooserInizio.getDate()) || dateChooserFine.getDate().before(dateChooserInizio.getDate())) {
					JOptionPane.showMessageDialog(null, "La data di fine non può essere uguale, o minore, alla data di inizio di un'attività!");
					return false;
				}
			}
		}catch(ClassCastException x) {
			JOptionPane.showMessageDialog(null, "Errore nel cast della data!");
		}
		
		return true;
	}
}
