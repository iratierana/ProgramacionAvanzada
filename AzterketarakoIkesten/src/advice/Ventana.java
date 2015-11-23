package advice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

public class Ventana implements ActionListener, ItemListener {
	
	JFrame ventana;
	JMenuBar barra;
	JMenu editar, salir;
	JMenuItem opcionMenu;
	JButton boton, confirm;
	JToolBar toolBar;
	JTextField textField;
	JFileChooser fileChooser;
	File file;
	JList<String> lista;
	DefaultListModel<String> listModel;
	JCheckBox ingredients[];
	JComboBox<String> size, special;
	JRadioButton place[];
	ButtonGroup placeGroup;
	

	public static void main(String[] args) {
		Ventana ejercicio = new Ventana();
		ejercicio.miMain();
	}

	private void miMain() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		ventana = new JFrame("Prestatxen");
		ventana.setJMenuBar(menubarra());
		ventana.setSize((int)width, (int)height);
		ventana.setLocation(0,0);
		ventana.setContentPane(panelPrincipal());
		ventana.setEnabled(true);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container panelPrincipal() {
		JSplitPane panelCentral = new JSplitPane();
		panelCentral.setEnabled(true);
		panelCentral.setDividerLocation(500);
		panelCentral.setLeftComponent(panelIzquierdo());
		panelCentral.setRightComponent(panelDerecho());
		
		return panelCentral;
	}
	
	private Component panelDerecho() {
		JPanel panelDerecho =  new JPanel(new BorderLayout());
		panelDerecho.add(crearPanelOpciones(), BorderLayout.EAST);
		panelDerecho.add(crearPanelBotones(), BorderLayout.WEST);
		panelDerecho.add(crearCentral(), BorderLayout.CENTER);
		
		return panelDerecho;
	}

	private Component crearCentral() {
		JPanel panel = new JPanel (new GridLayout(2,1,10,0));
		JPanel panelSuperior = new JPanel (new GridLayout(1,3,10,0));
		JPanel panelInferior = new JPanel (new FlowLayout());
		textField = new JTextField();
		panelSuperior.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GREEN),
				BorderFactory.createTitledBorder("Places")));
		panelSuperior.setBackground(Color.WHITE);
		
		place = new JRadioButton[2];
		place[0] = new JRadioButton("Take in the local factory");
		place[1] = new JRadioButton("Take it in: ");
		place[0].addItemListener(this);
		place[1].addItemListener(this);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(place[0]);
		buttonGroup.add(place[1]);
		
		panelSuperior.add(place[0]);
		panelSuperior.add(place[1]);
		panelSuperior.add(textField);
		
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		panelInferior.add(confirm);
		
		panel.add(panelSuperior);
		panel.add(panelInferior);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel( new GridLayout(2,1,0,10));
		
		panel.add(boton = new JButton("button"));
		JTextField text  = new JTextField();
		panel.setBorder(BorderFactory.createTitledBorder("best border eva"));
		
		panel.add(text);
		
		return panel;
	}

	private Component crearPanelOpciones() {
		JPanel panel = new JPanel(new GridLayout(2,1,0,10));
		
		panel.add(crearCombo());
		panel.add(crearCheck());
		
		return panel;
	}

	private Component crearCheck() {
		JPanel panel = new JPanel (new GridLayout(3,2,10,10));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.YELLOW),
				BorderFactory.createTitledBorder("Ingredients")));
		panel.setBackground(Color.white);
		
		ingredients = new JCheckBox[6];
		ingredients[0] = new JCheckBox("Ingredient1");
		ingredients[1] = new JCheckBox("Ingredient2");
		ingredients[2] = new JCheckBox("Ingredient3");
		ingredients[3] = new JCheckBox("Ingredient4");
		ingredients[4] = new JCheckBox("Ingredient5");
		ingredients[5] = new JCheckBox("Ingredient6");
		
		
		panel.add(ingredients[0]);
		panel.add(ingredients[1]);
		panel.add(ingredients[2]);
		panel.add(ingredients[3]);
		panel.add(ingredients[4]);
		panel.add(ingredients[5]);
		
		return panel;
	}

	private Component crearCombo() {
		String[] sizes = {"size1", "size2", "size3"};
		String[] specials = {"special1", "Special2", "special3"};
		JPanel panel = new JPanel (new GridLayout(1,2,10,0));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN),
				BorderFactory.createTitledBorder("Places")));
		panel.setBackground(Color.white);
		
		size = new JComboBox<String>(sizes);
		special = new JComboBox<String>(specials);
		
		panel.add(size);
		panel.add(special);
		return panel;
	}

	private Component panelIzquierdo() {
		JPanel panelIzquierdo = new JPanel(new BorderLayout());
		
		panelIzquierdo.add(crearToolBar(), BorderLayout.NORTH);
		
		panelIzquierdo.add(crearPanelIzquiCentral(), BorderLayout.CENTER);
		
		return panelIzquierdo;
	}

	private Component crearPanelIzquiCentral() {
		JPanel panelIzquierdoDere = new JPanel(new BorderLayout());
		JFileChooser file = new JFileChooser();
		
		panelIzquierdoDere.add(crearFileChooser() , BorderLayout.NORTH);
		panelIzquierdoDere.add(crearLista(), BorderLayout.CENTER);
		
		return panelIzquierdoDere;
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
	   // lista.addListSelectionListener(this);
		
	    JScrollPane listScrollPane = new JScrollPane(lista);
	    
	    return listScrollPane;
	}

	private Component crearFileChooser() {
		JPanel panel = new JPanel();
		
		panel.add(crearTextField(), BorderLayout.CENTER);
		panel.add(crearBoton(), BorderLayout.WEST);
		return panel;
	}

	private Component crearTextField() {
		JPanel panel = new JPanel();
		panel.add(textField = new JTextField(10));
		return panel;
	}

	private Component crearBoton() {
		JPanel panel = new JPanel();
		panel.add(boton = new JButton("..."));
	//	boton.addActionListener(this);
		
		return panel;
	}

	private Component crearToolBar() {
		toolBar = new JToolBar();
		toolBar.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JButton boton;
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/window_new.png")));
		boton.setActionCommand("Añadir");
	//	boton.addActionListener(this);
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/editcut.png")));
		boton.setActionCommand("Borrar");
//		boton.addActionListener(this);
		
		toolBar.add(Box.createHorizontalGlue());
		
		
		boton = (JButton) toolBar.add(new JButton(new ImageIcon("icons/exit.png")));
		boton.setActionCommand("Salir");
	//	boton.addActionListener(this);
		
		return toolBar;
	}

	private JMenuBar menubarra() {
		barra = new JMenuBar();
		barra.add(menuEditar());
		barra.add(menuSalir());
		
		return barra;
	}

	private JMenu menuSalir() {
	editar = new JMenu("Editar");
		
		opcionMenu = new JMenuItem("Añadir");
	//	opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/window_new.png"));
		editar.add(opcionMenu);
		
		editar.addSeparator();
		
		opcionMenu = new JMenuItem("Borrar");
	//	opcionMenu.addActionListener(this);
		opcionMenu.setIcon(new ImageIcon("icons/editcut.png"));
		editar.add(opcionMenu);
		
		return editar;
	}

	private JMenu menuEditar() {
		salir = new JMenu("Salir");
		return salir;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED) {
			if (e.getSource()==place[1]) {
				textField.setEditable(true);
			} else {
				textField.setEditable(false);
				textField.setText("");
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource()==confirm) {
			System.out.println("Uouuuuu");
		}
	}
		
	}

	
	

