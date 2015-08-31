package main.java.br.unip.seadc.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static WelcomeFrame instance = null;

	public static WelcomeFrame getInstance(){
		
		if(instance == null)
			instance = new WelcomeFrame();
		
		return instance;
	}

	public WelcomeFrame() {
		setTitle("Sistema de Triagem Cardiol\u00F3gica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 371);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imgHumanBody = new JLabel();
		imgHumanBody.setIcon(new ImageIcon(WelcomeFrame.class.getResource("/main/resources/imgs/human_body.png")));
		imgHumanBody.setBounds(10, 11, 160, 320);
		contentPane.add(imgHumanBody);
		
		JLabel lblWelcomeText = new JLabel("<html><strong>Sistema de Triagem Cardiol\u00F3gica</strong><br/>\r\n<br/>\r\n<p>O SDTC \u00E9 um sistema de triagem cardiol\u00F3gica <br> que utiliza um motor de infer\u00EAncia (IA) <br>para realizar o diagn\u00F3stico/triagem do paciente. <br>O sistema pode ser utilizado por qualquer pessoa, <br>entretanto, \u00E9 necess\u00E1rio que o conhecimento de um especialista humano<br> seja utilizado para incrementar a base de conhecimento.</p><br/>\r\n<br/>\r\n<p>Para iniciar a triagem do paciente, clique em <strong>Iniciar</strong></p></html>");
		lblWelcomeText.setBounds(180, 11, 269, 260);
		contentPane.add(lblWelcomeText);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new ApplicationFrame();
			}
		});
		btnIniciar.setIcon(new ImageIcon(WelcomeFrame.class.getResource("/main/resources/imgs/button_start.png")));
		btnIniciar.setBounds(337, 294, 112, 37);
		contentPane.add(btnIniciar);		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame frame = new WelcomeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
