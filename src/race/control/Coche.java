package race.control;

import java.io.Serializable;

public class Coche implements Serializable{

	private String marca;
	private String modelo;
	private int velocidadMax= 100;
	
	public Coche(String marca, String modelo, int velocidadMax) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.velocidadMax = velocidadMax;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getVelocidadMax() {
		return velocidadMax;
	}
	public String imprimir() {
		String s = marca+" "+modelo;
		return s;
	}
	
}
