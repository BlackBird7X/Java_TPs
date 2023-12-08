package Modelo;

import java.time.LocalDate;

public class Lote {
	private LocalDate fecha;
	private int cantInicial;
	private int cantExistente;
	
	public Lote(LocalDate fecha, int cantInicial, int cantExistente) {
		super();
		this.fecha = fecha;
		this.cantInicial = cantInicial;
		this.cantExistente = cantExistente;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int getCantInicial() {
		return cantInicial;
	}
	public void setCantInicial(int cantInicial) {
		this.cantInicial = cantInicial;
	}
	public int getCantExistente() {
		return cantExistente;
	}
	public void setCantExistente(int cantExistente) {
		this.cantExistente = cantExistente;
	}
	
	@Override
	public String toString() {
		return "Lote: [fecha=" + fecha + ", cantInicial=" + cantInicial + ", cantExistente=" + cantExistente + "]";
	}
}
