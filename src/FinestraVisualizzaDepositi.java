import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FinestraVisualizzaDepositi extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnBack;
	private JButton btnOk;
	private DefaultTableModel model;
	private final JPanel panelTop = new JPanel();
	private JPanel panelCentral;
	private JLabel lblWelcome;
	private JScrollPane scrollPane;
	private JTable table;
	
	public FinestraVisualizzaDepositi(String username, JTextField txtField, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA LA TABELLA:
				theController.allDepoistiDiUnProp(username, model);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				btnBack.doClick();
			}
		});
		theController = c;
		
		setTitle("Finestra scelta del deposito");
		setSize(560, 524);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelTop, BorderLayout.NORTH);
		{
			lblWelcome = new JLabel("Scegli il deposito");
			lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panelTop.add(lblWelcome);
		}
		{
			panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new BorderLayout(0, 0));
			{
				scrollPane = new JScrollPane();
				panelCentral.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel(
							new Object[][]{},
							new String[]{ "id deposito", "Indirizzo", "Dimensione (m²)", "Raccolto"}
						);;
					
					table = new JTable(model);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int selRow = table.rowAtPoint(e.getPoint());
							if(selRow != -1) {
								JOptionPane.showMessageDialog(null, "Hai selezionato il deposito con l'id "+ String.valueOf(table.getValueAt(selRow, 0)));
								txtField.setText(String.valueOf(table.getValueAt(selRow, 0)));
								btnOk.setEnabled(true);
							}
						}
					});
					scrollPane.setColumnHeaderView(table);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				btnOk = new JButton("OK");
				btnOk.setEnabled(false);
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnBack.doClick();
					}
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						theController.AggEVisualizzaTerre.setVisible(true);
						theController.AggEVisualizzaTerre.setEnabled(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				buttonPane.add(btnBack, BorderLayout.WEST);
			}
		}
	}

}
