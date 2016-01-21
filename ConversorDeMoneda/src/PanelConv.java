import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelConv extends JPanel {
	
	JPanel panelIzq, panelDer, panelCentral;
	JTextField pantallaEntrada, pantallaSalida;
	JComboBox<String> cboxEntrada, cboxSalida;
	JButton botonConv;
	Object tabla[][];
	Ventana ventana;
	

	public PanelConv(Object[][] tablaConv, Ventana ventana){
		tabla = tablaConv;
		this.ventana = ventana;
		setLayout(new BorderLayout(20, 20));
		add(crearPanelCentral(), BorderLayout.CENTER);
		add(crearPanelBoton(), BorderLayout.SOUTH);	
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		escribirTextos();
	}


	private Component crearPanelCentral() {
		panelCentral = new JPanel(new GridLayout(1, 2));
		
		panelIzq = new JPanel(new GridLayout(2, 1, 20, 20));
		
		cboxEntrada = new JComboBox<>(crearListaCbox());
		pantallaEntrada = new JTextField();
		
		panelIzq.add(cboxEntrada);
		panelIzq.add(pantallaEntrada);
		panelCentral.add(panelIzq);
		
		panelDer = new JPanel(new GridLayout(2, 1, 20, 20));
		
		cboxSalida = new JComboBox<>(crearListaCbox());

		pantallaSalida = new JTextField();
		pantallaSalida.setEditable(false);
		
		panelDer.add(cboxSalida);
		panelDer.add(pantallaSalida);
		panelCentral.add(panelDer);
		
		
		
		return panelCentral;
	}
	
	public void escribirTextos(){
		pantallaSalida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), ventana.getIdioma().getProperty("TituloPantallas", "Conversor de moneda")));
		pantallaEntrada.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), ventana.getIdioma().getProperty("TituloPantallas", "Conversor de moneda")));
		panelIzq.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), ventana.getIdioma().getProperty("TituloPanelIzq", "Entrada")));
		botonConv.setText(ventana.getIdioma().getProperty("TextoBotonConvertir", "Convertir"));
		panelDer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), ventana.getIdioma().getProperty("TituloPanelDer", "Resultado")));
	}

	public JTextField getPantallaSalida() {
		return pantallaSalida;
	}

	public void setPantallaSalida(JTextField pantallaSalida) {
		this.pantallaSalida = pantallaSalida;
	}

	public JTextField getPantallaEntrada() {
		return pantallaEntrada;
	}

	public void setPantallaEntrada(JTextField pantallaEntrada) {
		this.pantallaEntrada = pantallaEntrada;
	}

	public JPanel getPanelIzq() {
		return panelIzq;
	}

	public void setPanelIzq(JPanel panelIzq) {
		this.panelIzq = panelIzq;
	}

	public JPanel getPanelDer() {
		return panelDer;
	}

	public void setPanelDer(JPanel panelDer) {
		this.panelDer = panelDer;
	}

	public JComboBox<String> getCboxEntrada() {
		return cboxEntrada;
	}

	public void setCboxEntrada(JComboBox<String> cboxEntrada) {
		this.cboxEntrada = cboxEntrada;
	}

	public JComboBox<String> getCboxSalida() {
		return cboxSalida;
	}

	public void setCboxSalida(JComboBox<String> cboxSalida) {
		this.cboxSalida = cboxSalida;
	}

	public JButton getBotonConv() {
		return botonConv;
	}

	public void setBotonConv(JButton botonConv) {
		this.botonConv = botonConv;
	}

	private String[] crearListaCbox() {
		String ret[] = new String [tabla.length];
		for(int i= 0; i < tabla.length; i++){
			ret[i] = (String)tabla[i][Ventana.MONEDA];
		}
		return ret;
		
		
	}

	private Component crearPanelBoton() {
		botonConv = new JButton(new AbstractAction("Convertir") {
			
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					pantallaSalida.setText("" + Conversor.convertir(tabla, Double.parseDouble(pantallaEntrada.getText()), cboxEntrada.getSelectedIndex(), cboxSalida.getSelectedIndex()));
				}catch(Exception e){
					pantallaSalida.setText("0");
					JOptionPane.showMessageDialog(ventana, ventana.getIdioma().getProperty("MensajeErrorFormato", "Error de formato"), ventana.getIdioma().getProperty("TituloErrorFormato", "Mensaje de error"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
		
		return botonConv;
	}
	
	
	
	
}
