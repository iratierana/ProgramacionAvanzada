package edu.mondragon.pa.tablamodelcambiosbien;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaAlumnos extends DefaultTableColumnModel{
	
	TrazadorTablaAlumnos trazador;
	String poblaciones [] ={"Legazpia","Arrasate","Bergara","Vitoria-Gasteiz","Oñati","Galdakao",
			"Soraluce","Azpeitia","Mexico","El Salvador"
	};
	JComboBox<String> cPoblaciones = new JComboBox<>(poblaciones);
	
	public ModeloColumnasTablaAlumnos(TrazadorTablaAlumnos trazador){
		super();
		
		this.trazador = trazador;
		this.addColumn(crearColumna("Nombre",0,100));
		this.addColumn(crearColumna("Apellido 1",1,100));
		this.addColumn(crearColumna("Apellido 2",2,100));
		this.addColumn(crearColumna("Poblacion",3,200));
		this.addColumn(crearColumna("Edad",4,5));
		this.addColumn(crearColumna("Sexo",5,5));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		if (indice == 3 ) columna.setCellEditor(new DefaultCellEditor(cPoblaciones));
		return columna;
	}

}
