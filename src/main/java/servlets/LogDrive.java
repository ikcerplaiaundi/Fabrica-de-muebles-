package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.Client;

/**
 * Servlet implementation class LogDrive
 */
@WebServlet("/LogDrive")
public class LogDrive extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogDrive() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("log.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        modelo.DTO.GestorBDD GDBB = new modelo.DTO.GestorBDD();
		GDBB.abrirConexion();
		checkLogin(request, response, GDBB);
		Client client=new Client();
		client.setContactoClient(request.getParameter("newContact"));
		client.setNombreClient(request.getParameter("newname"));
		client.setContraseñaClient(request.getParameter("newcontra"));
		client.setDniClient(request.getParameter("newDNI"));
		client.setDireccionClient(request.getParameter("newDireccion"));
		client.setRegistrado(1);
		GDBB.pushCliente(client);
		
		
		
		
		GDBB.cerrarConexion();
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response, modelo.DTO.GestorBDD GDBB)
			throws IOException, ServletException {
		
		//
		String Mensage = "inserte el usuario y contraseña";
		modelo.DAO.User user = new modelo.DAO.User();

		user.setNombre(request.getParameter("nombre"));
		user.setContra(request.getParameter("contra"));

		

		// check
		Boolean[] chek = GDBB.ChekUser(user);
		

		if (!chek[0] || !chek[2]) {
			Mensage = "contrseña incorrecta";
		}
		if (!chek[1] || !chek[3]) {
			Mensage = "usuario no encontrado";
		}
		if ((chek[2]) && (chek[3])) {
			modelo.DAO.Client client = new modelo.DAO.Client();
			GDBB.pullCliente(client, user);
			HttpSession session = request.getSession();
			session.setAttribute("logedClient", client);

			// a que jsp?
			response.sendRedirect("ChooseProducts");
		} else {

			if ((chek[1]) && (chek[0])) {
				modelo.DAO.Empleado empleado = new modelo.DAO.Empleado();
				GDBB.pullEmpleado(empleado, user);
				HttpSession session = request.getSession();
				session.setAttribute("logedEmpleado", empleado);

				switch (empleado.getRol()) {
				case "Gestor":
					response.sendRedirect("ManagerPage");
					break;
				case "ap_Admin":
					request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
					break;
				default:
					response.sendRedirect("ChooseProducts");
					break;
				}
				
			} else {

				// enviar datos
				request.setAttribute("Mensage", Mensage);
				// a que jsp?
				request.getRequestDispatcher("log.jsp").forward(request, response);

			}

		}
	}
	
}