package modelo.DAO;

public class Proveedor {
	// This is the class of the supplier of the goods("Piezas")
	private String direccion;
	private String cif;
	private String telefono;
	private String nombreProveedor;
	private int idProveedor;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombreProveedor;
	}

	public void setNombre(String nombre) {
		this.nombreProveedor = nombre;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

}
