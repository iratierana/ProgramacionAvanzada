package circulo_cuadrado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import figuras.Circulo;
import figuras.Forma;
import figuras.Rectangulo;

public class MiDialogoFormas extends JDialog implements ActionListener {
	JPanel panelPrincipal, panelSur, panelCentro, panelNorte;
	JRadioButton rbCirculo, rbRectangulo;
	JButton bAceptar, bCancelar;
	ButtonGroup tipoForma;
	JLabel lDescripcion, lPosX, lPosY, lLadoRadio, lLado2;
	JTextField tfDescripcion, tfPosX, tfPosY, tfLadoRadio, tfLado2;

	public MiDialogoFormas(JFrame padre) {
		super(padre, "Crear Forma");
		lDescripcion = new JLabel("Descripcion:");
		lPosX = new JLabel("Posción x:");
		lPosY = new JLabel("Posicion y:");
		lLadoRadio = new JLabel("Radio:");
		lLado2 = new JLabel("Lado 2:");
		lLado2.setVisible(false);
		tfDescripcion = new JTextField("");
		tfPosX = new JTextField("");
		tfPosY = new JTextField("");
		tfLadoRadio = new JTextField("");
		tfLado2 = new JTextField("");
		tfLado2.setVisible(false);
		panelPrincipal = new JPanel(new BorderLayout());
		panelNorte = new JPanel(new GridLayout(1, 2));
		panelCentro = new JPanel(new GridLayout(5, 2, 20, 20));
		panelSur = new JPanel(new GridLayout(1, 2, 20, 0));
		rbCirculo = new JRadioButton("Circulo");
		rbRectangulo = new JRadioButton("Rectangulo");
		tipoForma = new ButtonGroup();
		bAceptar = new JButton("Aceptar");
		bCancelar = new JButton("Cancelar");

		this.setSize(400, 350);
		this.setLocation(450, 230);
		agregarListeners();
		rellenarPanel();
		this.setModal(true);
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	private void agregarListeners() {
		rbCirculo.addActionListener(this);
		rbRectangulo.addActionListener(this);
		bAceptar.addActionListener(this);
		bCancelar.addActionListener(this);
	}

	private void rellenarPanel() {
		rbCirculo.setSelected(true);
		tipoForma.add(rbCirculo);
		tipoForma.add(rbRectangulo);
		panelNorte.add(rbCirculo);
		panelNorte.add(rbRectangulo);
		panelPrincipal.add(panelNorte, BorderLayout.NORTH);
		panelCentro.add(lDescripcion);
		panelCentro.add(tfDescripcion);
		panelCentro.add(lPosX);
		panelCentro.add(tfPosX);
		panelCentro.add(lPosY);
		panelCentro.add(tfPosY);
		panelCentro.add(lLadoRadio);
		panelCentro.add(tfLadoRadio);
		panelCentro.add(lLado2);
		panelCentro.add(tfLado2);
		panelCentro.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Panel Datos"),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);

		panelSur.add(bAceptar);
		panelSur.add(bCancelar);
		panelSur.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setContentPane(panelPrincipal);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Circulo":
			lLadoRadio.setText("Radio:");
			lLado2.setVisible(false);
			tfLado2.setVisible(false);
			break;
		case "Rectangulo":
			lLadoRadio.setText("Lado 1:");
			lLado2.setVisible(true);
			tfLado2.setVisible(true);
			break;
		case "Aceptar":
			if(comprobarDatos())
				this.dispose();
			else
				JOptionPane.showMessageDialog(this,"Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
			break;
		case "Cancelar":
			tfDescripcion.setText("");
			tfPosX.setText("");
			tfPosY.setText("");
			tfLadoRadio.setText("");
			tfLado2.setText("");
			this.dispose();
			break;

		default:
			break;
		}
	}

	private boolean comprobarDatos() {
		if(0!=tfDescripcion.getText().compareTo(""))
			if(0!=tfPosX.getText().compareTo(""))
				if(0!=tfPosY.getText().compareTo(""))
					if(0!=tfLadoRadio.getText().compareTo(""))
						if(lLado2.isVisible()){
							if(0!=tfLado2.getText().compareTo(""))
								return true;
						}else
							return true;
		return false;		
	}
	public Forma getForma(){
		Forma ret;
		if(tfDescripcion.getText().compareTo("")==0)
			ret = null;
		else if(0==tfLado2.getText().compareTo(""))
			ret = new Circulo(Integer.valueOf((tfPosX.getText())),
					Integer.valueOf((tfPosY.getText())),
					Integer.valueOf((tfLadoRadio.getText())),
					tfDescripcion.getText());		 
		else
			ret = new Rectangulo(Integer.valueOf((tfPosX.getText())),
					Integer.valueOf((tfPosY.getText())),
					Integer.valueOf((tfLadoRadio.getText())),
					Integer.valueOf((tfLado2.getText())),
					tfDescripcion.getText());
		return ret;
	}
}
