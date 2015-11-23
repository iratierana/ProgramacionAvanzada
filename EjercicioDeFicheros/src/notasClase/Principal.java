package notasClase;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Principal extends JFrame implements ListSelectionListener{

	MisAcciones accGuardar, accCargar;
	
	public Principal(String titulo, int posX , int posY){
		this.setSize(400, 400);
		this.setTitle(titulo);
		this.setLocation(posX, posY);
		this.crearAcciones();
		this.setJMenuBar(crearMenuBar());
		this.add(crearToolBar(), BorderLayout.NORTH);		
		this.add(crearPanelVentana(), BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Component crearPanelVentana() {
		JSplitPane panel = new JSplitPane();
		
		panel.setDividerLocation(200);
		panel.setLeftComponent(crearPanelIzquierdo());
		panel.setRightComponent(crearPanelDerecho());
		
		return panel;
	}

	private Component crearPanelIzquierdo() {
		JPanel panel = new JPanel(new BorderLayout());
		
		return panel;
	}

	private JToolBar crearToolBar() {
		JToolBar toolBar = new JToolBar();
		
		JButton botonGuardar = new JButton(accGuardar);
		JButton botonCargar  = new JButton(accCargar);
		
		toolBar.add(botonGuardar);
		toolBar.add(accCargar);
		
		return toolBar;
	}

	private JMenuBar crearMenuBar() {
		JMenuBar barraMenu = new JMenuBar();
		barraMenu.add(crearMenuFile());
		
		return barraMenu;
	}

	private JMenu crearMenuFile() {
		JMenu menu = new JMenu("File");
		
		JMenuItem guardar = new JMenuItem(accGuardar);
		JMenuItem cargar = new JMenuItem(accCargar);
		
		menu.add(guardar);
		menu.add(cargar);
		
		return menu;
	}

	private void crearAcciones() {
		accGuardar = new MisAcciones("Guardar");
		accCargar = new MisAcciones("Cargar");
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
