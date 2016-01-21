import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.glass.ui.Timer;

public class Principal extends JFrame implements ActionListener {

	File fOriginal, fDestino;

	JTextField tfNombreOrigen, tfNombreDestino, tfPathOrigen, tfPathDestino, tfTamañoOrigen, tfTamañoDestino;

	JButton bCargar, bCopiar;

	public Principal() {
		setTitle("Copiador de archivos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(200, 200);
		setSize(600, 400);

		getContentPane().add(crearPanelPrincipal(), BorderLayout.CENTER);

		setVisible(true);
	}

	private Component crearPanelPrincipal() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearPanelIzquierda(), crearPanelDerecha());
		//panel.setDividerLocation(3);
		panel.setContinuousLayout(true);
		return panel;
	}

	private Component crearPanelDerecha() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 20, 20));

		panel.setBorder(BorderFactory.createTitledBorder("Destino: "));

		tfNombreDestino = new JTextField();
		tfNombreDestino
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Nombre: "));
		tfNombreDestino.setEditable(true);
		panel.add(tfNombreDestino);

		tfPathDestino = new JTextField();
		tfPathDestino.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Path: "));
		tfPathDestino.setEditable(true);
		panel.add(tfPathDestino);

		tfTamañoDestino = new JTextField();
		tfTamañoDestino
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Tamaño: "));
		tfTamañoDestino.setEditable(true);
		panel.add(tfTamañoDestino);

		bCopiar = new JButton("Copiar..");
		bCopiar.setActionCommand("copiar");
		bCopiar.addActionListener(this);
		bCopiar.setEnabled(false);
		panel.add(bCopiar);

		return panel;
	}

	private Component crearPanelIzquierda() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 20, 20));

		panel.setBorder(BorderFactory.createTitledBorder("Origen: "));

		tfNombreOrigen = new JTextField();
		tfNombreOrigen
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Nombre: "));
		tfNombreOrigen.setEditable(true);
		panel.add(tfNombreOrigen);

		tfPathOrigen = new JTextField();
		tfPathOrigen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Path: "));
		tfPathOrigen.setEditable(true);
		panel.add(tfPathOrigen);

		tfTamañoOrigen = new JTextField();
		tfTamañoOrigen
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Tamaño: "));
		tfTamañoOrigen.setEditable(true);
		panel.add(tfTamañoOrigen);

		bCargar = new JButton("Cargar..");
		bCargar.setActionCommand("cargar");
		bCargar.addActionListener(this);
		panel.add(bCargar);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		switch (e.getActionCommand()) {
		case "cargar":
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(false);

			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				if (null != fc.getSelectedFile()) {
					fOriginal = fc.getSelectedFile();
					tfNombreOrigen.setText(fOriginal.getName());
					tfPathOrigen.setText(fOriginal.getAbsolutePath());
					tfTamañoOrigen.setText("" + fOriginal.length());
					bCopiar.setEnabled(true);
					fDestino = null;
					tfPathDestino.setText("");
					tfNombreDestino.setText("");
					tfTamañoDestino.setText("");
				}
			}
			break;
		case "copiar":
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setMultiSelectionEnabled(false);
			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				if (null != fc.getSelectedFile()) {
					fDestino = fc.getSelectedFile();
					copiarFichero();
					fDestino = new File(fDestino.getAbsolutePath() + "\\" + fOriginal.getName());
					tfNombreDestino.setText(fDestino.getName());
					tfPathDestino.setText(fDestino.getAbsolutePath());
					tfTamañoDestino.setText("" + fDestino.length());
				}
			}
			break;
		default:
			break;
		}

	}

	private void copiarFichero() {
		int b;
		FileInputStream src = null;
		FileOutputStream dest = null;

		try {
			src = new FileInputStream(fOriginal);
			dest = new FileOutputStream(fDestino.getAbsolutePath() + "\\" + fOriginal.getName());


			while((b = src.read()) != -1) {
				
				dest.write(b);
				System.out.println("1 byte");
			}


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				src.close();
				dest.close();
			} catch (Exception e) {
			}
			;
		}

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unused")
		Principal a = new Principal();
	}

}
