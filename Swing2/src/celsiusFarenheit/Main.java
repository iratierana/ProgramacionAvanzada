package celsiusFarenheit;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{

	JFrame ventana;
	JRadioButton celcius, farenheit;
	ButtonGroup grupo;
	JTextField textField;
	JButton boton;
	
	Temperatura temperatura;
	
	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();
	}

	private void miMain() {
		temperatura = new Temperatura();
		ventana = new JFrame("Conversor de temperaturas");
		ventana.setSize(350, 400);
		ventana.setLocation(200, 350);
		ventana.setContentPane(crearPanel());
		ventana.setVisible(true);
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,20,40,20));
		
		panel.add(creaTexto(), BorderLayout.NORTH);
		panel.add(crearRadioButton(), BorderLayout.CENTER);
		panel.add(crearBoton(), BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearBoton() {
		boton = new JButton("Convertir");
		boton.addActionListener(this);
		return boton;
	}

	private Component crearRadioButton() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		
		celcius = new JRadioButton("Celcius");
		farenheit = new JRadioButton("Farenheit");
		celcius.addActionListener(this);
		farenheit.addActionListener(this);
		
		celcius.setSelected(true);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(celcius);
		grupo.add(farenheit);
		
		panel.add(celcius);
		panel.add(farenheit);
		
		return panel;
	}

	private Component creaTexto() {
		textField = new JTextField("");
		return textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int valor = 0;
		String texto;
		
		if(e.getActionCommand() == "Convertir"){
			texto = textField.getText();
			valor = Integer.valueOf(texto);
			
			if(celcius.isSelected()){
				textField.setText(Integer.toString(temperatura.celciusToFarenheit(valor)));
			}
			if(farenheit.isSelected()){
				textField.setText(Integer.toString(temperatura.farenheitToCelcius(valor)));
			}
		}
	}

}
