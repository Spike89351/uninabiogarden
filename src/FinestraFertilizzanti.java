import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class FinestraFertilizzanti extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtLetame;
	private JTextField txtCompost;
	private JTextField txtGranulari;
	private JTextField txtLiquidi;

	
	public FinestraFertilizzanti(int idDep, Controller c) {
		theController = c;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				theController.paginaDettagliDeposito.setEnabled(true);
			}
		});
		
		setTitle("Pagina Fertilizzanti");
		setSize(450, 328);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				JLabel lblWelcome = new JLabel("Qui puoi aggiungere i fertilizzanti al tuo deposito con id "+ idDep);
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			JLabel lblMessaggioAttenzione = new JLabel("Attenzione il tuo fertilizzante si sommerà a quello già presente");
			lblMessaggioAttenzione.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMessaggioAttenzione.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel lblLetame = new JLabel("Letame");
			lblLetame.setFont(new Font("Tahoma", Font.PLAIN, 12));
			JLabel lblCompost = new JLabel("Compost");
			lblCompost.setFont(new Font("Tahoma", Font.PLAIN, 12));
			JLabel lblGranulari = new JLabel("Granulari");
			lblGranulari.setFont(new Font("Tahoma", Font.PLAIN, 12));
			JLabel lblLiquidi = new JLabel("Liquidi");
			lblLiquidi.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			txtLetame = new JTextField();
			txtLetame.setColumns(10);
			
			txtCompost = new JTextField();
			txtCompost.setColumns(10);
			
			txtGranulari = new JTextField();
			txtGranulari.setColumns(10);
			
			txtLiquidi = new JTextField();
			txtLiquidi.setColumns(10);
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblMessaggioAttenzione, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(123)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblLetame, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtLetame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblCompost, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtCompost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblGranulari, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtGranulari, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblLiquidi, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtLiquidi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap())
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblMessaggioAttenzione)
						.addGap(29)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblLetame)
							.addComponent(txtLetame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCompost, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtCompost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblGranulari, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtGranulari, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblLiquidi, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtLiquidi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(47, Short.MAX_VALUE))
			);
			panelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel panelBottom = new JPanel();
			getContentPane().add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new BorderLayout(0, 0));
			{
				JButton btnAggiungi = new JButton("Aggiungi");
				btnAggiungi.setToolTipText("I fertilizzanti che inserirai verranno sommati a quelli già presenti");
				btnAggiungi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//CONTROLLO SE I DATI SONO STATI INSERITI CORRETTAMENTE:
						if(ctrlFieldTxt()) {
							//AGGIUNGI FERTILIZZANTE:
							if(theController.inserisciFertilizzante(idDep, Double.valueOf(txtLetame.getText().trim()), Double.valueOf(txtCompost.getText().trim()), Double.valueOf(txtGranulari.getText().trim()), Double.valueOf(txtLiquidi.getText().trim()))) {
								clearTextFields();
								JOptionPane.showMessageDialog(null, "L'inserimento dei fertilizzante è avvenuto con successo");
							}else {
								JOptionPane.showMessageDialog(null, "Errore nell'inserimento del fertilizzante");
							}
						}
					}
				});
				btnAggiungi.setActionCommand("OK");
				panelBottom.add(btnAggiungi, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnAggiungi);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						
						//PULISCI I CAMPI NEL CASO CI FOSSE BISOGNO:
						clearTextFields();
					}
				});
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}
	
//METODI:
	//METODO CHE PULISCE I CAMPI DI TESTO:
	private void clearTextFields() {
		txtLetame.setText(null);
		txtCompost.setText(null);
		txtGranulari.setText(null);
		txtLiquidi.setText(null);
	}
	
	//CONTROLLA CAMPI DI TESTO:
	private boolean ctrlFieldTxt() {
		if(txtLetame.getText().isBlank()) {
			txtLetame.setText("-1");
		}else {
			if(Double.valueOf(txtLetame.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "Errore ma il letame non può essere un valore minore o uguale a 0!");
				return false;
			}
		}
		if(txtCompost.getText().isBlank()) {
			txtCompost.setText("-1");
		}else {
			if(Double.valueOf(txtCompost.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "Errore ma il compost non può avere un valore minore o uguale a 0!");
				return false;
			}
		}
		if(txtGranulari.getText().isBlank()) {
			txtGranulari.setText("-1");
		}else {
			if(Double.valueOf(txtGranulari.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "Errore ma i granulari non possono avere un valore minore o uguale a 0!");
				return false;
			}
		}
		if(txtLiquidi.getText().isBlank()) {
			txtLiquidi.setText("-1");
		}else {
			if(Double.valueOf(txtLiquidi.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "Errore ma i liquidi non può avere un valore minore o uguale a 0!");
				return false;
			}
		}
		return true;
	}
}
