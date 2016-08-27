package dominio.acciones;

import java.util.List;

import org.joda.time.DateTime;

import dominio.PuntoDeInteres;
import dominio.usuario.Usuario;

public abstract class AccionesDeUsuario {
	
	private Boolean permiso;
	
	public AccionesDeUsuario(Boolean permiso) {
		this.setPermiso(permiso);
	}

	public abstract void aplicarAccion(DateTime tiempoI, DateTime tiempoF, String textoLibre,
			List<PuntoDeInteres> listaDePOIsBuscados, Usuario unUsuario, int tiempoDeBusqueda);
	
	public void puedeAplicarAccion(DateTime tiempoI, DateTime tiempoF,
			String textoLibre, List<PuntoDeInteres> listaDePOIsBuscados, Usuario unUsuario, int tiempoDeBusqueda){
		
		if ( this.getPermiso() ){
			this.aplicarAccion(tiempoI, tiempoF, textoLibre, listaDePOIsBuscados, unUsuario, tiempoDeBusqueda);
			
		}
	}

	public Boolean getPermiso() {
		return permiso;
	}

	public void setPermiso(Boolean permiso) {
		this.permiso = permiso;
	}

	

	
	
} 
