package popbl3;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.media.j3d.Canvas3D;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.leapmotion.leap.Controller;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class PacienteFrame extends JFrame implements ListSelectionListener, Observer{

	Paciente paciente;
	
	MiAction accVerResultados;
	MiAction accHacerEjercicio;
	MiAction accLogOut;

	JLabel textoDescripcion;
	JLabel textoResultadoInSitu;
	JLabel pantallaImagen;
	JLabel pantallaWebcam;
	JPanel panatallaLeap;
	Canvas3D visualizador;
	GestorMano gestor;
	Mano3D mano3d;
	
	
	ModeloListaEjercicios modelo;
	EjercicioRenderer cellRenderer;
	JList<Ejercicio> listaEjercicios;

	private LeapMotionListener leapListener;

	private Controller controlador;
	
	public PacienteFrame(Paciente paciente){
		super (paciente.getNombre()+" "+paciente.getApellido1()+" "+paciente.getApellido2()+" (Paciente)");
		this.paciente = paciente;
		this.crearAcciones();
		this.setSize(800,600);
		this.setMinimumSize(new Dimension(800,600));
		this.setLocation(50,25);
		this.setJMenuBar(crearBarraMenu());
		this.getContentPane().add(crearToolBar(),BorderLayout.NORTH);
		this.getContentPane().add(crearPanelVentana(),BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		gestor = new GestorMano();
		gestor.addObserver(this);
		
		leapListener = new LeapMotionListener(gestor);
		controlador = new Controller();
		
		controlador.addListener(leapListener);
		
		
	}

	private void crearAcciones() {
		accVerResultados = new MiAction("Ver resultados", new ImageIcon("iconos/verResultados.png"), "Ver resultados del ejercicio", KeyEvent.VK_V);
		accHacerEjercicio = new MiAction("Hacer ejercicio", new ImageIcon("iconos/hacerEjercicio.png"), "Realizar ejercicio selecionado", KeyEvent.VK_H);
		accLogOut = new MiAction("Log out", new ImageIcon("iconos/logOut.png"), "Cerrar la sesión actual", KeyEvent.VK_O);
	}
	
	private JMenuBar crearBarraMenu() {
		JMenuBar barra =  new JMenuBar();
		JMenu editar = new JMenu("Editar");
		JMenu salir = new JMenu("Salir");
		
		editar.add(accVerResultados);
		editar.addSeparator();
		editar.add(accHacerEjercicio);
		
		barra.add(editar);
		barra.add(Box.createHorizontalGlue());
		
		salir.add(accLogOut);
		barra.add (salir);
		
		return barra;	
	}
	
	private Component crearToolBar() {
		JToolBar toolBar = new JToolBar();
		JButton boton;
		
		toolBar.setFloatable(false);
		
		toolBar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	
		boton =(JButton) toolBar.add(new JButton (accVerResultados));
		toolBar.addSeparator(new Dimension (20,0));
		boton =(JButton) toolBar.add(new JButton (accHacerEjercicio));
		
		toolBar.add(Box.createHorizontalGlue());
		
		boton =(JButton) toolBar.add(new JButton (accLogOut));
		
		return toolBar;
	}


	private Component crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(crearPanelListaEjercicios(), BorderLayout.WEST);
		panel.add(crearPanelEjercicio(), BorderLayout.CENTER);
		
		return panel;
	}

	private Component crearPanelListaEjercicios() {
		JScrollPane panelScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelScroll.setPreferredSize(new Dimension(200,0));
		
		cellRenderer = new EjercicioRenderer();
		modelo = new ModeloListaEjercicios(paciente);
		listaEjercicios = new JList<>();
		
		listaEjercicios.setModel(modelo);
		listaEjercicios.setCellRenderer(cellRenderer);
		
		listaEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEjercicios.addListSelectionListener(this);
		
		panelScroll.setViewportView(listaEjercicios);
		
		return panelScroll;
	}

	private Component crearPanelEjercicio() {
		JPanel panel = new JPanel(new BorderLayout(20,20));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		panel.add(crearPanelDescripcion(), BorderLayout.NORTH);
		panel.add(crearPanelCentro(), BorderLayout.CENTER);
		panel.add(crearPanelResultadoInSitu(), BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearPanelDescripcion() {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setPreferredSize(new Dimension(0,50));
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		textoDescripcion = new JLabel(" ");
		textoDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		textoDescripcion.setVerticalAlignment(SwingConstants.CENTER);
		
		panel.add(textoDescripcion);
		
		return panel;
	}

	private Component crearPanelCentro() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		
		panel.add(crearPanelImagen());
		panel.add(crearPanelWebcam());
		
		return panel;
	}

	private Component crearPanelImagen() {
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		pantallaImagen = new JLabel();
		pantallaImagen.setHorizontalAlignment(JLabel.CENTER);
		pantallaImagen.setVerticalAlignment(JLabel.CENTER);
		
		panel.add(pantallaImagen);
		
		return panel;
	}

	private Component crearPanelWebcam() {
		panatallaLeap = new JPanel(new BorderLayout());
		panatallaLeap.setBorder(BorderFactory.createLoweredBevelBorder());
		
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		visualizador = new Canvas3D(gc);
		
		panatallaLeap.add(visualizador, BorderLayout.CENTER);
		
		return panatallaLeap;
	}

	private Component crearPanelResultadoInSitu() {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setPreferredSize(new Dimension(0,50));
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		textoResultadoInSitu = new JLabel(" ");
		textoResultadoInSitu.setHorizontalAlignment(SwingConstants.CENTER);
		textoResultadoInSitu.setVerticalAlignment(SwingConstants.CENTER);
		
		panel.add(textoResultadoInSitu);
		
		return panel;
	}

	private class MiAction extends AbstractAction {
		String texto;
		
		public MiAction (String texto, Icon imagen, String descrip, Integer nemonic){
			super(texto,imagen);
			this.texto = texto;
			this.putValue(Action.SHORT_DESCRIPTION ,descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(texto){
			case "Ver resultados":
				JOptionPane.showMessageDialog(PacienteFrame.this, "Deberia salir el dialogo de ver la tabla de resultados", "Ver resultados", JOptionPane.WARNING_MESSAGE);
				break;
			case "Hacer ejercicio":
				LeapMotionFrame FrameEjercicio = new LeapMotionFrame();
				
				//JOptionPane.showMessageDialog(PacienteFrame.this, "Deberia empezar a realizarse el ejercicio", "Hacer ejercicio", JOptionPane.WARNING_MESSAGE);
				break;
			case "Log out":
				MainFrame frame = new MainFrame();
				PacienteFrame.this.dispose();
				break;
			default:
				JOptionPane.showMessageDialog(PacienteFrame.this, "Error en el switch case", "Error", JOptionPane.ERROR_MESSAGE);
				break;
					
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Random random = new Random();
		if (!e.getValueIsAdjusting()) {
			textoDescripcion.setText(listaEjercicios.getSelectedValue().getDescripcion());
			
			textoResultadoInSitu.setText(String.valueOf(random.nextInt(50)));
			
			ImageIcon icono = new ImageIcon(listaEjercicios.getSelectedValue().getDirectorioGIF());
			pantallaImagen.setIcon(icono); //Las imagenes son GIF de 450x450

			
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		textoResultadoInSitu.setText(gestor.isCerrada() ? "Cerrada" : "Abierta");
		
		if(gestor.getHand() != null && gestor.getHand().isValid()){
			if(mano3d == null){
				mano3d = new Mano3D(visualizador, gestor.getHand());
			}else{
				mano3d.setHand(gestor.getHand());
			}
		}
		
	}
	public static void main(String[] args) {
		
		PacienteFrame frame = new PacienteFrame((Paciente)MainFrame.cargarUsuario("irati"));
					
	}

	
}
