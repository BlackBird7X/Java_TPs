package Modelo;

public class Producto {
	private int idProducto;
	private String codigo;
	private String nombre;
	private double precio;
	private int tipo;
	
	public Producto(int idProducto, String codigo, String nombre, double precio, int tipo) throws Exception{
		super();
		this.idProducto = idProducto;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) throws Exception {
			this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Producto: [idProducto=" + idProducto + ", codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + "]\n";
	}
}
