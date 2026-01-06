import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FinestraInserisciIndirizzoColtivatore extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnCompleta;
	private JButton btnBack;
	private JPanel panelTop;
	private JPanel PanelCentral;
	private JLabel lblWelcome;
	private JLabel lblNewLabel;
	private JTextField txtIndirizzo;
	
	public FinestraInserisciIndirizzoColtivatore(Utente u, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				btnBack.doClick();
			}
		});
		theController = c;
		
		setTitle("Finestra inserisci indirizzo del coltivatore");
		setSize(450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				lblWelcome = new JLabel("Inserisci l'indirizzo di dove abiti");
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
		}
		{
			PanelCentral = new JPanel();
			contentPanel.add(PanelCentral, BorderLayout.CENTER);
			{
				lblNewLabel = new JLabel("Indirizzo");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			
			txtIndirizzo = new JTextField();
			txtIndirizzo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnCompleta.doClick();
					}
				}
			});
			txtIndirizzo.setToolTipText("Il formato dell'indirizzo deve essere: \"Via Garibaldi, 25, 00100 Roma (RM)\"");
			txtIndirizzo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtIndirizzo.setColumns(10);
			GroupLayout gl_panelCentral = new GroupLayout(PanelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
						.addGap(98)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
						.addGap(70))
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGap(90)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(99, Short.MAX_VALUE))
			);
			PanelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel panelBottom = new JPanel();
			getContentPane().add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new BorderLayout(0, 0));
			{
				btnCompleta = new JButton("Completa");
				btnCompleta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//COMPLETA REGISTRAZIONE:
						if(ctrlField()) {
							//FACCIAMO CONFERMARE ALL'UTENTE LA REGISTRAZIONE COSI' DA ESSERE SICURI CHE NON ABBIA SBAGLIATO A CLICCARE:
							int risposta = JOptionPane.showConfirmDialog(
						            null, // Nessuna finestra padre (verrà mostrato al centro dello schermo)
						            "Sei sicuro di voler registrati come coltivatore?",
						            "Conferma",
						            JOptionPane.YES_NO_OPTION
						        );
							
							if(risposta == JOptionPane.YES_OPTION) {
								//CHIAMATA DELLA FUNZIONE PER CREARE IL COLTIVATORE:
								theController.inserisciColtivatore(u, txtIndirizzo.getText().trim());
								JOptionPane.showMessageDialog(null, "La registrazione è avvenuta con successo!");
								
								//TORNA ALLA HOME PAGE:
								setVisible(false);
								theController.homePage.setVisible(true);
							}
						}
					}
				});
				btnCompleta.setActionCommand("OK");
				panelBottom.add(btnCompleta, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnCompleta);
			}
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TORNA INDIETRO:
						setVisible(false);
						theController.paginaRegistrati.setVisible(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}
//METODI:
	private boolean ctrlField() {
		if(txtIndirizzo.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo 'indirizzo' non può essere vuoto");
			return false;
		}else {
			if(! txtIndirizzo.getText().matches("^[A-Za-zÀ-ÿ0-9'\\s\\.]+,\\s(\\d{1,4}[A-Za-z]?|[Ss][Nn][Cc]|[Kk][Mm]\\s?\\d+),\\s\\d{5}\\s[A-Za-zÀ-ÿ'\\s]+\\s\\([A-Z]{2}\\)$")) {
				JOptionPane.showMessageDialog(null, "Errore il formato dell'indirizzo è errato!");
				return false;
			}
		}
		return true;
	}
}
