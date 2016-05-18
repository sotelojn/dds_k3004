package dominio;

import java.time.LocalTime;
import java.util.Set;

public class Comercio extends POI {
	
	private Rubro rubro;
	public static Set<DiaDeAtencion> horarios;
	private Set<DiaDeAtencion> dias;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	
	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
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
	
	public Set<DiaDeAtencion> getDias() {
		return dias;
	}
	public void setDias(Set<DiaDeAtencion> dias) {
		this.dias = dias;
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
		return  this.getHorarioApertura().isAfter(hora) && this.getHorarioCierre().isBefore(hora);
	}
	
	public boolean estaDisponible(DiaDeAtencion dia, LocalTime hora){
		return this.getDias().contains(dia) && this.estaDisponibleEnHorario(hora);
	}
}
