import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;

@SuppressWarnings("serial")
public class AccionIdioma extends AbstractAction {
	
	Ventana ventana;
	String archivo;

	public AccionIdioma(String archivo, Ventana ventana) throws IOException {
		super(archivo.split("[.]")[0]);
		this.archivo = archivo;
		this.ventana = ventana;
		putValue(Action.SHORT_DESCRIPTION, "cambiar al idioma en " + archivo);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			try {
				ventana.getIdioma().load(new FileInputStream(archivo));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
