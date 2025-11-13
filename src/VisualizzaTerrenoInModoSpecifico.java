import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualizzaTerrenoInModoSpecifico extends JFrame {
	private Controller theController;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelProgetto;
	private Terreno terrSelezionato;
	private JButton btnVisualizzaAltriDettagli;
	private int idProgettoSelezionato;
	
	
	public VisualizzaTerrenoInModoSpecifico(String idTerreno, Controller c) {
		theController = c;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					theController.popolaTabellaProgettiPerTerreno(Integer.valueOf(idTerreno), modelProgetto);
				}catch(Exception x) {
					JOptionPane.showMessageDialog(null, "Errore nel popolamento della tabella dei progetti, nella pagina visualizzaTerrenoInMododSpecifico"+ x);
				}
			}
		});
		
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
		
		JLabel lblVisualizzaProgetti = new JLabel("Elenco progetti");
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
				.addGroup(Alignment.LEADING, gl_panelCentral.createSequentialGroup()
					.addGap(66)
					.addComponent(lblDatiTerrenoSelezionato)
					.addPreferredGap(ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
					.addComponent(lblVisualizzaProgetti)
					.addGap(137))
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFertilità, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblSuperfice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblTipoTerreno, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 112, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblTipoTerrenoComparsa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblFertiliàComparsa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSuperficeComparsa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
					.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatiTerrenoSelezionato, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVisualizzaProgetti))
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.rowAtPoint(e.getPoint());
				if(selectedRow != -1) {
					String idProgettoStringSel = String.valueOf(table.getValueAt(selectedRow, 0));
					idProgettoSelezionato = Integer.valueOf(idProgettoStringSel);
					
				}
			}
		});
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
		panelBottom.setLayout(new BorderLayout(0, 0));
		panelBottom.add(btnBack, BorderLayout.WEST);
		btnVisualizzaAltriDettagli = new JButton("Altri dettagli");
		btnVisualizzaAltriDettagli.setEnabled(false);
		btnVisualizzaAltriDettagli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DEVO POTER VISUALIZZARE IL TIPO DI ATTIVITA' CHE SI STA FACENDO SUL TERRENO :
				theController.daPaginaTerrenoSpecificoAPaginaAttività(Integer.valueOf(idTerreno.trim()), idProgettoSelezionato);
			}
		});
		panelBottom.add(btnVisualizzaAltriDettagli, BorderLayout.EAST);

	}
	
//METODI:
	//PER TORNARE INDIETRO:
	private void daPaginaVisualizzaTerrenoA() {
		theController.paginaTerrenoSpecifico.setVisible(false);
		
		theController.paginaProprietario.setVisible(true);
		theController.AggEVisualizzaTerre.setVisible(true);		
	}
}
