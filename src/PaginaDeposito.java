import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaginaDeposito extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtDimensione;
	private JTextField txtIndirizzo;
	private JButton btnBack;
	private JButton btnVisualizzaDettagli;
	private DefaultTableModel model;

	private int idDepositoSelezionato;
	
	public PaginaDeposito(Controller c, int idProprietario) {
		theController = c;
		
		setTitle("Dettagli Depositi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 319);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Qui puoi vedere tutti i tuoi depositi");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelTable = new JPanel();
		
		JLabel lblElencoDepositi = new JLabel("Elenco dei depositi");
		lblElencoDepositi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblAggiungiDeposito = new JLabel("Aggiungi un deposito");
		lblAggiungiDeposito.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDimensione = new JLabel("Dimensione");
		lblDimensione.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtDimensione = new JTextField();
		txtDimensione.setColumns(10);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setToolTipText("Il formato dell'indirizzo deve essere: \"Via Garibaldi, 25, 00100 Roma (RM)\"");
		txtIndirizzo.setColumns(10);
		
		JButton btnAggiungiDeposito = new JButton("Crea Deposito");
		btnAggiungiDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrlText()) {
					//CREAZIONE DI UN DEPOSITO:
					theController.creaDeposito(idProprietario, txtIndirizzo.getText(), Double.valueOf(txtDimensione.getText()));
					
					//AGGIORNAMENTO DELLA TABELLA DEI DEPOSITI:
					theController.popolaTabellaDepositi(idProprietario, model);
					
					//PULIZIA DEI CAMPI:
					clearFields();
				}
			}
		});
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(58)
					.addComponent(lblAggiungiDeposito)
					.addPreferredGap(ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
					.addComponent(lblElencoDepositi)
					.addGap(98))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIndirizzo)
								.addComponent(lblDimensione))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDimensione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(45)
							.addComponent(btnAggiungiDeposito)))
					.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblElencoDepositi)
						.addComponent(lblAggiungiDeposito, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIndirizzo)
								.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDimensione, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDimensione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnAggiungiDeposito)))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model  = new DefaultTableModel(
				new Object[][]{},
				new String[]{"Id deposito", "Indirizzo", "Dimensione"}
			);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.rowAtPoint(e.getPoint());
				if(selectedRow != -1) {
					try {
						//PRESA DEL DATO DALLA TABELLA:
						String idDepositoString = String.valueOf(table.getValueAt(selectedRow, 0));
						//CAST DEL DATO DA STRING A INT:
						idDepositoSelezionato = Integer.valueOf(idDepositoString);
					}catch(ClassCastException x) {
						JOptionPane.showMessageDialog(scrollPane, "Errore nel cast!");
					}
					//RENDO DISPONIBILE IL PULSANTE PER VISUALIZZARE I DETTAGLI:
					btnVisualizzaDettagli.setEnabled(true);
				}
			}
		});
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel paneBottom = new JPanel();
		contentPane.add(paneBottom, BorderLayout.SOUTH);
		paneBottom.setLayout(new BorderLayout(0, 0));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNA INDIETRO:
				setVisible(false);
				theController.paginaProprietario.setVisible(true);
				
				//PULISCI I CAMPI:
				clearFields();
				
				//DISATTIVO IL PULSANTE PER I DETTAGLI:
				btnVisualizzaDettagli.setEnabled(false);
			}
		});
		paneBottom.add(btnBack, BorderLayout.WEST);
		
		btnVisualizzaDettagli = new JButton("Dettagli");
		btnVisualizzaDettagli.setEnabled(false);
		btnVisualizzaDettagli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PASSO L'ID DEL DEPOSITO SELEZIONATO E VADO IN UN'ALTRA PAGINA:
								
				
				//PULISCI I CAMPI:
				clearFields();
			}
		});
		paneBottom.add(btnVisualizzaDettagli, BorderLayout.EAST);
		
		//POPOLA TABELLA CON I DEPOSITI:
		try {
			theController.popolaTabellaDepositi(idProprietario, model);
		}catch(Exception x) {
			JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella dei depositi!");
		}
		
	}

//METODI:
	//PULISICI I CAMPI DI TESTO:
	private void clearFields() {
		txtIndirizzo.setText(null);
		txtDimensione.setText(null);
	}
	
	private boolean ctrlText() {
		//CONTROLLO SE L'INDIRIZZO E' DI UN CERTO FORMATO:
		if(txtIndirizzo.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo di testo indirizzo non può essere vuoto!");
			return false;
		}else {
			if(! txtIndirizzo.getText().trim().matches("^[A-Za-z\\s]+,\\s\\d{1,4}[A-Za-z]?,\\s\\d{5}\\s[A-Za-z\\s]+\\s\\([A-Z]{2}\\)$")) {
				JOptionPane.showMessageDialog(null, "Errore il formato dell'indirizzo è errato!");
				return false;
			}
		}
		if(Double.valueOf(txtDimensione.getText()) <= 0) {
			JOptionPane.showMessageDialog(null, "Errore, la dimensione del deposito non può essere minore o uguale a 0!");
			return false;
		}
		return true;
	}
}
