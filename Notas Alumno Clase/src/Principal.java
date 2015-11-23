import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Principal extends JFrame implements ListSelectionListener{
	
	final int ANCHO=500, ALTO=400;
	MisAcciones accFile, accGuardar, accCargar;
	public static ListaAlumnos listaAlumCarga;
	JList<Alumno> listaDeAlumnos;
	JList<ResultadoAprendizaje> listaNotas;
	DefaultListModel<ResultadoAprendizaje> modelo;
	public static String nombreFichero;
	AdaptadorNotas adapNotas;
	AdaptadorAlumno adapAlumno;

	public Principal(String titulo, int posX, int posY){
		
		this.setSize(ANCHO, ALTO);
		this.setTitle(titulo);
		this.setLocation(posX, posY);
		this.crearAcciones();	
		this.setJMenuBar(crearMenuBar());
		this.add(crearToolBar(), BorderLayout.NORTH);		
		this.add(crearPanelVentana(), BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void crearAcciones() {			
		accGuardar=new MisAcciones("Guardar");
		accCargar=new MisAcciones("Cargar");
	}
	
	private JToolBar crearToolBar() {
		JToolBar toolBar=new JToolBar();
		JButton bGuardar=new JButton(accGuardar);
		JButton bCargar=new JButton(accCargar);
		
		toolBar.add(bGuardar);
		toolBar.add(bCargar);
		
		return toolBar;
	}

	private JMenuBar crearMenuBar() {
		JMenuBar barraMenu=new JMenuBar();
		barraMenu.add(crearMenuaFile());			
		return barraMenu;
	}


	private JMenu crearMenuaFile() {
		JMenu menuFile=new JMenu("File");
		JMenuItem opcionGuardar=new JMenuItem(accGuardar);	
		JMenuItem opcionCargar=new JMenuItem(accCargar);	
		menuFile.add(opcionGuardar);
		menuFile.add(opcionCargar);
		return menuFile;
	}

	private Container crearPanelVentana() {		
		JSplitPane panelGen=new JSplitPane();		
		panelGen.setDividerLocation(175);	
		panelGen.setLeftComponent(crearPanelIzquierdo());
		panelGen.setRightComponent(crearPanelDerecho());
		return panelGen;
	}

	private Component crearPanelDerecho() {
		JPanel panelDerecho=new JPanel(new GridLayout());
		listaNotas=new JList<ResultadoAprendizaje>();
		adapNotas=new AdaptadorNotas();
		panelDerecho.add(listaNotas);
		listaNotas.setCellRenderer(adapNotas);
		return panelDerecho;
	}

	private Component crearPanelIzquierdo() {
		JPanel panelIzquierdo=new JPanel (new BorderLayout());
		adapAlumno=new AdaptadorAlumno();
		listaDeAlumnos=new JList<Alumno>();
		this.listaDeAlumnos.addListSelectionListener(this);
		listaAlumCarga=new ListaAlumnos();		
		listaDeAlumnos.setModel(listaAlumCarga);
		listaDeAlumnos.setCellRenderer(adapAlumno);
		panelIzquierdo.add(listaDeAlumnos);
		
		return panelIzquierdo;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//if (e.getValueIsAdjusting()) return;
		Alumno a=listaDeAlumnos.getSelectedValue();	
		modelo=new DefaultListModel<ResultadoAprendizaje>();
		for(ResultadoAprendizaje r: a.notas){
			modelo.addElement(r);
		}
		listaNotas.setModel(modelo);
	}
}
