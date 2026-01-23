import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FinestraManutenzione extends JDialog {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private JComboBox comboBoxStato;
	private JComboBox comboBoxStatoCercato;
	private JLabel lblAttrezzoScelto;
	private int idAttrezzoSel;
	private String currentStateTool;
	private JButton btnBack;
	private JButton btnCambiaStato;
	private ArrayList<Attrezzo> elenco = new ArrayList<Attrezzo>();
	
	public FinestraManutenzione(int idDep, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA LA TABELLA GIA' DI DEFAULT:
				model.setRowCount(0);
				if(! elenco.isEmpty()) {
					elenco.clear();
				}
				elenco = theController.popolaTabellaTramiteStatoAttrezzo(idDep, "Nessuna");
				for(Attrezzo at : elenco) {
					model.addRow(new Object[]{at.getNome(), String.valueOf(at.getTipo()), String.valueOf(at.getStatoAttrezzo())});
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				btnBack.doClick();
			}
		});
		theController = c;
		
		setTitle("Finestra manutenzione");
		setSize(670, 315);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				JLabel lblWelcome = new JLabel("Qui puoi cambiare e  visualizzare gli utensili tramite stato di manutenzione");
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			
			JLabel lblStatoMan = new JLabel("Stato manutenzione ");
			lblStatoMan.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			String[] tipoManutenzione = {"", "Nessuna", "Pianificata", "In corso", "Completata"};
			
			comboBoxStato = new JComboBox(tipoManutenzione);
			
			JPanel panelTable = new JPanel();
			JLabel lblStatoCercato = new JLabel("Cerca tramite stato di manutenzione");

			String[] cercaPerManutenzione = {"Nessuna", "Pianificata", "In corso", "Completata"};
			
			comboBoxStatoCercato = new JComboBox(cercaPerManutenzione);
			comboBoxStatoCercato.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					 if (e.getStateChange() == ItemEvent.SELECTED) {
				         model.setRowCount(0);
				         elenco.clear();
				         elenco = theController.popolaTabellaTramiteStatoAttrezzo(idDep, String.valueOf(e.getItem().toString().trim()));
				         for(Attrezzo at : elenco) {
								model.addRow(new Object[]{at.getNome(), String.valueOf(at.getTipo()), String.valueOf(at.getStatoAttrezzo())});
				         }
					 }
				}
			});
			
			lblAttrezzoScelto = new JLabel("L'id del attrezzo scelto è "+idAttrezzoSel);
			lblAttrezzoScelto.setVisible(false);
			lblAttrezzoScelto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAttrezzoScelto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addComponent(lblStatoMan)
								.addGap(18)
								.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblAttrezzoScelto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(49)
								.addComponent(lblStatoCercato)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxStatoCercato, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(18)
								.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(16)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboBoxStatoCercato, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblStatoCercato))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(40)
								.addComponent(lblAttrezzoScelto)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblStatoMan)
									.addComponent(comboBoxStato, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(47, Short.MAX_VALUE))
			);
			panelTable.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelTable.add(scrollPane, BorderLayout.CENTER);
			
			model = new DefaultTableModel(
					new Object[][]{},
					new String[]{"Nome", "Tipo", "Stato Manutenzione"}
				);
			
			table = new JTable(model);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//POSSO ANCHE CAMBIARE LO STATO DI UN ATTREZZO DIVERSO DA QUELLO CON CUI SONO ENTRATO:
					int selectedRow = table.rowAtPoint(e.getPoint());
					if(selectedRow != -1) {
						idAttrezzoSel = elenco.get(selectedRow).getIdAttrezzo();
						lblAttrezzoScelto.setVisible(true);
						currentStateTool = String.valueOf(table.getValueAt(selectedRow, 2));
						
						if(currentStateTool.equalsIgnoreCase("Completata")) {
							btnCambiaStato.setEnabled(false);
						}else {
							btnCambiaStato.setEnabled(true);
						}
						//CAMBIA L'ID NEL LABEL:
						lblAttrezzoScelto.setText("L'id del attrezzo scelto è "+String.valueOf(idAttrezzoSel));
					}
				}
			});
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			panelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel PanelBottom = new JPanel();
			getContentPane().add(PanelBottom, BorderLayout.SOUTH);
			PanelBottom.setLayout(new BorderLayout(0, 0));
			{
				btnCambiaStato = new JButton("Cambia stato");
				btnCambiaStato.setEnabled(false);
				btnCambiaStato.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//AGGIUNGI MANUTENZIONE ALL'ATTREZZO:
						if(ctrTxtField()){
							//CAMBIA STATO ATTREZZO:
							if(ctrlStateTool()) {
								boolean disp = false;
								if(comboBoxStato.getSelectedItem().toString().trim().equalsIgnoreCase("Completata") || comboBoxStato.getSelectedItem().toString().trim().equalsIgnoreCase("Pianificata")) {
									disp = true;
								}
								if(theController.manutenzioneAttrezzo(idAttrezzoSel, String.valueOf(comboBoxStato.getSelectedItem().toString().trim()), disp)) {
									JOptionPane.showMessageDialog(null, "Cambio dello stato avvenuto con successo!");
									//AGGIORNA LA TABELLA:
									model.setRowCount(0);
									elenco.clear();
									elenco = theController.popolaTabellaTramiteStatoAttrezzo(idDep, "Nessuna");
									comboBoxStatoCercato.setSelectedItem("Nessuna");
								}
							}
							//PULISCI IL CAMPO:
							clearField();
						}
					}
				});
				btnCambiaStato.setActionCommand("OK");
				PanelBottom.add(btnCambiaStato, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnCambiaStato);
			}
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TORNA INDIETRO:
						setVisible(false);
						theController.paginaAttrezzo.setEnabled(true);
						theController.paginaAttrezzo.setVisible(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				PanelBottom.add(btnBack, BorderLayout.WEST);
			}
			
			JPanel panelCetnralBottom = new JPanel();
			PanelBottom.add(panelCetnralBottom, BorderLayout.CENTER);
		}
	}

//METODI:
	private boolean ctrlStateTool() {
		if(comboBoxStato.getSelectedItem().toString().trim().equalsIgnoreCase(currentStateTool)) {
			JOptionPane.showMessageDialog(null, "Errore, l'attrezzo è gia in questo stato di manutenzione");
			return false;
		}else {
			if(currentStateTool.equalsIgnoreCase("Pianificata") && comboBoxStato.getSelectedItem().toString().trim().equalsIgnoreCase("Nessuna") ) {
				JOptionPane.showMessageDialog(null, "Errore, l'attrezzo non può avere questo stato che hai selezionato");
				return false;
			}
			if(currentStateTool.equalsIgnoreCase("In corso") && comboBoxStato.getSelectedItem().toString().trim().equalsIgnoreCase("Nessuna") ) {
				JOptionPane.showMessageDialog(null, "Errore, l'attrezzo non può avere questo stato che hai selezionato");
				return false;
			}
			if(currentStateTool.equalsIgnoreCase("In corso") && comboBoxStato.getSelectedItem().toString().trim().equalsIgnoreCase("Pianificata")) {
				JOptionPane.showMessageDialog(null, "Errore, l'attrezzo non può avere questo stato che hai selezionato");
				return false;
			}
		}
		return true;
	}
	
	private void clearField() {
		comboBoxStato.setSelectedItem(null);
	}
	
	private boolean ctrTxtField() {
		if(comboBoxStato.getSelectedItem().toString().isBlank()) {
			JOptionPane.showMessageDialog(null, "Errore, lo stato dell'attrezzo non può essere nullo!");
			return false;
		}
		return true;
	}
}
