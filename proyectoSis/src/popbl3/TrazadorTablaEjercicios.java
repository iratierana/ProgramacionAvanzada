package popbl3;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTablaEjercicios extends DefaultTableCellRenderer {

	//private static final DecimalFormat formatter = new DecimalFormat( "0.00" );
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {
		
		/*float valorNota = (Float)table.getModel().getValueAt(fila, 5);
        if (valorNota > 5) {
            super.setBackground(Color.GREEN);
        } else {
            super.setBackground(Color.red);
        }   */    
                
		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		
		switch (columna ){
		case 0: 
		case 1: 
		case 2: 
		case 3: 
		case 4: super.setHorizontalAlignment(CENTER);break;
					
		}
		
		
		return this;
	}

}
