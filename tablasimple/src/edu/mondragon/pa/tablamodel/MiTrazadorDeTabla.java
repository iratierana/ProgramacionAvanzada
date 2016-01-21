package edu.mondragon.pa.tablamodel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MiTrazadorDeTabla implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {
		
		if (columna == 5){
			Boolean elValor = (Boolean) valor;
			JLabel dibujo = new JLabel();
			if (elValor){
				dibujo.setIcon(new ImageIcon("images/male.png"));
			}else{
				dibujo.setIcon(new ImageIcon("images/female.png"));
			}
			
			dibujo.setBackground((isSelected)?Color.blue:Color.white);
			
			dibujo.setOpaque(true);
			return dibujo;
		}
		return null;
	}

}
