package race.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

public class Garaje {

	private String escuderia;
	private ArrayList<Coche> listaCoches;	
	
	public String getEscuderia() {
		return escuderia;
	}
	public void setEscuderia(String escuderia) {
		this.escuderia = escuderia;
	}
	public ArrayList<Coche> getListaCoches() {
		return listaCoches;
	}
	public void setListaCoches(ArrayList<Coche> listaCoches) {
		this.listaCoches = listaCoches;
	}

	public Garaje(String escuderia, ArrayList<Coche> listaCoches) {
		super();
		this.escuderia = escuderia;
		this.listaCoches = listaCoches;
	}
	public void llenarGaraje(File f) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream obs = new ObjectInputStream(fis);
		Coche c = new Coche("","",0);
		try {
			while((c=(Coche)obs.readObject())!=null) {
				if(c!=null)
				this.listaCoches.add(c);
			}
		}catch(java.io.EOFException eof) {}
		fis.close();
		obs.close();
	}
	public void llenarGarajeAleatorio() throws IOException, ClassNotFoundException {
		File padre = new File("Escuderias");
		File escuderias  [] = padre.listFiles();
		for(File f : escuderias) {
		ArrayList<Coche> lista = new ArrayList<Coche>();
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream obs = new ObjectInputStream(fis);
		Coche c = new Coche("","",0);
		try {
			while((c=(Coche)obs.readObject())!=null) {
				if(c!=null)
				lista.add(c);
			}
		}catch(java.io.EOFException eof) {}
		fis.close();
		obs.close();
		Random r = new Random();
		this.listaCoches.add(lista.get(r.nextInt(lista.size()-1)));
		}
	}
}
