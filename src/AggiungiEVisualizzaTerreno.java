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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AggiungiEVisualizzaTerreno extends JFrame {
	private Controller theController;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelTerreno;
	private JTextField txtSuperfice;
	private JComboBox<TipoTerreno> comboBoxTipoTerreno;
	private JComboBox<Fertilità> comboBoxFertilità;
	private int count;
	
	//ATTRIBUTI:
	private String idTerrenoSelezioanto;
	private JTextField txtIndirizzo;
	private JTextField txtDeposito;
	
	
	public AggiungiEVisualizzaTerreno(Controller c, Utente u) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				theController.paginaProprietario.setEnabled(true);
			}
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					theController.popolaTabellaTerreni(u.getUsername(), modelTerreno);
				}catch(Exception xxx) {
					JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella dei terreni: "+ xxx);
				}

				theController.paginaProprietario.setEnabled(false);
			}
		});
		theController = c;
		
			
		setTitle("Registra e visualizza terreni");
		setSize(850, 430);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Qui potrai visualizzare e aggiungere terreni");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblAggiungiTerreno = new JLabel("Aggiungi terreno");
		lblAggiungiTerreno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblVisualizzaTerreni = new JLabel("Visualizza terreni");
		lblVisualizzaTerreni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panelTable = new JPanel();
		
		JLabel lblSuperfice = new JLabel("Superficie (m²)");
		lblSuperfice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtSuperfice = new JTextField();
		txtSuperfice.setColumns(10);
		
		JLabel lblTipoTerreno = new JLabel("Tipo terreno");
		lblTipoTerreno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxTipoTerreno = new JComboBox(TipoTerreno.values());
		
		JLabel lblFertilità = new JLabel("Fertilità");
		lblFertilità.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxFertilità = new JComboBox(Fertilità.values());
		
		JButton btnAggiungiTerreno = new JButton("Aggiungi");
		btnAggiungiTerreno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(ctrlFieldSuperfice()){
					try {
						//VARI CAST:
						double supConv = Double.valueOf(txtSuperfice.getText().trim());
						TipoTerreno tipoTerrString = (TipoTerreno) comboBoxTipoTerreno.getSelectedItem();
						Fertilità fert = (Fertilità) comboBoxFertilità.getSelectedItem();
						
						//AGGIUNGI UN TERRENO:
						theController.aggiungiTerreno(u.getUsername(), supConv, tipoTerrString, fert, Integer.valueOf(txtDeposito.getText().trim()), txtIndirizzo.getText().trim());
						
						//AGGIORNA LA TABELLA:
						theController.popolaTabellaTerreni(u.getUsername(), modelTerreno);
						
						//PULISCI I CAMPI:
						clearTextField();
					}catch(Exception x) {
						JOptionPane.showMessageDialog(null, "Errore nel cast delle variabili per aggiungere un nuovo terreno");
					}
				}
			}
		});
		
		JButton btnVisualizzaTerreno = new JButton("Visualizza terreno");
		btnVisualizzaTerreno.setEnabled(false);
		btnVisualizzaTerreno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TERRENO SELEZIONATO:
				theController.daPaginaAggiungiEVisualizzaTerrenoAVisualizzaTerrenoSpecifico(idTerrenoSelezioanto);
			}
		});
		
		JLabel lblTerrenoSelezionato = new JLabel("L'id del terreno selezioanto è");
		lblTerrenoSelezionato.setVisible(false);
		lblTerrenoSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblTerrenoId = new JLabel("");
		lblTerrenoId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setToolTipText("Il formato dell'indirizzo deve essere: \"Via Garibaldi, 25, 00100 Roma (RM)\"");
		txtIndirizzo.setColumns(10);
		
		JLabel lblDeposito = new JLabel("Deposito");
		lblDeposito.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtDeposito = new JTextField();
		txtDeposito.setToolTipText("Fai click due volte con il mouse per fare apparire la tabella con i depositi");
		txtDeposito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				count++;
				if(count == 2) {
					//VAI ALLA PAGINA PER VISUALIZZARE I TUOI DEPOSITI:
					theController.vaiAMostraDepositiDisponibiliNellaFinestra(u.getUsername().trim(), txtDeposito);
					count = 0;
				}
			}
		});
		txtDeposito.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(45)
					.addComponent(lblAggiungiTerreno)
					.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
					.addComponent(lblVisualizzaTerreni)
					.addGap(139))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblSuperfice)
							.addGap(18)
							.addComponent(txtSuperfice, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblTipoTerreno, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(comboBoxTipoTerreno, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDeposito, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBoxFertilità, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDeposito, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))))
					.addGap(53)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblTerrenoSelezionato, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTerrenoId, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(44)
					.addComponent(btnAggiungiTerreno)
					.addPreferredGap(ComponentPlacement.RELATED, 369, Short.MAX_VALUE)
					.addComponent(btnVisualizzaTerreno)
					.addGap(221))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAggiungiTerreno)
						.addComponent(lblVisualizzaTerreni, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSuperfice, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSuperfice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGap(12)
									.addComponent(lblTipoTerreno, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGap(11)
									.addComponent(comboBoxTipoTerreno, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGap(12)
									.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGap(11)
									.addComponent(comboBoxFertilità, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDeposito, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDeposito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(6)
							.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTerrenoSelezionato)
								.addComponent(lblTerrenoId, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
					.addGap(13)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAggiungiTerreno)
						.addComponent(btnVisualizzaTerreno))
					.addGap(50))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		modelTerreno = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "id Terreno", "Indirizzo", "Superficie (m²)", "Tipo terreno", "Fertilità"}
			);
		
		table = new JTable(modelTerreno);
		//SERVE A NASCONDERE LA COLONNA DELL'ID:
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TERRENO SELEZIONATO:
				int row = table.rowAtPoint(e.getPoint());			
				if(row >= 0) {
					try {
						idTerrenoSelezioanto = String.valueOf(table.getValueAt(row, 0)) ;
						lblTerrenoSelezionato.setVisible(true);
						
						lblTerrenoId.setText(idTerrenoSelezioanto);
						lblTerrenoId.setForeground(Color.BLUE);
						
						btnVisualizzaTerreno.setEnabled(true);
					}catch(Exception es) {
						JOptionPane.showMessageDialog(null, "oooooo");
					}
				}
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
				setVisible(false);
				btnVisualizzaTerreno.setEnabled(false);
				theController.paginaProprietario.setVisible(true);
				theController.paginaProprietario.setEnabled(true);
				
				//PULISCI I CAMPI:
				clearTextField();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addGap(256)
					.addComponent(btnBack)
					.addContainerGap(264, Short.MAX_VALUE))
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(btnBack)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBottom.setLayout(gl_panelBottom);
		
	}
	
//METODI:
	//CONTROLLO VARIABILI PER AGGIUNGERE UN TERRENO:
	private boolean ctrlFieldSuperfice() {
		//CONTROLLO DEL CAMPO DI TESTO DELLA SUPERFICE:
		if(txtSuperfice.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Il campo superfice non può essere vuoto!");
			return false;
		}else {
			double supConv = Double.valueOf(txtSuperfice.getText().trim());
			if(supConv <= 0) {
				JOptionPane.showMessageDialog(null, "Il valore della superfice non può essere minore o uguale a zero");
				return false;
			}
		}
		//CONTROLLO DELL'INDIRIZZO:
		if(txtIndirizzo.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo di testo indirizzo non può essere vuoto!");
			return false;
		}else {
			if(! txtIndirizzo.getText().trim().matches("^[A-Za-zÀ-ÿ0-9'\\s\\.]+,\\s(\\d{1,4}[A-Za-z]?|[Ss][Nn][Cc]|[Kk][Mm]\\s?\\d+),\\s\\d{5}\\s[A-Za-zÀ-ÿ'\\s]+\\s\\([A-Z]{2}\\)$")) {
				JOptionPane.showMessageDialog(null, "Errore il formato dell'indirizzo è errato!");
				return false;
			}
		}
		return true;
	}
	
	//PULISCI I CAMPI:
	public void clearTextField() {
		txtSuperfice.setText(null);
		txtIndirizzo.setText(null);
		txtDeposito.setText(null);
	}
}
