package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Client;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class ClientManagement
 */
@WebServlet("/ClientManagement")
public class ClientManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		GestorBDD gdbb = new GestorBDD();
		
		gdbb.abrirConexion();
		
		ArrayList<Client> clientesregistrados = gdbb.pullClients("WHERE REGISTRADO=1");
		
		ArrayList<Client> clientesnoregistrados = gdbb.pullClients("WHERE REGISTRADO!=1");
		
		gdbb.cerrarConexion();
		
		request.setAttribute("clientesregistrados", clientesregistrados);
		request.setAttribute("clientesnoregistrados", clientesnoregistrados);
		request.getRequestDispatcher("ClientManagement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		GestorBDD gdbb = new GestorBDD();
		gdbb.abrirConexion();
		
		Client client = new Client();
		
		int idclient = Integer.parseInt(request.getParameter("Id"));
		String direcclient = request.getParameter("direccionclient");
		String nombreclient = request.getParameter("nombreclient");
		String contactoclient = request.getParameter("contactclient");
		String dniclient = request.getParameter("dniclient");
		
		client.setIdClient(idclient);
		client.setDireccionClient(direcclient);
		client.setNombreClient(nombreclient);
		client.setContactoClient(contactoclient);
		client.setDniClient(dniclient);
		
		gdbb.updateCliente(client);
		
		gdbb.cerrarConexion();
		
		response.sendRedirect("ClientManagement");
	}

}
