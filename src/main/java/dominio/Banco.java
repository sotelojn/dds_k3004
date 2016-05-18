package dominio;

import java.time.LocalTime;
import java.util.Set;

public class Banco extends POI {
	
	private Set<Dia> diasDeAtencion;
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
	


	public boolean estaDisponibleEnHorario(LocalTime hora){
		return  this.getHorarioApertura().isBefore(hora) && this.getHorarioCierre().isAfter(hora);
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


}
