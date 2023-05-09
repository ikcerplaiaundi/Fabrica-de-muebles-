package modelo.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DAO.Client;
import modelo.DAO.Empleado;
import modelo.DAO.Pedido;
import modelo.DAO.Producto;

public class GestorBDD extends Conexion {
	public Boolean[] ChekUser(modelo.DAO.User user) {
		// cheking
		Boolean[] Chek = new Boolean[4];
		Chek[0] = false;
		Chek[1] = false;
		Chek[2] = false;
		Chek[3] = false;

		// user in an employee ?
		String selectEMPLEADOS = "SELECT * FROM SYSTEM.EMPLEADOS WHERE NOMBRE_EMPLEADO = '" + user.getNombre() + "'";
		try {
			PreparedStatement mostrarEMPLEADOS = super.BBDDcon.prepareStatement(selectEMPLEADOS);
			ResultSet resultSetEMP = mostrarEMPLEADOS.executeQuery();
			while (resultSetEMP.next()) {
				Chek[0] = user.getNombre().equals(resultSetEMP.getString(2));

				Chek[1] = user.getContra().equals(resultSetEMP.getString(4));
			}
			// sort-cut
			if (Chek[1])
				return Chek;
		} catch (SQLException e1) {
			e1.printStackTrace();

		}
		// user in a client ?
		String selectCLIENTES = "SELECT * FROM SYSTEM.CLIENTES WHERE NOMBRE_CLIENTE = '" + user.getNombre()
				+ "' and REGISTRADO =1";
		try {

			PreparedStatement mostrarCLIENTES = super.BBDDcon.prepareStatement(selectCLIENTES);

			ResultSet resultSetCLI = mostrarCLIENTES.executeQuery();

			while (resultSetCLI.next()) {
				user.setId(resultSetCLI.getInt(1));

				Chek[2] = user.getNombre().equals(resultSetCLI.getString(3));

				Chek[3] = user.getContra().equals(resultSetCLI.getString(6));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();

		}
		return Chek;
	}

	public void pullCliente(Client client, modelo.DAO.User user) {
		System.out.println("Cliente craga");
		String selectClientes = "SELECT * FROM SYSTEM.CLIENTES WHERE ID_CLIENTES = '" + user.getId() + "'";
		try {
			PreparedStatement mostrarUsuarios = super.BBDDcon.prepareStatement(selectClientes);
			ResultSet resultSet = mostrarUsuarios.executeQuery();
			resultSet.next();
			// ID_CLIENTES DIRECCION_CLIENTES NOMBRE_CLIENTE CONTACTO_CLIENTE DNI_CLIENTE
			// REGISTRADO
			client.setIdClient(resultSet.getInt(1));
			client.setDireccionClient(resultSet.getString(2));
			client.setNombreClient(resultSet.getString(3));
			client.setContactoClient(resultSet.getString(4));
			client.setDniClient(resultSet.getString(5));
			client.setRegistrado(resultSet.getInt(7));

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void pullEmpleado(Empleado empleado, modelo.DAO.User user) {
		// TODO Auto-generated method stub
		String selectClientes = "SELECT * FROM SYSTEM.EMPLEADOS WHERE ID_EMPLEADOS = '" + user.getId() + "'";
		try {
			PreparedStatement mostrarUsuarios = super.BBDDcon.prepareStatement(selectClientes);
			ResultSet resultSet = mostrarUsuarios.executeQuery();
			resultSet.next();
			// ID_EMPLEADOS NOMBRE_EMPLEADO MGR EMP_PASWORD ROL
			empleado.setIdEmpleado(resultSet.getInt(1));
			empleado.setNombreEmpleado(resultSet.getString(2));
			empleado.setMgr(resultSet.getInt(3));
			empleado.setRol(resultSet.getString(5).toUpperCase());

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<Producto> pullProductos(String were) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String selectProductos = "SELECT * FROM SYSTEM.PRODUCTOS ";
		if(were != null) {selectProductos.concat(were);}
		
		PreparedStatement mostrarProductos;
		try {
			mostrarProductos = super.BBDDcon.prepareStatement(selectProductos);

			ResultSet resultSet = mostrarProductos.executeQuery();

			/*
			 * ID_PRODUCTOS NOMBRE_PROD DECRIPCION_PROD STOCK_PROD PRECIO_PROD ID_PEDIDOS
			 * FECHA_PEDIDO ID_EMPLEADOS
			 */

			while (resultSet.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(resultSet.getInt(1));
				producto.setNombreProducto(resultSet.getString(2));
				producto.setDescripcionProducto(resultSet.getString(3));
				producto.setStockProducto(resultSet.getInt(4));
				producto.setPrecioProducto(resultSet.getFloat(5));
				producto.setIdPedidos(resultSet.getInt(6));
				//producto.setFechaEntrada(resultSet.getString(7));
				producto.setIdFabricante(resultSet.getInt(8));
				productos.add(producto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}

	public ArrayList<Pedido> pullPedidos() {
		// TODO Auto-generated method stub
		
		
		
		
		return null;
	}

	
	
}
