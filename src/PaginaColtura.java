import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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
	
	public PaginaColtura(int idDep, Controller c) {
		theController = c;
		
		setTitle("Pagina coltura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(590, 325);
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
		
		JLabel lblTabella = new JLabel("Elenco colture disponibili");
		lblTabella.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblAggiungiColtura = new JLabel("Aggiungi coltura");
		lblAggiungiColtura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAggiungiColtura.setHorizontalAlignment(SwingConstants.CENTER);
		
		String[] elencoDisp = {"", "Non disponibile"};
		
		comboBoxDisponibile = new JComboBox();
		
		String[] elencoStagioni = {"", "Estivo", "Invernale", "Autunnale", "Primaverile"};
		
		comboBoxStagione = new JComboBox(elencoStagioni);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
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
							.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
							.addComponent(comboBoxDisponibile, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
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
					.addContainerGap(57, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model  = new DefaultTableModel(
				new Object[][]{},
				new String[]{"Nome", "Colore", "Tipo", "Stagione", "Disponibile"}
			);
		
		table = new JTable(model);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		panelBottom.add(btnRimuovi, BorderLayout.EAST);

	}
}
