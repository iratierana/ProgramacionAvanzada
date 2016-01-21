package edu.mondragon.pa.listapeliculas;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class Principal extends JFrame implements ActionListener{
	
	JScrollPane panelScroll;
	JButton bIzq, bDer;
	JList<Pelicula> listaDePeliculas;
	ListaPeliculas listaPeli;
	AdaptadorPelicula adapPeli;
	ModeloListaPeliculas modeloLista;
	JLabel pag;
	static int kont=5;

	Principal(){
		this.setTitle("Lista de peliculas");
		this.setLocation(new Point(200, 200));
		this.setSize(350, 500);
		this.add(crearPanelVentana());
		this.add(crearToolbar(), BorderLayout.NORTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Component crearToolbar() {
		JToolBar barraTool=new JToolBar();	
		barraTool.setBorder(BorderFactory.createEmptyBorder());
		bIzq=new JButton(new ImageIcon("iconos/1leftarrow.png"));	
		bIzq.setEnabled(false);
		bIzq.addActionListener(this);
		bIzq.setActionCommand("bIzq");
		bDer=new JButton(new ImageIcon("iconos/1rightarrow.png"));
		bDer.addActionListener(this);
		bDer.setActionCommand("bDer");
		pag=new JLabel("0-5");
		barraTool.add(bIzq);
		barraTool.add(Box.createGlue());
		barraTool.add(pag);
		barraTool.add(Box.createHorizontalGlue());
		barraTool.add(bDer);
		return barraTool;
	}

	private Component crearPanelVentana() {
		JPanel panelGeneral=new JPanel(new BorderLayout());
		panelGeneral.add(crearPanelLista(BorderLayout.CENTER));
		return panelGeneral;
	}

	private Component crearPanelLista(String center) {
		JPanel panelLista=new JPanel(new BorderLayout());
		
		panelScroll=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelLista.add(panelScroll, BorderLayout.CENTER);
		
		listaDePeliculas=new JList<Pelicula>();
		listaPeli=new ListaPeliculas();
		modeloLista=new ModeloListaPeliculas(listaPeli);
		adapPeli=new AdaptadorPelicula();
		
		listaDePeliculas.setModel(modeloLista);
		listaDePeliculas.setCellRenderer(adapPeli);
		this.panelScroll.setViewportView(listaDePeliculas);
		
		return panelLista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand().toLowerCase()){
		case "bder":
			if((kont+5)>14){
				bDer.setEnabled(false);
			}else{
				bDer.setEnabled(true);
			}
			if((kont-5)<0){
				bIzq.setEnabled(false);
			}else{
				bIzq.setEnabled(true);
			}
			modeloLista.clear();
			modeloLista.cargarSigCincoPelis(listaPeli);
			
			kont=kont+5;
			this.pag.setText((this.kont-5)+"-"+this.kont);
			break;
		case "bizq":
			if((kont+5)>14){
				bDer.setEnabled(false);
			}else{
				bDer.setEnabled(true);
			}
			if((kont-5)<0){
				bIzq.setEnabled(false);
			}else{
				bIzq.setEnabled(true);
			}
			modeloLista.clear();
			modeloLista.cargarAntCincoPelis(listaPeli);
			
			kont=kont-5;
			this.pag.setText((this.kont-5)+"-"+this.kont);
			break;
		}
	}
}
