package edu.mondragon.pa.tablaalumnos;

import java.io.EOFException; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaAlumno extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	final static String NOMBRE_FICHERO = "files/listaclase.dat";
	
	ModeloColumnasTablaAlumnos columnas;
	
	private ArrayList<Alumno> listaAlumnos;
	ArrayList<Alumno> listaAlumnosFiltrada;
	
	public ModeloTablaAlumno(ModeloColumnasTablaAlumnos columnas){
		super();
		this.columnas = columnas;
		leerTablaFichero();
	}
	
	private void leerTablaFichero() {
		listaAlumnos = new ArrayList<>();
		listaAlumnosFiltrada = new ArrayList<>();
		Alumno alumno = null;
		ObjectInputStream in = null;
		
		try {
			in= new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO));
			while((alumno =(Alumno)in.readObject()) != null){
				if (alumno!=null)listaAlumnos.add(alumno);
			}
		} catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(EOFException e){
			//Ez in ezer
		} catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		listaAlumnosFiltrada.addAll(listaAlumnos);
	}

	@Override
	public int getColumnCount() {
		
		return columnas.getColumnCount();
	}

	@Override
	public int getRowCount() {
		
		return listaAlumnosFiltrada.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Alumno a = listaAlumnosFiltrada.get(fila);
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
	
	public void noFiltrarAlumnos(){
		listaAlumnosFiltrada.addAll(listaAlumnos);
	}
	
	public void filtrarAlumnosPoblacion(String pueblo){
		listaAlumnosFiltrada.clear();
		for(Alumno a : listaAlumnos){
			if(a.poblacion.equals(pueblo))listaAlumnosFiltrada.add(a);
		}
	}
	
	public void filtrarAlumnosEdad(int edad){
		listaAlumnosFiltrada.clear();
		for(Alumno a : listaAlumnos){
			if(a.edad == edad)listaAlumnosFiltrada.add(a);
		}
	}
	
	public void filtrarAlumnosNombre(String nom){
		listaAlumnosFiltrada.clear();
		for(Alumno a : listaAlumnos){
			if(a.nombre.equals(nom))listaAlumnosFiltrada.add(a);
		}
	}
	
	public void filtrarAlumnosApellido(String apellido){
		listaAlumnosFiltrada.clear();
		for(Alumno a : listaAlumnos){
			if(a.apellido1.equals(apellido))listaAlumnosFiltrada.add(a);
		}
	}

}	