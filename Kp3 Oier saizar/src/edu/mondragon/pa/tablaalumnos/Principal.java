package edu.mondragon.pa.tablaalumnos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Principal extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	ModeloTablaAlumno tabla;
	 
	JTable vTabla;
	TrazadorTablaAlumnos trazador;
	ModeloColumnasTablaAlumnos columnas;
	JScrollPane panelS;
	JTextField tBuscador;
	JComboBox<String> cBDatos;
	String sDatos[] = {" ","Poblacion", "Edad","Nombre","Apellido1"};
	
	public Principal(){
		super("Alumnos 2º de grado de informática");
		trazador = new TrazadorTablaAlumnos();
		columnas = new ModeloColumnasTablaAlumnos (trazador);
		tabla = new ModeloTablaAlumno(columnas);
		this.setSize(900,600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
	}

	
	private Container crearPanelVentana() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,
											crearPanelOpciones(),crearPanelTabla());
		panel.setDividerLocation(200);
		panel.setEnabled(false);
		return panel;
	}


	private Component crearPanelOpciones() {
		JPanel opciones = new JPanel(new BorderLayout(20,20));
		opciones.add(crearPanelBotones(), BorderLayout.NORTH);
		opciones.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		return opciones;
	}


	private Component crearPanelBotones() {
		JPanel pBotones = new JPanel(new BorderLayout(20,20));
		
		cBDatos = new JComboBox<>(sDatos);
		
		pBotones.add(crearPanelBuscador(), BorderLayout.SOUTH);
		pBotones.add(cBDatos, BorderLayout.CENTER);
		
		return pBotones;
	}
	
	

private Component crearPanelBuscador() {
		JPanel pBuscador = new JPanel(new GridLayout(2,1));
		JButton buscar = new JButton("Buscar");
		buscar.setActionCommand("Buscar");
		buscar.addActionListener(this);
		
		tBuscador= new JTextField();
		
		pBuscador.add(tBuscador);
		pBuscador.add(buscar);
		
		return pBuscador;
	}


/*
	private Component crearBotones() {
		JPanel botones = new JPanel(new GridLayout(2,2,20,20));
		JRadioButton boton;
		
		for(int i = 0; i < sBotones.length; i++){
			boton = new JRadioButton(sBotones[i]);
			boton.addActionListener(this);
			boton.setActionCommand(sBotones[i]);
			botones.add(boton);
		}

		return botones;
	}
*/

	private Container crearPanelTabla() {
		JPanel panel = new JPanel (new BorderLayout());
		panelS = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(panelS,BorderLayout.CENTER);
		crearTabla();
		return panel;
	}
	private void crearTabla() {
		vTabla = new JTable(tabla,columnas);
		vTabla.setFillsViewportHeight(true);
		panelS.setViewportView(vTabla);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Buscar")){
			String buscado = tBuscador.getText();
			int index = cBDatos.getSelectedIndex();
			
			switch(index){
			
			case 0: tabla.noFiltrarAlumnos(); break;
			case 1: tabla.filtrarAlumnosPoblacion(buscado); break;
			case 2: tabla.filtrarAlumnosEdad(Integer.parseInt(buscado)); break;
			case 3: tabla.filtrarAlumnosNombre(buscado); break;
			case 4: tabla.filtrarAlumnosApellido(buscado);break;
			}
			this.repaint();
		}
		
	}
	

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Principal ejercicio = new Principal();

	}


	

}