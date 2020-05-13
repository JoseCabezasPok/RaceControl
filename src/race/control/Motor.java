package race.control;

import java.util.ArrayList;
import java.util.Comparator;
public abstract class Motor {
	private Carrera carrera;
	private ArrayList<Coche> listaCoches;
	private Comparator<CocheCorriendo> compararDistancia = new Comparator<CocheCorriendo>() {
		@Override
		public int compare(CocheCorriendo o1, CocheCorriendo o2) {			
			return o1.getDistanciaRecorrida()-o2.getDistanciaRecorrida();
		}		
	};
	public Motor(Carrera carrera, ArrayList<Coche> listaCoches) {
		super();
		this.carrera = carrera;
		this.listaCoches = listaCoches;
	}
	public ArrayList<Coche> simularCarrera(){
		ArrayList<Coche> podium = new ArrayList<Coche>();
		ArrayList<CocheCorriendo> parrilla= new ArrayList<CocheCorriendo>();
		for(Coche coche: this.listaCoches) {
			CocheCorriendo cc = new CocheCorriendo(coche.getMarca(),coche.getModelo(),coche.getVelocidadMax(),10,0);
			parrilla.add(cc);
		}		
		parrilla = this.simulacion(parrilla);
		for(int i = 0;i<3;i++) {
			podium.add(parrilla.get(i));
		}
		return podium;		
	}
	public abstract ArrayList<CocheCorriendo> simulacion(ArrayList<CocheCorriendo> c);
	
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	public ArrayList<Coche> getListaCoches() {
		return listaCoches;
	}
	public void setListaCoches(ArrayList<Coche> listaCoches) {
		this.listaCoches = listaCoches;
	}
	public Comparator<CocheCorriendo> getCompararDistancia() {
		return compararDistancia;
	}
	public void setCompararDistancia(Comparator<CocheCorriendo> compararDistancia) {
		this.compararDistancia = compararDistancia;
	}
	
}
