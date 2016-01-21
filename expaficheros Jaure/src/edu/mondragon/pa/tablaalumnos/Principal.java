package edu.mondragon.pa.tablaalumnos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Principal extends JFrame implements ActionListener{
	
	JButton sel;
	JRadioButton pobl, edad, nomb, apell;
	JTextField zonTxt;
	JScrollPane panelS;
	JTable vTabla;	
	TrazadorTablaAlumnos trazador;
	ModeloColumnasTablaAlumnos columnas;
	ModeloTablaAlumno tabla;
	ListaAlumnos listaAlumnos;
	
	Principal(){
		this.setTitle("Alumnos 2º de grado de informatica");
		this.setLocation(new Point(200, 150));
		this.setSize(1000, 400);
		this.add(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Component crearPanelVentana() {
		JPanel panelGen =new JPanel(new BorderLayout(10,0));
		panelGen.add(crearPanelDerecho(), BorderLayout.CENTER);
		panelGen.add(crearPanelIzquierdo(), BorderLayout.WEST);
		return panelGen;
	}

	private Component crearPanelDerecho() {
		JPanel panelDerc=new JPanel(new BorderLayout());
		this.panelS=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelDerc.add(panelS, BorderLayout.CENTER);
		crearTabla();		
		return panelDerc;
	}
	
	private void crearTabla() {
		trazador = new TrazadorTablaAlumnos();
		columnas = new ModeloColumnasTablaAlumnos (trazador);
		tabla = new ModeloTablaAlumno(columnas);
		vTabla = new JTable(tabla,columnas);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.setFillsViewportHeight(true);
		vTabla.getTableHeader().setReorderingAllowed(false);
		vTabla.setRowSelectionInterval(0, 0);
		panelS.setViewportView(vTabla);
	}

	private Component crearPanelIzquierdo() {
		JPanel panelIzq=new JPanel();		
		panelIzq.add(crearPanelIzquierdoSur());
		return panelIzq;
	}
	
	private Component crearPanelIzquierdoSur(){
		JPanel panelIzqSur=new JPanel(new BorderLayout());
		panelIzqSur.setBorder(BorderFactory.createLineBorder(Color.blue));
		panelIzqSur.add(crearPanelNort(), BorderLayout.NORTH);
		panelIzqSur.add(crearPanelCentro(), BorderLayout.CENTER);
		panelIzqSur.add(crearPanelSur(), BorderLayout.SOUTH);
		return panelIzqSur;
	}

	private Component crearPanelSur() {
		JPanel panelBot=new JPanel();
		this.sel=new JButton("Seleccionar");
		sel.addActionListener(this);
		sel.setActionCommand("sel");
		panelBot.add(sel);
		return panelBot;
	}

	private Component crearPanelCentro() {
		JPanel panelTxt=new JPanel();
		this.zonTxt=new JTextField();
		zonTxt.setPreferredSize(new Dimension(130, 20));
		panelTxt.add(zonTxt);
		return panelTxt;
	}

	private Component crearPanelNort() {
		JPanel panelSur=new JPanel(new GridLayout(2, 2));
		ButtonGroup grupoBot=new ButtonGroup();
		this.nomb=new JRadioButton("Nombre");
		grupoBot.add(nomb);
		nomb.addActionListener(this);
		this.pobl=new JRadioButton("Poblacion");
		grupoBot.add(pobl);
		pobl.addActionListener(this);
		this.edad=new JRadioButton("Edad");
		grupoBot.add(edad);
		edad.addActionListener(this);
		this.apell=new JRadioButton("Apellido1");
		grupoBot.add(apell);
		apell.addActionListener(this);
		panelSur.add(pobl);panelSur.add(edad);panelSur.add(nomb);panelSur.add(apell);
		return panelSur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listaAlumnos=new ListaAlumnos();
		if(e.getActionCommand().equals("sel")){
			if(pobl.isSelected()){
				listaAlumnos.llenarPorDatos(zonTxt.getText(), -1, null, null);
			}
			if(edad.isSelected()){
				listaAlumnos.llenarPorDatos(null, Integer.parseInt(zonTxt.getText()), null, null);
			}
			if(nomb.isSelected()){
				listaAlumnos.llenarPorDatos(null, -1, zonTxt.getText(), null);
			}
			if(apell.isSelected()){
				listaAlumnos.llenarPorDatos(null, -1, null, zonTxt.getText());
			}
			tabla.refrescarLista(listaAlumnos);
		}
		
	}
	
	
}
