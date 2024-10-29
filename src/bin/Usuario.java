package bin;

public class Usuario extends Persona{
	private int edad;
	private float peso;
	private float altura;
	
	public Usuario() {
		super();
	}
	public Usuario(String nomPersona, String telefono, String email, String dir) {
		super(nomPersona, telefono, email, dir);
	}
	public Usuario(int edad, float peso, float altura) {
		super();
		this.edad = edad;
		this.peso = peso;
		this.altura = altura;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	@Override
	public String toString() {
		return "Usuario [edad=" + edad + ", peso=" + peso + ", altura=" + altura + "]";
	}
	
	
}
