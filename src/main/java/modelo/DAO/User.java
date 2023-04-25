package modelo.DAO;

public class User {
	private String nombre;
	private String contra;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", contra=" + contra;
	}

}
