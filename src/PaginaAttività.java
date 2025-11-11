import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class PaginaAttività extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel panelCentral;
	private JPanel panelBottom;
	private JLabel lblWelcome;
	
	public PaginaAttività(int idTerreno, Controller c) {
		theController = c;
		
		setTitle("Pagina attività");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		lblWelcome = new JLabel("Cambia il tipo di attività sul terreno con id "+idTerreno);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblWelcome);
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
	}

}
