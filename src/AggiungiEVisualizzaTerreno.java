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
	
	
	//ATTRIBUTI:
	private String idTerrenoSelezioanto;
	
	
	public AggiungiEVisualizzaTerreno(Controller c, Utente u) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				theController.paginaProprietario.setEnabled(true);
			}
			@Override
			public void windowActivated(WindowEvent e) {
				theController.paginaProprietario.setEnabled(false);
			}
		});
		theController = c;
		
			
		setTitle("Registra e visualizza terreni");
		setSize(597, 350);
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
		
		JLabel lblSuperfice = new JLabel("Superfice");
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
						theController.aggiungiTerreno(u.getUsername(), supConv, tipoTerrString, fert);
						
						//AGGIORNA LA TABELLA:
						theController.popolaTabellaTerreni(u.getUsername(), modelTerreno);
						
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
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(45)
					.addComponent(lblAggiungiTerreno)
					.addPreferredGap(ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
					.addComponent(lblVisualizzaTerreni)
					.addGap(139))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblSuperfice, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
									.addGap(46)
									.addComponent(txtSuperfice, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblTipoTerreno, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(comboBoxTipoTerreno, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(comboBoxFertilità, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(46)
							.addComponent(btnAggiungiTerreno)))
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblTerrenoSelezionato)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTerrenoId, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap(381, Short.MAX_VALUE)
					.addComponent(btnVisualizzaTerreno)
					.addGap(109))
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
							.addGap(17)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGap(1)
									.addComponent(lblSuperfice, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtSuperfice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGap(1)
									.addComponent(lblTipoTerreno, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBoxTipoTerreno, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentral.createSequentialGroup()
									.addGap(1)
									.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBoxFertilità, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAggiungiTerreno))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTerrenoSelezionato)
								.addComponent(lblTerrenoId, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
					.addGap(13)
					.addComponent(btnVisualizzaTerreno)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		modelTerreno = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "id Terreno", "Superfice", "Tipo terreno", "Fertilità"}
			);;
		
		table = new JTable(modelTerreno);
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
				theController.paginaProprietario.setEnabled(true);
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
		
		try {
			theController.popolaTabellaTerreni(u.getUsername(), modelTerreno);
		}catch(Exception xxx) {
			JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella dei terreni: "+ xxx);
		}

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
		return true;
	}
}
