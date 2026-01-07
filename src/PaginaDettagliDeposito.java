import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class PaginaDettagliDeposito extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtIndirizzo;
	private JTextField txtQuantitàRaccolto;
	private JTextField txtDimensione;
	private JButton btnBack;
	private JButton btnModifica;
	
	
	public PaginaDettagliDeposito(Controller c, int idDeposito) {
		theController = c;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA LA TABELLA CON LA TUPLA:
				try{
					theController.popolaTabellaDepositoConUnaTupla(idDeposito, model);
				}catch(Exception x) {
					JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella "+ x);
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				btnBack.doClick();
			}
		});
				
		setTitle("Dettagli deposito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(780, 297);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuItemAttrezzo = new JMenu("Attrezzo");
		menuBar.add(menuItemAttrezzo);
		
		JMenuItem itemAggiungiAttrezzo = new JMenuItem("Aggiungi o elimina attrezzo");
		itemAggiungiAttrezzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VAI ALLA FINESTRA DEDICATA:
				theController.daPaginaDettagliDepositoAPaginaAttrezzo(idDeposito);
			}
		});
		menuItemAttrezzo.add(itemAggiungiAttrezzo);
		
		JMenu menuFertilizzante = new JMenu("Fertilizzante");
		menuBar.add(menuFertilizzante);
		
		JMenuItem menuItemFertilizzante = new JMenuItem("aggiungi fertilizzante");
		menuItemFertilizzante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VAI ALLA PAGINA DEDICATA AI FERTILIZZANTI:
				theController.daPaginaDettagliDepositoAPaginaFertilizzanti(idDeposito);
			}
		});
		menuFertilizzante.add(menuItemFertilizzante);
		
		JMenu menuColtura = new JMenu("Coltura");
		menuBar.add(menuColtura);
		
		JMenuItem menuItemColtiura = new JMenuItem("Visualizza dati coltura");
		menuItemColtiura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VAI ALLA PAGINA DEDICATA ALLA COLTURA:
				theController.daPaginaDettagliDepositoAPaginaColtura(idDeposito);
			}
		});
		menuColtura.add(menuItemColtiura);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("il deposito che stai visualizzando è "+ idDeposito);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelTable = new JPanel();
		
		JLabel lblModificaDati = new JLabel("Qui puoi modiicare i dati del deposito selezionato ");
		lblModificaDati.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificaDati.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Indirizzo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setEnabled(false);
		txtIndirizzo.setToolTipText("Il formato dell'indirizzo è: \"via/Piazza, numero civico, cap citta (provincia)\"");
		txtIndirizzo.setColumns(10);
		
		JLabel lblQuantitàRaccolto = new JLabel("Quantità");
		lblQuantitàRaccolto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtQuantitàRaccolto = new JTextField();
		txtQuantitàRaccolto.setEnabled(false);
		txtQuantitàRaccolto.setColumns(10);
		
		JLabel lblDimensione = new JLabel("Dimensione");
		lblDimensione.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtDimensione = new JTextField();
		txtDimensione.setEnabled(false);
		txtDimensione.setColumns(10);
		
		JButton btnAbilitaModifica = new JButton("Abilita modifica");
		btnAbilitaModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ABILITA LA MODIFICA:
				btnModifica.setEnabled(true);
				
				txtIndirizzo.setEnabled(true);
				txtQuantitàRaccolto.setEnabled(true);
				txtDimensione.setEnabled(true);
			}
		});
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
								.addComponent(lblModificaDati, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
									.addComponent(lblDimensione)
									.addGap(18)
									.addComponent(txtDimensione, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(101)
									.addComponent(lblQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
							.addComponent(btnAbilitaModifica)
							.addGap(297))))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(19)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblModificaDati)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDimensione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDimensione, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnAbilitaModifica))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model  = new DefaultTableModel(
				new Object[][]{},
				new String[]{"Id deposito", "Indirizzo", "Dimensione", "Raccolto"}
			);
		
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
				theController.paginaDeposito.setVisible(true);
				
				//IL PULSANTE SI DISATTIVA
				btnModifica.setEnabled(false);
				
				//PULISCI I CAMPI DI TESTO:
				clearTextField();				
			}
		});
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		btnModifica = new JButton("Modifica");
		btnModifica.setEnabled(false);
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MODIFICA DATI:
				if(ctrlTextFields()) {
					if(theController.modificaDeposito(idDeposito, txtIndirizzo.getText(), Double.valueOf(txtQuantitàRaccolto.getText().trim()), Double.valueOf(txtDimensione.getText().trim()))) {
						//AGGIORNO LA TABELLA:
						theController.inserisciInTabellaLaTuplaDaVisualizzare(idDeposito, model);
						JOptionPane.showInternalMessageDialog(null, "Le modifiche sono state apportate correttamente!");
					}else {
						JOptionPane.showMessageDialog(null, "Le mdoifiche non sono state apportate correttamente!");
					}
				}
				btnModifica.setEnabled(false);
				clearTextField();
			}
		});
		panelBottom.add(btnModifica, BorderLayout.EAST);
		
	}
	
//METODI:
	//PULISCI I CAMPI DI TESTO:
	private void clearTextField() {
		txtIndirizzo.setText(null);
		txtDimensione.setText(null);
		txtQuantitàRaccolto.setText(null);
	}
	
	//CONTROLLO SE I CAMPI DI TESTO SONO CORRETTI:
	private boolean ctrlTextFields() {
		//QUI DEVI CONTROLLARE SE I CAMPI DI TESTO RISPETTANO 
		if(txtIndirizzo.getText().isBlank()) {
			txtIndirizzo.setText("-1");
		}else {
			if(! txtIndirizzo.getText().trim().matches("^[A-Za-zÀ-ÿ'\\s]+,\\s(\\d{1,4}[A-Za-z]?|[Ss][Nn][Cc]),\\s\\d{5}\\s[A-Za-zÀ-ÿ'\\s]+\\s\\([A-Z]{2}\\)$")) {
				JOptionPane.showMessageDialog(null, "Mi dispiace ma il formato dell'inidirzzo non è corretto!");
				return false;
			}
		}
		if(txtDimensione.getText().isBlank()) {
			txtDimensione.setText("-1");
		}else if(Double.valueOf(txtDimensione.getText().trim()) <= 0){
			JOptionPane.showMessageDialog(null, "Mi dispiace ma la dimensione del deposito non può essere minore o uguale a 0!");
			return false;
		}
		if(txtQuantitàRaccolto.getText().isBlank()) {
			txtQuantitàRaccolto.setText("-1");
		}else {
			if(Double.valueOf(txtQuantitàRaccolto.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(null, "Mi dispiace ma la quantità raccolta non può essere minore di 0");
				return false;
			}
		}
		return true;
	}
}
