package dominio.POI.cgp;

import java.time.DayOfWeek;
import java.util.List;

import dominio.HorarioAtencion;

public class Servicio {

	private String tipoServicio;
	private List<HorarioAtencion> horarios;
	
	public Servicio() {
	}
	
	public Servicio(String nombre, List<HorarioAtencion> horariosAAgregar){
		this.tipoServicio = nombre;
		this.horarios = horariosAAgregar;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}
	
	public List<HorarioAtencion> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioAtencion> horarios) {
		this.horarios = horarios;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}


	public Boolean estaDisponibleElServicio(String nombreServicio, double hora, DayOfWeek dia) {
		
		return (nombreServicio == null ||
				nombreServicio.equals(this.tipoServicio) || 
				nombreServicio.equals("")
				)
				&& (horarios.stream().anyMatch(horario -> 
			horario.estaDisponibleElHorario(hora, dia) ) );
	}
		

	public boolean esValido() {
		
		return tipoServicio.length() > 0 && this.getHorarios().stream().allMatch(
				unHorario -> unHorario.esHorarioValido() );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horarios == null) ? 0 : horarios.hashCode());
		result = prime * result + ((tipoServicio == null) ? 0 : tipoServicio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Servicio unServicio = (Servicio) obj;
		
		if (tipoServicio.equals(unServicio.getTipoServicio())) {
			
			if (unServicio.getHorarios().stream().allMatch(otroTipoServicio -> 
			horarios.stream().anyMatch(unHorario -> unHorario.equals(otroTipoServicio))))
				return true;	
		else return false;
		
		}else return true;
		
	}


	
}
