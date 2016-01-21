import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class GuardarAction extends AbstractAction {

	Principal ventana;
	JFileChooser fc;
	File file;
	
	public GuardarAction(Principal ventana) {
		super("Guardar");
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);
		this.ventana = ventana;
		this.putValue(MNEMONIC_KEY, KeyEvent.VK_G);
		this.putValue(SHORT_DESCRIPTION, "Guardar el alumno");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(JFileChooser.APPROVE_OPTION == fc.showSaveDialog(ventana)){
			if((file = fc.getSelectedFile()) != null){			
				ventana.guardarEnArchivo("$", fc.getSelectedFile().getAbsolutePath());
			}
		}
		
	}

}
