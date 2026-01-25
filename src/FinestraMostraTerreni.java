import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FinestraMostraTerreni extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnBack;
	private JButton btnOk;
	private JPanel panelTop;
	private JPanel panelCentral;
	private JLabel lblWelcome;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	
	public FinestraMostraTerreni(String username, JTextField txtField, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				//POPOLA LA TABELLA CON I TERRENI DEL PROPRIETARIO:
				theController.popolaTabellaConITerreniLiberi(username, model);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				btnBack.doClick();
			}
		});
		theController = c;
		
		setTitle("Finestra terreni in possesso");
		setSize(450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			{
				lblWelcome = new JLabel("Scegli il terreno dove si svolgerà il progetto");
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
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
							new String[]{ "Id terreno", "Indirizzo", "Superficie (m²)", "Tipo terreno", "Fertilità"}
						);;
					
					table = new JTable(model);
					table.getColumnModel().getColumn(0).setMinWidth(0);
					table.getColumnModel().getColumn(0).setMaxWidth(0);
					table.getColumnModel().getColumn(0).setWidth(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(0);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int selRow = table.rowAtPoint(e.getPoint());
							if(selRow != -1) {
								JOptionPane.showMessageDialog(null, "Il terreno è stato selezioanto");
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
				btnBack = new JButton("BACK");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						theController.paginaProprietario.setVisible(true);
						theController.paginaProprietario.setEnabled(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				buttonPane.add(btnBack, BorderLayout.WEST);
			}
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
		}
	}

}
