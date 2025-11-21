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
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	public FinestraVisualizzaColtivatoriAttività(int idAttività, String statAtt, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				theController.popolaTabellaConColtivatoriAssociatiAllAttività(idAttività, model, statAtt);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setEnabled(false);
				setVisible(false);
				theController.paginaAttività.setVisible(true);
				theController.paginaAttività.setEnabled(true);
			}
		});
		theController = c;
		
		setTitle("Finestra per visualizzare i coltivatori associati all'attività");
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
							new String[]{"id", "username", "Nome", "Cognome", "Data di Nascita"}
						);
					
					table = new JTable(model);
					
					// Abilita la selezione multipla
			        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			        
			        selectedIDs = new int[0];

			        // Aggiungi un ListSelectionListener per aggiornare l'array in tempo reale
			        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			            @Override
			            public void valueChanged(ListSelectionEvent e) {
			                if (!e.getValueIsAdjusting()) {
			                    // 9. Recupera gli indici delle righe selezionate
			                    int[] selectedRows = table.getSelectedRows();

			                    // 10. Aggiorna l'array degli ID selezionati
			                    selectedIDs = new int[selectedRows.length];
			                    for (int i = 0; i < selectedRows.length; i++) {
			                        int row = selectedRows[i];
			                        String val = String.valueOf(table.getValueAt(row, 0));
			                        selectedIDs[i] = Integer.valueOf(val); // ID nella prima colonna
			                    }

			                }
			            }
			        });			        
			        
					scrollPane.setColumnHeaderView(table);
					scrollPane.setViewportView(table);
				}
			}
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
						theController.paginaAttività.setVisible(true);
						theController.paginaAttività.setEnabled(true);
					}
				});
				btnBack.setActionCommand("Cancel");
				panelButtom.add(btnBack);
			}
		}
	}

}
