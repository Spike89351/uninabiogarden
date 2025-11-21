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
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FinestraNotificheColtivatore extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private JButton btnSegnaComeLetto;
	private JButton btnBack;
	private JComboBox comboBox;
	private JTable table;
	private int idNotificaSelezionata;
	
	public FinestraNotificheColtivatore(int idColt, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA TABELLA:
				theController.visualizzaNotificheColtivatore(idColt, model, "");
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				theController.paginaColtivatore.setVisible(true);
				theController.paginaColtivatore.setEnabled(true);
			}
		});
		theController = c;
		
		setTitle("Finestra notifiche");
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
				JLabel lblNewLabel = new JLabel("Elenco notifiche");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblNewLabel);
			}
		}
		{
			JPanel panelCetnral = new JPanel();
			contentPanel.add(panelCetnral, BorderLayout.CENTER);
			JPanel panel = new JPanel();
			
			JLabel lblElencoNotifiche = new JLabel("Elenco notifiche");
			lblElencoNotifiche.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			String[] tipoNot = {"", "Visualizzato"};
			
			comboBox = new JComboBox(tipoNot);
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					//POPOLA TABELLA:
					theController.visualizzaNotificheColtivatore(idColt, model, comboBox.getSelectedItem().toString().trim());
				}
			});
			GroupLayout gl_panelCetnral = new GroupLayout(panelCetnral);
			gl_panelCetnral.setHorizontalGroup(
				gl_panelCetnral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCetnral.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelCetnral.createParallelGroup(Alignment.TRAILING)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_panelCetnral.createSequentialGroup()
								.addComponent(lblElencoNotifiche)
								.addPreferredGap(ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_panelCetnral.setVerticalGroup(
				gl_panelCetnral.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelCetnral.createSequentialGroup()
						.addGap(16)
						.addGroup(gl_panelCetnral.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblElencoNotifiche)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
						.addContainerGap())
			);
			panel.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			
			model = new DefaultTableModel(
					new Object[][]{},
					new String[]{"id notifica", "Tipo", "Descrizione", "Data invio"}
				);
			
			table = new JTable(model);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//RENDI IL BOTTONE DISONIBILE:
					int selectedRow = table.rowAtPoint(e.getPoint());
					if(selectedRow != -1) {
						String idNotifyString = String.valueOf(table.getValueAt(selectedRow, 0));
						idNotificaSelezionata = Integer.valueOf(idNotifyString);
						btnSegnaComeLetto.setEnabled(true);
						btnSegnaComeLetto.setForeground(Color.RED);
					}
					
				}
			});
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			panelCetnral.setLayout(gl_panelCetnral);
		}
		{
			JPanel panelBottom = new JPanel();
			getContentPane().add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new BorderLayout(0, 0));
			{
				btnSegnaComeLetto = new JButton("Segna come lette");
				btnSegnaComeLetto.setEnabled(false);
				btnSegnaComeLetto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//SEGNA COME LETTE, UNA AD  UNA:
						if(theController.cambiaVisualNotifica(idNotificaSelezionata)) {
							
							JOptionPane.showMessageDialog(null, "Notifica resa visualizzata! ");
							theController.visualizzaNotificheColtivatore(idColt, model, "");
							//UNA VOLTA SEGNATO COME LETTO FAI RITORNARE IL PULSANTE COME PRIMA:
							btnSegnaComeLetto.setEnabled(false);
							btnSegnaComeLetto.setForeground(Color.BLACK);
						}
					}
				});
				panelBottom.add(btnSegnaComeLetto, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnSegnaComeLetto);
			}
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						theController.paginaColtivatore.setVisible(true);
						theController.paginaColtivatore.setEnabled(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}
}
