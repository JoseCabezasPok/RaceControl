package race.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MotorEstandard extends Motor{


	public MotorEstandard(Carrera carrera, ArrayList<Coche> listaCoches) {
		super(carrera, listaCoches);
	}
	@Override
	public ArrayList<CocheCorriendo> simulacion(ArrayList<CocheCorriendo> c) {
		Random random = new Random();
		boolean acelerar;	
		//Simulamos una carrera de 180 minutos en la que los coches aceleran/ frenan cada 10 minutos
		for(int i=0;i<18;i++) {			
					for(CocheCorriendo coche: c) {
							acelerar = random.nextBoolean();
					if(acelerar)
							coche.acelerar();
						else
								coche.frenar();
						coche.setDistanciaRecorrida(coche.getDistanciaRecorrida()+coche.getVelocidad());
						}
		}
		Collections.sort(c,this.getCompararDistancia());
		return c;
	}
	
}
