package race.control;

import java.io.Serializable;

public class Carrera implements Serializable{
	
		private String nombre;

		
		public Carrera(String nombre) {
			this.nombre = nombre;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

}
