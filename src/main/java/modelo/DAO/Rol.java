package modelo.DAO;

public class Rol {
	// the difference between employees, and a difference between any employed and
	// any client.
	private int rolId;
	private String rolNombre;

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

}
