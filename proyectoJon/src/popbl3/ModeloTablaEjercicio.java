package popbl3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaEjercicio extends AbstractTableModel {
	
	//final static String NOMBRE_FICHERO = "files/Alumnos.txt";
	
	ModeloColumnasTablaEjercicios columnas;
	ArrayList<Ejercicio> listaEjercicios;
	
	public ModeloTablaEjercicio(ModeloColumnasTablaEjercicios columnas, Paciente paciente){
		super();
		
		this.listaEjercicios = paciente.getEjercicios();
		this.columnas = columnas;
	}

	@Override
	public int getColumnCount() {
		return columnas.getColumnCount();
	}

	@Override
	public int getRowCount() {
		return listaEjercicios.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Ejercicio a = listaEjercicios.get(fila);
		return a.getFieldAt(columna);
		
	}
		
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//if (columnIndex == 3) return true;
		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0,columnIndex).getClass();
	}

	public Ejercicio getEjercicio (int indice){
		return listaEjercicios.get(indice);
	}
	
	public void insertarListaEjercicios(ArrayList<Ejercicio> ejercicios){
		this.listaEjercicios = ejercicios;
		this.fireTableDataChanged();
	}
	
	public void eliminarListaEjercicios(){
		this.listaEjercicios = null;
	}
	
}
