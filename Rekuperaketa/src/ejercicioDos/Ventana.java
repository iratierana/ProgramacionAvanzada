package ejercicioDos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Ventana extends JFrame implements Observer, ActionListener {

	JTextField textField;
	JButton boton;
	String nombre;
	Buffer buffer;
	
	JList<String> list;
	DefaultListModel<String> listModel;
	
	public Ventana (int posicionX, int posicionY, String nombre, Buffer buffer) {
		super("Ventana usuario: " + nombre);
		this.setContentPane(crearVentana());
		this.setLocation(posicionX, posicionY);
		this.setSize(480, 640);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.nombre = nombre;
		this.buffer = buffer;
	}

	private Container crearVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(crearPanelChat(), BorderLayout.CENTER);
		panel.add(crearPanelEscribir(), BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearPanelChat() {	
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setCellRenderer(new MiCellRenderer());
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		return scrollPane;
	}

	private Component crearPanelEscribir() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(textField = new JTextField(), BorderLayout.CENTER);
		panel.add(boton = new JButton("enviar"), BorderLayout.EAST);
		boton.addActionListener(this);
		
		return panel;
	}
	
	private void enviarMensaje(String texto){
		Mensaje mensaje = new Mensaje(texto, nombre);
		buffer.meterMensaje(mensaje);
	}

	@Override
	public void update(Observable o, Object arg) {
		Mensaje mensaje = buffer.recibirUltimo();
		String texto;
		
		if (mensaje.autor == nombre) {
			texto = mensaje.getMensaje() + "\t";
		} else {
			texto =  "\t" + mensaje.getAutor() + ": " + mensaje.getMensaje();
		}
		
		listModel.addElement(texto);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "enviar") {
			String mensaje = textField.getText();
			if (mensaje.length() > 0) {
				enviarMensaje(mensaje);
			}
			textField.setText("");
		}
	}
}
