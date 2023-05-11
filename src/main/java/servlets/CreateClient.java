package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Client;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class CreateClient
 */
@WebServlet("/CreateClient")
public class CreateClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClient() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		
		GestorBDD gdbb = new GestorBDD();
		
		Client client = new Client();
		
		String direccioncliente = request.getParameter("direccioncliente");
		String nombrecliente = request.getParameter("nombreclient");
		String contactcliente = request.getParameter("contactclient");
		String dnicliente = request.getParameter("dniclient");
		
		client.setDireccionClient(direccioncliente);
		client.setNombreClient(nombrecliente);
		client.setContactoClient(contactcliente);
		client.setDniClient(dnicliente);
		
		gdbb.abrirConexion();
		
		gdbb.pushCliente(client);
		
		gdbb.cerrarConexion();
		
		response.sendRedirect("ClientManagement");
	}

}
