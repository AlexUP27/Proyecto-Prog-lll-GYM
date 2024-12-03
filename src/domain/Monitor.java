package domain;

public class Monitor {
	private int autorizacion;
	private String nombre;
	private String apellido;
	private String email;
	private int edad;
	private String especialidad;
	private int telefono;
	
	public Monitor(int autorizacion, String nombre, String apellido, String email, int edad, String especialidad,
			int telefono) {
		super();
		this.autorizacion = autorizacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.edad = edad;
		this.especialidad = especialidad;
		this.telefono = telefono;
	}

	
	public int getAutorizacion() {
		return autorizacion;
	}
	
	public void setAutorizacion(int autorizacion) {
		this.autorizacion = autorizacion;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	

	@Override
	public String toString() {
		return "Monitor [autorizacion=" + autorizacion + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", edad=" + edad + ", especialidad=" + especialidad + ", telefono=" + telefono + "]";
	}	
	
}
