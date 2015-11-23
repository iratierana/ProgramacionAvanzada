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

public class Principal extends JFrame implements ActionListener {
	
	final int ALTURA=400;
	final int ANCHURA=400;
	JButton buscarFich, salir;
	JTextField nombre, path, tamaño1, tamaño2;
	File fich;
		
	public Principal(String titulo, int posX, int posY){	
		this.setTitle(titulo);
		this.setSize(ALTURA, ANCHURA);		
		this.setLocation(posX, posY);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panelGenreral=new JPanel(new BorderLayout());
		
		panelGenreral.add(crearPanelCentral(), BorderLayout.CENTER);
		panelGenreral.add(crearPanelInferior(), BorderLayout.SOUTH);
		panelGenreral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		return panelGenreral;
	}

	private Component crearPanelCentral() {
		JPanel panelCentr=new JPanel(new GridLayout(3, 1, 20 ,20));
		
		nombre=new JTextField();
		nombre.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Nombre de fichero"));
		path=new JTextField();
		path.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Path"));
		
		panelCentr.add(nombre);panelCentr.add(path);
		panelCentr.add(crearPanelDiv());
		
		return panelCentr;
	}

	private Component crearPanelDiv() {
		JPanel panel=new JPanel(new GridLayout(1, 2, 40, 40));
		
		tamaño1=new JTextField();
		tamaño1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Tamaño File"));
		tamaño2=new JTextField();
		tamaño2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Mi Tamaño"));
		panel.add(tamaño1);
		panel.add(tamaño2);
		return panel;
	}

	private Component crearPanelInferior() {
		JPanel panelInf =new JPanel(new GridLayout(1, 2, 20, 20));
		
		panelInf.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		buscarFich=new JButton("Buscar fichero");
		buscarFich.addActionListener(this);
		buscarFich.setActionCommand("buscar");
		salir=new JButton("Salir");
		salir.addActionListener(this);
		salir.setActionCommand("salir");
		panelInf.add(buscarFich);
		panelInf.add(salir);
		
		return panelInf;
	}
	
	public String calcularTamaño(File fichero){
		String devolver;
		long kont=0;
		try {
			FileInputStream lector=new FileInputStream(fichero);
			while(lector.read()!=-1){
				kont++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		devolver=String.valueOf(kont);
		return devolver;
	}
	
	public void respuestaBotonBuscar(){
		JFileChooser buscador=new JFileChooser();
		if(buscador.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			fich=buscador.getSelectedFile();
			nombre.setText(fich.getName());
			path.setText(fich.getPath());
			tamaño1.setText(String.valueOf(fich.length()));
		}
		tamaño2.setText(calcularTamaño(fich));																																																											//Jaure HiJoputa
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()){
		case "buscar":
			respuestaBotonBuscar();			
			break;
		case "salir":
			this.dispose();
			break;
		}
		
		
	}

}
