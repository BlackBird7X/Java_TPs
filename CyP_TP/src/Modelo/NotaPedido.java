package Modelo;

import java.time.LocalDate;

public class NotaPedido {
	private LocalDate fecha;
	private int cantidad;
	private String cliente;
	
	public NotaPedido(LocalDate fecha, int cantidad, String cliente) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "NotaPedido: [fecha=" + fecha + ", cantidad=" + cantidad + ", cliente=" + cliente + "]\n";
	}
	
	
}
