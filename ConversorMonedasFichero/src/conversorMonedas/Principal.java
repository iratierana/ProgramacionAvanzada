package conversorMonedas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Principal implements ActionListener {
	
	//final String FICH_PROPIEDADES = "propiedades.txt";
	final static int ANCHOPANTALLA = 600;
	final static int LARGOPANTALLA = 300;
	final static int POSPANTALLAX = 100;
	final static int POSPANTALLAY = 100;
	int distanciaBorde = 20;
	String nombre = "Conversor de monedas";
	JFrame ventana;
	JLabel resultado;
	JToolBar toolBar;
	JTextField texto;
	JComboBox<String> tipoMonedasOrigen, tipoMonedasDestino;
	Properties propiedades;
	
	public Principal(){
	
		try {
			propiedades = new Properties();
			propiedades.load(new FileInputStream("castellano.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Se utilizarán las propiedades por defecto");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ventana = new JFrame(propiedades.getProperty("TITULO","conversor"));
		ventana.setSize(ANCHOPANTALLA, LARGOPANTALLA);
		ventana.setResizable(false);
		ventana.setLocation(POSPANTALLAX, POSPANTALLAY);
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearToolBar(), BorderLayout.NORTH);
		panel.setBorder(BorderFactory.createEmptyBorder(distanciaBorde,distanciaBorde,distanciaBorde,distanciaBorde));
		panel.add(crearAbajo(), BorderLayout.SOUTH);
		panel.add(creaCentro(), BorderLayout.CENTER);
		return panel;
	}

	private Component crearToolBar() {
		toolBar = new JToolBar();
		toolBar.setBorder(BorderFactory.createRaisedBevelBorder());	
		
		toolBar.add(Box.createHorizontalGlue());
		
		JButton boton;
		boton = (JButton) toolBar.add(new JButton("Castellano"));
		boton.setActionCommand("Castellano");
	//	boton.setActionCommand(this);
		
		boton = (JButton) toolBar.add(new JButton("Ingles"));
		boton.setActionCommand("Ingles");
	//	boton.setActionCommand(this);
		
		return toolBar;
	}

	private Component creaCentro() {
		int fila = 1;
		int columna = 2;
		JPanel panel = new JPanel(new BorderLayout());
	//	JPanel panel = new JPanel(new GridLayout(fila,columna));
		panel.add(creaDentroOrigen(), BorderLayout.WEST);
		panel.add(creaDentroDestino(), BorderLayout.CENTER);
		return panel;
	}


	private Component creaDentroDestino() {
		int fila = 2;
		int columna = 1;
		
		JPanel panel = new JPanel(new GridLayout(fila,columna));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(0, Color.black, Color.cyan, Color.cyan, Color.cyan), 
				BorderFactory.createEmptyBorder(distanciaBorde,distanciaBorde,distanciaBorde,distanciaBorde)));
		panel.add(meteTablaMonedas2());
		panel.add(dameMonedaCambiada());
		return panel;
	}

	private Component dameMonedaCambiada() {
		resultado = new JLabel("0");
		resultado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0, Color.LIGHT_GRAY, Color.white), propiedades.getProperty("BORDE_COMBOIZ","Cantidad"),
				0, 0, (new Font("arial", Font.BOLD, 12)), Color.black));
		return resultado;
	}

	private Component meteCantidad() {
		texto = new JTextField();
		texto.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0, Color.LIGHT_GRAY, Color.white), propiedades.getProperty("BORDE_COMBOIZ","Cantidad"),
				0, 0, (new Font("arial", Font.BOLD, 12)), Color.black));
		texto.addActionListener(this);
		return texto;
	}

	private Component meteTablaMonedas() {
		String monedas [] = {"                                           ","Euro", "Libra", "Peso", "Rublo", "Peseta", "Corona", "$"};
		tipoMonedasOrigen = new JComboBox<>(monedas);
		tipoMonedasOrigen.setBorder(BorderFactory.createEmptyBorder(distanciaBorde,0,distanciaBorde,0));
		return tipoMonedasOrigen;
	}
	
	private Component meteTablaMonedas2() {
		String monedas [] = {"                                           ","Euro", "Libra", "Peso", "Rublo", "Peseta", "Corona", "$"};
		tipoMonedasDestino = new JComboBox<>(monedas);
		tipoMonedasDestino.setBorder(BorderFactory.createEmptyBorder(distanciaBorde,0,distanciaBorde,0));
		return tipoMonedasDestino;
	}

	private Component creaDentroOrigen() {
		int fila = 2;
		int columna = 1;
		JPanel panel = new JPanel(new GridLayout(fila,columna));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(0, Color.black, Color.cyan, Color.cyan, Color.cyan),
				BorderFactory.createEmptyBorder(distanciaBorde,distanciaBorde,distanciaBorde,distanciaBorde)));
		panel.add(meteTablaMonedas());
		panel.add(meteCantidad());
		return panel;
	}

	private Component crearAbajo(){
		String nameBoton = propiedades.getProperty("BOTON","mostrar cambio");
		JPanel panel = new JPanel();
		JButton boton = new JButton(nameBoton);
		boton.setActionCommand(nameBoton);
		boton.addActionListener(this);
		panel.add(boton);
		return panel;
	}

	public void actionPerformed(ActionEvent evento) {
		double monedaInicio = 0;
		double aux = 0;
		double monedaFinal = 0;
		float Euro_dolar = 1.09765f;
		float Euro_peseta = 166.6666667f;
		float Euro_peso = 18.2336916f;
		float Euro_libra = 0.716652466f;
		float Euro_rublo = 70.577f;
		float Euro_corona = 27.113f;
		String tipoInicio, tipoFinal, monedas;
		try{
			if(evento.getActionCommand().equals("Mostrar cambio")){
				monedas =  texto.getText();
				monedaInicio = Double.parseDouble(monedas);
				tipoInicio = ""+tipoMonedasOrigen.getSelectedItem();
				switch (tipoInicio){
					case "Euro":  aux = monedaInicio;break;
					case "$":  aux = monedaInicio/Euro_dolar;break;
					case "Peseta": aux = monedaInicio/Euro_peseta;break;
					case "Peso": aux = monedaInicio/Euro_peso;break;
					case "Libra": aux = monedaInicio/Euro_libra;break;
					case "Rublo": aux = monedaInicio/Euro_rublo;break;
					case "Corona": aux = monedaInicio/Euro_corona;break;
					default: break;
				}
				tipoFinal = ""+tipoMonedasDestino.getSelectedItem();
				switch (tipoFinal){
					case "Euro":  monedaFinal = aux;break;
					case "$":  monedaFinal = aux*Euro_dolar;break;
					case "Peseta": monedaFinal = aux*Euro_peseta;break;
					case "Peso": monedaFinal = aux*Euro_peso;break;
					case "Libra": monedaFinal = aux*Euro_libra;break;
					case "Rublo": monedaFinal = aux*Euro_rublo;break;
					case "Corona": monedaFinal = aux*Euro_corona;break;
					default: break;
				}
			}
			resultado.setText(String.valueOf(monedaFinal));
		}
		catch(NumberFormatException exception){
			System.out.println("Mete solo numeros");
		}
		
		if(evento.getActionCommand().toLowerCase().equals("ingles")){
			try {
				propiedades.load(new FileInputStream("english.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}

	public static void main(String[] args) {
		Principal ejercicio = new Principal();
	}


}

