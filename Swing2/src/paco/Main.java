package paco;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main implements ActionListener {

	JFrame ventana;
	JPanel botonComida;
	JLabel precio;
	JPanel panel;
	ImageIcon imagen;
	ArrayList<Producto> productoLi;
	ListIterator<Producto> productoIt;
	int total = 0;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();

	}

	private void miMain() {
		productoLi = new ArrayList<Producto>();
		ventana = new JFrame("Bar de Paco");
		ventana.setLocation(0, 0);
		ventana.setSize(340, 400);
		ventana.setContentPane(crearPanel());
		ventana.setResizable(false);
		ventana.setVisible(true);
		ventana.pack();

	}

	private Container crearPanel() {
		panel = new JPanel(new BorderLayout());
		panel.add(crearPanelPrecio(), BorderLayout.NORTH);
		panel.add(crearPanelComida(), BorderLayout.CENTER);
		return panel;
	}

	private Component crearPanelComida() {
		botonComida = new JPanel(new GridLayout(2, 2));

		JButton comida1 = new JButton();
		JButton comida2 = new JButton();
		JButton comida3 = new JButton();
		JButton comida4 = new JButton();

		imagen = new ImageIcon("./imagen/cerveza.jpg");
		comida1.setIcon(imagen);
		comida1.setText("Cerveza");
		comida1.addActionListener(this);

		imagen = new ImageIcon("./imagen/pintxo.jpg");
		comida2.setIcon(imagen);
		comida2.setText("Pintxo");
		comida2.addActionListener(this);

		imagen = new ImageIcon("./imagen/vino.jpg");
		comida3.setIcon(imagen);
		comida3.setText("Vino");
		comida3.addActionListener(this);

		imagen = new ImageIcon("./imagen/total.jpg");
		comida4.setIcon(imagen);
		comida4.setText("Total");
		comida4.addActionListener(this);

		botonComida.add(comida1);
		botonComida.add(comida2);
		botonComida.add(comida3);
		botonComida.add(comida4);

		return botonComida;
	}

	private Component crearPanelPrecio() {
		precio = new JLabel();
		precio.setText("0");
		return precio;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Producto producto;
		productoIt = productoLi.listIterator();

		switch (e.getActionCommand()) {
		case "Cerveza":
			producto = new Producto("Cerveza", 3);
			productoLi.add(producto);
			total = total + 3;
			break;

		case "Pintxo":
			producto = new Producto("Pintxo", 2);
			productoLi.add(producto);
			total = total + 2;
			break;

		case "Vino":
			producto = new Producto("Vino", 1);
			productoLi.add(producto);
			total = total + 1;
			break;

		case "Total":
			JOptionPane.showMessageDialog(null, "Total: " + total+ " €");
			total = 0;
			break;

		}
		precio.setText(Integer.toString(total)+"€");
	}

}
