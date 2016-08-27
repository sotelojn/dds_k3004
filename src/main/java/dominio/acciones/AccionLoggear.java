package dominio.acciones;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import dominio.PuntoDeInteres;
import dominio.Repositorio;
import dominio.usuario.Usuario;

public class AccionLoggear extends AccionesDeUsuario{

	private final static Logger log = Logger.getLogger(Repositorio.class);
	
	public AccionLoggear(Boolean permiso) {
		super(permiso);
	}

	@Override
	public void aplicarAccion(DateTime tiempoI, DateTime tiempoF, String textoLibre,
			List<PuntoDeInteres> listaDePOIsBuscados, Usuario unUsuario, int tiempoDeBusqueda) {
		 
		int tiempoDeConsulta = Seconds.secondsBetween(tiempoI, tiempoF).getSeconds();
		
		log.info("Nombre Buscado : " + textoLibre);
		log.info("Cantidad Resultados : " + (listaDePOIsBuscados.size()) );
		log.info("Tiempo de Busqueda : " + tiempoDeConsulta);
	}
}
