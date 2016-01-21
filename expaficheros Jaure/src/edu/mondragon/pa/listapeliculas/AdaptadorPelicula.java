package edu.mondragon.pa.listapeliculas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.PanelUI;

public class AdaptadorPelicula implements ListCellRenderer<Pelicula>  {
	
	Pelicula pelicula;

	@Override
	public Component getListCellRendererComponent(JList<? extends Pelicula> arg0, Pelicula pelicula, int arg2, boolean arg3,
			boolean arg4) {
		this.pelicula=pelicula;
		JPanel panelAdap=new JPanel(new BorderLayout(0,30));
		panelAdap.setBorder(BorderFactory.createLineBorder(Color.gray));		
		panelAdap.add(crearPanelImagen(), BorderLayout.WEST);
		panelAdap.add(crearPanelDatos(), BorderLayout.CENTER);
		panelAdap.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,0,10,0),
				BorderFactory.createLineBorder(Color.darkGray)));
		return panelAdap;
	}


	private Component crearPanelDatos() {
		JPanel panelDatos= new JPanel(new GridLayout(5, 0, 10, 10));
		JLabel l1=new JLabel();JLabel l2=new JLabel();JLabel l3=new JLabel();JLabel l4=new JLabel();JLabel l5=new JLabel();
		l1.setBackground(Color.cyan);
		l1.setText(pelicula.getTitulo());
		l2.setText(String.valueOf(pelicula.getAño()));
		l3.setText(pelicula.getDirector());
		l4.setText(pelicula.getNacionalidad());
		l5.setBackground(Color.red);
		l5.setText(pelicula.getGenero());
		panelDatos.add(l1);panelDatos.add(l2);panelDatos.add(l3);panelDatos.add(l4);panelDatos.add(l5);
		return panelDatos;
	}

	private Component crearPanelImagen() {
		JPanel panImage=new JPanel();
		JLabel imagen=new JLabel(new ImageIcon("caratulas/"+pelicula.getCaratula()));
		panImage.add(imagen);
		return panImage;
	}

}
