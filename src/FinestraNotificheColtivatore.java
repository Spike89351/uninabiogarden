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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinestraNotificheColtivatore extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private JButton btnVisualizza;
	private JButton btnBack;
	
	public FinestraNotificheColtivatore(int idColt, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA TABELLA:
				
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
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
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelCentral.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel(
							new Object[][]{},
							new String[]{"Tipo", "Descrizione", "Data invio"}
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
				btnVisualizza = new JButton("Segna come lette");
				panelBottom.add(btnVisualizza, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnVisualizza);
			}
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						theController.paginaColtivatore.setEnabled(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}

}
