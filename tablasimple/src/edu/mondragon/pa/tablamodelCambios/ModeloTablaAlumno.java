package edu.mondragon.pa.tablamodelCambios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaAlumno extends AbstractTableModel {
	
	final static String NOMBRE_FICHERO = "listaclase.txt";
	
	ModeloColumnasTablaAlumnos columnas;
	
	ArrayList<Alumno> listaAlumnos;
	
	public ModeloTablaAlumno(ModeloColumnasTablaAlumnos columnas){
		super();
		leerTablaFichero();
		
		this.columnas = columnas;
		
	}
	
	public void guardar(){
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(NOMBRE_FICHERO));
			for(int i = 0; i < listaAlumnos.size(); i++){
				out.println(listaAlumnos.get(i).escribir());
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if (out!=null){
				out.close();
			}
		}
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
		
		return columnas.getColumnCount();
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
		if (columnIndex == 3) return true;
		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0,columnIndex).getClass();
	}

	public Alumno getAlumno (int indice){
		return listaAlumnos.get(indice);
	}
	public void insertar(Alumno alumno) {
		listaAlumnos.add(alumno);
		
	}
	public void borrar(int indice) {
		listaAlumnos.remove(indice);
		
	}
}
