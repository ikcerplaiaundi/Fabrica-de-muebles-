package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			//open and show all products
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
		//Search the stock of each product type for sell, and save how many of what type of products need
		GestorBDD gdbb = new GestorBDD();
		gdbb.abrirConexion();
		ArrayList<Producto> productos = gdbb.pullProductos(" /**/ ");
		gdbb.cerrarConexion();
		ArrayList<Producto> productosCompra = new ArrayList<Producto>();
		Producto productoCompra;
		
		for (Producto producto : productos) {
			if (null != request.getParameter("" + producto.getIdProducto())&&("0"!=request.getParameter("" + producto.getIdProducto())&&(""!=request.getParameter("" + producto.getIdProducto())))) {
				productoCompra=new Producto();
				productoCompra.setPrecioProducto(producto.getPrecioProducto());
				productoCompra.setNombreProducto(producto.getNombreProducto());
				productoCompra.setIdProducto(producto.getIdProducto());
				productoCompra.setStockProducto(Integer.parseInt(request.getParameter(""+producto.getIdProducto()))) ; 
				
				productosCompra.add(productoCompra);
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("productosCompra", productosCompra);
		
		
		// a que jsp?
		response.sendRedirect("BuyInfo");
		

	}
}