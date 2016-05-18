package dominio;

import java.time.LocalTime;
import java.util.Set;

public class CGP extends POI {

	private Comuna comuna;
	private Servicio servicio;
	private Set<Servicio> servicios;

	public CGP(String nombre, Double latitud, Double longitud) {
		super(nombre, latitud, longitud);
	}

	public CGP(String nombre, Posicion posicion) {
		super(nombre, posicion);
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	public boolean estaCerca(Posicion posicionActual) {
		return comuna.estaAdentro(posicionActual);
	}

	public boolean estaDisponibleEnHorario(Servicio poi, LocalTime hora) {
		return poi.getHorarioApertura().isAfter(hora) && poi.getHorarioCierre().isBefore(hora);
	}

	public boolean estaDisponible(Servicio servicio1, HorarioDeAtencion dia, LocalTime hora) {
		return servicio1.getDias().contains(dia) && estaDisponibleEnHorario(servicio1, hora);
	}

	public boolean estaDisponible(HorarioDeAtencion dia, LocalTime hora) {
		return true;
	}

}
