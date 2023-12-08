package Test;

import java.time.LocalDate;

import Modelo.Pedido;
import Modelo.Sistema;

public class test {

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		
		
		//Agrego los productos:
		try {
			sistema.agregarProducto("111", "lapicero", 200, 1);
			sistema.agregarProducto("222", "libreta", 200, 1);
			sistema.agregarProducto("333", "regla", 200, 2);
			sistema.agregarProducto("444", "goma", 200, 3);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		//Agrego los pedidos:
		sistema.agregarPedido(sistema.traerProducto("111"));
		sistema.agregarPedido(sistema.traerProducto("222"));
		sistema.agregarPedido(sistema.traerProducto("333"));
		sistema.agregarPedido(sistema.traerProducto("444"));

		
		
		//Agrego los almacenes:
		sistema.agregarAlmacen(sistema.traerProducto("222"), 10, 8);
		sistema.agregarAlmacen(sistema.traerProducto("333"), 120, 100);
		
		
		//Agrego los pedidos de los clientes:
		((Pedido)sistema.traerStock(1)).agregarNotaPedido(LocalDate.of(2021, 7, 14), 600, "cliente 1");
		((Pedido)sistema.traerStock(2)).agregarNotaPedido(LocalDate.of(2021, 7, 15), 320, "cliente 1");
		((Pedido)sistema.traerStock(3)).agregarNotaPedido(LocalDate.of(2021, 7, 16), 450, "cliente 2");
		((Pedido)sistema.traerStock(4)).agregarNotaPedido(LocalDate.of(2021, 7, 17), 100, "cliente 3");

		
//1) Articulo mas vendido:		
		System.out.println("\n");
		System.out.println("Listado de productos vendidos con sus cantidades:");
		sistema.pedidoConCantidadVendida(sistema.traerProducto("111"));
		sistema.pedidoConCantidadVendida(sistema.traerProducto("222"));
		sistema.pedidoConCantidadVendida(sistema.traerProducto("333"));
		sistema.pedidoConCantidadVendida(sistema.traerProducto("444"));
		
		
//3) Articulo mas vendido:
		System.out.println("\n");
		sistema.productoMasVendido(sistema.traerPedido());
		
		
//3) Articulo menos vendido:
		System.out.println("\n");
		sistema.productoMenosVendido(sistema.traerPedido());
		
		
//4) Ventas realizadas previo a una fecha dada:		
		System.out.println("\n");
		System.out.println("Ventas realizadas previo a 18-07-2021:");
		System.out.println(sistema.traerPedidosPrevioA(LocalDate.of(2021, 7, 18)));
		
		
//5) Dado un cliente, listar sus compras con su fecha correspondiente:
		System.out.println("\n");
		System.out.println(sistema.traerPedidoPorCliente("cliente 1"));	
	}

}
