import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VisualizzaTerrenoInModoSpecifico extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public VisualizzaTerrenoInModoSpecifico(Controller c, String idTerreno) {
		theController = c;
		
		setTitle("Terreno Selezionato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(618, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("L'id del terreno che stai visualizzando è "+idTerreno);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblVisualizzaProgetti = new JLabel("Progetti");
		lblVisualizzaProgetti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblDatiTerrenoSelezionato = new JLabel("Dati terreno selezionato");
		lblDatiTerrenoSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panel = new JPanel();
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelCentral.createSequentialGroup()
					.addGap(66)
					.addComponent(lblDatiTerrenoSelezionato)
					.addPreferredGap(ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
					.addComponent(lblVisualizzaProgetti, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(93))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap(414, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVisualizzaProgetti)
						.addComponent(lblDatiTerrenoSelezionato, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TORNA INDIETRO:
				setVisible(false);
				daPaginaVisualizzaTerrenoA();				
			}
		});
		panelBottom.add(btnBack);

	}
	
//METODI:
	//PER TORNARE INDIETRO:
	private void daPaginaVisualizzaTerrenoA() {
		theController.paginaTerrenoSpecifico.setVisible(false);
		
		theController.paginaProprietario.setVisible(true);
		theController.AggEVisualizzaTerre.setVisible(true);		
	}
}
