package edu.mondragon.pa.tablamodel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaAlumno extends AbstractTableModel {
	
	final static String NOMBRE_FICHERO = "listaclase.txt";
	final static String [] NOMBRE_COLUMNAS = {"Nombre", "Apellido_1","Apellido_2","Poblacion","Edad","Sexo"};
	
	
	

	ArrayList<Alumno> listaAlumnos;
	
	public ModeloTablaAlumno(){
		leerTablaFichero();
	}
	private void leerTablaFichero() {
		String linea = null;
		listaAlumnos = new ArrayList<>();
		Alumno alumno = null;
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(NOMBRE_FICHERO));
			
			while((linea = in.readLine())!=null){
				alumno = leerAlumno(linea);
				if (alumno!=null){
					listaAlumnos.add(alumno);
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

	@Override
	public int getColumnCount() {
		
		return NOMBRE_COLUMNAS.length;
	}

	@Override
	public int getRowCount() {
		
		return listaAlumnos.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Alumno a = listaAlumnos.get(fila);
		return a.getFieldAt(columna);
		
	}
		
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 4) return true;
		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return listaAlumnos.get(0).getFieldClass(columnIndex);
	}

}
