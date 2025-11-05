import java.awt.EventQueue;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaginaRegistraProprietario extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPartitaIva;

	
	public PaginaRegistraProprietario(Utente u, Controller c) {
		theController = c;
		
		setTitle("Pagina per la registrazione del proprietario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 270);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Completa i campi per completare la registrazione");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblPartitaIva = new JLabel("Partita iva");
		lblPartitaIva.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartitaIva.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		txtPartitaIva = new JTextField();
		txtPartitaIva.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(138)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPartitaIva, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtPartitaIva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPartitaIva, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPartitaIva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton lblContinua = new JButton("Avanti");
		lblContinua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrlString(txtEmail.getText(), txtPartitaIva.getText())) {
//					theController.daPaginaRegistraProprietarioATerreno(u, txtEmail.getText().trim(), txtPartitaIva.getText().trim());
					theController.daPaginaRegistraProprietarioAPaginaRegistraDeposito(u, txtEmail.getText().trim(), txtPartitaIva.getText().trim());
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.daPaginaProprietarioARegistraUtente(u);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addComponent(btnBack)
					.addPreferredGap(ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
					.addComponent(lblContinua))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContinua)
						.addComponent(btnBack)))
		);
		panel.setLayout(gl_panel);

	}
	
	
//METODI:
	
	public boolean  ctrlString(String email, String partitaIva) {
		//CONTROLLO PER L'EMAIL:
		if(email.isBlank()) {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma il campo Eamil non può essere vuoto!");
			return false;
		}else if(! email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
			JOptionPane.showMessageDialog(null, "Mi dispiace ma il formato dell'email non è valido, RIPROVA!");
			return false;
		}
		
		//CONTROLLO PER LA PARTITA IVA:
		if(partitaIva.isBlank()) {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma il campo partita iva non può essere vuoto!");
			return false;
		}else if( partitaIva.length() != 11) {
			JOptionPane.showMessageDialog(null, "Il formato della partita iva è errato!");
			return false;
		}
		return true;
	}
	
	
}
