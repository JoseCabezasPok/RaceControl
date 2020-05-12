package race.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MotorEstandard {

	private Carrera carrera;
	private ArrayList<Coche> listaCoches;
	
	public MotorEstandard(Carrera carrera, ArrayList<Coche> listaCoches) {
		super();
		this.carrera = carrera;
		this.listaCoches = listaCoches;
	}
	public ArrayList<Coche> simularCarrera(){
		ArrayList<Coche> podium = new ArrayList<Coche>();
		ArrayList<CocheCorriendo> parrilla= new ArrayList<CocheCorriendo>();
		Random random = new Random();
		boolean acelerar;
		for(Coche coche: this.listaCoches) {
			CocheCorriendo cc = new CocheCorriendo(coche.getMarca(),coche.getModelo(),coche.getVelocidadMax(),10,0);
			parrilla.add(cc);
		}
		
		//Simulamos una carrera de 180 minutos en la que los coches aceleran/ frenan cada 10 minutos
		for(int i=0;i<18;i++) {
			
			for(CocheCorriendo coche: parrilla) {
				acelerar = random.nextBoolean();
				if(acelerar)
					coche.acelerar();
				else
					coche.frenar();
				coche.setDistanciaRecorrida(coche.getDistanciaRecorrida()+coche.getVelocidad());
			}
		}
		Collections.sort(parrilla);
		for(int i = 0;i<3;i++) {
			podium.add(parrilla.get(i));
		}
		return podium;
		
	}
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
	
}
