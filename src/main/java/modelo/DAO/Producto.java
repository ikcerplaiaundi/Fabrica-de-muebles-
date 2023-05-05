package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Producto {
//type of products
	private int idProducto;
	private String nombreProd;
	private String descripcionProd;
	private double precioProd;
	private int idEmpleado;
	private int stockProd;
	private int idPedidos;
	private ArrayList<Piezas> piezas = new <Piezas>ArrayList();
//date for sql
	private String pattern = "yyyy-MM-dd";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private Date fechaPedido;

// date
	public Date getFechaEntrada() {
		return fechaPedido;
	}

	public String getStringFechaEntrada() {
		return "" + simpleDateFormat.format(this.fechaPedido);
	}

	public String getStringFechaEntrada(String patern) {
		this.setPattern(patern);
		return simpleDateFormat.format(this.fechaPedido);
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaPedido = fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		try {
			this.fechaPedido = (Date) simpleDateFormat.parse(fechaEntrada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getPattern() {
		return pattern;

	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
		String auxdate = getStringFechaEntrada();
		this.simpleDateFormat = new SimpleDateFormat(pattern);
		this.setFechaEntrada(auxdate);
	}

	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}

//Simple attributes
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProd;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProd = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProd;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProd = descripcionProducto;
	}

	public double getPrecioProducto() {
		return precioProd;
	}

	public boolean setPrecioProducto(double precioProducto) {
		// a price can't be negative
		if (precioProducto < 0) {
			return false;
		} else {
			this.precioProd = precioProducto;
			return true;
		}
	}

	public int getIdFabricante() {
		return idEmpleado;
	}

	public void setIdFabricante(int idFabricante) {
		this.idEmpleado = idFabricante;
	}

	public int getStockProducto() {
		return stockProd;
	}

	public boolean setStockProducto(int stockProducto) {

		// stock can't be negative
		if (stockProducto < 0) {
			return false;
		} else {
			this.stockProd = stockProducto;
			return true;
		}
	}

	public int getIdPedidos() {
		return idPedidos;
	}

	public void setIdPedidos(int idPedidos) {
		this.idPedidos = idPedidos;
	}

	public ArrayList<Piezas> getPiezas() {
		return piezas;
	}

	public void setPiezas(ArrayList<Piezas> piezas) {
		this.piezas = piezas;
	}

}
