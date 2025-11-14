import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PaginaColtivatore extends JFrame {
	private Controller theController;
	
	private int idColtivatore;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public PaginaColtivatore(String username, Controller c) {
		theController = c;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
