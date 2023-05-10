package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	// a list of products for send to a client
	private int idPedidos;
	private double costoPedido;
	private Client client;
	private int idFactura;
	private ArrayList<Producto> productos = new <Producto>ArrayList();
	// date for sql
	private String pattern = "yyyy-mm-dd";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private Date fechaPedido;

	// date for sql get set
	public Date getFechaPedido() {
		return fechaPedido;
	}

	public String getStringFechaPedido() {
		return "" + simpleDateFormat.format(this.fechaPedido);
	}

	public String getStringFechaPedido(String patern) {
		this.setPattern(patern);
		return simpleDateFormat.format(this.fechaPedido);
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public void setFechaPedido(String fechaPedido) {
		try {
			this.fechaPedido = simpleDateFormat.parse(fechaPedido);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getPattern() {
		return pattern;

	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
		String auxdate = getStringFechaPedido();
		this.simpleDateFormat = new SimpleDateFormat(pattern);
		this.setFechaPedido(auxdate);
	}

	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}

	// simple attribute get set

	public int getIdPedido() {
		return idPedidos;
	}

	public void setIdPedido(int idPedido) {
		this.idPedidos = idPedido;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public double getCosto() {
		return costoPedido;
	}

	public void setCosto(double costo) {
		this.costoPedido = costo;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

}
