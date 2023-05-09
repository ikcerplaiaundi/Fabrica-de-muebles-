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
	
	private ArrayList<Piezas> piezas = new <Piezas>ArrayList();




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

	public ArrayList<Piezas> getPiezas() {
		return piezas;
	}

	public void setPiezas(ArrayList<Piezas> piezas) {
		this.piezas = piezas;
	}
	
	
	
}
