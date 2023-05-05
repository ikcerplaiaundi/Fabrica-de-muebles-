package modelo.DAO;

public class Empleado {
	// all employers need to be an employ and role
	private int idEmpleado;
	private String nombreEmpleado;
	private int mgr;
	private String rol;
	private String empPassword;

	// get/set
	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}


	public String getContraseñaEmpleado() {
		return empPassword;
	}

	public void setContraseñaEmpleado(String contraseña) {
		this.empPassword = contraseña;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	

}
