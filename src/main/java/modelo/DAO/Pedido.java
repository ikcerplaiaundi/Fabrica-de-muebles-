package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	private int idPedido;
	private double costo;
	private Client client;
	private ArrayList <Producto> productos = new <Producto> ArrayList();
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
