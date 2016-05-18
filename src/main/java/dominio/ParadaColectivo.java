package dominio;

import java.time.LocalTime;


public class ParadaColectivo extends POI {

	private final static int RANGO_DE_CERCANIA = 1;
	
	public ParadaColectivo(String nombre, Double latitud, Double longitud) {
		super(nombre, latitud, longitud);
	}

	public ParadaColectivo(String nombre, Posicion posicion) {
		super(nombre, posicion);
	}
	
	public boolean estaDisponible(Dia dia, LocalTime hora){
	return	true;
	}
	
}
