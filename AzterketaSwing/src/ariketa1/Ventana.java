package ariketa1;

import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Ventana {
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
	
	
	
}
