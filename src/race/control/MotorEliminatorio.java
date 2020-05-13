package race.control; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MotorEliminatorio extends Motor{
	public MotorEliminatorio(Carrera carrera, ArrayList<Coche> listaCoches) {
		super(carrera, listaCoches);
	}
	@Override
	public ArrayList<CocheCorriendo> simulacion(ArrayList<CocheCorriendo> c) {
		ArrayList<CocheCorriendo> podium = new ArrayList<CocheCorriendo>();
		Random random = new Random();
		boolean acelerar;
		int vueltas = c.size()-1;
		for(int i =0; i<vueltas;i++) {
			for(int j=0;j<5;j++) {
				for(CocheCorriendo cc : c) {
					acelerar = random.nextBoolean();
					
					if(acelerar) {
						cc.acelerar();}
					else {
						cc.frenar();}
					cc.setDistanciaRecorrida(cc.getDistanciaRecorrida()+cc.getVelocidad());
				}
			}
			Collections.sort(c,this.getCompararDistancia());
			if (c.size()<=4) {
				podium.add(c.get(c.size()-1));
			}
			c.remove(c.size()-1);
		}
		Collections.reverse(podium);
		return podium;
	}

}
