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

public class PaginaDettagliDeposito extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	
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
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelTable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
						.addComponent(lblModificaDati, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(19)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblModificaDati)
					.addContainerGap(96, Short.MAX_VALUE))
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
}
