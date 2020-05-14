
package race.control;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Control {
	final static String PATHRACEHISTORY = "RaceHistory.txt";
	static ArrayList <Carrera> listaCarreras = new ArrayList<Carrera>();
	public static void  a�adirCoches() throws IOException{

		String marca="a",modelo="m";
		Scanner e = new Scanner(System.in);
		System.out.println("Introduce nombre Garaje: ");
		marca = e.nextLine();
		String archivo = "Escuderias\\"+marca.concat(".txt");
		File f = new File(archivo);	
		FileOutputStream fos = new FileOutputStream(f,true);	
		ObjectOutputStream obs = new ObjectOutputStream(fos);
		while(!modelo.equalsIgnoreCase("fin")) {
			System.out.println("Modelo:");
			modelo = e.nextLine();
			if(!modelo.equals("fin")) {
				Coche c = new Coche(marca,modelo,100);
				obs.writeObject(c);
			}
		}
		obs.close();
		fos.close();
	}
	public static void  a�adirCarreras() throws IOException, ClassNotFoundException{
		String nombre="a";
		Scanner e = new Scanner(System.in);
		System.out.println("Introduce nombre Carrera: ");
		nombre = e.nextLine();
		String archivo = "Carreras\\"+nombre.concat(".txt");
		File f = new File(archivo);	
		FileOutputStream fos = new FileOutputStream(f,true);	
		ObjectOutputStream obs = new ObjectOutputStream(fos);
				Carrera c = new Carrera(nombre);
				obs.writeObject(c);
		obs.close();
		fos.close();
		listaCarreras = cargarCarreras();
	}
	public static Garaje elegirGaraje() throws ClassNotFoundException, IOException{
		
				File archivoEscuderias = new File("Escuderias");
				File f1[] = archivoEscuderias.listFiles();
				Scanner e = new Scanner(System.in);
				int i = 1;
				for(File f2: f1) {
					System.out.println(i+". "+f2.getName().substring(0, f2.getName().length()-4));
					i++;
				}
				System.out.println(i+". "+"Inter-Escuderias");
				do {
				System.out.println("Elige tu escuder�a!");
				try{
				i=e.nextInt();	}catch(InputMismatchException ime) {i=Integer.MIN_VALUE; e.next();}
				
				}while(i<1 || i>f1.length+1);
				ArrayList<Coche> lista =new ArrayList<Coche>();
				Garaje g = new Garaje("",lista);
				if(i==f1.length+1)
					g.llenarGarajeAleatorio();
				else
					g.llenarGaraje(f1[i-1]);
				
				return g;
	}
	public static void carrera(ArrayList <Carrera> listaCarreras, String tipo) throws ClassNotFoundException, IOException {
		Scanner e = new Scanner(System.in);
		Garaje g = elegirGaraje();
		ArrayList<Coche> podium =new ArrayList<Coche>();
		int i=1;
		for(Carrera c : listaCarreras) {
			System.out.println(i+". "+c.getNombre());
			i++;
		}
		System.out.println("Elige tu carrrera!");
		i = e.nextInt();		
		if(tipo.equals("Eliminatorio")) {
		MotorEliminatorio me = new MotorEliminatorio(listaCarreras.get(i-1),g.getListaCoches());
		podium = me.simularCarrera();
		System.out.println("La carrera eliminatoria "+me.getCarrera().getNombre()+" ha terminado!");
		}
		else {
		MotorEstandard me = new MotorEstandard(listaCarreras.get(i-1),g.getListaCoches());
		podium = me.simularCarrera();
		System.out.println("La carrera estandard "+me.getCarrera().getNombre()+" ha terminado!");
		}		
		File history = new File(PATHRACEHISTORY);
		FileWriter fw = new FileWriter(history,true);
		LocalDateTime date = LocalDateTime.now();
		fw.write(date.toString()+"\n");
		String st;
		if(tipo.equals("Eliminatorio"))
			st="Eliminatoria";
		else
			st="Estandard";
		fw.write(listaCarreras.get(i-1).getNombre()+"-- "+st+"\n");
		i=1;
		for(Coche c: podium) {
			System.out.println(i+". "+c.imprimir());
			fw.write(i+". "+c.imprimir()+"\n");
			i++;
		}
		fw.close();
	}
	public static int menu() {
		int opt = 0;
		Scanner e = new Scanner(System.in);
		do {
		System.out.println("1.Carrera eliminatoria");
		System.out.println("2.Carrera estandard");
		System.out.println("3.Torneo eliminatorio");
		System.out.println("4.Torneo Estandard");
		System.out.println("5.A�adir Carreras");
		System.out.println("6.A�adir Escuderia");
		System.out.println("7.Historico de Carreras");
		System.out.println("8.fin");
		System.out.println("Opcion: ");
		opt = e.nextInt();
		}while(opt<1 || opt>8);
		return opt;
	}
	public static void torneo(String tipo) {
		
	}
	public static ArrayList <Carrera> cargarCarreras() throws ClassNotFoundException, IOException {
		listaCarreras.clear();
		File fc = new File("Carreras");
		File fc1[] = fc.listFiles();
		for(File f : fc1) {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream obs = new ObjectInputStream(fis);
			listaCarreras.add((Carrera)obs.readObject());
			fis.close();
			obs.close();
		}
		return listaCarreras;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
			
			listaCarreras = cargarCarreras();
			int opt =0;
			do {
			switch(opt=menu()) {
			case 1:carrera(listaCarreras,"Eliminatorio"); break;
			case 2: carrera(listaCarreras,"Estandard"); break;
			case 3: 
				Torneo te = new Torneo(listaCarreras,"Eliminatorio");
				te.organizarTorneo();
				te.lanzarTorneo(elegirGaraje());
				break;
			case 4: 
				Torneo ts = new Torneo(listaCarreras,"Estandard");
				ts.organizarTorneo();
				ts.lanzarTorneo(elegirGaraje());			
				break;
			case 5: a�adirCarreras();break;
			case 6: a�adirCoches(); break;
			case 7: File historial = new File(PATHRACEHISTORY);
					Desktop ds = Desktop.getDesktop();
					ds.open(historial);
					break;
			default: System.out.println("Fin del programa"); break;
			}
		}while(opt!=8);
	

	}
}