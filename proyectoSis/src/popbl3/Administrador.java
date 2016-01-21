package popbl3;

public class Administrador extends Usuario {

	public Administrador(String userName, String nombre, String apellido1, String apellido2, String centro) {
		super(userName, nombre, apellido1, apellido2, centro);
	}

	public Administrador(String userName) {
		super(userName);
		
	}

	@Override
	public String toString() {
		
		return this.getNombre()+" "+this.getApellido1()+" "+this.getApellido2();
	}

	public void setAdministrador(Administrador admin) {
		super.setUserName(admin.getUserName());
		super.setNombre(admin.getNombre());
		super.setApellido1(admin.getApellido1());
		super.setApellido2(admin.getApellido2());
		super.setCentro(admin.getCentro());
	}

}
