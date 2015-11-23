package conversorDeMoneda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class MainFrame implements ActionListener {
	final Color CELESTE = new Color(0, 255, 255);
	JFrame ventana;
	JLabel cantidad;
	JComboBox<String> comboMonedasIz;
	JComboBox<String> comboMonedasDe;
	JTextField cantidadIn;
	public MainFrame() {

		ventana = new JFrame("Conversor de moneda");
		ventana.setSize(400, 200);
		ventana.setLocation(450, 280);
		ventana.setContentPane(crearPanelPrincipal());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}

	private Container crearPanelPrincipal() {
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelPrincipal.add(crearPanelCentral(), BorderLayout.CENTER);
		panelPrincipal.add(crearPanelSur(), BorderLayout.SOUTH);
		return panelPrincipal;
	}

	private Component crearPanelSur() {
		JPanel panel = new JPanel();
		JButton cambio = new JButton("Mostrar Cambio");
		cambio.addActionListener(this);
		cambio.setActionCommand("cambio");
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		panel.add(cambio);
		return panel;
	}

	private Component crearPanelCentral() {
		JSplitPane panelCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		panelCentral.setLeftComponent(crearPanelIzquierdo());
		panelCentral.setRightComponent(crearPanelDerecho());
		return panelCentral;
	}

	private Component crearPanelDerecho() {
		JPanel panel = new JPanel(new BorderLayout());
		cantidad = new JLabel("");
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CELESTE),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		comboMonedasDe = new JComboBox<String>(new String[] { "Euro", "Libra", "US Dólar" });
		cantidad.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Cantidad"));
		panel.add(comboMonedasDe, BorderLayout.NORTH);
		panel.add(cantidad, BorderLayout.CENTER);
		return panel;
	}

	private Component crearPanelIzquierdo() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(CELESTE),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		cantidadIn = new JTextField("100");
		cantidadIn.addActionListener(this);
		cantidadIn.setActionCommand("cambio");
		comboMonedasIz = new JComboBox<String>(new String[] { "Euro", "Libra", "US Dólar" });
		cantidadIn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Cantidad"));
		panel.add(comboMonedasIz, BorderLayout.NORTH);
		panel.add(cantidadIn, BorderLayout.CENTER);
		return panel;
	}

	private double calcularCambio() {
		double valor;
		String stringDe,stringA;
		stringDe = (String) comboMonedasIz.getSelectedItem();
		stringA = (String) comboMonedasDe.getSelectedItem();
		valor = Conversor.calcularValor(Double.parseDouble(cantidadIn.getText()), stringDe, stringA);
		return valor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		double valorLabel;
		switch (e.getActionCommand()) {
		case "cambio":
			valorLabel=calcularCambio();
			cantidad.setText(String.valueOf(valorLabel));
			break;

		default:
			break;
		}
	}

	public static void main(String[] args) {
		MainFrame prog = new MainFrame();
	}

}
