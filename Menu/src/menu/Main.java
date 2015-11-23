package menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Main implements ActionListener {

	JFrame ventana;
	JMenuBar barra;
	JMenu menuFile, menuEdit, subMenu;
	JMenuItem opcionMenu;
	JToolBar toolBar;
	AbstractAction accCut, accPaste, accCopy;
	
	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();

	}

	private void miMain() {
		ventana = new JFrame("Menu");
		this.crearAcciones();
		ventana.setJMenuBar(crearBarraMenu());
		ventana.setSize(640, 480);
		ventana.setLocation(200, 100);
		ventana.setContentPane(crearPanelVentana());
		ventana.getContentPane().add(crearToolBar(), BorderLayout.NORTH);
		ventana.setEnabled(true);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void crearAcciones(){
		accCut = new OpenAccion("Cut", new ImageIcon("icons/editcut.png"), "Cortar", null);
		accCopy = new OpenAccion("Copy", new ImageIcon("icons/editcopy.png"),"Copiar", null);
		
	}

	private JToolBar crearToolBar() {
		
		toolBar = new JToolBar();
		toolBar.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JButton boton;
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/window_new.png")));
		boton.setActionCommand("Open");
		boton.addActionListener(this);
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/kgpg.png")));
		boton.setActionCommand("Close");
		boton.addActionListener(this);
		
		toolBar.addSeparator(new Dimension(20, 0));
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/editcopy.png")));
		boton.setActionCommand("Copy");
		boton.addActionListener(this);
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/editcut.png")));
		boton.setActionCommand("Cut");
		boton.addActionListener(this);
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/editpaste.png")));
		boton.setActionCommand("Paste");
		boton.addActionListener(this);
		
		toolBar.add(Box.createHorizontalGlue());
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/exit.png")));
		boton.setActionCommand("Exit");
		boton.addActionListener(this);
		
		return toolBar;
	}

	private JMenuBar crearBarraMenu() {
		barra = new JMenuBar();
		barra.add(crearMenuFile());
		barra.add(crearMenuEdit());
		barra.add(Box.createHorizontalGlue());
		barra.add(new JMenu("Exit"));
		return barra;
	}

	private JMenu crearMenuEdit() {
		menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		
		
		/*	
		opcionMenu = new JMenuItem("Copy");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/editcopy.png"));
		menuEdit.add(opcionMenu);
		*/
		opcionMenu = menuEdit.add(accCopy);//esto hace lo mismo que las 4 lineas de arriba
		
		menuEdit.addSeparator();
		/*	
		opcionMenu = new JMenuItem("Cut");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/editcut.png"));
		menuEdit.add(opcionMenu);
		*/
		opcionMenu = menuEdit.add(accCut);
		
		menuEdit.addSeparator();
		
		opcionMenu = new JMenuItem("Paste");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/editpaste.png"));
		menuEdit.add(opcionMenu);
		
		menuEdit.addSeparator();
		
		menuEdit.add(crearSubmenuConver());
		
		return menuEdit;
	}

	private JMenu crearSubmenuConver() {
		subMenu = new JMenu("Content Assist");
		
		opcionMenu = new JMenuItem("Java type Proposals");
		opcionMenu.addActionListener(this);
		
		opcionMenu.setMnemonic(KeyEvent.VK_P);
		subMenu.add(opcionMenu);
		
		return subMenu;
	}

	

	private JMenu crearMenuFile() {
		menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		
		opcionMenu = new JMenuItem("New");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/window_new.png"));
		menuFile.add(opcionMenu);
		
		
		menuFile.addSeparator();
		
		opcionMenu = new JMenuItem("Open");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/window_new.png"));
		menuFile.add(opcionMenu);
		
		
		opcionMenu = new JMenuItem("Close");
		opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/tux.png"));
		menuFile.add(opcionMenu);
		
		return menuFile;
	}

	private Container crearPanelVentana(){
		JPanel panel= new JPanel(new BorderLayout());
		
		return panel;
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
			JMenuItem opcion;
			String textoMsg = null;
			
			JOptionPane.showMessageDialog(Main.this.ventana, texto,"mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	
	
	public void actionPerformed(ActionEvent e){
		JMenuItem opcion;
		String textoMsg = null;
		
		if(e.getSource()instanceof JMenuItem){
			opcion = (JMenuItem) e.getSource();
			textoMsg = opcion.getText();
		}
		if (e.getSource() instanceof JButton) {
			textoMsg = ((JButton) e.getSource()).getActionCommand();
			if(textoMsg.equals("Exit")){
				System.exit(0);
			}
		}
		JOptionPane.showMessageDialog(ventana, textoMsg,"mensaje",JOptionPane.INFORMATION_MESSAGE);
	}
}
