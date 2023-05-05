package modelo.DAO;

public class Proveedor {
	// This is the class of the supplier of the goods("Piezas")
	private String direccionProveedor;
	private String cifProveedor;
	private String contactProveedor;
	private String nombreProveedor;
	private int idProveedor;

	public String getDireccion() {
		return direccionProveedor;
	}

	public void setDireccion(String direccion) {
		this.direccionProveedor = direccion;
	}

	public String getCif() {
		return cifProveedor;
	}

	public void setCif(String cif) {
		this.cifProveedor = cif;
	}

	public String getTelefono() {
		return contactProveedor;
	}

	public void setTelefono(String telefono) {
		this.contactProveedor = telefono;
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
