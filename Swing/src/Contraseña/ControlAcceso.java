package Contraseņa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ControlAcceso extends JFrame implements ActionListener {

	JFrame ventana;

	String nombre;
	String contraseņa;

	JButton ok, cancelar, salir;

	public ControlAcceso() {
		ventana = new JFrame("Control de acceso");
		ventana.setSize(400, 500);
		ventana.setLocation(450, 200);

		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(30,5,5,5));
		panel.add(panelSur(), BorderLayout.SOUTH);
		panel.add(panelCentro(),BorderLayout.CENTER);
		return panel;
	}

	private Component panelCentro() {
		JPanel panel = new JPanel(new GridLayout(2,2,20,10));
		JLabel lValor;
		JLabel lValor1;
		
		JTextField text = new JTextField();
		JPasswordField pass = new JPasswordField();
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(0,Color.RED, Color.red),"Identificacion"));
				
		
		lValor = new JLabel();
		lValor.setFont(new Font("Verdana", Font.CENTER_BASELINE,12));
		lValor.setForeground(Color.black);
		lValor.setHorizontalAlignment(lValor.LEFT);
		
		
		lValor1 = new JLabel();
		lValor1.setFont(new Font("Verdana", Font.CENTER_BASELINE,12));
		lValor1.setForeground(Color.black);
		lValor1.setHorizontalAlignment(lValor.LEFT);
		
		lValor.setText("Usuario:");
		panel.add(lValor);
		
		lValor.setText("Contraseņa:");
		panel.add(lValor1);
		
		text = new JTextField();
		
		pass = new JPasswordField();
		
		
		return panel;
	}

	private Component panelSur() {
		JPanel panel = new JPanel(new GridLayout(1,3,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(20,5,0,0));
		
		ok = new JButton("ok");
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		panel.add(ok);
		
		cancelar = new JButton("cancelar");
		cancelar.addActionListener(this);
		ok.setActionCommand("cancelar");
		panel.add(cancelar);
		
		salir = new JButton("salir");
		salir.addActionListener(this);
		ok.setActionCommand("salir");
		panel.add(salir);
		
		return panel;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		ControlAcceso ejercicion = new ControlAcceso();
	}
}
