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
		});
				
		setTitle("Dettagli deposito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 305);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuItemAttrezzo = new JMenu("Attrezzo");
		menuBar.add(menuItemAttrezzo);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Aggiungi attrezzo");
		menuItemAttrezzo.add(mntmNewMenuItem_3);
		
		JMenu menuFertilizzante = new JMenu("Fertilizzante");
		menuBar.add(menuFertilizzante);
		
		JMenuItem menuItemFertilizzante = new JMenuItem("aggiungi fertilizzante");
		menuFertilizzante.add(menuItemFertilizzante);
		
		JMenu menuColtura = new JMenu("Coltura");
		menuBar.add(menuColtura);
		
		JMenuItem menuItemColtiura = new JMenuItem("Visualizza dati coltura");
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
		txtIndirizzo.setToolTipText("Il formato dell'indirizzo è: \"via/Piazza, numero civico, cap citta (provincia)\"");
		txtIndirizzo.setColumns(10);
		
		JLabel lblQuantitàRaccolto = new JLabel("Quantità");
		lblQuantitàRaccolto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtQuantitàRaccolto = new JTextField();
		txtQuantitàRaccolto.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
						.addComponent(lblModificaDati, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(19)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblModificaDati)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantitàRaccolto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(38, Short.MAX_VALUE))
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
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNA INDIETRO:
				setVisible(false);
				theController.paginaDeposito.setVisible(true);
				
				
			}
		});
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		JButton btnAvanti = new JButton("Avanti");
		panelBottom.add(btnAvanti, BorderLayout.EAST);
		
	}
	
//METODI:
	//CONTROLLO SE I CAMPI DI TESTO SONO CORRETTI:
	public boolean ctrlTextFields() {
		//QUI DEVI CONTROLLARE SE I CAMPI DI TESTO RISPETTANO 
		if(! txtIndirizzo.getText().trim().matches("^[A-Za-z\\s.]+,\\s\\d{1,4}[A-Za-z]?,\\s\\d{5}\\s[A-Za-z\\s]+\\s\\([A-Za-z]{2}\\)$")) {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma il formato dell'inidirzzo non è corretto!");
			return false;
		}
		if(Double.valueOf(txtQuantitàRaccolto.getText().trim()) < 0) {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma la quantità raccolta non può essere minore di 0");
			return false;
		}
		return true;
	}
}
