package proba1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelImagen extends JPanel{

	JButton bOK, bCancel, bSalir;
	JLabel lValor;
	JTextField texto1;
	JPasswordField pass;
	
	
	public PanelImagen(){				
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(200,450,200,450));
		this.add(crearPanelTextos());
		this.add(crearPanelBotones());
		
	}
	
	private Component crearPanelTextos() {
		JPanel panel = new JPanel (new GridLayout(2,1,20,20));
		texto1 = new JTextField();
		pass = new JPasswordField();
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(100,100,100,100),
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
	//	bOK.addActionListener(this);
		bOK.setActionCommand("OK");

		bSalir = new JButton("Salir");
	//	bSalir.addActionListener(this);
		bSalir.setActionCommand("Salir");

		panel.add(bOK);
		panel.add(bSalir);

		panel.setOpaque(false);
		
		return panel;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(new ImageIcon("/image/image7.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		setOpaque(false);
		super.paint(g);
	}	
}
