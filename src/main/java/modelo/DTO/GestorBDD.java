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
		String selectCLIENTES = "SELECT * FROM SYSTEM.CLIENTES WHERE ID_CLIENTES = ? ";
		try {
			
			mostrarUsuarios = super.BBDDcon.prepareStatement(selectCLIENTES);
		
			mostrarUsuarios.setString(1, user.getNombre());
			ResultSet resultSet = mostrarUsuarios.executeQuery();
			resultSet.next();
			
			
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

	public void pullCliente(Client client) {
		String selectUsuarios = "SELECT * FROM clientes WHERE Nombre= ?";
		
	}

	public void pullEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}
	
}
