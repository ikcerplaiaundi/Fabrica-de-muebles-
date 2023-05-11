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
		
		request.setAttribute("clientes", clientesregistrados);
		request.getRequestDispatcher("ClientManagement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
