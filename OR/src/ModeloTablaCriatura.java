import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaCriatura extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ModeloColumnasTablaCriatura columnas;
	ArrayList<Criatura> listaCriaturas = new ArrayList<Criatura>();
	
	public ModeloTablaCriatura(ModeloColumnasTablaCriatura columnas){
		super();
		this.columnas = columnas;
	}

	@Override
	public int getColumnCount() {
		
		return columnas.getColumnCount();
	}

	@Override
	public int getRowCount() {
		
		return listaCriaturas.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Criatura a = listaCriaturas.get(fila);
		return a.getFieldAt(columna);
	}
	
	public Criatura getCriatura(int indice){
		return listaCriaturas.get(indice);
	}
	
	public void insertar(Criatura c){
		listaCriaturas.add(c);
	}

	public void setListaCriaturas(ArrayList<Criatura> listaCriaturas) {
		this.listaCriaturas = listaCriaturas;
	}
	

}
