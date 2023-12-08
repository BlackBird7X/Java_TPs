package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<Stock> stocks;
	private List<Producto> productos;
	
	public Sistema() {
		super();
		this.stocks = new ArrayList<Stock>();
		this.productos = new ArrayList<Producto>();
	}
	
	public List<Stock> getStocks() {
		return stocks;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	
	@Override
	public String toString() {
		return "Sistema: [stocks=" + stocks + ", productos=" + productos + "]";
	}
	

	public boolean agregarProducto(String codigo, String nombre, double precio, int tipo) throws Exception {
		int id=1;
			
		if(productos.size()>0) {
			id = productos.get(productos.size()-1).getIdProducto()+1;
		}
			
		return productos.add(new Producto(id, codigo, nombre, precio, tipo));
	}


		public Producto traerProducto(String codigo) {
			Producto pAux = null;
			int i=0;
			
			while(i<productos.size() && pAux==null) {
				if(productos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
					pAux = productos.get(i);
				}
				i++;
			}
			return pAux;
		}
		

		public boolean agregarPedido(Producto producto) {
			int id=1;
			
			if(stocks.size()>0) {
				id = stocks.get(stocks.size()-1).getIdStock()+1;
			}
			
			System.out.println("Se agrego pedido.");
			
			return stocks.add(new Pedido(id, producto));
		}
		

		public boolean agregarAlmacen(Producto producto, int unidadesDeseable, int unidadesMinima) {
			int id=1;
			
			if(stocks.size()>0) {
				id = stocks.get(stocks.size()-1).getIdStock()+1;
			}
			
			System.out.println("Se agrego almacen.");
			
			return stocks.add(new Almacen(id, producto, unidadesDeseable, unidadesMinima));
		}
		
		
		public List<Stock> traerStock(Producto producto){
			List<Stock> lstSAux = new ArrayList<Stock>();
			
			for(Stock s : stocks) {
				if(s.getProducto().equals(producto)) {
					lstSAux.add(s);
				}
			}	
			return lstSAux;
		}
		
		
		public Stock traerStock(int idStock) {
			Stock sAux = null;
			int i=0;
			
			while(i<stocks.size() && sAux==null) {
				if(stocks.get(i).getIdStock()==idStock) {
					sAux = stocks.get(i);
				}
				i++;
			}
			return sAux;
		}		
			
		
		public List<Pedido> traerPedido(Producto producto) {
			List<Stock> lstSAux = traerStock(producto);
			List<Pedido> lstPAux = new ArrayList<>();

			for(Stock s : lstSAux) {
				if(s instanceof Pedido) {
					lstPAux.add((Pedido) s);
				}
			}
			return lstPAux;
		}
		
		
		public List<Producto> traerProducto(int tipo) {
			List<Producto> lstPAux = new ArrayList<>();
			
			
			for(Producto p : productos) {
				if(p.getTipo() == tipo) {
					lstPAux.add(p);
				}
			}
			return lstPAux;
		}
		
//CU: 1	
		public void pedidoConCantidadVendida(Producto producto) {
			for(Pedido p : traerPedido(producto)) {
				System.out.println(p.getProducto().getNombre());
				System.out.println("Cantidad vendida:");
				System.out.println(p.cantidadProductosVendidos());
			}
		}
		
		
		public List<Pedido> traerPedido() {
			List<Pedido> lstPAux = new ArrayList<>();

			for(Stock s : stocks) {
				if(s instanceof Pedido) {
					lstPAux.add((Pedido) s);
				}
			}
			return lstPAux;
		}
		
//CU: 2		
		public void productoMasVendido(List<Pedido> pedidos) {
			int cantidadMayor = 0;
			Producto aux = null;
			
			for(Pedido p : pedidos) {
				if(p.cantidadProductosVendidos()>cantidadMayor) {
					cantidadMayor = p.cantidadProductosVendidos();
					aux = p.getProducto();
				}
			}
			System.out.println("Producto mas vendido: \n" + aux);
			System.out.println("Cantidad total: " + cantidadMayor);
		}
		
//CU: 3	
		public void productoMenosVendido(List<Pedido> pedidos) {
			int cantidadMayor = 0;
			int cantidadMenor = 0;
			Producto aux = null;
			
			for(Pedido p : pedidos) {
				if(p.cantidadProductosVendidos()>=cantidadMayor) {
					cantidadMayor = p.cantidadProductosVendidos();
				}
			}
			
			for(Pedido p : pedidos) {
				if(p.cantidadProductosVendidos()<=cantidadMayor) {
					cantidadMenor = p.cantidadProductosVendidos();
					aux = p.getProducto();
				}
			}
			System.out.println("Producto menos vendido: \n" + aux);
			System.out.println("Cantidad total: " + cantidadMenor);
		}
		
		public List<NotaPedido> traerNotasPedidos(){
			List<NotaPedido> listNPAux = new ArrayList<>();
			
			for(Pedido p : traerPedido()) {
				for(NotaPedido np : p.getNotasPedidos()) {
					listNPAux.add(np);
				}	
			}
			return listNPAux;
		}
		
//CU: 4		
		public List<NotaPedido> traerPedidosPrevioA(LocalDate fecha) {
			List<NotaPedido> aux = new ArrayList<>();
			
			for(NotaPedido np : traerNotasPedidos()) {
				if(np.getFecha().isBefore(fecha)) {
					aux.add(np);
				}
			}
			return aux;
		}
		
		public List<Producto> traerPedidoDeCliente(String cliente) {
			List<Producto> listAux = new ArrayList<>();
			
			for(Pedido p : traerPedido()) {
				for(NotaPedido np : p.getNotasPedidos()) {
					if(np.getCliente().equals(cliente)) {
						listAux.add(p.getProducto());
					}
				}
			}
			return listAux;
		}
		
		
//Dado un cliente, listar sus compras con su fecha correspondiente		
		public List<NotaPedido> traerPedidoPorCliente(String cliente) {
			List<NotaPedido> aux = new ArrayList<>();
			
			
			for(NotaPedido np : traerNotasPedidos()) {
				if(np.getCliente().equals(cliente)) {
					aux.add(np);
				}
			}
			
			System.out.println(traerPedidoDeCliente(cliente));
			
			return aux;
		}
		
		
		
		
		
		
		
		
}
