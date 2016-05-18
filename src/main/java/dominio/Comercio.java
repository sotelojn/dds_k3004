package dominio;

import java.time.LocalTime;
import java.util.Set;

public class Comercio extends POI {
	
	private Rubro rubro;
	private Set<HorarioDeAtencion> horarios;
	private Set<Dia> diasDeAtencion;
	
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
	
	public boolean estaDisponibleEnHorario(LocalTime hora){
		boolean hora1 = false;
		for(HorarioDeAtencion i: horarios){
			if(i.getHorarioApertura().isAfter(hora) && i.getHorarioCierre().isBefore(hora)){
				hora1 = true;
			}
			else
				hora1 = false;
		}
		return hora1;
	}
	
	public boolean estaDisponible(Dia dia, LocalTime hora){
		return this.getDiasDeAtencion().contains(dia) && this.estaDisponibleEnHorario(hora);
	}

	public Set<Dia> getDiasDeAtencion() {
		return diasDeAtencion;
	}

	public void setDiasDeAtencion(Set<Dia> diasDeAtencion) {
		this.diasDeAtencion = diasDeAtencion;
	}

	public Set<HorarioDeAtencion> getHorarios() {
		return horarios;
	}

	public void setHorarios(Set<HorarioDeAtencion> horarios) {
		this.horarios = horarios;
	}
}
