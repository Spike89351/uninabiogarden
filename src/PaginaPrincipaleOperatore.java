import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PaginaPrincipaleOperatore extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public PaginaPrincipaleOperatore(Controller c) {
		theController = c;
		
		setTitle("Pagina Principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);//RENDE LA PAGINA A SCHERMO INTERO DIRETTAMENTE;
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
