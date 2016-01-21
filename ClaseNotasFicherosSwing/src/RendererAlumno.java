import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class RendererAlumno implements ListCellRenderer<Alumno> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Alumno> list,
			Alumno alumno,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		
		
		JLabel label = new JLabel();
		String text;
		text = (index + 1) + ". " + alumno;
		label.setText(text);
		label.setFont(new Font("Arial",Font.ITALIC,16));
        label.setBackground(isSelected ? Color.BLUE : (alumno.isTodoAprovado() ? Color.GREEN : Color.RED));
        label.setForeground(isSelected ? Color.WHITE : Color.black);
        
        label.setOpaque(true);				
		return label;
	
	}

}
