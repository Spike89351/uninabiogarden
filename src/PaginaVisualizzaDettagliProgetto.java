import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class PaginaVisualizzaDettagliProgetto extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelTable;
	private JDateChooser dateChooser;
	private String statoAttPrg;
	private JComboBox comboBoxNewStatoPrg;
	private JTextField txtNewNomeProgetto;
	private java.sql.Date dataInizioPrg;
	
	
	public PaginaVisualizzaDettagliProgetto(Controller c, int idProgetto) {
		theController = c;
		
		setTitle("Dettagli progetto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 325);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setText("Il progetto che stai visualizzando ha come id "+idProgetto);		
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblDettagliProgetto = new JLabel("Dettagli del progetto selezionato");
		lblDettagliProgetto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panelTable = new JPanel();
		
		JLabel lblModificaAggiungiDatiProgetto = new JLabel("Modifica o Aggiungi dei dati al progetto");
		lblModificaAggiungiDatiProgetto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("Aggiorna stato progetto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblInserisciLaData = new JLabel("Inserisci la data di fine");
		lblInserisciLaData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		dateChooser = new JDateChooser();
		
		String[] tipoStato = {" ", "In corso", "Completato"};
		
		comboBoxNewStatoPrg = new JComboBox(tipoStato);
		
		JLabel lblModificaNome = new JLabel("Modifica nome ");
		lblModificaNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtNewNomeProgetto = new JTextField();
		txtNewNomeProgetto.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(lblDettagliProgetto)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(comboBoxNewStatoPrg, 0, 106, Short.MAX_VALUE)
							.addGap(37)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblModificaNome)
									.addGap(18)
									.addComponent(txtNewNomeProgetto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(34)
									.addComponent(lblInserisciLaData, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
								.addComponent(lblModificaAggiungiDatiProgetto))))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDettagliProgetto)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblModificaAggiungiDatiProgetto)
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(comboBoxNewStatoPrg, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblModificaNome, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtNewNomeProgetto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblInserisciLaData, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		modelTable = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "Id progetto", "Nome", "Data inizio", "Data fine", "Stato progetto", "Id terreno", "Superfice", "Tipo terreno", "Fertilità"}
			);;
		
		table = new JTable(modelTable);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				theController.paginaProprietario.setVisible(true);
				//PULISCI I CAMPI: 
				clearFields();
			}
		});
		btnBack.setToolTipText("Premi per tornare indietro");
		
		JButton btnNewButton = new JButton("Modifica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CONTROLLO DEI CAMPI + ALTRI CONTROLLI:
				if(ctrlFields()) {
					try {
						//CAST:
						java.sql.Date sqlDate = null;
						
						if(dateChooser.getDate() != null) {
							LocalDate dataFineLocalDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
							sqlDate = java.sql.Date.valueOf(dataFineLocalDate);
						}
						
						theController.modificaDatiProgetto(idProgetto, txtNewNomeProgetto.getText(), sqlDate, String.valueOf(comboBoxNewStatoPrg.getSelectedItem()));
					}catch(Exception xxx) {
						System.out.println("Errore nel try catch nella pagina per visualizzare i deettagli del progetto");
					}
				}
				//AGGIORNA TABELLA CON I DATI DELLA TUPLA:
				theController.inserisciInTabellaLaTuplaDaVisualizzare(idProgetto, modelTable);
				//PULISCI I CAMPI:
				clearFields();
			}
		});
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelBottom.createSequentialGroup()
					.addComponent(btnBack)
					.addPreferredGap(ComponentPlacement.RELATED, 624, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnBack)))
		);
		panelBottom.setLayout(gl_panelBottom);
		
		//POPOLA LA TABELLA CON LA TUPLA:
		try {
			theController.inserisciInTabellaLaTuplaDaVisualizzare(idProgetto, modelTable);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errore nell'inserimento della tupla nella tabella");
		}
		
		//PRENDI LO STATO DEL PROGETTO + LA DATA DI INIZIO:
		if (table.getRowCount() > 0) {
		    table.setRowSelectionInterval(0, 0); // SELEZIONA LA PRIMA RIGA
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow != -1) {
		        try {
		        	//SELEZIONO LO STATO ATTUALE DEL PROGETTO:
		        	statoAttPrg = String.valueOf(table.getValueAt(selectedRow, 4)); //PRENDI LO STATO DEL PROGETTO
		        	
		        	//SELZIONO LA DATA DI INIZIO DEL PROGETTO:
		        	dataInizioPrg = (java.sql.Date) table.getValueAt(selectedRow, 2); //PRENDI LA DATA DI INIZIO DEL PROGETTO
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, "Errore nel prelevare i dati del progetto: " + e);
		        }
		    }
		}
		
		

	}

//MEOTDI
	private boolean ctrlFields() {
		//CONTROLLO DELLA DATA FINE DEL PROGETTO:
		Date dataOdierna = new Date();
		java.sql.Date dataCorrenteSql = new java.sql.Date(dataOdierna.getTime());
		if(dateChooser.getDate() != null) {
			if(dateChooser.getDate().compareTo(dataCorrenteSql) <= 0) {
				JOptionPane.showMessageDialog(null, "Errore, la data di fine del progetto non può essere prima, o uguale, alla data corrente!");
				return false;
			}
			if(dateChooser.getDate().compareTo(dataInizioPrg) <= 0) {
				JOptionPane.showMessageDialog(null, "Errore, la data di fine del progetto selezionato non può essere uguale o precedente alla data di inizio del progetto!");
				return false;
			}
		}
		//CONTROLLO DELLO STATO DEL PROGETTO + OMBINAZIONI:
		if(String.valueOf(comboBoxNewStatoPrg.getSelectedItem()).equals(statoAttPrg)) {			
			JOptionPane.showMessageDialog(null, "Errore, lo stato inserito del prgetto non può essere uguale allo stato attuale!");
			return false;
		}
		if(String.valueOf(comboBoxNewStatoPrg.getSelectedItem()).equalsIgnoreCase("In corso") && dateChooser.getDate() != null) {
			JOptionPane.showMessageDialog(null, "Errore, non puoi avere lo stato del progetto in 'in corso' e aggiungere una data di fine del progetto");
			return false;
		}
		if(String.valueOf(comboBoxNewStatoPrg.getSelectedItem()).isBlank() && dateChooser.getDate() != null) {
			JOptionPane.showMessageDialog(null, "Errore, non puoi inserire la data di fine del progetto senza cambiare lo stato in 'Completato'! ");
			return false;
		}
		if(String.valueOf(comboBoxNewStatoPrg.getSelectedItem()).equalsIgnoreCase("Completato") && dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Errore, non puoi inserire lo stato progetto  'Completato' senza avdr inserito una data di fine!");
			return false;
		}
		return true;
	}
	
	//PULISCI I CAMPI:
	public void clearFields() {
		txtNewNomeProgetto.setText(null);
		dateChooser.setDate(null);
		comboBoxNewStatoPrg.setSelectedItem(null);
	}
	
}
