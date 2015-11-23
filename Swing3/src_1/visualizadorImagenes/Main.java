package visualizadorImagenes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main implements ActionListener, ListSelectionListener {
	JFrame ventana;
	JLabel imagen;
	JButton boton;
	JTextField textField;
	JFileChooser fileChooser;
	File file;
	JList<String> lista;
	DefaultListModel<String> listModel;
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	public Main(){
		ventana = new JFrame("Visor grafico");
		ventana.setSize(600, 300);
		ventana.setLocation(450, 200);
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private Container crearPanelVentana() {
		JSplitPane panelCentral = new JSplitPane();
		panelCentral.setDividerLocation(0.7);
		panelCentral.setLeftComponent(panelIzquierdo());
		panelCentral.setRightComponent(visor());
		return panelCentral;
	}
	private Component panelIzquierdo() {
		JPanel panelIzquierdo = new JPanel(new BorderLayout());
		JFileChooser file = new JFileChooser();
		
		panelIzquierdo.add(crearFileChooser() , BorderLayout.NORTH);
		panelIzquierdo.add(crearLista(), BorderLayout.CENTER);
		
		return panelIzquierdo;
	}
	private Component crearFileChooser() {
		JPanel panel = new JPanel();
		
		panel.add(crearTextField(), BorderLayout.CENTER);
		panel.add(crearBoton(), BorderLayout.WEST);
		return panel;
	}
	private Component crearLista() {
		listModel = new DefaultListModel<String>();
		listModel.addElement("cerveza.png");
		listModel.addElement("pintxo.png");
		listModel.addElement("tigre1.png");
		listModel.addElement("tigre2.png");
		listModel.addElement("tigre3.png");
		listModel.addElement("tigre4.png");
		listModel.addElement("tigre5.png");
		listModel.addElement("tigre6.png");
		listModel.addElement("tigre7.png");
		listModel.addElement("tigre8.png");
		listModel.addElement("tigre9.png");
		listModel.addElement("tigre10.png");
		listModel.addElement("tigre11.png");
		listModel.addElement("tigre12.png");
		listModel.addElement("total.png");
		listModel.addElement("vino.png");
		
		lista = new JList<String>(listModel);
	    lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    lista.setSelectedIndex(0);
	    lista.addListSelectionListener(this);
		
	    JScrollPane listScrollPane = new JScrollPane(lista);
	    
	    return listScrollPane;
	}
	private Component crearBoton() {
		JPanel panel = new JPanel();
		panel.add(boton = new JButton("..."));
		boton.addActionListener(this);
		
		return panel;
	}
	private Component crearTextField() {
		JPanel panel = new JPanel();
		panel.add(textField = new JTextField(10));
		return panel;
	}
	private Component visor() {
		imagen = new JLabel();
		
		JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setLocation(100, 100);
		scroll.setViewportView(imagen);
		
		imagen.setIcon(new ImageIcon("imagen/" + lista.getSelectedValue()));
		
		return imagen;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "...":
			fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(ventana);
			file = fileChooser.getSelectedFile();
			textField.setText(file.getAbsolutePath());
			listModel.addElement(file.getAbsolutePath());
			break;

		default:
			break;
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		imagen.setIcon (new ImageIcon("imagen/" + lista.getSelectedValue()));

	}
}
