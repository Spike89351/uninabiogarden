import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Questa è la tua pagina");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGap(0, 428, Short.MAX_VALUE)
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGap(0, 203, Short.MAX_VALUE)
		);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FAI IL LOGOUT:
				setVisible(false);
				theController.homePage.setVisible(true);
			}
		});
		panelBottom.add(btnLogout, BorderLayout.WEST);

	}

}
