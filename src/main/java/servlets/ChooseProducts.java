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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GestorBDD gdbb =new GestorBDD();
		gdbb.abrirConexion();
		ArrayList <Producto> productos = gdbb.pullProductos(" /**/ ");
		gdbb.abrirConexion();
		
		
		
		request.setAttribute("productos", productos);
		response.sendRedirect("ChooseProducts.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
