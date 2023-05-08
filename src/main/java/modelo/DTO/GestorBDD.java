package modelo.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.DAO.Client;
import modelo.DAO.Empleado;



public class GestorBDD extends Conexion{
	public Boolean[] ChekUser(modelo.DAO.User user) {
		
		String selectEMPLEADOS = "SELECT * FROM SYSTEM.EMPLEADOS WHERE NOMBRE_EMPLEADO = '"+user.getNombre()+"'";
		Boolean[] Chek =new Boolean[4];
		Chek[0]=false;
		Chek[1]=false;
		Chek[2]=false;
		Chek[3]=false;
		
		
		try {
			PreparedStatement mostrarEMPLEADOS = super.BBDDcon.prepareStatement(selectEMPLEADOS);
			ResultSet resultSetEMP = mostrarEMPLEADOS.executeQuery();
			while (resultSetEMP.next()) {
				System.out.println(" rest"+resultSetEMP.getInt(1));
				System.out.println(" rest"+resultSetEMP.getString(2));
				System.out.println(" rest"+resultSetEMP.getString(3));
				System.out.println(" rest"+resultSetEMP.getString(4));
				System.out.println(" rest"+resultSetEMP.getString(5));
				System.out.println(" rest"+resultSetEMP.getString(6));
				System.out.println(" rest"+resultSetEMP.getInt(7));
			
			
			Chek[0]= user.getNombre().equals(resultSetEMP.getString(2));
			
			Chek[1]= user.getContra().equals(resultSetEMP.getString("EMP_PASWORD"));
			}
			if(resultSetEMP.getString("EMP_PASWORD") !=null)return Chek;
		} catch (SQLException e1) {
			e1.printStackTrace();
			
		}
		String selectCLIENTES = "SELECT * FROM SYSTEM.CLIENTES WHERE NOMBRE_CLIENTE = '"+user.getNombre()+"'";
		try {
			
			PreparedStatement mostrarCLIENTES = super.BBDDcon.prepareStatement(selectCLIENTES);
		
			
			ResultSet resultSetCLI = mostrarCLIENTES.executeQuery();
			
			while (resultSetCLI.next()) {
				
			System.out.println(" rest"+resultSetCLI.getInt(1));
			System.out.println(" rest"+resultSetCLI.getString(2));
			System.out.println(" rest"+resultSetCLI.getString(3));
			System.out.println(" rest"+resultSetCLI.getString(4));
			System.out.println(" rest"+resultSetCLI.getString(5));
			System.out.println(" rest"+resultSetCLI.getString(6));
			System.out.println(" rest"+resultSetCLI.getInt(7));
			
			user.setId(resultSetCLI.getInt(1));
			
			Chek[2]= user.getNombre().equals(resultSetCLI.getString(3));
			
			Chek[3]= user.getContra().equals(resultSetCLI.getString(6));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			
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
