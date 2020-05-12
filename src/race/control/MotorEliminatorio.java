package race.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MotorEliminatorio {
	
	private Carrera carrera;
	private ArrayList<Coche> listaCoches;
	public MotorEliminatorio(Carrera carrera, ArrayList<Coche> listaCoches) {
		super();
		this.carrera = carrera;
		this.listaCoches = listaCoches;
	}
	public ArrayList<Coche> simularCarrera(){
		ArrayList<Coche> podium = new ArrayList<Coche>();
		ArrayList<CocheCorriendo> parrilla= new ArrayList<CocheCorriendo>();
		Random random = new Random();
		boolean acelerar;
		int vueltas = this.listaCoches.size()-1;

		for(Coche coche: this.listaCoches) {
			CocheCorriendo cc = new CocheCorriendo(coche.getMarca(),coche.getModelo(),coche.getVelocidadMax(),10,0);
			parrilla.add(cc);
		}
		for(int i =0; i<vueltas;i++) {
			for(int j=0;j<5;j++) {
				for(CocheCorriendo cc : parrilla) {
					acelerar = random.nextBoolean();
					
					if(acelerar) {
						cc.acelerar();}
					else {
						cc.frenar();}
					cc.setDistanciaRecorrida(cc.getDistanciaRecorrida()+cc.getVelocidad());
				}
			}
			Collections.sort(parrilla);
			if (parrilla.size()<=4) {
				podium.add(parrilla.get(parrilla.size()-1));
			}
			parrilla.remove(parrilla.size()-1);
		}
		Collections.reverse(podium);
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
