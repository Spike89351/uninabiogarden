import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.Color;

public class FinestraFertilizzanti extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtLetame;
	private JTextField txtCompost;
	private JTextField txtGranulari;
	private JTextField txtLiquidi;
	private JTable table;
	private DefaultTableModel model;

	
	public FinestraFertilizzanti(int idDep, Controller c) {
		theController = c;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				theController.paginaDettagliDeposito.setEnabled(true);
			}
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA LA TABELLA:
				try {
					theController.popolaTabellaFertilizzanti(idDep, model);
				}catch(Exception xxx) {
					JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella!");
				}
			}
		});
		
		setTitle("Pagina Fertilizzanti");
		setSize(600, 346);
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
			
			JPanel panelTable = new JPanel();
			
			JLabel lblNewLabel = new JLabel("Quantità dei tuoi fertilizzanti");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addComponent(lblMessaggioAttenzione, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addComponent(lblLiquidi, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtLiquidi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelCentral.createSequentialGroup()
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
										.addComponent(txtGranulari, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGap(30)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))))
						.addContainerGap())
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblMessaggioAttenzione)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(26)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblLetame)
									.addComponent(txtLetame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(44)
								.addComponent(lblNewLabel)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblCompost, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtCompost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblGranulari, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtGranulari, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addComponent(panelTable, 0, 0, Short.MAX_VALUE))
						.addGap(18)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblLiquidi, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtLiquidi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(50, Short.MAX_VALUE))
			);
			panelTable.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelTable.add(scrollPane, BorderLayout.CENTER);
			
			model = new DefaultTableModel(
					new Object[][]{},
					new String[]{"Letame (Kg)", "Compost (kg)", "Granulari (Kg)", "Liquidi (Kg)"}
				);
			
			table = new JTable(model);
			table.setForeground(SystemColor.textHighlight);
			table.setFont(new Font("Tahoma", Font.PLAIN, 15));
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			panelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel panelBottom = new JPanel();
			getContentPane().add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new BorderLayout(0, 0));
			{
				JButton btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						theController.paginaDettagliDeposito.setVisible(true);
						theController.paginaDettagliDeposito.setEnabled(true);
						
						//PULISCI I CAMPI NEL CASO CI FOSSE BISOGNO:
						clearTextFields();
					}
				});
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack, BorderLayout.WEST);
			}
			
			JPanel panel = new JPanel();
			panelBottom.add(panel, BorderLayout.CENTER);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnAggiungi = new JButton("Aggiungi");
				btnAggiungi.setForeground(Color.BLUE);
				panel.add(btnAggiungi);
				btnAggiungi.setToolTipText("I fertilizzanti che inserirai verranno sommati a quelli già presenti");
				btnAggiungi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//CONTROLLO SE I DATI SONO STATI INSERITI CORRETTAMENTE:
						if(ctrlFieldTxt()) {
							//AGGIUNGI FERTILIZZANTE:
							if(theController.inserisciFertilizzante(idDep, Double.valueOf(txtLetame.getText().trim()), Double.valueOf(txtCompost.getText().trim()), Double.valueOf(txtGranulari.getText().trim()), Double.valueOf(txtLiquidi.getText().trim()))) {
								//PULISCI I CAMPI DI TESTO:
								clearTextFields();
								//POPOLA LA TABELLA:
								theController.popolaTabellaFertilizzanti(idDep, model);
								JOptionPane.showMessageDialog(null, "L'inserimento dei fertilizzante è avvenuto con successo");
							}else {
								//PULISCI I CAMPI DI TESTO:
								clearTextFields();
								JOptionPane.showMessageDialog(null, "Errore nell'inserimento del fertilizzante");
							}
						}
					}
				});
				btnAggiungi.setActionCommand("OK");
				getRootPane().setDefaultButton(btnAggiungi);
			}
			
			JButton btnSottrai = new JButton("Sottrai");
			btnSottrai.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(ctrlFieldTxt()) {
						//SOTTRAI:
						theController.sottraiFertilizzante(idDep, Double.valueOf(txtLetame.getText().trim()), Double.valueOf(txtCompost.getText().trim()), Double.valueOf(txtGranulari.getText().trim()), Double.valueOf(txtLiquidi.getText().trim()));
						
						//PULISCI I CAMPI DI TESTO:
						clearTextFields();
						
						//POPOLA TABELLA:
						theController.popolaTabellaFertilizzanti(idDep, model);
					}else {
						//PULISCI I CAMPI DI TESTO:
						clearTextFields();
						JOptionPane.showMessageDialog(null, "Errore nel sottrarre i fertilizzanti!");
					}
				}
			});
			btnSottrai.setForeground(Color.RED);
			panelBottom.add(btnSottrai, BorderLayout.EAST);
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
		if(txtLetame.getText().trim().isBlank()) {
			txtLetame.setText("0");
		}else {
			if(Double.valueOf(txtLetame.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(null, "Errore ma il letame non può essere un valore minore o uguale a 0!");
				return false;
			}
		}
		if(txtCompost.getText().trim().isBlank()) {
			txtCompost.setText("0");
		}else {
			if(Double.valueOf(txtCompost.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(null, "Errore ma il compost non può avere un valore minore o uguale a 0!");
				return false;
			}
		}
		if(txtGranulari.getText().trim().isBlank()) {
			txtGranulari.setText("0");
		}else {
			if(Double.valueOf(txtGranulari.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(null, "Errore ma i granulari non possono avere un valore minore o uguale a 0!");
				return false;
			}
		}
		if(txtLiquidi.getText().trim().isBlank()) {
			txtLiquidi.setText("0");
		}else {
			if(Double.valueOf(txtLiquidi.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(null, "Errore ma i liquidi non può avere un valore minore o uguale a 0!");
				return false;
			}
		}
		return true;
	}
}
