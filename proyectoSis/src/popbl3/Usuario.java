package popbl3;

public abstract class Usuario {
	final String SEPARADOR = "$";
	private String nombre, apellido1, apellido2, userName, centro;

	public Usuario(String userName, String nombre, String apellido1, String apellido2, String centro) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.userName = userName;
		this.centro = centro;
	}
	
	

	public Usuario(String userName) {
		this.nombre = getNombre();
		this.apellido1 = getApellido1();
		this.apellido2 = getApellido2();
		this.userName = userName;
		this.centro = getCentro();
	}

	public String getCentro() {
	if (centro == null)
			centro = cargarCentro();
		return centro;
	}

	private String cargarCentro() {
		// TODO Auto-generated method stub
		return new String("CARGAR MI CENTRO AQUI");
	}

	public String getNombre() {
		if (nombre == null)
			nombre = cargarNombre();
		return nombre;
	}

	private String cargarNombre() {
		// TODO Auto-generated method stub
		return new String("CARGA MI NOMBRE PLEASE");
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		if (apellido1 == null)
			apellido1 = cargarApellido1();
		return apellido1;
	}

	private String cargarApellido1() {
		// TODO Auto-generated method stub
		return new String("CARGAR MI APELLIDO1 PLEASE");
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		if (apellido2 == null)
			apellido2 = cargarApellido2();
		return apellido2;
	}

	private String cargarApellido2() {
		// TODO Auto-generated method stub
		return new String("CARGA MI APELLIDO2 PLEASE");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}
	
	@Override
	public String toString() {
		return  nombre+ " " + apellido1 +" "+ apellido2 ;
	}

	public String guardar() {		
		return userName+SEPARADOR+nombre+SEPARADOR+apellido1+SEPARADOR+apellido2+SEPARADOR+centro;
	}

}
