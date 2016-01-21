package popbl3;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaEjercicios extends DefaultTableColumnModel{
	
	TrazadorTablaEjercicios trazador;
	
	
	public ModeloColumnasTablaEjercicios(TrazadorTablaEjercicios trazador){
		super();
		
		this.trazador = trazador;
		this.addColumn(crearColumna("ID",0,5));
		this.addColumn(crearColumna("Nombre",1,200));
		this.addColumn(crearColumna("Fecha",2,10));
		this.addColumn(crearColumna("Repeticiones",3,20));
		this.addColumn(crearColumna("Resultado",4,25));
	
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setHeaderRenderer(new DefaultTableCellRenderer(){

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
		
				switch (column ){
				case 0: 
				case 1: 
				case 2: 
				case 3: 
				case 4: setBackground(Color.black); setForeground(Color.white); ;setHorizontalAlignment(CENTER); break;
				}
				
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}		
		});
		columna.setCellRenderer(trazador);
		return columna;
	}

}
