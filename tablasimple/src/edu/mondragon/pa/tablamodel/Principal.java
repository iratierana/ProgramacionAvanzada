package edu.mondragon.pa.tablamodel;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Principal extends JFrame {
	
	
	ModeloTablaAlumno tabla;
	 
	JTable vTabla;
	public Principal(){
		super("Alumnos 2º de grado de informática");
		
		tabla = new ModeloTablaAlumno();
		this.setSize(800,600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
	}

	
	private Container crearPanelVentana() {
		crearTabla();
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setViewportView(vTabla);
		return panel;
	}
	private void crearTabla() {
		vTabla = new JTable(tabla);
		vTabla.setFillsViewportHeight(true);
		vTabla.getTableHeader().setReorderingAllowed(false);
		vTabla.getColumnModel().getColumn(5).setCellRenderer(new MiTrazadorDeTabla());
	}
	

	public static void main(String[] args) {
		Principal ejercicio = new Principal();

	}

}
