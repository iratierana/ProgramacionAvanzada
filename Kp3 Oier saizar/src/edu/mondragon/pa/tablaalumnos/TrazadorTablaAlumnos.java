package edu.mondragon.pa.tablaalumnos;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTablaAlumnos extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {
		
		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		switch (columna ){
		case 0:
		case 1:
		case 2: 
			super.setHorizontalAlignment(LEFT);
			super.setForeground(Color.black);
			break;
		case 3: 
			super.setHorizontalAlignment(CENTER);
			super.setForeground(Color.black);
			break;
		case 4: 
			super.setHorizontalAlignment(RIGHT);
			super.setForeground(Color.black);
			break;
		case 5: 
			if(((double)valor) < 5)super.setForeground(Color.red);
			else super.setForeground(Color.black);
			break;
				
		}
		
		
		return this;
	}

}