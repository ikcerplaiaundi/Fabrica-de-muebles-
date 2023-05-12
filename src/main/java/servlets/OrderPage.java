package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Client;
import modelo.DAO.Pedido;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class OrderPage
 */
@WebServlet("/OrderPage")
public class OrderPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderPage() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//@param This method take info from the BBDD of the orders, and takes it to the JSP for display

		GestorBDD gdbb = new GestorBDD();

		gdbb.abrirConexion();

		ArrayList<Pedido> pedidos = gdbb.pullPedidos(" /**/ ");

		gdbb.cerrarConexion();

		request.setAttribute("pedidos", pedidos);
		request.getRequestDispatcher("OrderPage.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//@param This method is callled once you press the modify, it takes the info from the JSP and then update it on the BBDD
		
		GestorBDD gdbb = new GestorBDD();
		gdbb.abrirConexion();

		Pedido pedido = new Pedido();
		Client client = new Client();

		int id = Integer.parseInt(request.getParameter("Id"));
		double costo = Double.parseDouble(request.getParameter("Costo"));
		int idClient = Integer.parseInt(request.getParameter("Idcliente"));
		String DireccionClient = request.getParameter("DireccionClient");
		int idFactura = Integer.parseInt(request.getParameter("factura"));
		String fecha = request.getParameter("fecha");

		client.setDireccionClient(DireccionClient);
		client.setIdClient(idClient);

		pedido.setIdPedido(id);
		pedido.setCosto(costo);
		pedido.setIdFactura(idFactura);
		pedido.setFechaPedido(fecha);
		pedido.setClient(client);

		gdbb.updatePedidos(pedido);

		gdbb.cerrarConexion();

		response.sendRedirect("OrderPage");
	}
}
