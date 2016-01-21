package edu.mondragon.pa.tablasimple;

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
	final static String NOMBRE_FICHERO = "listaclase.txt";
	final static String [] NOMBRE_COLUMNAS = {"Nombre", "Apellido_1","Apellido_2","Poblacion","Edad","Sexo"};
	
	ArrayList<Alumno> tabla;
	 
	JTable vTabla;
	public Principal(){
		super("Alumnos 2º de grado de informática");
		
		leerTablaFichero();
		this.setSize(800,600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(crearPanelVentana());
		
		this.setVisible(true);
	}

	private void leerTablaFichero() {
		String linea = null;
		tabla = new ArrayList<>();
		Alumno alumno = null;
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(NOMBRE_FICHERO));
			
			while((linea = in.readLine())!=null){
				alumno = leerAlumno(linea);
				if (alumno!=null){
					tabla.add(alumno);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private Alumno leerAlumno(String linea) {
		String [] palabras = linea.split("[$]");
		Alumno alumno = new Alumno (palabras[0],palabras[1],palabras[2],palabras[3],
				Integer.parseInt(palabras[4]),
				(palabras[5].equals("H")?true:false));
		return alumno;
	}
	private Container crearPanelVentana() {
		vTabla = new JTable(crearArray(tabla),NOMBRE_COLUMNAS);
		vTabla.setFillsViewportHeight(true);
		JScrollPane panel = new JScrollPane(vTabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return panel;
	}
	
	private String [][] crearArray(ArrayList<Alumno> tabla) {
		String [][] datos = new String [tabla.size()][NOMBRE_COLUMNAS.length];
		int i = 0;
		for (Alumno a: tabla){
			datos[i] = a.toArray();
			i++;
		}
		return datos;
	}

	public static void main(String[] args) {
		Principal ejercicio = new Principal();

	}

}
