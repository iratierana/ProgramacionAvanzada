import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Ventana extends JFrame implements Observer{

	final static int MONEDA = 0;
	final static int CONVERSION = 1;

	Object tablaConv[][];
	PanelConv panel;
	Idioma idioma;

	public Ventana() {
		
		Object aux[][] = { { "Euro", 1.0 }, { "Dolar US", 0.9 }, { "Dolar AU", 0.8 }, { "Libre esterlina", 1.2 } };

		try {
			leerMonedas("monedas.txt");

		} catch (Exception e) {
			tablaConv = aux;
		}
		idioma = new Idioma();
		idioma.addObserver(this);
		setTitle(idioma.getProperty("Titulo", "Conversor de moneda"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 350));
		setLocation(new Point(100, 100));
		panel = new PanelConv(tablaConv, this);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(crearBarraIdiomas(), BorderLayout.NORTH);
		setResizable(false);
		setVisible(true);

	}

	private void leerMonedas(String string) throws NumberFormatException, IOException {
		BufferedReader in = null;
		String linea;
		String fields[];
		ArrayList<Double> cambio = new ArrayList<>();
		ArrayList<String> nombre = new ArrayList<>();

		try {
			in = new BufferedReader(new FileReader(string));
			while ((linea = in.readLine()) != null) {
				fields = linea.split("=");
				for (int i = 0; i < fields.length; i++) {
					fields[i].trim();
				}
				nombre.add(fields[0]);
				cambio.add(Double.parseDouble(fields[1]));
			}
			tablaConv = new Object[nombre.size()][nombre.size()];
			for (int i = 0; i < nombre.size(); i++) {
				tablaConv[i][MONEDA] = nombre.get(i);
				tablaConv[i][CONVERSION] = cambio.get(i);
			}
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			;
		}

	}

	private Component crearBarraIdiomas() {
		JToolBar toolbar = new JToolBar();
		toolbar.add(Box.createHorizontalGlue());
		try {
			toolbar.add(new AccionIdioma("ingles.txt", this));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		try {
			toolbar.add(new AccionIdioma("castellano.txt", this));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return toolbar;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public PanelConv getPanel() {
		return panel;
	}

	public void setPanel(PanelConv panel) {
		this.panel = panel;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Ventana a = new Ventana();
	}

	@Override
	public void update(Observable o, Object arg) {
		setTitle(idioma.getProperty("Titulo", "Conversor de moneda"));
		panel.escribirTextos();
		
		//repaint();
	}

}
