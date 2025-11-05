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

//import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

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
	private Genere gen;

	public PaginaRegistrati(Controller c) {
		theController = c;
		
		setTitle("Registrati");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(425, 412);
		setLocationRelativeTo(null);
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
		txtUsername.setToolTipText("L'username deve essere lungo almeno 5 caratteri");
		txtUsername.setColumns(10);
				
		JComboBox<Genere> comboBoxGenere = new JComboBox(Genere.values());
		
		JDateChooser dateChooser = new JDateChooser();
		
		JButton btnColtivatore = new JButton("Coltivatore");
		//btnColtivatore.setEnabled(false);
		btnColtivatore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(control(txtNome, txtCognome, dateChooser, txtUsername, txtPassword)) {	
					try{
						//FACCIO IL CAST DELLA DATA:
						java.sql.Date data = new java.sql.Date(dateChooser.getDate().getTime());
						
						//CASTO ANCHE LA VIARIABILE DI TIPO GENERE:
						gen = (Genere) comboBoxGenere.getSelectedItem();
						
						//INSERISCO I DATI IN UNA VARIABILE DI TIPO UTENTE PER POI PASSARLA ALLA PAGGINA SUCCESSIVA:
						Utente u = new Utente(txtNome.getText(), txtCognome.getText(), data, gen, txtUsername.getText(), txtPassword.getText());
						
						//FACCIAMO CONFERMARE ALL'UTENTE LA REGISTRAZIONE COSI' DA ESSERE SICURI CHE NON ABBIA SBAGLIATO A CLICCARE:
						int risposta = JOptionPane.showConfirmDialog(
					            null, // Nessuna finestra padre (verrà mostrato al centro dello schermo)
					            "Sei sicuro di voler continuare?",
					            "Conferma",
					            JOptionPane.YES_NO_OPTION
					        );
						
						if(risposta == JOptionPane.YES_OPTION) {
							//CHIAMATA DELLA FUNZIONE CHE CREA IL COLTIVATORE:
							theController.inserisciColtivatore(u);
							JOptionPane.showMessageDialog(null, "La registrazione è avvenuta con successo!");
							
							//PULIAMO I CAMPI:
							clearFields(txtNome, txtCognome, dateChooser, txtUsername, txtPassword);
							
							//TORNIAMO ALL'HOMEPAGE:
							theController.paginaRegistrati.setVisible(false);
							theController.homePage.setVisible(true);
						}
						
					}catch(Exception x) {
						JOptionPane.showMessageDialog(null, "Errore nel blocco try-catch di coltivatore");
					}
				}
			}
		});
		
		JButton btnProprietario = new JButton("Proprietario");
		//btnProprietario.setEnabled(false);
		btnProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(control(txtNome, txtCognome, dateChooser, txtUsername, txtPassword)) {
					try{
						//FACCIO IL CAST DELLA DATA:
						java.sql.Date data = new java.sql.Date(dateChooser.getDate().getTime());
						
						//CASTO ANCHE LA VIARIABILE DI TIPO GENERE:
						gen = (Genere) comboBoxGenere.getSelectedItem();
						
						//INSERISCO I DATI IN UNA VARIABILE DI TIPO UTENTE PER POI PASSARLA ALLA PAGGINA SUCCESSIVA:
						Utente u = new Utente(txtNome.getText().trim(), txtCognome.getText().trim(), data, gen, txtUsername.getText().trim(), txtPassword.getText().trim());
						
						//CHIAMO LA FUNZIONE CHE MI PERMETTE DI PASSARE A UN'ALTRA PAGINA:
						theController.daPaginaRegistratiAProprietario(u);
						
					}catch(Exception x) {
						JOptionPane.showMessageDialog(null, "Errore nel blocco try-catch dell'utente");
					}
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Come ti identifichi:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("Inserisci una password lunga almeno 8 caratteri e che contenga caratteri speciali come: '!$%&/(=?'^*[]'");
		
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
				clearFields(txtNome, txtCognome, dateChooser, txtUsername, txtPassword);
				theController.paginaRegistrati.setVisible(false);
				theController.homePage.setVisible(true);
			}
		});
		panelBottom.add(btnBack);
	}
	
	
	
//METODI:
	private boolean control(JTextField nome, JTextField cognome, JDateChooser data, JTextField username, JTextField password) {
		 
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
		}else if( password.getText().length() < 8) {
			JOptionPane.showMessageDialog(null, "Il campo passowrd deve essere lunga almeno 8 caratteri");
			return false;
		}else if(! (matcher.find())){
			JOptionPane.showMessageDialog(null, "La password deve contenere almeno un carattere speciale: !@#$%^&*()\\\\-_=+{}[]:;'<>?,./ \");");
			return false;
		}
		return true;
	}
	
	
	private void clearFields(JTextField nome, JTextField cognome, JDateChooser data, JTextField username, JTextField password) {
		nome.setText(null);
		cognome.setText(null);
		data.setDate(null);
		username.setText(null);
		password.setText(null);
	}
	
	
}
