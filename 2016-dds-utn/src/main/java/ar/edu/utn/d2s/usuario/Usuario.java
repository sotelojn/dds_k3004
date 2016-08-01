package ar.edu.utn.d2s.usuario;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.utn.d2s.PuntoDeInteres;

public interface Usuario{
	
	public List<PuntoDeInteres> realizarBuquedaDePdi(String textoLibre, DateTime tiempoDeBusqueda);
}
