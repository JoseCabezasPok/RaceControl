package race.control;

public class CocheCorriendo extends Coche{
	private int velocidad;
	private int distanciaRecorrida;
	private int puntuacion=0;
	public CocheCorriendo(String marca, String modelo, int velocidadMax, int velocidad, int distancia) {
		super(marca, modelo, velocidadMax);
		this.velocidad = velocidad;
		this.distanciaRecorrida = distancia;
	}


	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public void acelerar() {
		if(this.velocidad<this.getVelocidadMax()) {
		this.velocidad = this.velocidad+10;		
		}
	}
	public void frenar() {
		if (this.velocidad>0) {
			this.velocidad = this.velocidad-10;
		}
	}
	public int getDistanciaRecorrida() {
		return distanciaRecorrida;
	}
	public void setDistanciaRecorrida(int distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}


	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}
