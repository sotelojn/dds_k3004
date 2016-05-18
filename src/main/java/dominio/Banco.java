package dominio;

public class Banco extends POI {
	
	public Banco(String nombre, Double latitud, Double longitud) {
		super(nombre, latitud, longitud);
	}
	
	public Banco(String nombre, Posicion posicion) {
		super(nombre, posicion);
	}

}
