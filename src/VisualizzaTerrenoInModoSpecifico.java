import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JProgressBar;

public class VisualizzaTerrenoInModoSpecifico extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelProgetto;
	private Terreno terrSelezionato;
	
	public VisualizzaTerrenoInModoSpecifico(Controller c, String idTerreno) {
		theController = c;
		
		terrSelezionato = theController.trovaTerreno(idTerreno);
		
		
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
		
		JPanel panelTable = new JPanel();
		
		JLabel lblSuperfice = new JLabel("La superfice è");
		lblSuperfice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblTipoTerreno = new JLabel("Il tipo di terreno è ");
		lblTipoTerreno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblFertilità = new JLabel("La fertilità del terreno è ");
		lblFertilità.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblFertiliàComparsa = new JLabel(""+terrSelezionato.getfertTerreno().toString());
		lblFertiliàComparsa.setForeground(Color.RED);
		lblFertiliàComparsa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblTipoTerrenoComparsa = new JLabel(""+terrSelezionato.getTipologiaTerreno().toString());
		lblTipoTerrenoComparsa.setForeground(Color.RED);
		lblTipoTerrenoComparsa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblSuperficeComparsa = new JLabel(""+terrSelezionato.getSuperficie());
		lblSuperficeComparsa.setForeground(Color.RED);
		lblSuperficeComparsa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addGap(66)
					.addComponent(lblDatiTerrenoSelezionato)
					.addPreferredGap(ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
					.addComponent(lblVisualizzaProgetti, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(93))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblSuperfice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblTipoTerreno, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 112, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFertiliàComparsa, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoTerrenoComparsa, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSuperficeComparsa, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVisualizzaProgetti)
						.addComponent(lblDatiTerrenoSelezionato, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSuperfice)
								.addComponent(lblSuperficeComparsa))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipoTerreno, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipoTerrenoComparsa))
							.addGap(18)
							.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFertiliàComparsa))))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		modelProgetto = new DefaultTableModel(
				new Object[][]{},
				new String[]{ "id Progetto", "Nome progetto", "Data inizio", "Stato progetto"}
			);;
		
		table = new JTable(modelProgetto);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
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
