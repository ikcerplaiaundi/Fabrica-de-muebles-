package modelo.DAO;

public class Client {
	// who want buy something
	private int idClientes;
	private String nombreCliente;
	private String contactoCliente;
	private String dniCliente;
	private String direccionCliente;
	private int registrado;
	private String cliPassword;

	public int getIdClient() {
		return idClientes;
	}

	public void setIdClient(int intClient) {
		this.idClientes = intClient;
	}

	public String getNombreClient() {
		return nombreCliente;
	}

	public void setNombreClient(String NombreClient) {
		this.nombreCliente = NombreClient;
	}

	public String getContactoClient() {
		return contactoCliente;
	}

	public void setContactoClient(String contactoClient) {
		this.contactoCliente = contactoClient;
	}

	public String getDniClient() {
		return dniCliente;
	}

	public void setDniClient(String dniClient) {
		this.dniCliente = dniClient;
	}

	public String getDireccionClient() {
		return direccionCliente;
	}

	public void setDireccionClient(String direccionClient) {
		this.direccionCliente = direccionClient;
	}


	public String getContraseñaClient() {
		return cliPassword;
	}

	public void setContraseñaClient(String contraseñaClient) {
		this.cliPassword = contraseñaClient;
	}

	public int getRegistrado() {
		return registrado;
	}

	public void setRegistrado(int registrado) {
		this.registrado = registrado;
	}

}
