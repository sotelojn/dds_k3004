package dominio;

public class CGP extends POI{
	
	private Comuna comuna;
	
	public CGP(String nombre, Double latitud, Double longitud) {
		super(nombre, latitud, longitud);
	}
	
	public CGP(String nombre, Posicion posicion) {
		super(nombre, posicion);
	}
	
	public boolean estaCerca(Posicion posicionActual){
		return comuna.estaAdentro(posicionActual);
	}
}
