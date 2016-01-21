package edu.mondragon.pa.tablamodelcambiosbien;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class Principal extends JFrame implements ActionListener, ListSelectionListener {
	
	
	ModeloTablaAlumno tabla;
	
	JLabel lTexto;
	JTable vTabla;
	
	
	TrazadorTablaAlumnos trazador;
	ModeloColumnasTablaAlumnos columnas;
	JScrollPane panelS;
	public Principal(){
		super("Alumnos 2º de grado de informática");
		
		this.setSize(800,600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
	}

	
	private Container crearPanelVentana() {
		
		JPanel panel = new JPanel (new BorderLayout(20,20));
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelS = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(crearPanelTexto(), BorderLayout.NORTH);
		panel.add(panelS,BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		crearTabla();
		return panel;
	}
	


	private Component crearPanelTexto() {
		JPanel panel = new JPanel (new GridLayout(1,1));
		lTexto = new JLabel(" ");
		lTexto.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.add(lTexto);
		return panel;
	}


	private void crearTabla() {
		trazador = new TrazadorTablaAlumnos();
		columnas = new ModeloColumnasTablaAlumnos (trazador);
		tabla = new ModeloTablaAlumno(columnas);
		vTabla = new JTable(tabla,columnas);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.getSelectionModel().addListSelectionListener(this);
		vTabla.setFillsViewportHeight(true);
		vTabla.getTableHeader().setReorderingAllowed(false);
		vTabla.setRowSelectionInterval(0, 0);
		panelS.setViewportView(vTabla);
	}
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
		JButton bAñadir = new JButton ("Añadir");
		JButton bBorrar = new JButton ("Borrar");
		
		bAñadir.setActionCommand("añadir");
		bBorrar.setActionCommand("borrar");
		
		bAñadir.addActionListener (this);
		bBorrar.addActionListener(this);
		panel.add(bAñadir);
		panel.add(bBorrar);
		
		return panel;
	}

	public static void main(String[] args) {
		Principal ejercicio = new Principal();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "borrar":
			int indice = vTabla.getSelectedRow();
			if (indice != -1){
				tabla.borrar(indice);
			}
			
			break;
		case "añadir" :
			//mostrar dialogo de inserción alumno
			//obtener alumno creado
			tabla.insertar(new Alumno ("Txema","Perez","Lazare","Basauri",54,false));
			break;
		}
		vTabla.setRowSelectionInterval(0, 0);
	}
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (vTabla.getSelectedRow()==-1) return;
		if (!e.getValueIsAdjusting()){
			
			lTexto.setText (tabla.getAlumno(vTabla.getSelectedRow()).toString());
		}
		
	}

}
