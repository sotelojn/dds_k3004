package dominio.POI.cgp;

import java.time.DayOfWeek;

import org.uqbar.geodds.Polygon;

import dominio.Coordenada;
import dominio.Direccion;
import dominio.PuntoDeInteres;
 

public class CGP extends PuntoDeInteres {
	
	private Comuna comuna;
	
	public CGP() {
	}
	
	public CGP (Direccion unaDireccion, Coordenada unaCoordenada, String unNombre, Comuna unaComuna){
		super(unaDireccion, "CGP", unNombre, unaCoordenada);
		this.setComuna(unaComuna);
	}
	
	public CGP(Direccion unaDireccion, String string, String unNombre, Coordenada unaCoordenada) {
	}

	@Override
	public boolean esValido() {
		return this.tieneAtributosDePOIValidos() && this.getComuna().comunaValida() ;
	}
	
	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
	
	public Comuna getComuna() {
		return comuna;
	}
	
	@Override
	public boolean estaCercaDe(Coordenada unaCoordenada) {
		Polygon zona=this.comuna.getZona();
		return zona.isInside(unaCoordenada);
	}
	
	public boolean contenesConsulta (String textoLibre){
		return (String.valueOf(this.comuna.getNumeroComuna())).equals(textoLibre) ||
			(this.getComuna().getServicios()).stream().anyMatch(unServicio -> 
			unServicio.getTipoServicio().equals(textoLibre));
		}


	public Boolean estaDisponible (double hora,String nombreServicio, DayOfWeek dia){
		return comuna.estaDisponibleElServicio(hora, nombreServicio, dia);
	}
	
	public Boolean estaDisponible (double hora, DayOfWeek dia){
		return comuna.estaDisponibleElServicio(hora, null, dia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		CGP other = (CGP) obj;
		if (comuna == null) {
			if (other.comuna != null)
				return false;
		} 
		if (!comuna.equals(other.comuna))
			return false;
		return true;
	}
	
	
	
}
