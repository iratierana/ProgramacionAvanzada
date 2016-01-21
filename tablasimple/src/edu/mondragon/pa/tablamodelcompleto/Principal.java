package edu.mondragon.pa.tablamodelcompleto;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Principal extends JFrame {
	
	
	ModeloTablaAlumno tabla;
	 
	JTable vTabla;
	TrazadorTablaAlumnos trazador;
	ModeloColumnasTablaAlumnos columnas;
	JScrollPane panelS;
	public Principal(){
		super("Alumnos 2º de grado de informática");
		trazador = new TrazadorTablaAlumnos();
		columnas = new ModeloColumnasTablaAlumnos (trazador);
		tabla = new ModeloTablaAlumno(columnas);
		this.setSize(800,600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
	}

	
	private Container crearPanelVentana() {
		
		JPanel panel = new JPanel (new BorderLayout());
		panelS = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(panelS,BorderLayout.CENTER);
		crearTabla();
		return panel;
	}
	private void crearTabla() {
		vTabla = new JTable(tabla,columnas);
		vTabla.setFillsViewportHeight(true);
		panelS.setViewportView(vTabla);
	}
	

	public static void main(String[] args) {
		Principal ejercicio = new Principal();

	}

}
