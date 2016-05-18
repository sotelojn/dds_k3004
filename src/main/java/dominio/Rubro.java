package dominio;

import java.time.LocalTime;
import java.util.Set;

public class Rubro {
	
	private int rangoCercania;
	private String descripcion;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private Set<HorarioDeAtencion> dias;
	
	public int getRangoCercania() {
		return rangoCercania;
	}

	public void setRangoCercania(int rangoCercania) {
		this.rangoCercania = rangoCercania;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}

	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}

	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public Set<HorarioDeAtencion> getDias() {
		return dias;
	}

	public void setDias(Set<HorarioDeAtencion> dias) {
		this.dias = dias;
	}
	
	}
