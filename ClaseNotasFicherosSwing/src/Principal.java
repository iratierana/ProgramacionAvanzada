import java.awt.BorderLayout;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class Principal extends JFrame implements ListSelectionListener {
	
	Clase clase;
	
	JList<Alumno> lAlumno;
	JList<Asignatura> lAsignaturas;
	
	AbrirAction aAbrir;
	GuardarAction aGuardar;
	
	JLabel lTitle;
	
	
	
	public Principal(){
		setTitle("Visor de clase");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		setLocation(200, 200);
		
		aAbrir = new AbrirAction(this);
		aGuardar = new GuardarAction(this);
		aGuardar.setEnabled(false);
		
		setJMenuBar(crearMenuBar());
		getContentPane().add(crearToolBar(), BorderLayout.NORTH);
		getContentPane().add(crearPanelPrincipal(), BorderLayout.CENTER);
		
		
		//leerDeArchivo("[$]", "clase.txt");
		//guardarEnArchivo("$", "copiadeclase.txt");
		
		setVisible(true);
	}

	private JMenuBar crearMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu();
		file.add(aAbrir);
		file.add(aGuardar);
		menuBar.add(file);
		
		return menuBar;
	}

	private Component crearToolBar() {
		JToolBar toolbar = new JToolBar();
		
		toolbar.add(aAbrir);
		toolbar.add(aGuardar);
		
		return toolbar;
	}

	private Component crearPanelPrincipal() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel panelN = new JPanel();
		lTitle = new JLabel("");
		panelN.add(lTitle);
		panel.add(panelN, BorderLayout.NORTH);
		
		panel.add(crearPanelListas(), BorderLayout.CENTER);
		
		return panel;
	}

	private Component crearPanelListas() {
		
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearPanelAlumnos(), crearPanelAsignaturas());
		panel.setDividerLocation(0.3);
		
		return panel;
	}

	private Component crearPanelAsignaturas() {
		JScrollPane panel = new JScrollPane();
		lAsignaturas = new JList<>();
		lAsignaturas.setCellRenderer(new RendererAsignatura());
		panel.setViewportView(lAsignaturas);
		return panel;
	}

	private Component crearPanelAlumnos() {
		JScrollPane panel = new JScrollPane();
		lAlumno = new JList<>();
		lAlumno.setCellRenderer(new RendererAlumno());
		lAlumno.addListSelectionListener(this);
		panel.setViewportView(lAlumno);
		return panel;
	}

	
	public void guardarEnArchivo(String separator, String path){
		PrintWriter wr = null;
		
		try {
			wr = new PrintWriter(new FileWriter(path));
			wr.println(clase.escribirEnFichero(separator));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{wr.close();}catch(Exception e){};
		}

	}
	
	public void leerDeArchivo(String separador, String path){
		BufferedReader rd = null;
		String linea;
		Alumno a = null;
		boolean firstLine = true, firstRead = true;
		
		try {
			rd = new BufferedReader(new FileReader(path));
			clase = new Clase(rd.readLine());
			while((linea = rd.readLine()) != null){
				if(linea.length() == 0){
					firstLine = true;
					if(!firstRead) clase.add(a);
				}else if(firstLine){
					a = new Alumno(linea, separador);
					firstLine = false;
					firstRead = false;
				}else{
					a.addAsignatura(new Asignatura(linea, separador));
				}
			}
			
		} catch (FileNotFoundException e) {
			lTitle.setText("No se ha encontrado el archivo");
			clase = null;
		} catch (IOException e) {
			lTitle.setText("Error en la lectura del archivo");
			clase = null;
		} catch (Exception e){
			lTitle.setText("Error de formato desconocido");
			clase = null;
		}finally {
			try{rd.close();}catch(Exception e){};
		}
		
		
	}
	

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		Principal a = new Principal();
		
		

	}

	public JLabel getlTitle() {
		return lTitle;
	}

	public void setlTitle(JLabel lTitle) {
		this.lTitle = lTitle;
	}

	public JList<Alumno> getlAlumno() {
		return lAlumno;
	}

	public void setlAlumno(JList<Alumno> lAlumno) {
		this.lAlumno = lAlumno;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	

	public JList<Asignatura> getlAsignaturas() {
		return lAsignaturas;
	}

	public void setlAsignaturas(JList<Asignatura> lAsignaturas) {
		this.lAsignaturas = lAsignaturas;
	}

	public AbrirAction getaAbrir() {
		return aAbrir;
	}

	public void setaAbrir(AbrirAction aAbrir) {
		this.aAbrir = aAbrir;
	}

	public GuardarAction getaGuardar() {
		return aGuardar;
	}

	public void setaGuardar(GuardarAction aGuardar) {
		this.aGuardar = aGuardar;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(((JList<Alumno>)e.getSource()).getSelectedValue() == null){
		
		}else{
			lAsignaturas.setModel(((JList<Alumno>)e.getSource()).getSelectedValue().getAsignaturas());
		}
		
	}

}
