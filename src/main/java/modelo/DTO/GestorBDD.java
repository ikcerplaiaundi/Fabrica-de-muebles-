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
import modelo.DAO.Proveedor;

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

	public void pushPedidos(Pedido pedido) {

		String insertarPedido = "INSERT INTO ap_Admin.PEDIDOS(ID_PEDIDOS,FECHA_PEDIDO, ID_CLIENTES, DIRECCION_CLIENTES, COSTO_PEDIDO, ID_FACTURAS) VALUES (id_pedidos_seq.NEXTVAL,?,?,?,?,?)";

		Client client = new Client();
		client = pedido.getClient();

		try {
			PreparedStatement stinsertpedido = super.BBDDcon.prepareStatement(insertarPedido);

		
			stinsertpedido.setDate(1, new Date(pedido.getFechaPedido().getTime()));
			stinsertpedido.setInt(2, client.getIdClient());
			stinsertpedido.setString(3, client.getDireccionClient());
			stinsertpedido.setDouble(4, pedido.getCosto());
			stinsertpedido.setInt(5, pedido.getIdFactura());

			stinsertpedido.execute();

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

	public void pullClienteViaId(Client client) {
		// @param pull the "Cliente" specified "
		String selectClientes = "SELECT * FROM ap_Admin.CLIENTES WHERE ID_CLIENTES=?";
		try {
			PreparedStatement mostrarUsuarios = super.BBDDcon.prepareStatement(selectClientes);
			mostrarUsuarios.setInt(1, client.getIdClient());

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

	public void lessStockProductos(ArrayList<Producto> productos) {
		// *@param update "productos" table's row by id
		for (Producto producto : productos) {

			String updateString = "UPDATE ap_Admin.productos SET STOCK_PROD = (STOCK_PROD - ?) WHERE ID_productos = ?";

			try {
				PreparedStatement psProductos = super.BBDDcon.prepareStatement(updateString);

				psProductos.setInt(1, producto.getStockProducto());
				psProductos.setInt(2, producto.getIdProducto());

				psProductos.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			sqlCommit();
		}
	}

	
	
	public ArrayList<Proveedor> pullProveedores(String where){
		
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		String proveedoresseleccion = "SELECT * FROM ap_Admin.PROVEEDORES  ";
		
		if (where != null) {
			proveedoresseleccion.concat(where);
		}

		try {
			PreparedStatement pullProveed = super.BBDDcon.prepareStatement(proveedoresseleccion);
			ResultSet resultSet = pullProveed.executeQuery();
			
			while(resultSet.next()) {
				Proveedor proveedor = new Proveedor();
				
				proveedor.setDireccion(resultSet.getString(1));
				proveedor.setCif(resultSet.getString(2));
				proveedor.setTelefono(resultSet.getString(3));
				proveedor.setNombre(resultSet.getString(4));
				proveedor.setIdProveedor(resultSet.getInt(5));
				
				proveedores.add(proveedor);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlCommit();
		
		return proveedores;
	}

	public void updateProveedor(Proveedor proveedor) {
		
		String updateProv = "UPDATE ap_Admin.PROVEEDORES SET DIRECCION_PROVEEDOR=?, CIF_PROVEEDOR=?, CONTACT_PROVEEDOR=?, NOMBRE_PROVEEDOR=? WHERE ID_PROVEEDOR=?";
		
		try {
			
			PreparedStatement updatedProv = super.BBDDcon.prepareStatement(updateProv);
			
			updatedProv.setString(1, proveedor.getDireccion());
			updatedProv.setString(2, proveedor.getCif());
			updatedProv.setString(3, proveedor.getTelefono());
			updatedProv.setString(4, proveedor.getNombre());
			
			updatedProv.setInt(5, proveedor.getIdProveedor());
			
			updatedProv.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		sqlCommit();
	}
	
	public void pushProveedor(Proveedor proveedor) {
		
		String pushProve = "INSERT INTO ap_Admin.PROVEEDORES (DIRECCION_PROVEEDOR, CIF_PROVEEDOR, CONTACT_PROVEEDOR, NOMBRE_PROVEEDOR,ID_PROVEEDORES) VALUES (?,?,?,?,id_proveedores_seq.NEXTVAL)";
		
		try {
			
			PreparedStatement pushproveedor = super.BBDDcon.prepareStatement(pushProve);
			
			pushproveedor.setString(1, proveedor.getDireccion());
			pushproveedor.setString(2, proveedor.getCif());
			pushproveedor.setString(3, proveedor.getTelefono());
			pushproveedor.setString(4, proveedor.getNombre());
			
			pushproveedor.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		sqlCommit();
	}

	public void pushPedidosProductos(Pedido pedido, ArrayList<Producto> productosCompra) {
		// *@param push how many of what products are in one "pedido" 
		for (Producto producto : productosCompra) {
			/*
			 * CREATE TABLE productos_pedidos ( ID_productos NUMBER, ID_pedidos NUMBER ,
			 * fecha_pedido date, stock_prod NUMBER, PRIMARY KEY ( ID_productos,
			 * ID_pedidos,fecha_pedido) )TABLESPACE FabricaMuebles;
			 */
			String INSERTClientes = "INSERT INTO ap_Admin.PRODUCTOS_PEDIDOS (ID_productos, ID_pedidos, fecha_pedido, stock_prod)VALUES (?,?,?,?)";
			PreparedStatement stINSERTPRODUCTOS_EMPEADOS;
			try {
				stINSERTPRODUCTOS_EMPEADOS = super.BBDDcon.prepareStatement(INSERTClientes);
				stINSERTPRODUCTOS_EMPEADOS.setInt(1, producto.getIdProducto());
				stINSERTPRODUCTOS_EMPEADOS.setInt(2, pedido.getIdPedido());
				stINSERTPRODUCTOS_EMPEADOS.setDate(3, new Date(pedido.getFechaPedido().getTime()));
				stINSERTPRODUCTOS_EMPEADOS.setInt(4, producto.getStockProducto());
				
				
				
				stINSERTPRODUCTOS_EMPEADOS.execute();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			sqlCommit();
			
			
			
		}

	}


}
