import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;

public class FinestraDettagliAttività extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtQuantità;
	private JTextField txtStato;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnCompleta;
	
	public FinestraDettagliAttività(int idTerreno, int idAttività, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setEnabled(false);
				theController.paginaAttività.setEnabled(true);
			}
			@Override
			public void windowActivated(WindowEvent e) {
				theController.paginaAttività.setEnabled(false);
				//POPOLA TABELLA: 
				theController.popolaTabellaConQuantitàRaccolto(idTerreno, model);
				setEnabled(true);
			}
		});
		theController = c;
		
		setTitle("Finestra dettagli Attività");
		setSize(450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				JLabel lblWelcome = new JLabel("Qui puoi completare l'attività del progetto inserendo la quantità del raccolto");
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			
			JLabel lblStato = new JLabel("Qunatità ");
			lblStato.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			txtQuantità = new JTextField();
			txtQuantità.setHorizontalAlignment(SwingConstants.CENTER);
			txtQuantità.setColumns(10);
			
			JLabel lblQuantità = new JLabel("Qunatità ");
			lblQuantità.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			txtStato = new JTextField();
			txtStato.setHorizontalAlignment(SwingConstants.CENTER);
			txtStato.setText("Completato");
			txtStato.setEnabled(false);
			txtStato.setEditable(false);
			txtStato.setColumns(10);
			
			JPanel panelTable = new JPanel();
			{
				btnCompleta = new JButton("Completa");
				btnCompleta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(ctrlTxtFields()) {
							//COMPLETA ATTIVITA':
							if(theController.modificaTipoAttivitàInRaccolto(txtStato.getText(), Double.valueOf(txtQuantità.getText()), idAttività)) {
								JOptionPane.showMessageDialog(null, "Complimenti, l'azione è andata a buon fine!");
								clearTxtFields();
								//AGGIORNA TABELLA:
								theController.popolaTabellaConQuantitàRaccolto(idTerreno, model);
							}else {
								JOptionPane.showMessageDialog(null, "Errore, l'azione non è andata a buon fine!");
							}
						}
					}
				});
				btnCompleta.setActionCommand("OK");
				getRootPane().setDefaultButton(btnCompleta);
			}
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblStato)
										.addGap(18)
										.addComponent(txtStato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblQuantità, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtQuantità, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(57)
								.addComponent(btnCompleta)))
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
						.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGap(33)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
							.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblStato)
									.addComponent(txtStato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblQuantità, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtQuantità, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCompleta)))
						.addContainerGap(67, Short.MAX_VALUE))
			);
			panelTable.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelTable.add(scrollPane, BorderLayout.CENTER);
			
			model = new DefaultTableModel(
					new Object[][]{},
					new String[]{ "Id attività", "Quantità", "Data inizio", "Data fine"}
				);
			
			table = new JTable(model);
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			panelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel panelBottom = new JPanel();
			getContentPane().add(panelBottom, BorderLayout.SOUTH);
			{
				JButton btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack);
			}
		}
	}
	
//METODI:
	private boolean ctrlTxtFields() {
		if(Double.valueOf(txtQuantità.getText()) < 0) {
			JOptionPane.showMessageDialog(null, "La quantità del raccolto non può essere MINORE di 0");
			return false;
		}
		return true;
	}
	
	//MI SERVE PER PULIRE I CAMPI:
	private void clearTxtFields() {
		txtQuantità.setText(null);
	}
	
	
	
}
