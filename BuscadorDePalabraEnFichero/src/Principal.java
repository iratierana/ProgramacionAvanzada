import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Principal extends JFrame implements ActionListener {
	
	File archivo;
	
	JButton bSeleccionar, bBuscar;
	JTextField tfVeces, tfPath, tfPalabra;
	

	public Principal() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Buscar palabra");
		setLocation(200, 200);
		setSize(600, 500);
		
		getContentPane().add(crearPanelPrincipal(), BorderLayout.CENTER);
		
		
		setVisible(true);
	}

	private Component crearPanelPrincipal() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 20 ,20));
		
		tfPath = new JTextField();
		tfPath.setActionCommand("find");
		tfPath.addActionListener(this);
		tfPath.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN),
				"Path: "));
		panel.add(tfPath);
		
		tfPalabra = new JTextField();
		tfPalabra.setActionCommand("find");
		tfPalabra.addActionListener(this);
		tfPalabra.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN),
				"Palabra a buscar: "));
		panel.add(tfPalabra);
		
		tfVeces  = new JTextField();
		tfVeces.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN),
				"Veces en fichero: "));
		panel.add(tfVeces);
		
		JPanel subPanel = new JPanel();
		
		bSeleccionar = new JButton("...");
		bSeleccionar.addActionListener(this);
		bSeleccionar.setActionCommand("select");
		subPanel.add(bSeleccionar);
		
		bBuscar = new JButton("Buscar");
		bBuscar.addActionListener(this);
		bBuscar.setActionCommand("find");
		subPanel.add(bBuscar);
		
		panel.add(subPanel);
		
		
		return panel;
	}

	public int buscarPalabraFile(String archivo, String palabra) throws FileNotFoundException, PalabraLargaExcertion {
		int veces = 0;
		BufferedReader in = null;
		String linea;
		String palabras[];

		in = new BufferedReader(new FileReader(archivo));
		try {
			if(palabra.split(" ").length != 1) throw new PalabraLargaExcertion();
			while ((linea = in.readLine()) != null) {
				palabras = linea.split(" ");
				for(int i = 0; i < palabras.length; i++){
					if(palabra.compareTo(palabras[i]) == 0){
						veces++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			;
		}
		
		return veces;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "find":
			
			
			try {
				tfVeces.setText("" + buscarPalabraFile(
						tfPath.getText(),
						tfPalabra.getText()));
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(this, "Archivo no encontrado");
			} catch (PalabraLargaExcertion e1) {
				JOptionPane.showMessageDialog(this, "Debe ser una sola palabra");
			}
		
			
			break;
		case "select":
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(false);
			if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(this)){
				if(fc.getSelectedFile() != null){
					archivo = fc.getSelectedFile();
					tfPath.setText(archivo.getPath());
				}
			}
			
			break;

		default:
			break;
		}
		
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Principal a = new Principal();
	}
	

}
