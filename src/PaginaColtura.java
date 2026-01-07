import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.FlowLayout;

public class PaginaColtura extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtColore;
	private JTextField txtNome;
	private JTable table;
	private JComboBox comboBoxTipoOrtaggio;
	private DefaultTableModel model;
	private JComboBox comboBoxDisponibile;
	private JComboBox comboBoxStagione;
	private JButton btnRimuovi;
	private JButton btnBack;
	private int idColturaSelezionata;
	private JLabel lblAvvisoColturaScelta;
	private JButton btnCambiaDisponibilità;
	
	
	public PaginaColtura(int idDep, Controller c) {
		theController = c;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA LA TABELLA DI DEFAULT CON TUTTE LE COLTURE DISP:
				theController.popolaTabellaColtureDispONon(idDep, model, true);
			}
		});
		
		setTitle("Pagina coltura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 347);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Qui puoi aggiungere e visualizzare la coltura che hai nel deposito "+ idDep);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblColore = new JLabel("Colore");
		lblColore.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblStagione = new JLabel("Stagione");
		lblStagione.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblTipoOratggio = new JLabel("Tipo oratggio");
		lblTipoOratggio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		String[] elencoTipoOrtaggio = {"", "Da Frutto", "Legumi", "Da Fiore", "Da Foglia", "Da Fusto", "Da Bulbo", "Da Radice", "Da Tubero"};
		
		comboBoxTipoOrtaggio = new JComboBox(elencoTipoOrtaggio);
		
		txtColore = new JTextField();
		txtColore.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JPanel panelTable = new JPanel();
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AGGIUNGI:
				if(ctrlTxtFields()) {
					if(theController.inserisciColtura(idDep, txtNome.getText(), txtColore.getText(), comboBoxStagione.getSelectedItem().toString(), comboBoxTipoOrtaggio.getSelectedItem().toString())) {
						theController.popolaTabellaColtureDispONon(idDep, model, true);
						clearTxtFields();
						JOptionPane.showMessageDialog(null, "Hai inserito correttamente la coltura!");
					}
				}
			}
		});
		
		JLabel lblTabella = new JLabel("Elenco colture disponibili");
		lblTabella.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblAggiungiColtura = new JLabel("Aggiungi coltura");
		lblAggiungiColtura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAggiungiColtura.setHorizontalAlignment(SwingConstants.CENTER);
		
		String[] elencoDisp = {"", "Non disponibile"};
		
		comboBoxDisponibile = new JComboBox(elencoDisp);
		comboBoxDisponibile.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//UNA VOLTA CAMBIATO CERCA PER CIO' CHE SI E' CAMBIATO:
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(String.valueOf(e.getItem().toString()).equals("")) {
						theController.popolaTabellaColtureDispONon(idDep, model, true);
					}else {
						theController.popolaTabellaColtureDispONon(idDep, model, false);
					}
				}
			}
		});
		
		String[] elencoStagioni = {"", "Estivo", "Invernale", "Autunnale", "Primaverile"};
		
		comboBoxStagione = new JComboBox(elencoStagioni);
		
		lblAvvisoColturaScelta = new JLabel("La coltura che hai scelto ha come id "+idColturaSelezionata);
		lblAvvisoColturaScelta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvvisoColturaScelta.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblTipoOratggio, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBoxTipoOrtaggio, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStagione, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblColore)
										.addComponent(lblNome))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBoxStagione, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtColore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(56)
							.addComponent(btnAggiungi))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAggiungiColtura, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblTabella)
							.addPreferredGap(ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
							.addComponent(comboBoxDisponibile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
						.addComponent(lblAvvisoColturaScelta, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTabella)
						.addComponent(lblAggiungiColtura)
						.addComponent(comboBoxDisponibile, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblColore, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtColore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStagione, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxStagione, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipoOratggio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTipoOrtaggio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAggiungi)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAvvisoColturaScelta)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model  = new DefaultTableModel(
				new Object[][]{},
				new String[]{"id Coltura", "Nome", "Colore", "Stagione", "Tipo", "Disponibile"}
			);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.rowAtPoint(e.getPoint());
				if(selectedRow != -1) {
					//PRENDO IL NOME DELLA COLTURA, COLORE E IL TIPO:
					String idColturaString = String.valueOf(table.getValueAt(selectedRow, 0));
					
					idColturaSelezionata = Integer.valueOf(idColturaString);
					
					lblAvvisoColturaScelta.setText("La coltura che hai scelto ha come id "+idColturaSelezionata);
					
					//SBLOCCO IL PULSANTE 'RIMUOVI' e 'CAMBIADISPONIBILITA'':
					btnRimuovi.setEnabled(true);
					btnCambiaDisponibilità.setEnabled(true);
				}
			}
		});
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNA INDIETRO:
				setVisible(false);
				theController.paginaDettagliDeposito.setVisible(true);
				btnRimuovi.setEnabled(false);
				
				//PULISCO I CAMPI:
				clearTxtFields();
			}
		});
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.setEnabled(false);
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RIMUOVI:
				if(theController.eliminaColtura(idDep, idColturaSelezionata)) {
					//POPOLA LA TABELLA:
					theController.popolaTabellaColtureDispONon(idDep, model, true);
					//PULISCO I CAMPI:
					clearTxtFields();
					//L'ID DELLA COLTURA SELEZIONATA LA METTO A 0:
					idColturaSelezionata = 0;
					JOptionPane.showMessageDialog(null, "Hai eliminato correttamente la coltura");
				}
			}
		});
		panelBottom.add(btnRimuovi, BorderLayout.EAST);
		
		JPanel panelBottomCentral = new JPanel();
		panelBottom.add(panelBottomCentral, BorderLayout.CENTER);
		panelBottomCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnCambiaDisponibilità = new JButton("Cambia disponibilità");
		btnCambiaDisponibilità.setEnabled(false);
		btnCambiaDisponibilità.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CAMBIA DISPONIBILITA' DELLA COLTURA:
				if(theController.cambiaDisponibilitàDiUnaColtura(idColturaSelezionata)) {
//					theController.popolaTabellaColtureDispONon(idDep, model, true);
					//PULISCO LE VARIABILI:
					idColturaSelezionata = 0;
					
					JOptionPane.showMessageDialog(null, "Hai cambiato correttamente la disponiiblità della coltura!");
				}else {
					JOptionPane.showMessageDialog(null, "La disponibilità della coltura selezionata non è stata cambiata correttamente, RIPROVA!");
				}
			}
		});
		panelBottomCentral.add(btnCambiaDisponibilità);

	}
//METODI:
	//MI SERVE PER PULIRE I CAMPI:
	private void clearTxtFields() {
		txtNome.setText(null);
		txtColore.setText(null);
		comboBoxStagione.setSelectedItem(null);
		comboBoxTipoOrtaggio.setSelectedItem(null);
		
		//PULISCO LE VARIABILI:
		idColturaSelezionata = 0;
	}
	
	//SERVE PER CONTROLLARE I CAMPI SE SONO CORRETTI:
	private boolean ctrlTxtFields() {
		if(txtNome.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo nome non può essere vuoto!");
			return false;
		}
		if(txtColore.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo colore non può essere vuoto!");
			return false;
		}
		if(comboBoxStagione.getSelectedItem().toString().trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo stagione non può essere vuoto!");
			return false;
		}
		if(comboBoxTipoOrtaggio.getSelectedItem().toString().trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo tipo ortaggio non può essere vuoto!");
			return false;
		}
		return true;
	}
}
