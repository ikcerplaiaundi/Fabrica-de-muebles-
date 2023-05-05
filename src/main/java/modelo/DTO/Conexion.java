package modelo.DTO;

import java.sql.*;
import java.util.Properties;

public class Conexion {
	protected Connection BBDDcon;
	protected String username ="AP_ADMIN";
	protected String userpassword="123";
	//conection driver to sql 
	public Conexion() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public Connection getCn() {
		return BBDDcon;
	}

	public void setCn(Connection cn) {
		this.BBDDcon = cn;
	}
	
	public void abrirConexion() {
		try {
			
			//String url = "jdbc:mysql:///FabricaMuebles";
			            //"jdbc:oracle:thin:@<ip_de_la_maquina_host>:49161:xe";
			 String url = "jdbc:oracle:thin:@192.168.100.152:49161:xe";       
             Properties props = new Properties();
             props.setProperty("user", username);
             props.setProperty("password", userpassword);
             BBDDcon = DriverManager.getConnection(url,props);
             
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			
			BBDDcon.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

