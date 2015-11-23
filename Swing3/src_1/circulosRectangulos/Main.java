package circulosRectangulos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.Scrollable;



public class Main implements ActionListener {
	
	JFrame ventana;
	JMenuBar barra;
	JMenu editar, salir;
	JMenuItem opcionMenu;
	JToolBar toolBar;
	

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();
	}

	private void miMain() {
		ventana = new JFrame("Lista figuras");
		ventana.setJMenuBar(crearBarraMenu());
		ventana.setSize(640, 480);
		ventana.setLocation(200, 100);
		ventana.setContentPane(crearPanelVentana());
		ventana.setEnabled(true);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	private Container crearPanelVentana() {
		JSplitPane panelCentral = new JSplitPane();
		panelCentral.setDividerLocation(0.7);
		panelCentral.setLeftComponent(panelIzquierdo());
		panelCentral.setRightComponent(panelDerecho());
		return panelCentral;
	}

	private Component panelDerecho() {
		JPanel panelDerecho = new JPanel();
		panelDerecho.setPreferredSize(new Dimension(250, 500));
		return panelDerecho;
	}

	private Component panelIzquierdo() {
		JPanel panelIzquierdo = new JPanel(new BorderLayout());
		
		panelIzquierdo.add(crearToolBar(), BorderLayout.NORTH);
		
		panelIzquierdo.add(crearPanelCentral(), BorderLayout.CENTER);
		return panelIzquierdo;
	}

	private Component crearPanelCentral() {
		ScrollPane scroll  = new ScrollPane();
		
		return scroll;
	}

	private JToolBar crearToolBar() {
		toolBar = new JToolBar();
		toolBar.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JButton boton;
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/window_new.png")));
		boton.setActionCommand("Añadir");
		boton.addActionListener(this);
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/editcut.png")));
		boton.setActionCommand("Borrar");
		boton.addActionListener(this);
		
		toolBar.add(Box.createHorizontalGlue());
		
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/exit.png")));
		boton.setActionCommand("Salir");
		boton.addActionListener(this);
		
		return toolBar;
	}

	private JMenuBar crearBarraMenu() {
		barra = new JMenuBar();
		barra.add(crearMenuEditar());
		barra.add(crearMenuSalir());
		
		return barra;
	}

	private JMenu crearMenuEditar() {
		editar = new JMenu("Editar");
		
		opcionMenu = new JMenuItem("Añadir");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/window_new.png"));
		editar.add(opcionMenu);
		
		editar.addSeparator();
		
		opcionMenu = new JMenuItem("Borrar");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/editcut.png"));
		editar.add(opcionMenu);
		
		return editar;
	}

	private JMenu crearMenuSalir() {
		salir = new JMenu("Salir");
		return salir;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	private class OpenAccion extends AbstractAction{
		String texto;
		public OpenAccion(String texto, Icon imagen,String descrip, Integer nemonic ){
			super(texto, imagen);
			this.texto = texto;
			this.putValue(Action.SHORT_DESCRIPTION, descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
			
		}
		public void actionPerformed(ActionEvent e){
						
		}
		
	}

}
