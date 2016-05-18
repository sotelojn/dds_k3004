package dominio;

import java.time.LocalTime;
import java.util.Set;

public class Banco extends POI {
	
	private Set<DiaDeAtencion> dias;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	
	public Banco(String nombre, Double latitud, Double longitud) {
		super(nombre, latitud, longitud);
	}
	
	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}
	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}
	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}
	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}
	
	public Banco(String nombre, Posicion posicion) {
		super(nombre, posicion);
	}
	
	public Set<DiaDeAtencion> getDias() {
		return dias;
	}

	public void setDias(Set<DiaDeAtencion> dias) {
		this.dias = dias;
	}


	public boolean estaDisponibleEnHorario(LocalTime hora){
		return  this.getHorarioApertura().isAfter(hora) && this.getHorarioCierre().isBefore(hora);
	}
	
	public boolean estaDisponible(DiaDeAtencion dia, LocalTime hora){
		return this.getDias().contains(dia) && this.estaDisponibleEnHorario(hora);
	}


}
