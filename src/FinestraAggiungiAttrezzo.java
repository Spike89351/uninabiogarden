import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinestraAggiungiAttrezzo extends JDialog {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JComboBox<TipoAttrezzo> comboBoxTipoAttrezzo;
	private JComboBox<StatoAttrezzo> comboBoxStatoAttrezzo;
	
	public FinestraAggiungiAttrezzo(int idDeposito, Controller c) {
		theController = c;
		
		setTitle("Aggiungi attrezzo");
		setSize(535, 318);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPanel.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Qui puoi aggiungere un attrezzo");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPanel.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblTipoAttrezzo = new JLabel("Tipo");
		lblTipoAttrezzo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		comboBoxTipoAttrezzo = new JComboBox<TipoAttrezzo>(TipoAttrezzo.values());
		
		comboBoxStatoAttrezzo = new JComboBox<StatoAttrezzo>(StatoAttrezzo.values());
		
		JPanel panel = new JPanel();
		
		JLabel lblDatiAttrezzo = new JLabel("Dati attrezzo");
		lblDatiAttrezzo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDatiAttrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblElencoAttrezzi = new JLabel("Elenco Attrezzi ");
		lblElencoAttrezzi.setHorizontalAlignment(SwingConstants.CENTER);
		lblElencoAttrezzi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblTipoAttrezzo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBoxTipoAttrezzo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBoxStatoAttrezzo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(lblNome)
							.addGap(18)
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDatiAttrezzo, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
						.addComponent(lblElencoAttrezzi, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblElencoAttrezzi, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(25)
					.addComponent(lblDatiAttrezzo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoAttrezzo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxTipoAttrezzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStato, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxStatoAttrezzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		panelCentral.setLayout(gl_panelCentral);
		{
			JPanel PanelBottom = new JPanel();
			getContentPane().add(PanelBottom, BorderLayout.SOUTH);
			PanelBottom.setLayout(new BorderLayout(0, 0));
			{
				JButton btnAggiungi = new JButton("Aggiungi");
				btnAggiungi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//AGGIUNGI ATTREZZO NEL DATA BASE:
						
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
						
					}
				});
				btnBack.setActionCommand("Cancel");
				PanelBottom.add(btnBack, BorderLayout.WEST);
			}
		}
	}
}
