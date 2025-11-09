import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
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

public class FinestraManutenzione extends JDialog {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	
	public FinestraManutenzione(int idAttrezzo, Controller c) {
		theController = c;
		
		setSize(669, 311);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				JLabel lblWelcome = new JLabel("Qui puoi aggiungere e visualizzare gli utensili ");
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
			
			JComboBox comboBox = new JComboBox(tipoManutenzione);
			
			JPanel panelTable = new JPanel();
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblStatoMan)
						.addGap(18)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addGap(49)
						.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(28)
								.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(65)
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblStatoMan)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(55, Short.MAX_VALUE))
			);
			panelTable.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelTable.add(scrollPane, BorderLayout.CENTER);
			
			model = new DefaultTableModel(
					new Object[][]{},
					new String[]{ "Id attrezzo", "Nome", "Stato Manutenzione"}
				);
			
			table = new JTable(model);
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
			panelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel PanelBottom = new JPanel();
			getContentPane().add(PanelBottom, BorderLayout.SOUTH);
			PanelBottom.setLayout(new BorderLayout(0, 0));
			{
				JButton btnAggiungi = new JButton("Aggiungi");
				btnAggiungi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//AGGIUNGI MANUTENZIONE ALL'ATTREZZO:
						
					}
				});
				btnAggiungi.setActionCommand("OK");
				PanelBottom.add(btnAggiungi, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnAggiungi);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TORNA INDIETRO:
						setVisible(false);
						theController.paginaAttrezzo.setVisible(true);
						theController.paginaAttrezzo.setEnabled(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				PanelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}
}
