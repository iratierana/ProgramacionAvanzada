package conversorMoneda;

public class Conversor {

	private static double getValor(String cambio) {
		double valor;
		switch (cambio.toLowerCase()) {
		case "euros":
			valor = 1;
			break;
		case "libras":
			valor = 0.72113;
			break;
		case "dolares":
			valor = 1.10342;
			break;
		default:
			valor = 1;
			System.err.println("error en divisa");
			break;
		}
		return valor;
	}
	public static double calcularValor(double cantidad,String de, String a){
		double valor,valorDe,valorA,factor;
		valorDe = getValor(de);
		valorA = getValor(a);
		factor = valorA/valorDe;
		valor = cantidad*factor;
		return valor;
	}
}
