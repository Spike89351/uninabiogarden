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
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PaginaVisualizzaDettagliProgetto extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel colonneName;
	
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
		
		JLabel lblNewLabel = new JLabel("Dettagli del progetto selezionato");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panelTable = new JPanel();
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		colonneName = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "Id progetto", "Nome", "Data inizio", "Data fine", "Stato progetto", "Id terreno", "Superfice", "Tipo terreno", "Fertilità"}
			);;
		
		table = new JTable(colonneName);
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
			}
		});
		btnBack.setToolTipText("Premi per tornare indietro");
		panelBottom.add(btnBack);
		
		//POPOLA LA TABELLA CON LA TUPLA:
		try {
			theController.inserisciINTabellaLaTuplaDaVisualizzare(idProgetto, colonneName);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errore nell'inserimento della tupla nella tabella");
		}

	}
}
