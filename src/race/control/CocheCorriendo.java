package race.control;

public class CocheCorriendo extends Coche implements Comparable<CocheCorriendo>{
	private int velocidad;
	private int distanciaRecorrida;
	
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
	@Override
	public int compareTo(CocheCorriendo o) {
		// TODO Auto-generated method stub
		return o.distanciaRecorrida-this.distanciaRecorrida;
	}

}
