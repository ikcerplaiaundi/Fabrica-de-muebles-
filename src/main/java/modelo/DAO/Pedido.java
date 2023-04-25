package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	//a list of products for send to a client
	private int idPedido;
	private double costo;
	private Client client;
	private ArrayList<Producto> productos = new <Producto>ArrayList();
	// date for sql
	private String pattern = "yyyy-MM-dd";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private Date fechaPedido;

	// date
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
			this.fechaPedido = (Date) simpleDateFormat.parse(fechaPedido);
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
	
	
	//simple atribute
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
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
