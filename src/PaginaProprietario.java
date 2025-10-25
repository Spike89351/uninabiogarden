import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaginaProprietario extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel elencoAttributiPrg;

	public PaginaProprietario(String username, Controller c) {
		theController = c;
		
		setTitle("La tua pagina - Proprietario ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 326);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menùTerreni = new JMenu("Terreni");
		menuBar.add(menùTerreni);
		
		JMenuItem menùItemAggiungiTerreni = new JMenuItem("Aggiungi e visualizza terreni");
		menùItemAggiungiTerreni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PAGINA DOVE POTER AGGIUNGERE UN TERRENO:
				Utente u = theController.prendiDatiUtente(username);
				theController.daPaginaProprietarioAFinestraTerreni(u);
			}
		});
		menùTerreni.add(menùItemAggiungiTerreni);
		
		JMenu menùDatiUtente = new JMenu("Dati utente");
		menuBar.add(menùDatiUtente);
		
		JMenuItem menuItemisualizzaDati = new JMenuItem("Visualizza e modifica dati utente");
		menuItemisualizzaDati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PRENDO I DATI DELL'UTENTE E LI PORTO NELL'ALTRA FINESTRA COSS' DA POTER INSERIRLI NEI CAMPI DI TESTO
				theController.daPaginaProprietarioAFinestraDatiUtente(theController.prendiDatiUtente(username));
			}
		});
		menùDatiUtente.add(menuItemisualizzaDati);
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
		
		JLabel lblElencoProgetti = new JLabel("Elenco progetti");
		lblElencoProgetti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblAggiungiProgetto = new JLabel("Aggiungi progetto");
		lblAggiungiProgetto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel = new JPanel();
		
		JButton btnVisualizzaProgetto = new JButton("Visualizza progetto");
		btnVisualizzaProgetto.setEnabled(false);
		btnVisualizzaProgetto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VISUALIZZA PROGETTO:
				
			}
		});
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addComponent(lblAggiungiProgetto)
								.addPreferredGap(ComponentPlacement.RELATED, 405, Short.MAX_VALUE)
								.addComponent(lblElencoProgetti)
								.addGap(152))
							.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
							.addComponent(btnVisualizzaProgetto)
							.addGap(147))))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAggiungiProgetto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblElencoProgetti))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVisualizzaProgetto)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		DefaultTableModel elencoAttributiPrg  = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "id progetto", "Nome progetto", "id terreno", "data inizio"}
			);;
		
		table = new JTable(elencoAttributiPrg);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// una volta cliccato su un progetto si sblocca il tasto visualizza progetto:
				btnVisualizzaProgetto.setEnabled(true);
				
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
				//LOGOUT
				
			}
		});
		panelBottom.add(btnBack);

	}
}
