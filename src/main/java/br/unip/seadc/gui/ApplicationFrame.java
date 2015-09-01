package main.java.br.unip.seadc.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import main.java.br.unip.seadc.controllers.LerArquivo;

public class ApplicationFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private LerArquivo lerArquivo = new LerArquivo();
	
	private JCheckBox checkBoxFatores[];
	private JCheckBox checkBoxSintomas[];
	private JPanel fatoresDeRiscoPanel;
	private JPanel sintomasPanel;
	private JButton btnDiagnosticar;
	
	private List<JCheckBox> selectedFatoresList = new ArrayList<JCheckBox>();
	private List<JCheckBox> selectedSintomasList = new ArrayList<JCheckBox>();
	
	public ApplicationFrame(){
		setTitle("SEADC");
		setBounds(100, 100, 604, 560);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		initComponents();
	}

	public void initComponents() {
		
		fatoresDeRiscoPanel = new JPanel();
		fatoresDeRiscoPanel.setBorder(new TitledBorder(null, "Fatores de risco", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fatoresDeRiscoPanel.setBounds(6, 33, 592, 108);
		getContentPane().add(fatoresDeRiscoPanel);
		
		sintomasPanel = new JPanel();
		sintomasPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Sintomas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sintomasPanel.setBounds(6, 153, 592, 318);
		getContentPane().add(sintomasPanel);
		
		checkBoxFatores = new JCheckBox[lerArquivo.getFatoresList().size()];
		checkBoxSintomas = new JCheckBox[lerArquivo.getSintomasList().size()];
		
		for (int i = 0; i < lerArquivo.getFatoresList().size(); i++) {
			checkBoxFatores[i] = new JCheckBox(lerArquivo.getFatoresList().get(i));
			fatoresDeRiscoPanel.add(checkBoxFatores[i]);
		}
		
		for (int i = 0; i < lerArquivo.getSintomasList().size(); i++) {
			checkBoxSintomas[i] = new JCheckBox(lerArquivo.getSintomasList().get(i));
			sintomasPanel.add(checkBoxSintomas[i]);
		}
		
		btnDiagnosticar = new JButton("Diagnosticar");
		btnDiagnosticar.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/main/resources/imgs/btnDiagnosticar.png")));
		btnDiagnosticar.setBounds(461, 483, 137, 49);
		getContentPane().add(btnDiagnosticar);
		
		btnDiagnosticar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				// Make sure both lists are empty before adding anything
				selectedFatoresList.clear();
				selectedSintomasList.clear();
				
				for ( Component compFator : fatoresDeRiscoPanel.getComponents() ) {
					if( compFator instanceof JCheckBox ){
						if(((JCheckBox) compFator).isSelected()){
							selectedFatoresList.add((JCheckBox) compFator);
						}
					}
				}
				
				for ( Component compSintoma : sintomasPanel.getComponents() ) {
					if( compSintoma instanceof JCheckBox ){
						if(((JCheckBox) compSintoma).isSelected()){
							selectedSintomasList.add((JCheckBox) compSintoma);
						}
					}
				}
				
				// DEBUG
				for (JCheckBox v : selectedFatoresList) {
					System.out.println(v.getText());
				}
				
				for (JCheckBox x : selectedSintomasList) {
					System.out.println(x.getText());
				}
			}
		});
	}
	
	public static void main(String[] args) {
		ApplicationFrame applicationFrame = new ApplicationFrame();
		applicationFrame.setVisible(true);
	}

}
