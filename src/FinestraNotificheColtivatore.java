import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FinestraNotificheColtivatore extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	
	
	public FinestraNotificheColtivatore(int idColt, Controller c) {
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
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelCentral.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel(
							new Object[][]{},
							new String[]{ "", "Tipo", "Descrizione", "Data invio"}
						);
					
					table = new JTable(model);
					scrollPane.setColumnHeaderView(table);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel panelBottom = new JPanel();
			getContentPane().add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new BorderLayout(0, 0));
			{
				JButton btnVisualizza = new JButton("Segna come lette");
				btnVisualizza.setActionCommand("OK");
				panelBottom.add(btnVisualizza, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnVisualizza);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}

}
