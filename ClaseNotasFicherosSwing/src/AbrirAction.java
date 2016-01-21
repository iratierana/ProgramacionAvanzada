import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Instant;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;


@SuppressWarnings("serial")
public class AbrirAction extends AbstractAction {

	JFileChooser fc;
	Principal ventana;
	File file;


	public AbrirAction(Principal ventana) {
		super("Abrir");
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);
		this.ventana = ventana;
		this.putValue(Action.SHORT_DESCRIPTION, "Abrir una clase");
		this.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(ventana)) {
			if ((file = fc.getSelectedFile()) != null) {
				ventana.leerDeArchivo("[$]", fc.getSelectedFile().getAbsolutePath());
				if (ventana.getClase() != null) {
					ventana.getlAlumno().setModel(ventana.getClase());
					ventana.getlTitle().setText(ventana.getClase().getNombre());
					ventana.getaGuardar().setEnabled(true);
				}else{
					ventana.getaGuardar().setEnabled(false);
				}
			}
		}

	}

}
