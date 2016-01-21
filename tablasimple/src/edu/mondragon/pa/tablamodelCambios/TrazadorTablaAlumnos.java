package edu.mondragon.pa.tablamodelCambios;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTablaAlumnos extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {
		
		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		switch (columna ){
		case 0: 
		case 1:
		case 2: super.setHorizontalAlignment(LEFT);break;
		case 3: super.setHorizontalAlignment(CENTER);break;
		case 4: super.setHorizontalAlignment(RIGHT);break;
		case 5: JLabel label = new JLabel("", new ImageIcon(((Boolean)valor)?"images/female.png":"images/male.png"),CENTER);
				label.setBackground((isSelected)?Color.blue:Color.white);
				return label;
		default: break;
		}
		
		
		return this;
	}

}
