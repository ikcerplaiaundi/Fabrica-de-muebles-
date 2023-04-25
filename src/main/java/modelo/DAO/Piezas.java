package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Piezas {
	//tipe of elements what conform products
	private int id;
	private String nombre;
	private int stock;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
