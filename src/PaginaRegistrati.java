import java.awt.EventQueue;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.time.Period;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaginaRegistrati extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	public PaginaRegistrati(Controller c) {
		theController = c;
		
		setTitle("Registrati");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Compila i seguenti dati per registrarti");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setHorizontalAlignment(SwingConstants.CENTER);
		lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDataDiNascita = new JLabel("Data di nascita");
		lblDataDiNascita.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDiNascita.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblGenere = new JLabel("Genere");
		lblGenere.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenere.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		txtCognome = new JTextField();
		txtCognome.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
				
		JComboBox<Genere> comboBoxGenere = new JComboBox(Genere.values());
		
		JDateChooser dateChooser = new JDateChooser();
		
		JButton btnColtivatore = new JButton("Coltivatore");
		//btnColtivatore.setEnabled(false);
		btnColtivatore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(control(txtNome, txtCognome, dateChooser, txtUsername, txtPassword)) {	
					//METODO CHE CREA UN UTENTE + IL PASSAGGIO DELL'UTENTE ALLA PAGINA SUCCESSIVA;
				}
				//CLEAR CAMPI
			}
		});
		
		JButton btnProprietario = new JButton("Proprietario");
		//btnProprietario.setEnabled(false);
		btnProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(control(txtNome, txtCognome, dateChooser, txtUsername, txtPassword)) {
					//METODO CHE CREA UN UTENTE + IL PASSAGGIO DELL'UTENTE ALLA PAGINA SUCCESSIVA;
					Utente u = new Utente(txtNome.getText(), txtCognome.getText(), dateChooser.getDate(), comboBoxGenere.getPrototypeDisplayValue(), txtUsername.getText(), txtPassword.getText());
					theController.daPaginaRegistratiAProprietario(u);
				}
				//CLEAR CAMPI
			}
		});
		
		JLabel lblNewLabel = new JLabel("Come ti identifichi:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtPassword = new JPasswordField();
		
		JCheckBox checkBoxMostraPassword = new JCheckBox("Mostra password");
		checkBoxMostraPassword.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
                txtPassword.setEchoChar((char) 0);
                checkBoxMostraPassword.setText("Nascondi password");
            } else {
                txtPassword.setEchoChar('*');
                checkBoxMostraPassword.setText("Mostra password");
            }
		});
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(101)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addComponent(btnProprietario)
								.addGap(44)
								.addComponent(btnColtivatore)))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDataDiNascita, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCognome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblGenere, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(33)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxGenere, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUsername)
								.addComponent(txtCognome)
								.addComponent(txtNome)
								.addComponent(txtPassword))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(checkBoxMostraPassword)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataDiNascita, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGenere, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxGenere, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBoxMostraPassword)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnColtivatore)
						.addComponent(btnProprietario))
					.addGap(38))
		);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNO NELLA PAGINA PRECEDENTE, CIOE' L'HOME;
			}
		});
		panelBottom.add(btnBack);

	}
	
	
	
//METODI:
	public boolean control(JTextField nome, JTextField cognome, JDateChooser data, JTextField username, JTextField password) {
		 
		LocalDate oggi = LocalDate.now();
		 Date dataConv = data.getDate(); 
		 LocalDate dataDiNascita = new java.sql.Date(dataConv.getDate()).toLocalDate();
		 Period periodo = Period.between(dataDiNascita, oggi);
		 
		 
		 //DEVE ESSERCI ALMENO UN CARATTERE SPECIALE NELLA PASSWORD:
		 String regex = "[@#$%^&*()!]";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher((CharSequence) password.getText());
		 
		if(nome.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Il campo nome non può essere vuoto!");
			return false;
		}
		if(cognome.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Il campo cognome non può essere vuoto!");
			return false;
		}
		if(data.getDate() == null) {
			JOptionPane.showMessageDialog(null, "La data non può essere nullo");
			return false;
		}else if(periodo.getYears() < 18) {
			JOptionPane.showMessageDialog(null, "L'utente per registrarsi non può avere meno di 18 anni!");
			return false;
		}
		if(username.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Il campo Username non può essere vuoto!");
			return false;
		}else if(username.getText().length() < 5) {
			JOptionPane.showMessageDialog(null, "L'username non può avere meno di 5 caratteri!");
			return false;
		}
		if(password.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Il campo Password non può essere vuoto!");
			return false;
		}else if( password.getText().length() < 8|| ! (matcher.find())) {
			JOptionPane.showMessageDialog(null, "Il campo passowrd deve essere lunga almeno 8 caratteri e deve contenere almeno un carattere speciale: !@#$%^&*()\\-_=+{}[\\]:;\"''<>?,./ ");
			return false;
		}
		return true;
	}
}
