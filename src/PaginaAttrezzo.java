import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class PaginaAttrezzo extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblWelcome;
	private JButton btnAggiungi;
	private JTextField txtNome;
	private JComboBox comboBoxTipo;
	private JComboBox comboBoxStato;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnRimuovi;
	private int idAttrezzoSelezionato;
	private JMenuBar menuBar;
	private JMenu menuAltro;
	private JMenuItem itemManutenzione;
	private ArrayList<Attrezzo> elenco;
	
	public PaginaAttrezzo(int idDep, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA TABELLA CON TUTTI GLI ATTREZZI:
				elenco = theController.popolaTabellaConTuttiGliAttrezziDelDeposito(idDep);
				for(Attrezzo at : elenco) {
					model.addRow(new Object[]{at.getNome(), String.valueOf(at.getTipo()), String.valueOf(at.getStatoAttrezzo())});
				}
			}
		});
		
		theController = c;
		
		setTitle("Pagina Attrezzo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(625, 380);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuAltro = new JMenu("Altro");
		menuBar.add(menuAltro);
		
		itemManutenzione = new JMenuItem("Manutenzione");
		itemManutenzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FAI QUALCOSA:
				theController.daPaginaAttrezzoAFinestraManutenzione(idDep);
			}
		});
		menuAltro.add(itemManutenzione);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		lblWelcome = new JLabel("Qui puoi aggiungere un attrezzo al deposito con id "+idDep);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		String[] tipoAttrezzo = {"","Manuale", "Macchinario"};
		
		comboBoxTipo = new JComboBox(tipoAttrezzo);
		
		String[] statoAttrezzo = {"", "Ottimo", "Buono", "Sufficiente", "In riparazione", "Da riparare", "Da rottamare"};
		
		comboBoxStato = new JComboBox(statoAttrezzo);
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrlTextFields()) {
					if(theController.creaAttrezzo(idDep, txtNome.getText(), comboBoxTipo.getSelectedItem().toString(), comboBoxStato.getSelectedItem().toString())) {
						//theController.popolaTabellaConTuttiGliAttrezziDelDeposito(idDep, model);
						clearTxtField();
						JOptionPane.showMessageDialog(null, "Hai inserito correttamente l'attrezzo!");
					}
				}
			}
		});
		
		JPanel panelTable = new JPanel();
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblNome)
									.addGap(18)
									.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(comboBoxTipo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(comboBoxStato, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(40)
							.addComponent(btnAggiungi)))
					.addGap(18)
					.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnAggiungi)))
					.addGap(45))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(
				new Object[][]{},
				new String[]{"Nome", "Tipo", "Stato"}
			);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint()); //MI SERVE PER CAPIRE A QUALE LINEA HA CLICCATO L'UTENTE COSI' LA CONFRONTO CON L'ARRAY NELLA STESSA POSIZIONE;
				if(row != -1) {
					try {
						//PRESA DEL DATO DALLA TABELLA:
						idAttrezzoSelezionato = elenco.get(row).getIdAttrezzo();
						
					}catch(ClassCastException x) {
						JOptionPane.showMessageDialog(scrollPane, "Errore nel cast!");
					}
					//RENDO DISPONIBILE IL PULSANTE PER RIMUOVERE E PER VISUALIZZARE LA MANUTEZIONE(MENU BAR):
					btnRimuovi.setEnabled(true);
				}
			}
		});
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNA INDIETRO:
				setVisible(false);
				theController.paginaDettagliDeposito.setVisible(true);
				//PULISCI IL CAMPO NOME:
				clearTxtField();
			}
		});
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.setEnabled(false);
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//UNA VOLTA SELEZIONATO UN ATTREZZO TRAMITE TABELLA IL PULSANTE SI SBLOCCA E POSSO ELIMINARLO:
				if(theController.eliminaAttrezzo(idAttrezzoSelezionato)) {
					//theController.popolaTabellaConTuttiGliAttrezziDelDeposito(idDep, model);
					clearTxtField();
					btnRimuovi.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Hai eliminato correttamente l'attrezzo!");
				}else {
					clearTxtField();
					JOptionPane.showMessageDialog(null, "Errore, non hai eliminato nessun attrezzo!");
				}
			}
		});
		panelBottom.add(btnRimuovi, BorderLayout.EAST);
	}
	
//METODI:
	private boolean ctrlTextFields() {
		if(txtNome.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, ma il nome dell'attrezzo non può essere vuoto!");
			return false;
		}
		if(comboBoxTipo.getSelectedItem().toString().trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo tipo di attrezzo non può essere vuoto!");
			return false;
		}
		if(comboBoxStato.getSelectedItem().toString().trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo stato attrezzo non può essere vuoto!");
			return false;
		}
		return true;
	}
	
	//PULISCI I CMAPI:
	private void clearTxtField() {
		txtNome.setText(null);
		comboBoxTipo.setSelectedItem("");
		comboBoxStato.setSelectedItem("");
	}
}
