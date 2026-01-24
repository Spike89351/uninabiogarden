import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PaginaRegistraDeposito extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDimensione;
	private JTextField txtIndirizzo;

	
	public PaginaRegistraDeposito(Utente u, String email, String pIva, Controller c) {
		theController = c;
		
		setTitle("Registra un deposito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Registra il tuo deposito");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDimensione = new JLabel("Dimensione");
		lblDimensione.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtDimensione = new JTextField();
		txtDimensione.setToolTipText("dimensioni in m²");
		txtDimensione.setHorizontalAlignment(SwingConstants.CENTER);
		txtDimensione.setColumns(10);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setToolTipText("Il formato dell'indirizzo deve essere: \"Via Garibaldi, 25, 00100 Roma (RM)\"");
		txtIndirizzo.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(129)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDimensione)
						.addComponent(lblIndirizzo))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtIndirizzo)
						.addComponent(txtDimensione, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addGap(94))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIndirizzo)
						.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDimensione, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDimensione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(124, Short.MAX_VALUE))
		);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNA INDIETRO:
				setVisible(false);
				theController.paginaRegistrati.setVisible(true);
			}
		});
		panelBottom.add(btnBack, BorderLayout.WEST);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VAI AVANTI:
				if(ctrlText()) {
					Deposito dep = new Deposito(txtIndirizzo.getText().trim(), Double.valueOf(txtDimensione.getText().trim()), -1);
					theController.daPaginaRegistraDepositoARegistraTerreno(u, email, pIva, dep);
				}
			}
		});
		panelBottom.add(btnAvanti, BorderLayout.EAST);

	}
	
//MEOTODI:
	private boolean ctrlText() {
		//CONTROLLO SE L'INDIRIZZO E' DI UN CERTO FORMATO:
		if(txtIndirizzo.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo di testo indirizzo non può essere vuoto!");
			return false;
		}else {
			if(! txtIndirizzo.getText().matches("^[A-Za-zÀ-ÿ'\\s]+,\\s(\\d{1,4}[A-Za-z]?|[Ss][Nn][Cc]),\\s\\d{5}\\s[A-Za-zÀ-ÿ'\\s]+\\s\\([A-Z]{2}\\)$")) {
				JOptionPane.showMessageDialog(null, "Errore il formato dell'indirizzo è errato!");
				return false;
			}
		}
		if(Double.valueOf(txtDimensione.getText()) <= 0) {
			JOptionPane.showMessageDialog(null, "Errore, la dimensione del deposito non può essere minore o uguale a 0!");
			return false;
		}
		return true;
	}
}
