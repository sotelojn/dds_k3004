package dominio.POI.cgp;

import java.time.DayOfWeek;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna {
	
	public Polygon zona;
	public Point unPunto;
	private int numeroComuna;
	private List<Servicio> servicios;
	
	public Comuna(int numero, Polygon zona, List<Servicio> servicio){
		this.setNumeroComuna(numero);
		this.setZona(zona);
		this.setServicios(servicio);
	}

	public int getNumeroComuna() {
		return numeroComuna;
	}
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	
	public Polygon getZona() {
		return zona;
	}

	public void setNumeroComuna(int numeroComuna) {
		this.numeroComuna = numeroComuna;
	}
	
	public void setZona(Polygon zona) {
		this.zona = zona;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Boolean estaDisponibleElServicio (double hora, String nombreServicio, DayOfWeek dia) {
		
		return (servicios.stream().anyMatch(servicio -> 
		servicio.estaDisponibleElServicio(nombreServicio, hora, dia)));
		
	}

	public boolean comunaValida() {
		return this.esComunaValida() && 
				this.getServicios().stream().allMatch(unServicio -> unServicio.esValido() ) ;
	}

	public boolean esComunaValida(){
		return this.numeroComuna > 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comuna other = (Comuna) obj;
		if (numeroComuna != other.numeroComuna)
			return false;
		if (servicios == null) {
			if (other.servicios != null)
				return false;
		} else if (!other.getServicios().stream().allMatch(unServicio ->
			servicios.stream().anyMatch(otroServicio -> 
			unServicio.equals(otroServicio))) )
			return false;
		if (unPunto == null) {
			if (other.unPunto != null)
				return false;
		} else if (!unPunto.equals(other.unPunto))
			return false;
		if (zona == null) {
			if (other.zona != null)
				return false;
		} else if (!zona.equals(other.zona))
			return false;
		return true;
	}

	
	
}
