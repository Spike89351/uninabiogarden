import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaginaSceltaColtivatore extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnBack;
	private JButton btnAssocia;
	private int idColtivatoreSelezionato;
	
	public PaginaSceltaColtivatore(int idAttività, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//POPOLA TABELLA:
				try {
					theController.popolaTabellaConColtivatori(model);
				}catch(Exception x) {
					JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella con tutti i coltivatori");
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				theController.paginaAttività.setVisible(true);
			}
		});
		theController = c;
		
		setTitle("Pagina scelta coltivatore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Qui ci sono tutti i coltivatori");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelCentral.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		model  = new DefaultTableModel(
				new Object[][]{},
				new String[]{"id coltivatore", "Nome", "Cognome", "Data di Nascita"}
			);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.rowAtPoint(e.getPoint());
				if(selectedRow != -1) {
					String idColtivatoreStirng = String.valueOf(table.getValueAt(selectedRow, 0));
					idColtivatoreSelezionato = Integer.valueOf(idColtivatoreStirng);
					btnAssocia.setEnabled(true);
				}
			}
		});
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNA INDIETRO:
				setVisible(false);
				theController.paginaAttività.setVisible(true);
			}
		});
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		btnAssocia = new JButton("Associa");
		btnAssocia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//METODO CHE PRENDE COME INPUT L'ID DELL'ATTIVITA' E COLTIVATORE E LI ASSOCIA + POI METTE IL BOOELAN FALSE DEL COLTIVATORE:
				if(theController.associaAttivitàAColtivatore(idAttività, idColtivatoreSelezionato)) {
					theController.popolaTabellaConColtivatori(model);
					//INVIA NOTIFICA:
					theController.iniviaNotificaPrezaServizio(idColtivatoreSelezionato);
					JOptionPane.showMessageDialog(null, "Complimenti, hai associato perfettamente il coltivatore all'attività!");
					btnAssocia.setEnabled(false);
				}else {
					JOptionPane.showMessageDialog(null, "ERRORE, non hai associato il coltivatore all'attività!");
				}
			}
		});
		btnAssocia.setEnabled(false);
		panelBottom.add(btnAssocia, BorderLayout.EAST);

	}

}
