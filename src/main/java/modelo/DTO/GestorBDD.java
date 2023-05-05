package modelo.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.DAO.Client;
import modelo.DAO.Empleado;



public class GestorBDD extends Conexion{
	public Boolean[] ChekUser(modelo.DAO.User user) {
		
		String selectEMPLEADOS = "SELECT * FROM SYSTEM.EMPLEADOS WHERE ID_EMPLEADOS = ? and REGISTRADO = 1";
		Boolean[] Chek =new Boolean[4];
		Chek[0]=false;
		Chek[1]=false;
		Chek[2]=false;
		Chek[3]=false;
		PreparedStatement mostrarUsuarios;
		try {
			mostrarUsuarios = super.BBDDcon.prepareStatement(selectEMPLEADOS);
		
			mostrarUsuarios.setString(1, user.getNombre());
			ResultSet resultSet = mostrarUsuarios.executeQuery();
			resultSet.next();
			
			
			if(user.getNombre().equals(resultSet.getString("NOMBRE_EMPLEADO"))) {
				Chek[0]=true;
			}
			if(user.getContra().equals(resultSet.getString("EMP_PASWORD"))) {
				Chek[1]=true;
			}
			if(resultSet.getString("EMP_PASWORD") !=null)return Chek;
		} catch (SQLException e1) {
			e1.printStackTrace();
			Chek[0]=false;
			Chek[1]=false;
		}
		String selectCLIENTES = "SELECT * FROM SYSTEM.CLIENTES WHERE NOMBRE_CLIENTE = ? and CLI_PASWORD =?";
		try {
			
			mostrarUsuarios = super.BBDDcon.prepareStatement(selectCLIENTES);
		
			mostrarUsuarios.setString(1, user.getNombre());
			mostrarUsuarios.setString(2, user.getContra());
			ResultSet resultSet = mostrarUsuarios.executeQuery();
			resultSet.next();
			user.setId(resultSet.getInt("ID_CLIENTES"));
			
			if(user.getNombre().equals(resultSet.getString("NOMBRE_CLIENTE"))) {
				Chek[2]=true;
			}
			if(user.getContra().equals(resultSet.getString("CLI_PASWORD"))) {
				Chek[3]=true;
			}
			if(resultSet.getString("CLI_PASWORD") !=null)return Chek;
		} catch (SQLException e1) {
			e1.printStackTrace();
			Chek[2]=false;
			Chek[3]=false;
		}
		return Chek;
	}

	public void pullCliente(Client client , modelo.DAO.User user ) {
		String selectClientes = "SELECT * FROM SYSTEM.CLIENTES WHERE ID_CLIENTES = ? ";
		try {
		PreparedStatement mostrarUsuarios = super.BBDDcon.prepareStatement(selectClientes);
		mostrarUsuarios.setInt(1, user.getId());
		ResultSet resultSet = mostrarUsuarios.executeQuery();
		resultSet.next();
		//ID_CLIENTES  DIRECCION_CLIENTES      NOMBRE_CLIENTE         CONTACTO_CLIENTE     DNI_CLIENTE           REGISTRADO
		client.setIdClient(resultSet.getInt("ID_CLIENTES"));
		client.setDireccionClient(resultSet.getString("DIRECCION_CLIENTES"));
		client.setNombreClient(resultSet.getString("NOMBRE_CLIENTE"));
		client.setContactoClient(resultSet.getString("CONTACTO_CLIENTE"));
		client.setDniClient(resultSet.getString("DNI_CLIENTE"));
		client.setRegistrado(resultSet.getInt("REGISTRADO"));
		
		
		} catch (SQLException e1) {e1.printStackTrace();}
	}

	public void pullEmpleado(Empleado empleado, modelo.DAO.User user) {
		// TODO Auto-generated method stub 
		String selectClientes = "SELECT * FROM SYSTEM.EMPLEADOS WHERE ID_EMPLEADOS = ? ";
		try {
		PreparedStatement mostrarUsuarios = super.BBDDcon.prepareStatement(selectClientes);
		mostrarUsuarios.setInt(1, user.getId());
		ResultSet resultSet = mostrarUsuarios.executeQuery();
		resultSet.next();
		//ID_EMPLEADOS NOMBRE_EMPLEADO    MGR   EMP_PASWORD        ROL
		empleado.setIdEmpleado(resultSet.getInt("ID_EMPLEADOS"));
		empleado.setNombreEmpleado(resultSet.getString("NOMBRE_EMPLEADO "));
		empleado.setMgr(resultSet.getInt("MGR"));
		empleado.setRol(resultSet.getString("ROL"));
		
		
		
		} catch (SQLException e1) {e1.printStackTrace();}
	}
	
}
