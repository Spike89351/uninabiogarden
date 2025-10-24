import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class FinestraVisualizzaEModificaDatiProprietario extends JDialog {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtNome;
	private JTextField txtCognome;
	private JComboBox comboBoxGenere;
	private JDateChooser dateChooser;
	
	public FinestraVisualizzaEModificaDatiProprietario(Controller c, Utente u) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				theController.paginaProprietario.setEnabled(true);
			}
		});
		
		
		theController = c;
		
		
		setSize(480, 312);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			
			JLabel lblWelcome = new JLabel("Visualizza e modifica i tuoi dati");
			lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panelTop.add(lblWelcome);
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			
			txtNome = new JTextField();
			txtNome.setForeground(Color.BLACK);
			txtNome.setEnabled(false);
			txtNome.setColumns(10);
			
			JLabel lblNome = new JLabel("Nome");
			lblNome.setHorizontalAlignment(SwingConstants.CENTER);
			lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JLabel lblCognome = new JLabel("Cognome");
			lblCognome.setHorizontalAlignment(SwingConstants.CENTER);
			lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			txtCognome = new JTextField();
			txtCognome.setEnabled(false);
			txtCognome.setColumns(10);
			
			dateChooser = new JDateChooser();
			dateChooser.setEnabled(false);
			
			JLabel lblDataDiNascita = new JLabel("Data di nascita");
			lblDataDiNascita.setHorizontalAlignment(SwingConstants.CENTER);
			lblDataDiNascita.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JLabel lblGenere = new JLabel("Genere");
			lblGenere.setHorizontalAlignment(SwingConstants.CENTER);
			lblGenere.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			comboBoxGenere = new JComboBox(Genere.values());
			comboBoxGenere.setEnabled(false);
			
			JButton btnSbloccaModifoche = new JButton("Sblocca modifica");
			btnSbloccaModifoche.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//SBLOCCO I CAMPI DI TESTO PER UN'EVENUTALE MODIFICA:
					sbloccaCampiDiTesto();
				}
			});
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(101)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblDataDiNascita, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblGenere, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addComponent(comboBoxGenere, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(154)
								.addComponent(btnSbloccaModifoche)))
						.addContainerGap(141, Short.MAX_VALUE))
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(1)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(1)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDataDiNascita, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(1)
								.addComponent(lblGenere, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addComponent(comboBoxGenere, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(33)
						.addComponent(btnSbloccaModifoche)
						.addContainerGap(62, Short.MAX_VALUE))
			);
			panelCentral.setLayout(gl_panelCentral);
		}
		
		JPanel panelBottom = new JPanel();
		contentPanel.add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.paginaProprietario.setEnabled(true);
				setVisible(false);
				
				//BLOCCA I CAMPI:
				bloccaCampiDiTesto();
			}
		});
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//CONTROLLO SE SONO STATI MODIFICATI I CAMPI, NEL CASO SETTA I DATI COM'ERANO:
					ctrlModificaCampi(u);
					
					//FACCIO IL CAST:
					Genere gen = (Genere) comboBoxGenere.getSelectedItem();
					java.sql.Date sqlDate = new java.sql.Date(dateChooser.getDate().getTime());
					
					//MODIFICO I DATI DELL'UTENTE:
					theController.modificaDati(u, txtNome.getText(), txtCognome.getText(), sqlDate, gen);
					
					//BLOCCA I CAMPI:
					bloccaCampiDiTesto();
				}catch(Exception eq) {
					System.out.println("problema nella pagina: "+eq);
				}
			}
		});
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnBack)
					.addGap(294)
					.addComponent(btnSalva)
					.addContainerGap())
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnSalva)))
		);
		panelBottom.setLayout(gl_panelBottom);
	}
	
//METODO:
	//METODO PER COMPILARE I CAMPI DI TESTO CON I DATI DELL'UTENTE CHE HA FATTO L'ACCESSO:
	private void compilaCampi(Utente u, JTextField txtNome, JTextField txtCognome, JDateChooser date, JComboBox genere, JTextField txtUsername) {
		txtNome.setText(u.getNome());
		txtCognome.setText(u.getCognome());
		date.setDate(u.getDataNascita());
		genere.setSelectedItem(u.getGenere());
		//txtPassword.setText(u.getPassword()); LA PASSWORD NON LA MOSTRIAMO PERCHE' E' COMPLICATO
	}
	
	//SERVE E SBLOCCARE I DATI PER MODIFICARLI:
	private void sbloccaCampiDiTesto() {
		txtNome.setEnabled(true);
		txtCognome.setEnabled(true);
		dateChooser.setEnabled(true);
		comboBoxGenere.setEnabled(true);
	}
	
	private void bloccaCampiDiTesto() {
		txtNome.setEnabled(false);
		txtCognome.setEnabled(false);
		dateChooser.setEnabled(false);
		comboBoxGenere.setEnabled(false);
	}
	
	//CONTROLLO SE I CAMPI SONO STATI MODIFICATI RISPETTANO DELLE REGOLE:
	private void ctrlModificaCampi(Utente u) {
		if(txtNome.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Mi dispiace ma il campo di testo 'Nome' non può essere vuoto! Per tanto sarà compilato con lo stesso testo di prima!");
			txtNome.setText(u.getNome());
		}
		if(txtCognome.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Mi dispiace il campo di testo 'Cognome' non può essere vuoto, per tanto sarà compilato con lo stesso testo di prima!");
			txtCognome.setText(u.getCognome());
		}
		try {
			//CAST DELLA DATA:
			LocalDate dateConvert = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			//CALCOLO IL PERIODO:
			Period età = Period.between(dateConvert, LocalDate.now());
			
			//CONTROLLO SULL'ETA':
			if(età.getYears() < 18) {
				JOptionPane.showMessageDialog(null, "Mi dispiace ma l'età non può essere minore di 18 anni, per tanto verrà rimessa l'età che ha messo nella registrazione!");
				dateChooser.setDate(u.getDataNascita());
			}
			
		}catch(Exception xxx) {
			JOptionPane.showMessageDialog(null, "C'è stato un'errore nella conversione della data!");
		}
		//USERNAME:
		
	}
	
}
