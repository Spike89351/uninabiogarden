import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PaginaColtivatore extends JFrame {
	private Controller theController;
	
	private int idColtivatore;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private JTable table;
	private DefaultTableModel model;
	
	public PaginaColtivatore(String username, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				idColtivatore = theController.trovaIdColtirvatore(username);
				if(idColtivatore == -1) {
					JOptionPane.showMessageDialog(null, "L'id non è stato trovato, ERRORE!");
				}else {
					theController.popolaTabellaDelleAttivitàConIdColtivatore(idColtivatore, model);
				}
			}
		});
		
		theController = c;
		
		setTitle("Pagina del coltivatore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(530, 308);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Questa è la tua pagina");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelTable = new JPanel();
		
		JLabel lblElencoAttività = new JLabel("Elenco attività");
		lblElencoAttività.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		String[] elencoAttività = {};
		
		comboBox = new JComboBox(elencoAttività);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap(231, Short.MAX_VALUE)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
							.addComponent(lblElencoAttività)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelTable, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblElencoAttività))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model  = new DefaultTableModel(
				new Object[][]{},
				new String[]{"Id attività", "Tipo", "Data inizio", "Data fine"}
			);
		
		table = new JTable(model);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FAI IL LOGOUT:
				setVisible(false);
				theController.homePage.setVisible(true);
			}
		});
		panelBottom.add(btnLogout, BorderLayout.WEST);

	}
}
