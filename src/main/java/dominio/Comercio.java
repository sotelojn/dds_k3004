package dominio;

import java.time.LocalTime;
import java.util.Set;

public class Comercio extends POI {
	
	private Rubro rubro;
	public static Set<DiaDeAtencion> horarios;
	
	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}


	public Comercio(String nombre, Double latitud, Double longitud) {
		super(nombre, latitud, longitud);
		
	}
	
	public Comercio(String nombre, Posicion posicion) {
		super(nombre, posicion);
	}
	
	public boolean estaCerca(Posicion posicionActual){
		return estaAMenosDe(posicionActual, rubro.getRangoCercania());
	}
	
	public boolean estaDisponibleEnHorario(Rubro rubro1, LocalTime hora){
		return  rubro1.getHorarioApertura().isAfter(hora) && this.getHorarioCierre().isBefore(hora);
	}
	
	public boolean estaDisponible(DiaDeAtencion dia, LocalTime hora){
		return this.getDias().contains(dia) && this.estaDisponibleEnHorario(hora);
	}
}
