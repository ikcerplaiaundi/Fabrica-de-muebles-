package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Producto;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class ChooseProducts
 */
@WebServlet("/ChooseProducts")
public class ChooseProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChooseProducts() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GestorBDD gdbb = new GestorBDD();
		gdbb.abrirConexion();
		ArrayList<Producto> productos = gdbb.pullProductos(" /**/ ");
		gdbb.cerrarConexion();

		request.setAttribute("productos", productos);
		request.getRequestDispatcher("ChooseProducts.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Search the stock of each product type for serl
		GestorBDD gdbb = new GestorBDD();
		gdbb.abrirConexion();
		ArrayList<Producto> productos = gdbb.pullProductos(" /**/ ");
		gdbb.cerrarConexion();
		ArrayList<Producto> productosCompra = new ArrayList<Producto>();
		Producto producto;
		
		for (int i = 0; productos.size() > i; i++) {
			if (null != request.getParameter("" + productos.get(i).getIdProducto())) {
				producto = new Producto();
				producto.setIdProducto(productos.get(i).getIdProducto());
				producto.setStockProducto(
						Integer.parseInt(request.getParameter("" + productos.get(i).getIdProducto())));
				productosCompra.add(producto);
				
			}
		}

	}
}