import java.awt.EventQueue;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	public HomePage(Controller c) {
		theController = c;
		
		setTitle("Accedi");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Accedi con le tue credenziali al web ");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.homePage.setVisible(false);
				//PAGINA ACCESSO:
			}
		});
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.homePage.setVisible(false);
				theController.paginaRegistrati.setVisible(true);
			}
		});
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(107)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(btnRegistrati)
							.addGap(18)
							.addComponent(btnAccedi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblUsername)
							.addGap(18)
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAccedi)
						.addComponent(btnRegistrati))
					.addGap(69))
		);
		panelCentral.setLayout(gl_panelCentral);

	}
}
