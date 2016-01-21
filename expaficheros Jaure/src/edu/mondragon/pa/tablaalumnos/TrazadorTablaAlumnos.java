package edu.mondragon.pa.tablaalumnos;


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
		case 5: return crarTexto(valor);
				
		}
		
		
		return this;
	}

	private Component crarTexto(Object valor) {
		JLabel label=new JLabel();
		if((double)valor<5.0){
			label.setForeground(Color.red);
			label.setText(String.valueOf(valor));
		}else{
			label.setForeground(Color.black);
			label.setText(String.valueOf(valor));
		}
		return label;
	}



}
