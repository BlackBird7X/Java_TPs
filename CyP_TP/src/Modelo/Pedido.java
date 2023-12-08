package Modelo;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido extends Stock{
	private List<NotaPedido> notasPedidos;

	public Pedido(int idStock, Producto producto) {
		super(idStock, producto);
		this.notasPedidos = new ArrayList<NotaPedido>();
	}

	public List<NotaPedido> getNotasPedidos() {
		return notasPedidos;
	}

	@Override
	public String toString() {
		return "Pedido: [notasPedidos=" + notasPedidos +  ", producto=" + producto + "]";
	}

//CU: 8	****BORRAR ESTE COMENTARIO****
	public boolean agregarNotaPedido(LocalDate fecha, int cantidad, String cliente) {
			
		return notasPedidos.add(new NotaPedido(fecha, cantidad, cliente));
		}
	
	
	public int cantidadProductosVendidos() {
		int totalVendidos = 0;
		
		for(NotaPedido np : notasPedidos) {
			totalVendidos += np.getCantidad();
		}
		
		return totalVendidos;
	}
	
	
	
	
}