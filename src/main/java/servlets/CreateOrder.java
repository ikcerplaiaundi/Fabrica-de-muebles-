package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.Client;
import modelo.DAO.Pedido;
import modelo.DTO.GestorBDD;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//@param this method creates a order with the info send from the JSP
		
		GestorBDD gdbb = new GestorBDD();
		
		gdbb.abrirConexion();
		
		Pedido pedido = new Pedido();
		
		Client client = new Client();
		
		int idclient = Integer.parseInt(request.getParameter("Idcliente"));
		int idfactura = Integer.parseInt(request.getParameter("Idfactura"));
		double coste = Double.parseDouble(request.getParameter("Costo"));
		String fecha = request.getParameter("fecha");
		int idpedido = Integer.parseInt(request.getParameter("Idpedido"));
		
		client.setIdClient(idclient);
		
		gdbb.pullClienteViaId(client);
		
		pedido.setClient(client);
		pedido.setIdFactura(idfactura);
		pedido.setCosto(coste);
		pedido.setFechaPedido(fecha);
		pedido.setIdPedido(idpedido);
		
		gdbb.pushPedidos(pedido);
		
		gdbb.cerrarConexion();
		
		response.sendRedirect("OrderPage");
	}

}
