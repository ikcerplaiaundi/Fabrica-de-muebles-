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
		String contraseña  = new String();
		

		modelo.DTO.GestorBDD GDBB = new modelo.DTO.GestorBDD();
		GDBB.abrirConexion();
		Boolean[] chek =GDBB.ChekUser(user);
		GDBB.cerrarConexion();
		
		if ( chek[0]) {
			Mensage = "contrseña incorrecta";
		}
		if ( chek[1]) {
			Mensage = "usuario no encontrado";
		}	
		if (( chek[0])&&( chek[1])) {
			HttpSession session = request.getSession();
			session.setAttribute("logedUser", user);
			Mensage = "bien venido " + user.getNombre();
			// enviar datos
			request.setAttribute("Mensage", Mensage);
			// a que jsp?
				response.sendRedirect("forRedirect");
			} else {
			// enviar datos
			request.setAttribute("Mensage", Mensage);
			// a que jsp?
			request.getRequestDispatcher("log.jsp").forward(request, response);
		}
		GDBB.cerrarConexion();

	}
}
