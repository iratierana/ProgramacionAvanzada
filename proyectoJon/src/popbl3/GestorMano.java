package popbl3;

import java.util.Observable;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;

import popbl3.GestorMano.DEDOS;

public class GestorMano extends Observable {

	public enum DEDOS {
		GORDO, INDICE, MEDIO, ANULAR, MEÑIQUE;
	}

	final float RADIO_MANO_CERRADA = 40;
	final int MANO_SIN_INICIALIZAR = -1;
	final double DISTANCIA_NO_VALIDA = -1;
	final double DISTANCIA_TOCANDO = 30;
	
	Hand handFrame = null;
	float radioMax = MANO_SIN_INICIALIZAR;
	float radioMin;
	float radioActual;
	boolean cerrada, conectado, inicializado;

	public synchronized float getRadioMax() {
		return radioMax;
	}

	public synchronized float getRadioMin() {
		return radioMin;
	}

	public synchronized float getRadioActual() {
		return radioActual;
	}

	public synchronized boolean isConectado() {
		return conectado;
	}

	public synchronized void setConectado(boolean conectado) {
		this.conectado = conectado;
		this.setChanged();
		this.notifyObservers();
	}

	public synchronized boolean isInicializado() {
		return inicializado;
	}

	public synchronized void setInicializado(boolean inicializado) {
		this.inicializado = inicializado;
		this.setChanged();
		this.notifyObservers();
	}

	public void setHand(Hand nuevaMano) {

		if (nuevaMano.isValid()) {

			handFrame = nuevaMano;
			setRadios();
			this.setChanged();
			this.notifyObservers();
		} else {
			handFrame = null;
		}

	}

	public double getDistDedo(DEDOS tipoDedo) {

		double gorX = 0, gorY = 0, gorZ = 0, dedoX = 0, dedoY = 0, dedoZ = 0, distancia = DISTANCIA_NO_VALIDA;
		boolean error = false;

		Finger gordo = null, dedo = null;

		Finger.Type tipoDedoLeap = null;

		if (handFrame != null) {

			switch (tipoDedo) {
			case INDICE:
				tipoDedoLeap = Finger.Type.TYPE_INDEX;
				break;

			case MEDIO:
				tipoDedoLeap = Finger.Type.TYPE_MIDDLE;
				break;

			case ANULAR:
				tipoDedoLeap = Finger.Type.TYPE_RING;
				break;

			case MEÑIQUE:
				tipoDedoLeap = Finger.Type.TYPE_PINKY;
				break;

			default:
				error = true;
				break;
			}

			if (!error) {

				for (Finger dedoAux : handFrame.fingers()) {
					if (dedoAux.type() == Finger.Type.TYPE_THUMB) {
						gordo = dedoAux;
						break;
					}
				}

				for (Finger dedoAux : handFrame.fingers()) {
					if (dedoAux.type() == tipoDedoLeap) {
						dedo = dedoAux;
						break;
					}
				}

				gorX = gordo.tipPosition().getX();
				gorY = gordo.tipPosition().getY();
				gorZ = gordo.tipPosition().getZ();

				dedoX = dedo.tipPosition().getX();
				dedoY = dedo.tipPosition().getY();
				dedoZ = dedo.tipPosition().getZ();

				distancia = Math
						.sqrt(Math.pow(gorX - dedoX, 2) + Math.pow(gorY - dedoY, 2) + Math.pow(gorZ - dedoZ, 2));
			}

		}
		return distancia;
	}

	private void setRadios() {

		radioActual = handFrame.sphereRadius();
		if (radioMax == MANO_SIN_INICIALIZAR) {
			radioMax = radioActual;
			radioMin = radioActual;
		} else if (radioMax < radioActual) {
			radioMax = radioActual;
		} else if (radioMin > radioActual) {
			radioMin = radioActual;
		}

	}

	public Hand getHand() {
		return handFrame;
	}

	public boolean isCerrada() {
		if (radioActual < RADIO_MANO_CERRADA)
			cerrada = true;
		else
			cerrada = false;
		return cerrada;
	}

	public float getRadio() {
		return radioActual;
	}



	public boolean estaTacandoDedo(DEDOS tipoDedo) {
		boolean tocando = false;
		double distancia = getDistDedo(tipoDedo);
		if (distancia != DISTANCIA_NO_VALIDA && distancia <DISTANCIA_TOCANDO){
			tocando = true;
		}
		else{
			tocando = false;
		}
		return tocando;
	}
}
