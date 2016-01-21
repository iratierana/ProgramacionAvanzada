package edu.mondragon.pa.listapeliculas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class Principal extends JFrame implements Observer,ItemListener{
	
	private static final long serialVersionUID = 1L;
	JList<Pelicula> lista;
	ListaPeliculas modelo;
	MiAccion adelante, atras;
	String texto = "";

	
	public Principal(){
		super("Cartelera");
		modelo = new ListaPeliculas();
		modelo.addObserver(this);
		setLocation (240,100);
		crearAcciones();
		setContentPane(crearPanelVentana());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void crearAcciones() {
		adelante = new MiAccion ("Añadir",new ImageIcon("iconos/1rightarrow.png"),"Adelante",
				new Integer(KeyEvent.VK_A));
		atras = new MiAccion ("Calificar",new ImageIcon("iconos/1leftarrow.png"),"Atras",
				new Integer(KeyEvent.VK_B));
	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout(0,10));
		panel.add(panelBarraBotones(), BorderLayout.NORTH);
		panel.add(panelLista(),BorderLayout.CENTER);
		return panel;
	}
	
	private Component panelBarraBotones() {
		JToolBar toolBar = new JToolBar();
		JButton boton;
		
		boton =(JButton) toolBar.add(atras);
		toolBar.add(Box.createHorizontalGlue());
		boton =(JButton) toolBar.add(adelante);
		return toolBar;
	}
	private Component panelLista() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lista = new JList<Pelicula>();
		lista.setModel(modelo);
		panel.setViewportView(lista);
		return panel;
	}
	
	private class MiAccion extends AbstractAction {
		String texto;
		public MiAccion (String texto, Icon imagen, String descrip, Integer nemonic){
			super(texto,imagen);
			this.texto = texto;
			this.putValue( Action.SHORT_DESCRIPTION ,descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//if(e.)
		}	
	}

	public void update(Observable ob) {
		this.repaint();
	}
	
	public static void main(String[] args) {
		Principal ariketa = new Principal();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
