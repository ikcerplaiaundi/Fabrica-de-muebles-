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
		String selectEMPLEADOS = "SELECT * FROM EMPLEADOS WHERE NOMBRE_EMPLEADO = '" + user.getNombre() + "'";
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
		String selectCLIENTES = "SELECT * FROM CLIENTES WHERE NOMBRE_CLIENTE = '" + user.getNombre()
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

		String selectClientes = "SELECT * FROM CLIENTES WHERE ID_CLIENTES = '" + user.getId() + "'";
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
		String selectClientes = "SELECT * FROM EMPLEADOS WHERE ID_EMPLEADOS = '" + user.getId() + "'";
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

	public ArrayList<Producto> pullProductos(String where) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String selectProductos = "SELECT * FROM PRODUCTOS ";
		if (where != null) {
			selectProductos.concat(where);
		}

		PreparedStatement mostrarProductos;
		try {
			mostrarProductos = super.BBDDcon.prepareStatement(selectProductos);

			ResultSet resultSet = mostrarProductos.executeQuery();

			/*
			 * ID_productos NUMBER primary key, nombre_prod VARCHAR2(50), decripcion_prod
			 * VARCHAR2(50), stock_prod NUMBER, precio_prod numeric(12,2), id_empleados
			 * NUMBER
			 */

			while (resultSet.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(resultSet.getInt(1));
				producto.setNombreProducto(resultSet.getString(2));
				producto.setDescripcionProducto(resultSet.getString(3));
				producto.setStockProducto(resultSet.getInt(4));
				producto.setPrecioProducto(resultSet.getFloat(5));
				producto.setIdFabricante(resultSet.getInt(6));
				productos.add(producto);
				System.out.println(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}

	public ArrayList<Pedido> pullPedidos(String where) {

		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		String pedidoseleccion = "SELECT * FROM PEDIDOS";
		if (where != null) {
			pedidoseleccion.concat(where);
		}

		try {
			PreparedStatement pedidoamostrar = super.BBDDcon.prepareStatement(pedidoseleccion);
			ResultSet resultSet = pedidoamostrar.executeQuery();

			while (resultSet.next()) {
				Pedido pedido = new Pedido();

				pedido.setIdPedido(resultSet.getInt(1));
				pedido.setFechaPedido(resultSet.getString(2));
				// pedido.setClient(resultSet.getInt(3)); not required
				pedido.setCosto(resultSet.getInt(5));
				pedido.setIdFactura(resultSet.getInt(6));

				pedidos.add(pedido);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return pedidos;
	}

}
