package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Proveedor;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class SupplierCreate
 */
@WebServlet("/SupplierCreate")
public class SupplierCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GestorBDD gdbb = new GestorBDD();
		
		Proveedor proveedor = new Proveedor();
		
		String direccionProve = request.getParameter("direccionprov");
		String cifProve = request.getParameter("cifprov");
		String contactProve = request.getParameter("contactoprov");
		String nombreProve = request.getParameter("nombreprov");


		proveedor.setDireccion(direccionProve);
		proveedor.setCif(cifProve);
		proveedor.setTelefono(contactProve);
		proveedor.setNombre(nombreProve);
		
		gdbb.abrirConexion();
		
		gdbb.pushProveedor(proveedor);
		
		gdbb.cerrarConexion();
		
		response.sendRedirect("SupplierPage");
	}

}
