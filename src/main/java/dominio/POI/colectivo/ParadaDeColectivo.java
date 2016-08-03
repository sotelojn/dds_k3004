package dominio.POI.colectivo;

import dominio.Coordenada;
import dominio.Direccion;

public class ParadaDeColectivo{
	
	private Direccion unaDireccion;
	private Coordenada ubicacion;
	
	public ParadaDeColectivo(Direccion direccion, Coordenada _ubicacion){
		this.setUbicacion(_ubicacion);
		this.setDireccion(direccion);
		
	}
	
	public void setDireccion(Direccion _direccion){
		this.setUnaDireccion(_direccion);
	}
	
	public void setUbicacion(Coordenada unaUbicacion){
		this.ubicacion = unaUbicacion;
	}
	
	public Coordenada getUbicacion(){
		return this.ubicacion;
	}
	
	public Coordenada dameCoordenada(){
		return this.ubicacion;
	}

	public Direccion getUnaDireccion() {
		return unaDireccion;
	}

	public void setUnaDireccion(Direccion unaDireccion) {
		this.unaDireccion = unaDireccion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParadaDeColectivo other = (ParadaDeColectivo) obj;
		if (ubicacion == null) {
			if (other.ubicacion != null)
				return false;
		} else if (!ubicacion.equals(other.ubicacion))
			return false;
		if (unaDireccion == null) {
			if (other.unaDireccion != null)
				return false;
		} else if (!unaDireccion.equals(other.unaDireccion))
			return false;
		return true;
	}
	
	
	
}
