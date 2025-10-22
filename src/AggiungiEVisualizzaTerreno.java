import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AggiungiEVisualizzaTerreno extends JFrame {
	private Controller theController;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AggiungiEVisualizzaTerreno(Controller c, String Username) {
		theController = c;
		
		
		setTitle("Registra e visualizza terreni");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
