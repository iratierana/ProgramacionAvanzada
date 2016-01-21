import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class RendererAsignatura implements ListCellRenderer<Asignatura> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Asignatura> list, Asignatura asignatura, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel label = new JLabel();
		String text;
		text = (index + 1) + ". " + asignatura;
		label.setText(text);
		label.setFont(new Font("Arial",Font.ITALIC,16));
        label.setBackground(isSelected ? Color.BLUE : Color.WHITE);
        label.setForeground(isSelected ? Color.WHITE : (asignatura.isAprovado() ? Color.black : Color.RED));
        
        label.setOpaque(true);				
		return label;
	}

}
