import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaginaRegistraTerreno extends JFrame {
	private Controller theController;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSuperfice;
	private JComboBox comboBox;
	
	public PaginaRegistraTerreno(Utente u, String email, String partitaIva, Controller c) {
		theController = c;
		
		
		setTitle("Registra Terreno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Inserisci i dati di almeno un terreno per completare la registrazione");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTop.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblSuperfice = new JLabel("Superfice");
		lblSuperfice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblTipoTerreno = new JLabel("Tipo terreno");
		lblTipoTerreno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtSuperfice = new JTextField();
		txtSuperfice.setColumns(10);
		
		comboBox = new JComboBox(TipoTerreno.values());
		
		JLabel lblFertilità = new JLabel("Fertilità");
		lblFertilità.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JComboBox comboBoxFertilità = new JComboBox(Fertilità.values());
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap(132, Short.MAX_VALUE)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipoTerreno)
						.addComponent(lblSuperfice)
						.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxFertilità, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
							.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtSuperfice)))
					.addGap(130))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSuperfice)
						.addComponent(txtSuperfice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoTerreno, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxFertilità, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		panelCentral.setLayout(gl_panelCentral);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BorderLayout(0, 0));
		
		JButton btnCompleta = new JButton("Completa");
		btnCompleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrlFields(txtSuperfice.getText())) {
					double sup = Double.valueOf(txtSuperfice.getText());
					TipoTerreno typ = (TipoTerreno) comboBox.getSelectedItem();
					Fertilità fert = (Fertilità) comboBoxFertilità.getSelectedItem();
					theController.inserisciPropreitario(u, email, partitaIva, sup, typ, fert);
					JOptionPane.showMessageDialog(null, "Perfetto hai completato la registrazione!");
					clearField(txtSuperfice);
					theController.daTerrenoAHomePage();
				}
			}
		});
		panelBottom.add(btnCompleta, BorderLayout.EAST);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearField(txtSuperfice);
				//BISOGNA AGGIUNGERE UNA FUNZIONE CHE FACCIA RITORNARE INDIETRO:
			}
		});
		panelBottom.add(btnBack, BorderLayout.WEST);
	}
	
//METODO:
	
	public boolean ctrlFields(String superfice) {
		//CONTROLLO SUPERFICE:
		if(superfice.isBlank()) {
			JOptionPane.showMessageDialog(null, "Il campo superfice non può essere vuoto!");
			return false;
		}else {
			try {
				double val = Double.parseDouble(superfice);
				if(val <= 0) {
					JOptionPane.showMessageDialog(null, "La superfice del deposito nonnpuò essere inferiore o uguale a 0!");
					return false;
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "C'è stato un errore nella conversione del valore!");
				return false;
			}
		}
		return true;
	}
	
	
	private void clearField(JTextField superfice) {
		superfice.setText(null);
	}
}
