package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Proveedor;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class SupplierPage
 */
@WebServlet("/SupplierPage")
public class SupplierPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		GestorBDD gdbb = new GestorBDD();
		
		gdbb.abrirConexion();
		
		ArrayList<Proveedor> proveedores = gdbb.pullProveedores(" /**/ ");
		
		gdbb.cerrarConexion();
		
		request.setAttribute("Proveedores", proveedores);
		request.getRequestDispatcher("SupplierPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GestorBDD gdbb = new GestorBDD();
		
		Proveedor proveedor = new Proveedor();
		
		String direccionProve = request.getParameter("direccionprov");
		String cifProve = request.getParameter("cifprov");
		String contactoProve = request.getParameter("contactoprov");
		String nombreProve = request.getParameter("nombreprov");
		int idProve = Integer.parseInt(request.getParameter("idprov"));
		
		proveedor.setDireccion(direccionProve);
		proveedor.setCif(cifProve);
		proveedor.setTelefono(contactoProve);
		proveedor.setNombre(nombreProve);
		proveedor.setIdProveedor(idProve);
		
		gdbb.abrirConexion();
		
		gdbb.updateProveedor(proveedor);
		
		gdbb.cerrarConexion();
		
		response.sendRedirect("SupplierPage");
	}

}
