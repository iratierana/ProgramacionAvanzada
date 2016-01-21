package proba1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainFrame implements ActionListener{

	JFrame ventana;
	JButton bOK, bCancel, bSalir;
	JLabel lValor;
	JTextField texto1;
	JPasswordField pass;
	static final String USERNAMECORRECTO = "admin";
	static final char[] PASSWORDCORRECTO = {'a','d','m','i','n'};
	static final String FONDO = "/image7.jpg";
	
	
	public MainFrame(){
		ventana = new JFrame ("Control de acceso");
	//	ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setSize(300,300);
		ventana.setLocation (100,100);
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(0,60));
		panel.setBorder(BorderFactory.createEmptyBorder(60,20,20,20));
		panel.add(crearPanelTextos(),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		
		return panel;
	}


	private Component crearPanelTextos() {
		JPanel panel = new JPanel (new GridLayout(2,1,20,20));
		texto1 = new JTextField();
		pass = new JPasswordField();
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,00,00,00),
				BorderFactory.createTitledBorder("Identificacion")));
		
		JLabel username = new JLabel("Username:");
		JLabel password = new JLabel("Password:");
		
		password.setBorder(BorderFactory.createRaisedBevelBorder());
		username.setHorizontalAlignment(JLabel.CENTER);
		password.setHorizontalAlignment(JLabel.CENTER);
		username.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel.add(username);
		panel.add(texto1);
		panel.add(password);
		panel.add(pass);
		
		panel.setOpaque(false);
		
		return panel;
	}
	

	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new GridLayout(1,2,20,0));
		panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

		bOK = new JButton("OK");
		bOK.addActionListener(this);
		bOK.setActionCommand("OK");

		bSalir = new JButton("Salir");
		bSalir.addActionListener(this);
		bSalir.setActionCommand("Salir");

		panel.add(bOK);
		panel.add(bSalir);

		panel.setOpaque(false);
		
		return panel;
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		String username;
		char[] password;
		
		if(e.getSource() == bOK){
			username = texto1.getText();
			password = pass.getPassword();
			if(username.equals(USERNAMECORRECTO) && Arrays.equals(password, PASSWORDCORRECTO)){
				ventana.setVisible(false);
				ventana.dispose();
			}else{
				texto1.setText(null);
				pass.setText(null);
			}
		}
		if(e.getSource() == bSalir){
			ventana.setVisible(false);
			ventana.dispose();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mainframe = new MainFrame();

	}
	
}
