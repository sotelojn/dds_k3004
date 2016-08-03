package dominio;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class HorarioAtencion {
	
	private DayOfWeek dia;
	private double horaInicio;
	private double horaFin;
	
	DayOfWeek lunes = DayOfWeek.MONDAY;
	DayOfWeek martes = DayOfWeek.TUESDAY;
	DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	DayOfWeek jueves = DayOfWeek.THURSDAY;
	DayOfWeek viernes = DayOfWeek.FRIDAY;
	DayOfWeek sabado = DayOfWeek.SATURDAY;
	DayOfWeek domingo = DayOfWeek.SUNDAY;
	
	public HorarioAtencion(DayOfWeek diaNuevo, double horarioInicio, double horarioFin){
		this.setDia(diaNuevo);
		this.setHoraInicio(horarioInicio);
		this.setHoraFin(horarioFin);
	}
	
	public HorarioAtencion() {
	}

	public List<HorarioAtencion> generarHorariosParaTodaLaSemana(double horarioInicio, double horarioFin){
		
		List<HorarioAtencion> listaDeDias = new ArrayList<HorarioAtencion>();
		
		HorarioAtencion dia1 = new HorarioAtencion(lunes, horarioInicio, horarioFin);
		HorarioAtencion dia2 = new HorarioAtencion(martes, horarioInicio, horarioFin);
		HorarioAtencion dia3 = new HorarioAtencion(miercoles, horarioInicio, horarioFin);
		HorarioAtencion dia4 = new HorarioAtencion(jueves, horarioInicio, horarioFin);
		HorarioAtencion dia5 = new HorarioAtencion(viernes, horarioInicio, horarioFin);
		
		listaDeDias.add(dia1);
		listaDeDias.add(dia2);
		listaDeDias.add(dia3);
		listaDeDias.add(dia4);
		listaDeDias.add(dia5);
		
		return listaDeDias;
	}
	
	public DayOfWeek getDia() {
		return dia;
	}
	
	public double getHoraInicio() {
		return horaInicio;
	}
	
	public double getHoraFin(){
		return horaFin;
	}
	
	public void setDia(DayOfWeek dia) {
		this.dia = dia;
	}
	
	public void setHoraInicio(double hora){
		this.horaInicio = hora;
	}
	
	public void setHoraFin(double hora){
		this.horaFin = hora;
	}
		
	public Boolean  disponible(double hora) {
		return ( (this.getHoraInicio() < hora) && (this.getHoraFin() > hora));
	}


	public Boolean estaDisponibleElHorario(double hora, DayOfWeek dia2) {
		if (dia == dia2){
			return (this.disponible(hora));
		}
		return false;
		}

	public boolean esHorarioValido() {

		return (horaInicio >= 0 && horaInicio <= 24) && 
				(horaFin >= 0 && horaFin <= 24);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorarioAtencion other = (HorarioAtencion) obj;
		if (dia != other.dia)
			return false;
		if (!dia.equals(other.dia))
			return false;
		if (Double.doubleToLongBits(horaFin) != Double.doubleToLongBits(other.horaFin))
			return false;
		if (Double.doubleToLongBits(horaInicio) != Double.doubleToLongBits(other.horaInicio))
			return false;
		return true;
	}

	

}
