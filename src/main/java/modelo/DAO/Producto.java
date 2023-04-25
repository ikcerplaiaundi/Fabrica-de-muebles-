package modelo.DAO;

import java.util.ArrayList;

public class Producto {
//tipe of products
	private int idProducto;
	private String nombreProducto;
	private String descripcionProducto ;
	private double precioProducto;
	private int  idFabricante;
	private int stockProducto;
	private ArrayList <Piezas> piezas = new <Piezas> ArrayList();
//set/get
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public boolean setPrecioProducto(double precioProducto) {
		//a price can't be negative
		if(precioProducto <0) {return false;}
		else {this.precioProducto = precioProducto; return true;}
	}
	public int getIdFabricante() {
		return idFabricante;
	}
	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}
	public int getStockProducto() {
		return stockProducto;
	}
	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}
	public ArrayList<Piezas> getPiezas() {
		return piezas;
	}
	public void setPiezas(ArrayList<Piezas> piezas) {
		this.piezas = piezas;
	}
	
	
}
