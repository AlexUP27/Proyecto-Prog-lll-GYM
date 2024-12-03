package domain;

public class Cliente {
	
	public enum Genre {
		MALE, FEMALE;
	}
	
	private String nombre;
	private String apellido;
	private int telefono;
	private String email;
	private String dir;
	private int edad;
	private Genre genre;
	
	public Cliente(String nombre, String apellido, int telefono, String email, String dir, int edad, Genre genre) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.dir = dir;
		this.edad = edad;
		this.genre = genre;
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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", email=" + email
				+ ", dir=" + dir + ", edad=" + edad + ", genre=" + genre + "]";
	}
	
	
	
	
	
}
