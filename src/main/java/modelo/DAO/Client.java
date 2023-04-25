package modelo.DAO;

public class Client {
	//who want buy something
	private int intClient;
	private String rolNombreClient;
	private String contactoClient;
	private String dniClient;
	private String direccionClient;
	private Rol rolClient;
	private String contraseñaClient;
	public int getIntClient() {
		return intClient;
	}
	public void setIntClient(int intClient) {
		this.intClient = intClient;
	}
	public String getRolNombreClient() {
		return rolNombreClient;
	}
	public void setRolNombreClient(String rolNombreClient) {
		this.rolNombreClient = rolNombreClient;
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
