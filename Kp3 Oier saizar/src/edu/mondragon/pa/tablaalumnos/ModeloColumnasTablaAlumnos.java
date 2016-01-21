package edu.mondragon.pa.tablaalumnos;

import javax.swing.table.DefaultTableColumnModel; 
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaAlumnos extends DefaultTableColumnModel{
	private static final long serialVersionUID = 1L;
	
	TrazadorTablaAlumnos trazador;
	
	public ModeloColumnasTablaAlumnos(TrazadorTablaAlumnos trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("Nombre",0,100));
		this.addColumn(crearColumna("Apellido 1",1,100));
		this.addColumn(crearColumna("Apellido 2",2,100));
		this.addColumn(crearColumna("Poblacion",3,200));
		this.addColumn(crearColumna("Edad",4,5));
		this.addColumn(crearColumna("Nota",5,5));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		
		return columna;
	}

}