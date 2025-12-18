import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FinestraVisualizzaColtivatoriAttività extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelTop;
	private JPanel panelCentral;
	private JLabel lblWelcome;
	private JPanel panelButtom;
	private JButton btnBack;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private int[] selectedIDs;
	private JPanel panel;
	private JLabel lblInviaNotifica;
	private JTextField txtDescrizione;
	private JLabel lblTipoNotifica;
	private JComboBox comboBox;
	private JButton btnInvia;
	private JButton btnInviaATutti;
	private int idSelected;
	
	public FinestraVisualizzaColtivatoriAttività(int idAttività, String statAtt, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				theController.popolaTabellaConColtivatoriAssociatiAllAttività(idAttività, model, statAtt);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				btnBack.doClick();
			}
		});
		theController = c;
		
		setTitle("Finestra per visualizzare i coltivatori associati all'attività");
		setSize(750, 472);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				lblWelcome = new JLabel("Stai visualizzando i coltivatori che lavorano all'attività con id "+idAttività);
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
		}
		{
			panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.X_AXIS));
			{
				scrollPane = new JScrollPane();
				panelCentral.add(scrollPane);
				{
					model  = new DefaultTableModel(
							new Object[][]{},
							new String[]{"id", "username", "Nome", "Cognome", "Data di Nascita", "Indirizzo"}
						);
					
					table = new JTable(model);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int selectedRow = table.rowAtPoint(e.getPoint());
							if(selectedRow != -1) {
								String idStrg = String.valueOf(table.getValueAt(selectedRow, 0));
								idSelected = Integer.valueOf(idStrg.trim());
								//IL PULSANTE 'INVIA' VIENE ABILITATO:
								btnInvia.setEnabled(true);
								//IL PULSANTE 'INVIA A TUTTI' VIENE DISABILITATO:
								btnInviaATutti.setEnabled(false);
							}
						}
					});
					
								        
					scrollPane.setColumnHeaderView(table);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				lblInviaNotifica = new JLabel("Invia notifica diversa");
				lblInviaNotifica.setFont(new Font("Tahoma", Font.PLAIN, 15));
			}
			
			JLabel lblDescrizione = new JLabel("Descrizione");
			lblDescrizione.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			txtDescrizione = new JTextField();
			txtDescrizione.setColumns(10);
			lblTipoNotifica = new JLabel("Tipo notifica");
			lblTipoNotifica.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			String[] tipNot = {"Anomalia"};			
			
			comboBox = new JComboBox(tipNot);
			
			btnInvia = new JButton("Invia");
			btnInvia.setEnabled(false);
			btnInvia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//INVIA SOLO A CHI HAI SELEZIONATO:
					//CONTROLLO CAMPI:
					if(ctrlTxt()) {
						if(theController.inviaNotificaModificata(idSelected, txtDescrizione.getText().trim(), comboBox.getSelectedItem().toString().trim())) {
							JOptionPane.showMessageDialog(null, "Complimenti, la notifica è stata inviata con successo");
						}else {
							JOptionPane.showMessageDialog(null, "Errore nell'invio della notificaal coltivatore scelto, id"+idSelected);
						}
					}
					clearTxt();
				}
			});
			
			btnInviaATutti = new JButton("Invia a tutti");
			btnInviaATutti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//INVIA A TUTTI LA NOTIFICA:
					//CONTROLLO CAMPI:
					if(ctrlTxt()) {
						int count = 0;
						for (int i = 0; i < model.getRowCount(); i++) {
		                	String strgValue = String.valueOf(model.getValueAt(i, 0));
		                	if(theController.inviaNotificaModificata(Integer.valueOf(strgValue.trim()), txtDescrizione.getText().trim(), comboBox.getSelectedItem().toString().trim())) {
		                		count++;
		                	}else {
		                		count--;
		                	}
		                }
		                if(count == model.getRowCount()) {
		                	JOptionPane.showMessageDialog(null, "Complimenti, la notifica è stata inviata a tutti");
		                }else {
		                	JOptionPane.showMessageDialog(null, "ERRORE, la notifica NON è stata inviata a tutti");
		                }
					}
	                clearTxt();
				}
			});
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(196)
								.addComponent(lblInviaNotifica))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblDescrizione, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtDescrizione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
								.addComponent(lblTipoNotifica, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(46)
						.addComponent(btnInvia)
						.addPreferredGap(ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
						.addComponent(btnInviaATutti)
						.addGap(63))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblInviaNotifica, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDescrizione)
							.addComponent(txtDescrizione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTipoNotifica, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnInvia)
							.addComponent(btnInviaATutti))
						.addGap(19))
			);
			panel.setLayout(gl_panel);
		}
		{
			panelButtom = new JPanel();
			getContentPane().add(panelButtom, BorderLayout.SOUTH);
			panelButtom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TORNA INDIETRO:
						setEnabled(false);
						setVisible(false);
						theController.paginaSceltaColtivatore.setVisible(true);
						theController.paginaSceltaColtivatore.setEnabled(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				panelButtom.add(btnBack);
			}
		}
	}
//METODI:
	private void clearTxt() {
		txtDescrizione.setText(null);
		comboBox.setSelectedIndex(0);
		btnInvia.setEnabled(false);
		btnInviaATutti.setEnabled(true);
	}
	
	//MI SERVE PER CONTROLLARE I CAMPI DI TESTO:
	private boolean ctrlTxt() {
		if(txtDescrizione.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, il campo descrizione non può essere vuoto!");
			return false;
		}
		if(comboBox.getSelectedItem().toString().isBlank()) {
			
			//MESSAGGIO SULLO SCHERMO:
			int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler continuare senza inserire il tipo di notifica?", "Conferma Azione", JOptionPane.YES_NO_OPTION);
				
	        if (scelta == JOptionPane.NO_OPTION) {
	        	return false;
	        }
		}
		return true;
	}
}
