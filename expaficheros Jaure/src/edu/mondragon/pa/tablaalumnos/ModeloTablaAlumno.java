package edu.mondragon.pa.tablaalumnos;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaAlumno extends AbstractTableModel {
	
	final static String NOMBRE_FICHERO = "files/listaclase.dat";
	
	ModeloColumnasTablaAlumnos columnas;
	
	ListaAlumnos listaAlumnos;
	
	public ModeloTablaAlumno(ModeloColumnasTablaAlumnos columnas){
		super();
		
		listaAlumnos=new ListaAlumnos();
		listaAlumnos.leerTablaFichero();
		
		this.columnas = columnas;
		
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
		this.fireTableDataChanged();
		//this.fireTableChanged(new TableModelEvent(this));
	}
	public void borrar(int indice) {
		listaAlumnos.remove(indice);
		this.fireTableDataChanged();
		//this.fireTableChanged(new TableModelEvent(this));
	}	
	public void refrescarLista(ListaAlumnos lista){
		listaAlumnos.clear();
		listaAlumnos=lista;
		this.fireTableDataChanged();
	}
}
