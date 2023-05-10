package servlets;

import java.awt.geom.Arc2D.Double;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Client;
import modelo.DAO.Producto;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class BuyInfo
 */
@WebServlet("/BuyInfo")
public class BuyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// calc total price
		HttpSession session = request.getSession();
		ArrayList<Producto> productosCompra = (ArrayList<Producto>) session.getAttribute("productosCompra");
		double preciototal = 0;
		if(productosCompra!=null) {
			for (Producto producto : productosCompra) {
				preciototal = preciototal + (producto.getPrecioProducto()*producto.getStockProducto());
			}
		}
		preciototal=Math.round(preciototal*100);
		preciototal=preciototal/100;
		
		session.setAttribute("preciototal", preciototal);
		request.getRequestDispatcher("BuyInfo.jsp").forward(request, response);
//		response.sendRedirect("");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GestorBDD gdbb = new GestorBDD();
		HttpSession session = request.getSession();
		
		Client logedClient = (Client) session.getAttribute("logedClient");
		
		if (logedClient != null) { //usuario logueado
			logedClient.setDireccionClient(request.getParameter("Direccion"));
			logedClient.setNombreClient(request.getParameter("name"));
			logedClient.setContactoClient(request.getParameter("Contact"));
			logedClient.setDniClient(request.getParameter("DNI"));
			session.setAttribute("logedClient", logedClient);
			
			gdbb.abrirConexion();
			gdbb.updateCliente(logedClient);
			gdbb.cerrarConexion();
		}else { //usuario no resgistrado
			Client cliente =new Client();
			cliente.setDireccionClient(request.getParameter("Direccion"));
			cliente.setNombreClient(request.getParameter("name"));
			cliente.setContactoClient(request.getParameter("Contact"));
			cliente.setDniClient(request.getParameter("DNI"));
			gdbb.abrirConexion();
			gdbb.pushCliente(cliente);
			gdbb.cerrarConexion();
		}
		
		
		
	}

}
