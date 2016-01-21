import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableSelectionModel;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	JTable tablaAlumnos;
	TrazadorTablaAlumnos trazador;
	ModeloColumnasTablaAlumnos columnas;
	ModeloTablaAlumnos modelo;

	String ciudad;

	public Ventana() {
		ciudad = "Gotham";
		setTitle("Alumnos de " + ciudad);
		setLocation(200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);

		

		setContentPane(crearPanelTabla());

		setVisible(true);
	}

	private Container crearPanelTabla() {
		JScrollPane panel = new JScrollPane();

		
		trazador = new TrazadorTablaAlumnos();
		columnas = new ModeloColumnasTablaAlumnos(trazador);
		modelo = new ModeloTablaAlumnos(columnas);
		
		tablaAlumnos = new JTable(modelo, columnas);
		tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAlumnos.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					modelo.add(new Alumno("Nuevo", "Alumno", 4.65, "New York"));
					break;
				case KeyEvent.VK_B:
					int indice;
					indice = tablaAlumnos.getSelectedRow();
					if (indice != -1) {
						modelo.remove(indice);
					}
					tablaAlumnos.setRowSelectionInterval(0, 0);
					break;
				default:
					break;
				}

			}
		});
		setFocusable(true);
		tablaAlumnos.setFocusable(true);

		tablaAlumnos.setFillsViewportHeight(true);
		tablaAlumnos.getTableHeader().setReorderingAllowed(false);

		panel.setViewportView(tablaAlumnos);

		return panel;
	}

	public static void main(String[] args) {
		Ventana a = new Ventana();
	}

}
