package modelo.DTO;

import java.sql.*;

public class Conexion {
	protected Connection BBDDcon;
	//conection driver to sql 
	public Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCn() {
		return BBDDcon;
	}

	public void setCn(Connection cn) {
		this.BBDDcon = cn;
	}
	
	public void abrirConexion() {
		try {
			
			String urlconexion = "jdbc:mysql://localhost/test2";
			BBDDcon = (Connection) DriverManager.getConnection(urlconexion, "root", "");
			
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

