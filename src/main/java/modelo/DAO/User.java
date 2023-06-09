package modelo.DAO;

public class User {
	private String nombre;
	private String contra;
	private String rol;
	private int registrado;
	private int id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdLikeString(String parameter) {
		// String evolve to integer
		this.id = Integer.parseInt(parameter);
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getRegistrado() {
		return registrado;
	}

	public void setRegistrado(int registrado) {
		this.registrado = registrado;
	}
	
}
