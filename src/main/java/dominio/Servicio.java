package dominio;

import java.time.LocalTime;
import java.util.Set;

public class Servicio{
	
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private Set<HorarioDeAtencion> dias;
	private String nombre;
	
	
	public Set<HorarioDeAtencion> getDias() {
		return dias;
	}
	public void setDias(Set<HorarioDeAtencion> dias) {
		this.dias = dias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	

	
}
