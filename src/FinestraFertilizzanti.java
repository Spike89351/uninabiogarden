import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FinestraFertilizzanti extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	
	public FinestraFertilizzanti(int idDep, Controller c) {
		theController = c;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				theController.paginaDettagliDeposito.setEnabled(true);
			}
		});
		
		setTitle("Pagina Fertilizzanti");
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
				JLabel lblWelcome = new JLabel("Qui puoi aggiungere i fertilizzanti al tuo deposito con id "+ idDep);
				lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panelTop.add(lblWelcome);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			JLabel lblMessaggioAttenzione = new JLabel("Attenzione il tuo fertilizzante si sommerà a quello già presente");
			lblMessaggioAttenzione.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMessaggioAttenzione.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel lblLetame = new JLabel("Letame");
			lblLetame.setFont(new Font("Tahoma", Font.PLAIN, 12));
			JLabel lblCompost = new JLabel("Compost");
			lblCompost.setFont(new Font("Tahoma", Font.PLAIN, 12));
			JLabel lblGranulari = new JLabel("Granulari");
			lblGranulari.setFont(new Font("Tahoma", Font.PLAIN, 12));
			JLabel lblLiquidi = new JLabel("Liquidi");
			lblLiquidi.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
			gl_panelCentral.setHorizontalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
									.addComponent(lblMessaggioAttenzione, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
									.addComponent(lblLetame, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCompost, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblGranulari, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panelCentral.createSequentialGroup()
								.addGap(14)
								.addComponent(lblLiquidi, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCentral.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblMessaggioAttenzione)
						.addGap(18)
						.addComponent(lblLetame)
						.addGap(18)
						.addComponent(lblCompost, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblGranulari, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addGap(25)
						.addComponent(lblLiquidi, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(51, Short.MAX_VALUE))
			);
			panelCentral.setLayout(gl_panelCentral);
		}
		{
			JPanel panelBottom = new JPanel();
			getContentPane().add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new BorderLayout(0, 0));
			{
				JButton btnAggiungi = new JButton("Aggiungi");
				btnAggiungi.setToolTipText("I fertilizzanti che inserirai verranno sommati a quelli già presenti");
				btnAggiungi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//AGGIUNGI FERTILIZZANTE:
						if(theController.inserisciFertilizzante(idDep, idDep, idDep, idDep, idDep)) {
							JOptionPane.showMessageDialog(null, "L'inserimento dei fertilizzante è avvenuto con successo");
						}
					}
				});
				btnAggiungi.setActionCommand("OK");
				panelBottom.add(btnAggiungi, BorderLayout.EAST);
				getRootPane().setDefaultButton(btnAggiungi);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnBack.setActionCommand("Cancel");
				panelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}

}
