package race.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Torneo {	
	private ArrayList<Carrera> listaCarreras;
	String tipo;
	public Torneo(ArrayList<Carrera> listaCarreras,String tipo) {
		this.tipo = tipo;
		this.listaCarreras = listaCarreras;
	}
	public void organizarTorneo() {
		if(this.listaCarreras.size()<10) {
			int bucle = 10-this.listaCarreras.size();
			for(int i =0;i<bucle;i++) {
				this.listaCarreras.add(this.listaCarreras.get(i));
			}			
		}
		else {
			Collections.shuffle(this.listaCarreras);
			ArrayList<Carrera> aux = new ArrayList<Carrera>();
			for(int i =0;i<10;i++) {
				aux.add(this.listaCarreras.get(i));
			}
			this.listaCarreras.clear();
			this.listaCarreras = aux;
		}
	}
	public ArrayList<Carrera> getListaCarreras() {
		return listaCarreras;
	}

	public void setListaCarreras(ArrayList<Carrera> listaCarreras) {
		this.listaCarreras = listaCarreras;
	}
	public void lanzarTorneo(Garaje g) throws IOException {
		ArrayList<CocheCorriendo> parrilla = new ArrayList<CocheCorriendo>();
		ArrayList<Coche> podium = new ArrayList<Coche>();
		for(Coche c : g.getListaCoches()) {
			CocheCorriendo cc = new CocheCorriendo(c.getMarca(),c.getModelo(),c.getVelocidadMax(),10,0);
			parrilla.add(cc);
		}
		System.out.println("Torneo "+this.tipo);
		for(Carrera r : this.listaCarreras) {
			System.out.println("Bienvenido a "+r.getNombre());
			Motor me;
			if(this.tipo.equals("Eliminatorio")) {
				 me = new MotorEliminatorio(r,g.getListaCoches());}
			else {
				me = new MotorEstandard(r,g.getListaCoches());}
				podium = me.simularCarrera();
				int i = 1;
				for(Coche c :podium) {
					for(CocheCorriendo cc : parrilla) {
						if(cc.getMarca().equals(c.getMarca()) && cc.getModelo().equals(c.getModelo())) {
							cc.setPuntuacion(cc.getPuntuacion()+3-podium.indexOf(c));
						}
					}
				System.out.println(i+"."+c.imprimir());
				i++;
				}
		}
		System.out.println("Torneo finalizado!");
		File f = new File("tourneyHistory.txt");
		FileWriter fw = new FileWriter(f,true);
		LocalDateTime date = LocalDateTime.now();
		fw.write("Torneo "+this.tipo+" "+date.toString()+"\n");
		int i = 1;
		Comparator<CocheCorriendo> compararPuntuacion = new Comparator<CocheCorriendo>() {
			@Override
			public int compare(CocheCorriendo o1, CocheCorriendo o2) {			
				return o1.getPuntuacion()-o2.getPuntuacion();
			}		
		};
		Collections.sort(parrilla, compararPuntuacion);
		Collections.reverse(parrilla);
		for(CocheCorriendo c : parrilla) {
			System.out.println(i+"."+c.imprimir()+" "+c.getPuntuacion());
			fw.write(i+"."+c.imprimir()+" "+c.getPuntuacion()+"\n");
			i++;
		}
		fw.close();
	}
}
