package hirugarrena;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class MainFrame implements ActionListener {
	final static int ANCHOPANTALLA = 640;
	final static int LARGOPANTALLA = 480;
	final static int POSPANTALLAX = 100;
	final static int POSPANTALLAY = 100;
	JFrame ventana;
	JList<Forma> listaFormas;
	DefaultListModel<Forma> modelo;

	public MainFrame() {
		ventana = new JFrame("Lista Figuras");
		ventana.setSize(ANCHOPANTALLA, LARGOPANTALLA);
		ventana.setLocation(POSPANTALLAX, POSPANTALLAY);
		ventana.setContentPane(crearPanelPrincipal());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}

	private Container crearPanelPrincipal() {
		int distanciaBorde = 10;
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(distanciaBorde, distanciaBorde, distanciaBorde, distanciaBorde));
		panel.add(creaArriba(), BorderLayout.NORTH);
		panel.add(crearPanelIzquierdo(), BorderLayout.WEST);
		panel.add(crearPanelDerecho(), BorderLayout.CENTER);
		return panel;
	}

	private Component creaArriba() {
		JMenuBar menuBar = new JMenuBar();
		
		return menuBar;
	}

	private Component crearPanelIzquierdo() {
		JPanel pDerecho = new JPanel(new BorderLayout());
		pDerecho.add(crearToolBar(), BorderLayout.NORTH);

		pDerecho.add(crearPanelCentral(), BorderLayout.CENTER);
		pDerecho.add(crearPanelSur(), BorderLayout.SOUTH);
		return pDerecho;
	}

	private Component crearPanelSur() {
		JPanel panel = new JPanel();

		return panel;
	}

	private Component crearPanelCentral() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listaFormas = new JList<>();
		modelo = new DefaultListModel<>();
		listaFormas.setModel(modelo);
		panel.setViewportView(listaFormas);
		return panel;
	}

	public JToolBar crearToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(BorderFactory.createRaisedBevelBorder());
		JButton boton;
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("imagenes/window_new.png")));
		boton.setActionCommand("Mas");
		boton.addActionListener(this);

		boton = (JButton) toolBar.add(new JButton(new ImageIcon("imagenes/editdelete.png")));
		boton.setActionCommand("Menos");
		boton.addActionListener(this);

		toolBar.add(Box.createHorizontalGlue());

		boton = (JButton) toolBar.add(new JButton(new ImageIcon("imagenes/exit.png")));
		boton.setActionCommand("Cerrar");
		boton.addActionListener(this);

		return toolBar;
	}

	private Component crearPanelDerecho() {
		JPanel panel = new MiPanelPintura();
		panel.setPreferredSize(new Dimension(250, 500));
		return panel;
	}

	private class MiPanelPintura extends JPanel {
		@Override
		public void paint(Graphics g) {
			Forma e;
			super.paint(g);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			for (int i = 0; i < modelo.size(); i++) {
				 e = modelo.get(i);
				 e.paint(g);
			}
		}
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		MiDialogoFormas crearForma;
		Forma agregar;
		int sinSeleccionar = -1;
		switch (e.getActionCommand()) {
		case "Mas":
			crearForma = new MiDialogoFormas(ventana);
			agregar = crearForma.getForma();
			if (agregar != null)
				modelo.addElement(agregar);
			ventana.repaint();
			break;
		case "Menos":
			if(listaFormas.getSelectedIndex() != sinSeleccionar){
				modelo.remove(listaFormas.getSelectedIndex());
				ventana.repaint();
			}
			break;
		case "Cerrar":
			ventana.dispose();
			break;

		default:
			break;
		}
	}

	public static void main(String[] args) {
		MainFrame mainF = new MainFrame();
	}
}

