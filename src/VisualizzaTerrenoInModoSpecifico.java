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
