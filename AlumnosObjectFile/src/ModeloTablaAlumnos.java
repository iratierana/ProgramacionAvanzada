import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

@SuppressWarnings("serial")
public class ModeloTablaAlumnos extends AbstractTableModel {

final static String NOMBRE_FICHERO = "alumnos.alu";
	
	ModeloColumnasTablaAlumnos columnas;
	
	ArrayList<Alumno> listaAlumnos;
	
	public ModeloTablaAlumnos(ModeloColumnasTablaAlumnos columnas){
		super();
		leerTablaFichero();
		this.columnas = columnas;
		
	}
	private void leerTablaFichero() {
		listaAlumnos = new ArrayList<>();
		ObjectInputStream in = null;
		int idmax = 0;
		Alumno a;
		try {
			in = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO));
			
			while(true){
				a = (Alumno) in.readObject();
				if(a.getApellido().compareTo("Cena") == 0){
					listaAlumnos.add(a);
					if(a.getId() > idmax){
						idmax = a.getId();
					}
				}
				
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try{in.close();}catch(Exception e){};
			Alumno.setNum(idmax + 1);
		}
		
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
		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0,columnIndex).getClass();
	}
	
	
	
	public void add(Alumno al){
		listaAlumnos.add(al);
		fireTableDataChanged();
	}

	public void remove(int index){
		listaAlumnos.remove(index);
		fireTableDataChanged();
	}
}
