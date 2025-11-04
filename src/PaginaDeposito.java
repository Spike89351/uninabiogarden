import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PaginaDeposito extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	
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
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
					.addGap(58)
					.addComponent(lblAggiungiDeposito)
					.addPreferredGap(ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
					.addComponent(lblElencoDepositi)
					.addGap(98))
				.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
					.addContainerGap(282, Short.MAX_VALUE)
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel paneBottom = new JPanel();
		contentPane.add(paneBottom, BorderLayout.SOUTH);

	}
}
