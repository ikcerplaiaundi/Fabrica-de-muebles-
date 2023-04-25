package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Piezas {
	//type of elements what conform products
	private int idPiezas;
	private String nombrePiezas;
	private int stockPiezas;
	private String decripcion;
	//date for sql
	private String pattern = "yyyy-MM-dd";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private Date fechaEntrada;
	//date 
	public Date getFechaEntrada() {
		return  fechaEntrada;
	}
	public String getStringFechaEntrada() {
		return ""+simpleDateFormat.format(this.fechaEntrada);
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
		this.simpleDateFormat=new SimpleDateFormat(pattern);
		this.setFechaEntrada(auxdate);
	}


	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}
	//simple atributes
	public int getIdPiezas() {
		return idPiezas;
	}
	public void setIdPiezas(int idPiezas) {
		this.idPiezas = idPiezas;
	}
	public String getNombrePiezas() {
		return nombrePiezas;
	}
	public void setNombrePiezas(String nombrePiezas) {
		this.nombrePiezas = nombrePiezas;
	}
	public int getStockPiezas() {
		return stockPiezas;
	}
	public boolean setStockPiezas(int stockPiezas) {
		//stock cant be negative
		if(stockPiezas <0) {return false;}
		else {this.stockPiezas = stockPiezas; return true;}
	}
	public String getDecripcion() {
		return decripcion;
	}
	public void setDecripcion(String decripcion) {
		this.decripcion = decripcion;
	}
	
	
}
