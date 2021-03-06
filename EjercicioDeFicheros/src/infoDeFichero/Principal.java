package infoDeFichero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Principal extends JFrame implements ActionListener{

	JButton buscarFichero, salir;
	JTextField nombre, path, tama�o1, tama�o2;
	File fichero;
	
	public Principal(String titulo , int posX, int posY){
		this.setTitle(titulo);
		this.setSize(400, 400);
		this.setLocation(posX, posY);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(crearPanelCentral(), BorderLayout.CENTER);
		panel.add(crearPanelSur(), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		return panel;
	}

	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
		
		nombre = new JTextField();
		nombre.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Nombre"));
		
		path = new JTextField();
		path.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Path"));
		
		panel.add(nombre);
		panel.add(path);
		panel.add(crearPanelDiv());
		
		return panel;
	}

	private Component crearPanelDiv() {
		JPanel panel = new JPanel(new GridLayout(5, 5,40,40));
		tama�o1 = new JTextField();
		tama�o1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Tama�o fichero"));
		
		tama�o2 = new JTextField();
		tama�o2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Mi tama�o"));
		
		panel.add(tama�o1);
		panel.add(tama�o2);
		
		return panel;
	}

	private Component crearPanelSur() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 20 ,20));
		
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buscarFichero = new JButton("Buscar fichero");
		buscarFichero.addActionListener(this);
		buscarFichero.setActionCommand("buscar");
		
		salir = new JButton("Salir");
		salir.addActionListener(this);
		salir.setActionCommand("salir");
		
		panel.add(buscarFichero);
		panel.add(salir);
		
		return panel;
	}
	
	public String calcularTama�o(File fichero){
		String devolver;
		long kont=0;
		
		try {
			FileInputStream lector = new FileInputStream(fichero);
			while(lector.read()!=-1){
				kont++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		devolver=String.valueOf(kont);
		return devolver;
	}
	
	public void buscar(){
		JFileChooser buscador=new JFileChooser();
		
		if(buscador.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			fichero=buscador.getSelectedFile();
			nombre.setText(fichero.getName());
			path.setText(fichero.getPath());
			tama�o1.setText(String.valueOf(fichero.length()));
		}
		tama�o2.setText(calcularTama�o(fichero));																																																											//Jaure HiJoputa
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "buscar":
			buscar();
			break;
		case "salir":
			this.dispose();
			break;
		default:
			break;
		}
	}
}
