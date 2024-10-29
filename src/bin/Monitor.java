package bin;

public class Monitor {
	private int autorizacion;
	private String especialidad;
	
	public Monitor() {
		super();
	}

	public Monitor(int autorizacion, String especialidad) {
		super();
		this.autorizacion = autorizacion;
		this.especialidad = especialidad;
	}

	public int getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(int autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Monitor [autorizacion=" + autorizacion + ", especialidad=" + especialidad + "]";
	}
	
	
}
