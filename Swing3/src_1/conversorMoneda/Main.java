package conversorMoneda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class Main implements ActionListener {

	JFrame ventana;
	JPanel panel;
	JLabel cantidad;
	JTextField cantidadIn;
	JComboBox<String> comboBoxDerecha;
	JComboBox<String> comboBoxIzquierda;
	
	
	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();

	}

	public void miMain(){
		ventana = new JFrame("Conversor de moneda");
		ventana.setSize(600, 300);
		ventana.setLocation(450,20);
		
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(crearPanelCentral(), BorderLayout.CENTER);
		panel.add(crearPanelSur(), BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearPanelCentral() {
		JSplitPane panelCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		panelCentral.setDividerLocation(200);
		panelCentral.setLeftComponent(crearPanelIzquierdo());
		panelCentral.setRightComponent(crearPanelDerecho());
		
		return panelCentral;
	}
	
	private Component crearPanelIzquierdo() {
		JPanel panelIzquierdo = new JPanel(new BorderLayout());
		cantidadIn = new JTextField("");
		
		comboBoxIzquierda = new JComboBox<>();
		comboBoxIzquierda = new JComboBox<String>(new String [] {"Euros", "Libras", "Dolares"});
		
		panelIzquierdo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.BLUE),BorderFactory.createEmptyBorder(10,10,10,10)));
		
		cantidadIn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Cantidad", 0, 2,new Font("Verdana", Font.PLAIN, 12), Color.BLACK));
		panelIzquierdo.add(comboBoxIzquierda , BorderLayout.NORTH);
		panelIzquierdo.add(cantidadIn, BorderLayout.CENTER);
		
		return panelIzquierdo;
	}
	
	private Component crearPanelDerecho() {
		JPanel panelDerecho = new JPanel(new BorderLayout());
		cantidad = new JLabel("");
		
		comboBoxDerecha = new JComboBox<>();
		comboBoxDerecha = new JComboBox<String>(new String [] {"Euros", "Libras", "Dolares"});
		
		panelDerecho.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE),BorderFactory.createEmptyBorder(10,10,10,10)));		
		
		cantidad.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Cantidad", 0, 2,new Font("Verdana", Font.PLAIN, 12), Color.BLACK));
		panelDerecho.add(comboBoxDerecha , BorderLayout.NORTH);
		panelDerecho.add(cantidad, BorderLayout.CENTER);
		
		return panelDerecho;
	}


	private Component crearPanelSur() {
		JPanel panelSur = new JPanel();
		JButton boton = new JButton("Mostrar cambio");
		
		boton.addActionListener(this);
		boton.setActionCommand("boton");
		panelSur.add(boton);
		
		return panelSur;
	}
	
	private double calcularCambio() {
		double valor;
		String stringDe,stringA;
		stringDe = (String) comboBoxIzquierda.getSelectedItem();
		stringA = (String) comboBoxDerecha.getSelectedItem();
		valor = Conversor.calcularValor(Double.parseDouble(cantidadIn.getText()), stringDe, stringA);
		return valor;
	}
	
	public void actionPerformed(ActionEvent e){
		double valorLabel;
		
		switch (e.getActionCommand()) {
		case "boton":
			valorLabel=calcularCambio();
			cantidad.setText(String.valueOf(valorLabel));
			break;

		default:
			break;
		}
	}

	
}
