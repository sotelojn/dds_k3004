package ar.edu.utn.d2s.acciones;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import ar.edu.utn.d2s.PuntoDeInteres;
import ar.edu.utn.d2s.Repositorio;
import ar.edu.utn.d2s.usuario.UsuarioTerminal;

public class AccionLoggear extends AccionesDeUsuario{

	private final static Logger log = Logger.getLogger(Repositorio.class);
	
	public AccionLoggear(Boolean permiso) {
		super(permiso);
	}

	@Override
	public void aplicarAccion(DateTime tiempoI, DateTime tiempoF, String textoLibre,
			List<PuntoDeInteres> listaDePdisBuscados, UsuarioTerminal unUsuario, int tiempoDeBusqueda) {
		 
		int tiempoDeConsulta = Seconds.secondsBetween(tiempoI, tiempoF).getSeconds();
		
		log.info("Nombre Buscado : " + textoLibre);
		log.info("Cantidad Resultados : " + (listaDePdisBuscados.size()) );
		log.info("Tiempo de Busqueda : " + tiempoDeConsulta);
	}
}
