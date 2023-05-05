package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		String Mensage = "inserte el usuario y contraseña";
		modelo.DAO.User user = new modelo.DAO.User();
		user.setIdLikeString(request.getParameter("userId"));
		user.setNombre(request.getParameter("nombre"));
		user.setContra(request.getParameter("contraseña"));
		

		modelo.DTO.GestorBDD GDBB = new modelo.DTO.GestorBDD();
		GDBB.abrirConexion();
		
		//check 
		Boolean[] chek =GDBB.ChekUser(user);
		
		
		if ( !chek[0] ||!chek[2] ) {
			Mensage = "contrseña incorrecta";
		}
		if ( !chek[1] ||!chek[3]) {
			Mensage = "usuario no encontrado";
		}	
		if (( !chek[0])&&( !chek[1])) {
			modelo.DAO.Client client = new modelo.DAO.Client();
			client.setIdClient(user.getId());
			client.setNombreClient(user.getNombre());
			client.setContraseñaClient(user.getContra());
			GDBB.pullCliente(client,user);
			HttpSession session = request.getSession();
			session.setAttribute("logedclient", user);
			Mensage = "bien venido " + user.getNombre();
			// enviar datos
			request.setAttribute("Mensage", Mensage);
			// a que jsp?
				response.sendRedirect("forRedirect");
			} 
		
		if (( !chek[2])&&( !chek[3])) {
			modelo.DAO.Empleado empleado = new modelo.DAO.Empleado();
			empleado.setIdEmpleado(user.getId());
			empleado.setNombreEmpleado(user.getNombre());
			empleado.setContraseñaEmpleado(user.getContra());
			empleado.setRol(user.getRol());
			GDBB.pullEmpleado(empleado,user);
			HttpSession session = request.getSession();
			session.setAttribute("logedclient", user);
			Mensage = "bien venido " + user.getNombre();
			// enviar datos
			request.setAttribute("Mensage", Mensage);
			// a que jsp?
			response.sendRedirect("forRedirect");
			}
			
		
			// enviar datos
			request.setAttribute("Mensage", Mensage);
			// a que jsp?
			request.getRequestDispatcher("log.jsp").forward(request, response);
		
		
		
		
		
		
		
		GDBB.cerrarConexion();
	}
}