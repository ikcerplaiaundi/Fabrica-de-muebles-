package modelo.DAO;

public class Client {
	//who want buy something
	private int idClient;
	private String NombreClient;
	private String contactoClient;
	private String dniClient;
	private String direccionClient;
	private Rol rolClient;
	private String contraseñaClient;
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int intClient) {
		this.idClient = intClient;
	}
	public String getNombreClient() {
		return NombreClient;
	}
	public void setNombreClient(String NombreClient) {
		this.NombreClient = NombreClient;
	}
	public String getContactoClient() {
		return contactoClient;
	}
	public void setContactoClient(String contactoClient) {
		this.contactoClient = contactoClient;
	}
	public String getDniClient() {
		return dniClient;
	}
	public void setDniClient(String dniClient) {
		this.dniClient = dniClient;
	}
	public String getDireccionClient() {
		return direccionClient;
	}
	public void setDireccionClient(String direccionClient) {
		this.direccionClient = direccionClient;
	}
	public Rol getRolClient() {
		return rolClient;
	}
	public void setRolClient(Rol rolClient) {
		this.rolClient = rolClient;
	}
	public String getContraseñaClient() {
		return contraseñaClient;
	}
	public void setContraseñaClient(String contraseñaClient) {
		this.contraseñaClient = contraseñaClient;
	}
	
}
