import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaginaColtivatore extends JFrame {
	private Controller theController;
	
	private int idColtivatore;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public PaginaColtivatore(String username, Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				idColtivatore = theController.trovaIdColtirvatore(username);
				if(idColtivatore == -1) {
					JOptionPane.showMessageDialog(null, "L'id non è stato trovato, ERRORE!");
				}
			}
		});
		
		theController = c;
		
		setTitle("Pagina del coltivatore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
