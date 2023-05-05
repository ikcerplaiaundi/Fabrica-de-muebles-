package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Piezas {
	// type of elements what conform products
	private int idPiezas;
	private String nombrePieza;
	private int stockPieza;
	private String decripcionPieza;
	private int idProductos;
	// date for sql
	private String pattern = "yyyy-MM-dd";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private Date fechaEntrada;

	// date
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public String getStringFechaEntrada() {
		return "" + simpleDateFormat.format(this.fechaEntrada);
	}

	public String getStringFechaEntrada(String patern) {
		this.setPattern(patern);
		return simpleDateFormat.format(this.fechaEntrada);
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		try {
			this.fechaEntrada = (Date) simpleDateFormat.parse(fechaEntrada);
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

	// simple attributes
	public int getIdPiezas() {
		return idPiezas;
	}

	public void setIdPiezas(int idPiezas) {
		this.idPiezas = idPiezas;
	}

	public String getNombrePiezas() {
		return nombrePieza;
	}

	public void setNombrePiezas(String nombrePiezas) {
		this.nombrePieza = nombrePiezas;
	}

	public int getStockPiezas() {
		return stockPieza;
	}

	public int getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}

	public boolean setStockPiezas(int stockPiezas) {
		// stock can`t be negative
		if (stockPiezas < 0) {
			return false;
		} else {
			this.stockPieza = stockPiezas;
			return true;
		}
	}

	public String getDecripcion() {
		return decripcionPieza;
	}

	public void setDecripcion(String decripcion) {
		this.decripcionPieza = decripcion;
	}

}
