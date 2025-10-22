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

public class PaginaProprietario extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PaginaProprietario(String username, Controller c) {
		theController = c;
		
		setTitle("La tua pagina - Proprietario ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuProgetti = new JMenu("Progetti");
		menuBar.add(menuProgetti);
		
		JMenuItem menuItemVisualizzaProgetti = new JMenuItem("Visualizza progetti");
		menuItemVisualizzaProgetti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuProgetti.add(menuItemVisualizzaProgetti);
		
		JMenu menùTerreni = new JMenu("Terreni");
		menùTerreni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PAGINA CHE FA VISUALIZZARE TUTTI I TERRENI:
			}
		});
		menuBar.add(menùTerreni);
		
		JMenuItem menùItemAggiungiTerreni = new JMenuItem("Aggiungi terreno");
		menùItemAggiungiTerreni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PAGINA DOVE POTER AGGIUNGERE UN TERRENO:
			}
		});
		menùTerreni.add(menùItemAggiungiTerreni);
		
		JMenuItem menuItemVisualizzaTerreni = new JMenuItem("Visualizza terreni");
		menùTerreni.add(menuItemVisualizzaTerreni);
		
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
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);

	}
	
//METODI:

}
