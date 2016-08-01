package ar.edu.utn.d2s.acciones;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.utn.d2s.PuntoDeInteres;
import ar.edu.utn.d2s.usuario.UsuarioTerminal;

public abstract class AccionesDeUsuario {
	
	private Boolean permiso;
	
	public AccionesDeUsuario(Boolean permiso) {
		this.setPermiso(permiso);
	}

	public abstract void aplicarAccion(DateTime tiempoI, DateTime tiempoF, String textoLibre,
			List<PuntoDeInteres> listaDePdisBuscados, UsuarioTerminal unUsuario, int tiempoDeBusqueda);
	
	public void puedeAplicarAccion(DateTime tiempoI, DateTime tiempoF,
			String textoLibre, List<PuntoDeInteres> listaDePdisBuscados, UsuarioTerminal unUsuario, int tiempoDeBusqueda){
		
		if ( this.getPermiso() ){
			this.aplicarAccion(tiempoI, tiempoF, textoLibre, listaDePdisBuscados, unUsuario, tiempoDeBusqueda);
			
		}
	}

	public Boolean getPermiso() {
		return permiso;
	}

	public void setPermiso(Boolean permiso) {
		this.permiso = permiso;
	}

	

	
	
} 
