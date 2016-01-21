package conversorAndoni;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ConversionMoneda extends JFrame implements ActionListener{

	JComboBox<String> box1, box2;
	JTextField textField1, textField2;
	JButton bConvertir;
	//int arrayMonedas[][] = new int[3][3]; //0 Euro, 1 Dolar, 2 Libra
	int idioma = 0; // 0 español 1 ingles
	String nombreFichero, nombreFicheroMoneda = "Cambio.txt"; 
	String titulo, cantidad, cambio;
	Properties propiedades;
	HashMap<String, Float> valoresMonedas;
	MiAccion accEsp, accIng;
	JButton esp, ing;

	public ConversionMoneda(){

		super("titulo");
		valoresMonedas = new HashMap<>();
		leerDeFichero();
		this.setTitle(propiedades.getProperty("titulo", "titulo"));
		this.setSize(500,200);
		this.setLocation(800,400);
		//rellenarArray();
		this.setContentPane(CrearPanelVentana());
		leerMonedasDesdeFichero();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private Container CrearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelBarraBotones(), BorderLayout.NORTH);
		panel.add(CrearPanelCentral(), BorderLayout.CENTER);
		return panel;
	}


	private Component panelBarraBotones() {

		JToolBar toolBar = new JToolBar();

		toolBar.add(Box.createHorizontalGlue());
		JButton boton;
		boton =(JButton) toolBar.add(new JButton (accEsp));
		boton.addActionListener(this);
		boton.setActionCommand("Ing");
		
		boton =(JButton) toolBar.add(new JButton (accIng));
		boton.addActionListener(this);
		boton.setActionCommand("Esp");


		return toolBar;

	}


	private Component CrearPanelCentral() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(CrearPanelConversor(), BorderLayout.CENTER);
		panel.add(CrearPanelBoton(), BorderLayout.SOUTH);

		return panel;
	}
	private Component CrearPanelBoton() {
		JPanel panel = new JPanel();

		bConvertir= new JButton(propiedades.getProperty("BotonCambio", "no funciona"));
		bConvertir.addActionListener(this);
		bConvertir.setActionCommand("convertir");
		bConvertir.setSize(20,20);

		panel.add(bConvertir);
		return panel;
	}
	private Component CrearPanelConversor() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, CrearConversorIzq(), CrearConversorDer());

		panel.setDividerLocation(200);

		return panel;
	}
	private Component CrearConversorDer() {
		JPanel panel = new JPanel(new BorderLayout());



		box2 = new JComboBox<>();
		box2.addActionListener(this);
		box2.setActionCommand("box2");
		panel.add(box2, BorderLayout.NORTH);

		textField2 = new JTextField();
		textField2.setBorder(BorderFactory.createTitledBorder(propiedades.getProperty("MensajeCantidad", "no funciona")));
		textField2.setEditable(false);
		textField2.setBackground(Color.WHITE);
		textField2.setOpaque(true);
		panel.add(textField2, BorderLayout.SOUTH);

		return panel;
	}
	private Component CrearConversorIzq() {
		JPanel panel = new JPanel(new BorderLayout());

		box1 = new JComboBox<>();
		box1.addActionListener(this);
		box1.setActionCommand("box1");
		panel.add(box1, BorderLayout.NORTH);

		textField1 = new JTextField();
		textField1.setBorder(BorderFactory.createTitledBorder(propiedades.getProperty("MensajeCantidad", "no funciona")));
		textField1.setBackground(Color.WHITE);
		textField1.setOpaque(true);
		panel.add(textField1, BorderLayout.SOUTH);

		return panel;
	}

	/*public void rellenarArray(){
		int c = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3 ; j++){
				arrayMonedas[i][j] = c;
				//System.out.println("Posicion: " +i +" " +j +" = " +c);
				c++;
			}
		}

	}
	*/
	public void cambiarIdioma(){
		this.setTitle(propiedades.getProperty("titulo", "no funciona"));
		textField1.setBorder(BorderFactory.createTitledBorder(propiedades.getProperty("MensajeCantidad", "no funciona")));
		textField2.setBorder(BorderFactory.createTitledBorder(propiedades.getProperty("MensajeCantidad", "no funciona")));
		bConvertir.setText(propiedades.getProperty("BotonCambio", "no funciona"));
	}

	public void leerDeFichero(){

		if(idioma == 0){
			nombreFichero = "Español.txt";
		}else if(idioma == 1){
			nombreFichero = "Ingles.txt";
		}

		try {
			propiedades = new Properties();
			propiedades.load(new FileInputStream(nombreFichero));

		}catch(FileNotFoundException e) {
			System.out.println("No se encuentra fichero");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void leerMonedasDesdeFichero(){
		BufferedReader in = null;
		String linea = null;
		String valores[];

		try {
			in = new BufferedReader ( new FileReader (nombreFicheroMoneda));
			while ((linea = in.readLine())!=null){
				valores = linea.split("[=]");
				valoresMonedas.put(valores[0], Float.parseFloat(valores[1]));
				box1.addItem(valores[0]);
				box2.addItem(valores[0]);
			}
		}catch(FileNotFoundException e) {
			System.out.println("No se encuentra fichero");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	public static void main(String[] args) {

		ConversionMoneda ejercicio = new ConversionMoneda();

	}
	
	private void crearAcciones() {
		accEsp = new MiAccion ("Español", new ImageIcon("iconos/editcut.png"), "Español");
		accIng = new MiAccion ("Ingles",new ImageIcon("iconos/editcopy.png"),"Ingles");
		
	}

	private class MiAccion extends AbstractAction {
		String texto;
		public MiAccion (String texto, Icon imagen, String descrip){
			super(texto);
			this.texto = texto;
			this.putValue( Action.SHORT_DESCRIPTION ,descrip);
			//this.putValue(Action.MNEMONIC_KEY, nemonic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Float valor;

		if(e.getActionCommand().equals("convertir")){
			try{
				valor = Float.parseFloat(textField1.getText()) / valoresMonedas.get(box1.getSelectedItem()) * valoresMonedas.get(box2.getSelectedItem());
				textField2.setText(String.valueOf(valor));
			}catch(NumberFormatException a){
				JOptionPane.showMessageDialog(this, propiedades.getProperty("MensajeError", "no funciona"), propiedades.getProperty("TituloError", "no funciona"), JOptionPane.ERROR_MESSAGE);

			}

		}else if(e.getActionCommand().equals("Esp")){
			idioma = 0;
			this.leerDeFichero();
			this.cambiarIdioma();
		}else if(e.getActionCommand().equals("Ing")){
			idioma = 1;
			this.leerDeFichero();
			this.cambiarIdioma();
		}
	}



}
