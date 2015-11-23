package imagenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Principal implements ActionListener, ListSelectionListener {
	
	final static int ANCHOPANTALLA = 640;
	final static int LARGOPANTALLA = 480;
	final static int POSPANTALLAX = 100;
	final static int POSPANTALLAY = 100;
	int distanciaBorde = 20;
	String nombre = "Saca Imagenes";
	JFrame ventana;
	JScrollPane panelImagenes, scrollImagen;
	JLabel panelImagen;
	File file;
	JList<File>lista;
	DefaultListModel<File>modelo;
	ImageIcon imagen;
	
	public Principal(){
		ventana = new JFrame(nombre);
		ventana.setSize(ANCHOPANTALLA, LARGOPANTALLA);
		ventana.setResizable(false);
		ventana.setLocation(POSPANTALLAX, POSPANTALLAY);
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black, Color.black, Color.black));
		panel.add(creaNorte(), BorderLayout.NORTH);
		panel.add(creaSur(), BorderLayout.SOUTH);
		panel.add(creaOeste(), BorderLayout.WEST);
		panel.add(creaCentro(), BorderLayout.CENTER);
		return panel;
	}

	private Component creaCentro(){
		scrollImagen = new JScrollPane();
		panelImagen = new JLabel();
		panelImagen.setIcon(imagen);
		scrollImagen.getViewport().add(panelImagen);
		return scrollImagen;
	}

	private Component creaOeste() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black, Color.black, Color.black));
		panel.add(creaDirectorio(), BorderLayout.NORTH);
		panel.add(creaPanelFotos(), BorderLayout.CENTER);
		return panel;
	}

	private Component creaPanelFotos() {
		int ancho = 150;
		int alto = 350;
		JPanel panel = new JPanel();
		panelImagenes = new JScrollPane();
		panelImagenes.setPreferredSize(new Dimension(ancho,alto));
		lista = new JList<>();
		modelo = new DefaultListModel<>();
		lista.addListSelectionListener(this);
		lista.setModel(modelo);
		panelImagenes.setViewportView(lista);
		panel.add(panelImagenes);
		return panel;
	}

	private Component creaDirectorio() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Directorio");
		JButton boton = new JButton("examina");
		boton.addActionListener(this);
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black, Color.black, Color.black));
		panel.add(label);
		panel.add(boton);
		return panel;
	}

	private Component creaSur() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black, Color.black, Color.black));
		return panel;
	}

	private Component creaNorte() {
		JLabel label = new JLabel("Visor Gráfico");
		label.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.black, Color.gray, Color.black));
		return label;
	}
	
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equals("examina")){
			int valorObtenido;
			JFileChooser eleccion = new JFileChooser();
			eleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			valorObtenido = eleccion.showOpenDialog(ventana);
			if(valorObtenido == JFileChooser.APPROVE_OPTION){
				modelo.removeAllElements();
				file = eleccion.getSelectedFile();
				File[] ficheros = file.listFiles();
				
				for(int x = 0; x < ficheros.length; x++){
					modelo.addElement(ficheros[x]);
				}
				
				lista.setModel(modelo);
				lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			}
		}
	}
	
	public void valueChanged(ListSelectionEvent evento) {
		int indice = 0;
		indice = lista.getSelectedIndex();
		if(indice != -1){
			imagen = new ImageIcon(String.valueOf(modelo.get(indice)));
			panelImagen.setIcon(imagen);
		}
		
	}
	
	public static void main(String[] args) {
		Principal ejercicio = new Principal();
	}

}
