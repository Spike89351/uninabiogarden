import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;

public class FinestraCambiaStatoAttività extends JDialog {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnBack;
	private JButton btnCambiaStato;
	private ArrayList<Object> elencoDatiAttività;
	private JLabel lblDataInSel;
	private JLabel lblTipoAttSel;
	private JLabel lblStatoAttSel;
	private JComboBox comboBox;
	
	public FinestraCambiaStatoAttività(int idAtt, String statoAtt, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnBack.doClick();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				elencoDatiAttività = theController.prendiDatiAttività(idAtt);
				if(elencoDatiAttività.size() != 0) {
					lblDataInSel.setText(elencoDatiAttività.get(0).toString());
					lblTipoAttSel.setText(elencoDatiAttività.get(1).toString());
					lblStatoAttSel.setText(elencoDatiAttività.get(2).toString());					
				}else {
					//NON HA PRESO NESSUN DATO:
					JOptionPane.showMessageDialog(null, "Errore, l'arrayList è vuoto");
				}
				
			}
		});
		theController = c;
		
		setTitle("Finestra cambio stato attività");
		setSize(610, 305);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				JLabel lblWelcome = new JLabel("Cmabia lo stato della attività");
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			
			JLabel lblDatiAttività = new JLabel("Dati attività selezionata");
			lblDatiAttività.setHorizontalAlignment(SwingConstants.CENTER);
			lblDatiAttività.setFont(new Font("Tahoma", Font.PLAIN, 14));
			JLabel lblDataInizioAtt = new JLabel("Data inizio");
			lblDataInizioAtt.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDataInSel = new JLabel("");
			lblDataInSel.setForeground(Color.RED);
			lblDataInSel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JLabel lblTipoAttivit = new JLabel("Tipo attività");
			lblTipoAttivit.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTipoAttSel = new JLabel("");
			lblTipoAttSel.setForeground(Color.RED);
			lblTipoAttSel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			JLabel lblStatoAttivit = new JLabel("Stato attività");
			lblStatoAttivit.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblStatoAttSel = new JLabel("");
			lblStatoAttSel.setForeground(Color.RED);
			lblStatoAttSel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JLabel lblCambiaDati = new JLabel("Cambia stato attività");
			lblCambiaDati.setHorizontalAlignment(SwingConstants.CENTER);
			lblCambiaDati.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			JLabel lblNewLabel = new JLabel("Stato Attività");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			String[] elencoStato = {"", "Pianificata", "In Corso"};
			
			comboBox = new JComboBox(elencoStato);
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panelCentral.createSequentialGroup()
										.addComponent(lblDataInizioAtt)
										.addGap(18)
										.addComponent(lblDataInSel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addGap(45)
										.addComponent(lblTipoAttivit)
										.addGap(18)
										.addComponent(lblTipoAttSel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
										.addComponent(lblStatoAttivit)
										.addGap(18)
										.addComponent(lblStatoAttSel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblDatiAttività, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
									.addComponent(lblCambiaDati, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addGap(18)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGap(193))))
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblDatiAttività)
						.addGap(18)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataInizioAtt)
								.addComponent(lblDataInSel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblStatoAttivit, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblStatoAttSel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTipoAttivit, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTipoAttSel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(lblCambiaDati, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGap(36)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(61, Short.MAX_VALUE))
			);
			panelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				btnCambiaStato = new JButton("Cambia stato");
				btnCambiaStato.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//CONTROLLER DELLO STATO:
						if(ctrlText()) {
							//CAMBIA STATO:
							if(theController.cambiaStatoAttività(idAtt, comboBox.getSelectedItem().toString().trim())) {
								clearField();
								lblStatoAttSel.setText(comboBox.getSelectedItem().toString().trim());
								JOptionPane.showMessageDialog(null, "Complimenti lo stato dell'attività è stato cambiato correttamente!");
							}else {
								clearField();
								JOptionPane.showMessageDialog(null, "Errore, non è stato possibile cambiare lo stato dell'attività!");
							}
						}
					}
				});
				btnCambiaStato.setActionCommand("OK");
				buttonPane.add(btnCambiaStato, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnCambiaStato);
			}
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						theController.paginaAttività.setEnabled(true);
						theController.paginaAttività.setVisible(true);
						
						//PULISCI CAMPI:
						clearField();
					}
				});
				btnBack.setActionCommand("Cancel");
				buttonPane.add(btnBack, BorderLayout.WEST);
			}
		}
	}
//METODI:
	//MI SERVE PER PULIRE I CAMPI:
	private void clearField() {
		comboBox.setSelectedItem(null);
	}
	
	//MI SERVE A CAPIRE SE LO STATO SELEZIONATO E' CORRETTO:
	private boolean ctrlText() {
		if(comboBox.getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Lo stato dell'attività non può essere vuoto!");
			return false;
		}else {
			if(elencoDatiAttività.get(2).toString().equalsIgnoreCase("Pianificata")) {
				if(elencoDatiAttività.get(2).toString().equalsIgnoreCase("Nessuna")) {
					JOptionPane.showMessageDialog(null, "Errore, non puoi selezionare uno stato attività precedente!");
					return false;
				}else if(elencoDatiAttività.get(2).toString().equalsIgnoreCase("Pianificata")) {
					JOptionPane.showMessageDialog(null, "Errore, lo stato attività che hai selezionato già è in corso");
					return false;
				}
			}
			if(elencoDatiAttività.get(2).toString().equalsIgnoreCase("In Corso")) {
				if(elencoDatiAttività.get(2).toString().equalsIgnoreCase("In Corso")) {
					JOptionPane.showMessageDialog(null, "Errore, lo stato attività che hai selezionato già è in corso");
					return false;
				}else {
					JOptionPane.showMessageDialog(null, "Errore, lo stato attività non può passare a un'attività precednete");
					return false;
				}
			}
		}
		return true;
	}
}
