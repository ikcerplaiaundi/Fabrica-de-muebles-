package modelo.DTO;

import java.sql.Date;
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
		// @param cheking for log
		Boolean[] Chek = new Boolean[4];
		Chek[0] = false;
		Chek[1] = false;
		Chek[2] = false;
		Chek[3] = false;

		// user in an employee ?
		String selectEMPLEADOS = "SELECT * FROM ap_Admin.EMPLEADOS WHERE NOMBRE_EMPLEADO = '" + user.getNombre() + "'";
		try {
			PreparedStatement mostrarEMPLEADOS = super.BBDDcon.prepareStatement(selectEMPLEADOS);
			ResultSet resultSetEMP = mostrarEMPLEADOS.executeQuery();

			while (resultSetEMP.next()) {
				Chek[0] = user.getNombre().equals(resultSetEMP.getString(2));

				user.setId(resultSetEMP.getInt(1));

				Chek[1] = user.getContra().equals(resultSetEMP.getString(4));
			}
			// sort-cut
			if (Chek[1])
				return Chek;
		} catch (SQLException e1) {
			e1.printStackTrace();

		}
		// user in a client ?
		String selectCLIENTES = "SELECT * FROM ap_Admin.CLIENTES WHERE NOMBRE_CLIENTE ='" + user.getNombre()
				+ "' and REGISTRADO = 1";
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
		// @param pull the "Cliente" specified "
		String selectClientes = "SELECT * FROM ap_Admin.CLIENTES WHERE ID_CLIENTES = '" + user.getId() + "'";
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
		// @param pull the "Empleado" specified "
		String selectClientes = "SELECT * FROM ap_Admin.EMPLEADOS WHERE ID_EMPLEADOS = '" + user.getId() + "'";
		try {
			PreparedStatement mostrarUsuarios = super.BBDDcon.prepareStatement(selectClientes);
			ResultSet resultSet = mostrarUsuarios.executeQuery();
			resultSet.next();

			// ID_EMPLEADOS NOMBRE_EMPLEADO MGR EMP_PASWORD ROL
			empleado.setIdEmpleado(resultSet.getInt(1));
			empleado.setNombreEmpleado(resultSet.getString(2));
			empleado.setMgr(resultSet.getInt(3));
			empleado.setRol(resultSet.getString(5));

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<Producto> pullProductos(String where) {

		// @param pull the "Productos" list and if is required add a condition"
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String selectProductos = "SELECT * FROM ap_Admin.PRODUCTOS ";
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
			Producto producto;
			while (resultSet.next()) {
				producto = new Producto();
				producto.setIdProducto(resultSet.getInt(1));
				producto.setNombreProducto(resultSet.getString(2));
				producto.setDescripcionProducto(resultSet.getString(3));
				producto.setStockProducto(resultSet.getInt(4));
				producto.setPrecioProducto(resultSet.getFloat(5));
				producto.setIdFabricante(resultSet.getInt(6));

				productos.add(producto);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return productos;
	}

	public ArrayList<Pedido> pullPedidos(String where) {
		// pull the "Pedidos" list and if is required add a condition"
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		String pedidoseleccion = "SELECT * FROM ap_Admin.PEDIDOS";

		ArrayList<Client> pullClients = pullClients(" /**/");

		if (where != null) {
			pedidoseleccion.concat(where);
		}

		try {
			PreparedStatement pedidoamostrar = super.BBDDcon.prepareStatement(pedidoseleccion);
			ResultSet resultSet = pedidoamostrar.executeQuery();

			while (resultSet.next()) {
				Pedido pedido = new Pedido();

				pedido.setIdPedido(resultSet.getInt(1));
				pedido.setFechaPedido(resultSet.getDate(2));
				pedido.setClient(pullClients.get(0));
				pedido.setCosto(resultSet.getInt(5));
				pedido.setIdFactura(resultSet.getInt(6));

				pedidos.add(pedido);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return pedidos;
	}

	public void updatePedidos(Pedido pedido) {
		// *@param update "pedidos" table's row by id

		String updateString = "UPDATE ap_Admin.PEDIDOS SET FECHA_PEDIDO=?, ID_CLIENTES=?, DIRECCION_CLIENTES=?, COSTO_PEDIDO=?, ID_FACTURAS=? WHERE ID_PEDIDOS=?";

		Client client = new Client();
		client = pedido.getClient();

		try {
			PreparedStatement modifyPedido = super.BBDDcon.prepareStatement(updateString);

			modifyPedido.setDate(1, new Date(pedido.getFechaPedido().getTime()));
			modifyPedido.setInt(2, client.getIdClient());
			modifyPedido.setString(3, client.getDireccionClient());
			modifyPedido.setDouble(4, pedido.getCosto());
			modifyPedido.setInt(5, pedido.getIdFactura());

			modifyPedido.setInt(6, pedido.getIdPedido());

			modifyPedido.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sqlCommit();
	}

	public ArrayList<Client> pullClients(String where) {
		// *@param do a select all flom "Clientes" table
		ArrayList<Client> clientes = new ArrayList<Client>();
		String clientesselecion = "SELECT * FROM ap_Admin.CLIENTES  ";

		clientesselecion = clientesselecion + where;

		try {

			PreparedStatement cientestotal = super.BBDDcon.prepareStatement(clientesselecion);
			ResultSet resultSet = cientestotal.executeQuery();

			while (resultSet.next()) {
				Client client = new Client();
				client.setIdClient(resultSet.getInt(1));
				client.setDireccionClient(resultSet.getString(2));
				client.setNombreClient(resultSet.getString(3));
				client.setContactoClient(resultSet.getString(4));
				client.setDniClient(resultSet.getString(5));
				client.setRegistrado(resultSet.getInt(7));

				clientes.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public void updateCliente(Client client) {
		// *@param update "Clientes" table's row by id
		String updateClientes = "UPDATE ap_Admin.CLIENTES SET DIRECCION_CLIENTES =?, NOMBRE_CLIENTE =?, CONTACTO_CLIENTE =?, DNI_CLIENTE =? WHERE ID_CLIENTES =?";
		
		try {
			PreparedStatement stUpdateClientes = super.BBDDcon.prepareStatement(updateClientes);
			
			stUpdateClientes.setString(1, client.getDireccionClient());
			stUpdateClientes.setString(2, client.getNombreClient());
			stUpdateClientes.setString(3, client.getContactoClient());
			stUpdateClientes.setString(4, client.getDniClient());
			
			stUpdateClientes.setInt(5, client.getIdClient());
			
			stUpdateClientes.execute();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		sqlCommit();

	}
	
	public boolean pushCliente(Client cliente) {
		// *@param insert client data for register or for
		String INSERTClientes = "INSERT INTO ap_Admin.clientes (ID_clientes, direccion_clientes, nombre_cliente, contacto_cliente, dni_cliente,CLI_PASWORD,registrado)VALUES (id_clientes_seq.NEXTVAL,'"
				+ cliente.getDireccionClient() + "', '" + cliente.getNombreClient() + "', '"
				+ cliente.getContactoClient() + "', '" + cliente.getDniClient() + "','" + cliente.getContraseñaClient()
				+ "'," + cliente.getRegistrado() + ")";
		PreparedStatement stINSERTClientes;
		try {
			stINSERTClientes = super.BBDDcon.prepareStatement(INSERTClientes);
			stINSERTClientes.execute();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		sqlCommit();
		return false;
	}

	private boolean sqlCommit() {
		String commit = "commit";
		PreparedStatement stcommit;
		try {
			stcommit = super.BBDDcon.prepareStatement(commit);
			stcommit.execute();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

}
