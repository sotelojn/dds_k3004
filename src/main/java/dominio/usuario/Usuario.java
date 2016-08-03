package dominio.usuario;

import java.util.List;

import org.joda.time.DateTime;

import dominio.PuntoDeInteres;

public interface Usuario{
	
	public List<PuntoDeInteres> realizarBuquedaDePOI(String textoLibre, DateTime tiempoDeBusqueda);
}
