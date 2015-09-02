package main.java.br.unip.seadc.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import jess.Filter;
import jess.JessException;
import jess.Rete;
import main.java.br.unip.seadc.beans.Diagnostico;
import main.java.br.unip.seadc.beans.FatorDeRisco;
import main.java.br.unip.seadc.beans.Sintoma;
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

	private List<String> keywordFatoresList = new ArrayList<String>();

	private List<String> keywordSintomasList = new ArrayList<String>();
	
	private Iterator<Diagnostico> it;
	
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
			@SuppressWarnings("unchecked")
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
				
				assignMatchedKeywords();
				
				Rete engine = new Rete();
				
				try {
					engine.reset();
					engine.batch("src/main/resources/files/config/regras.clp");
					
					FatorDeRisco[] f = new FatorDeRisco[selectedFatoresList.size()];
					Sintoma[] s = new Sintoma[selectedSintomasList.size()];
					
					for (int i = 0; i < f.length; i++) {
						f[i] = new FatorDeRisco(keywordFatoresList.get(i));
						engine.add(f[i]);
					}
					
					for (int j = 0; j < s.length; j++) {
						s[j] = new Sintoma(keywordSintomasList.get(j));
						engine.add(s[j]);
					}
					
					engine.run();
					
					it = (engine.getObjects(new Filter.ByClass(Diagnostico.class)));
					
					List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
					
					while(it.hasNext()){
						diagnosticos.add(it.next());
					}
					
					if(diagnosticos != null && diagnosticos.size() > 0){
						System.out.println(diagnosticos.get(0).toString());
					}else{
						JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum diagnóstico compatível com as características informadas!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (JessException je) {
					je.printStackTrace();
				}
			}
		});
	}
	
	private void assignMatchedKeywords(){
		
		// Make sure both lists are empty before adding anything
		keywordFatoresList.clear();
		keywordSintomasList.clear();
		
		for (JCheckBox check : selectedFatoresList) {
			keywordFatoresList.add(lerArquivo.getFatoresMap().get(check.getText()));
		}
		
		for (JCheckBox check : selectedSintomasList) {
			keywordSintomasList.add(lerArquivo.getSintomasMap().get(check.getText()));
		}
	}
	
	public static void main(String[] args) {
		ApplicationFrame applicationFrame = new ApplicationFrame();
		applicationFrame.setVisible(true);
	}

}
