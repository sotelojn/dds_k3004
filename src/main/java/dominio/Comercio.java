package dominio;

public class Comercio extends POI {
	
	private Rubro rubro;
	
	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Comercio(String nombre, Double latitud, Double longitud) {
		super(nombre, latitud, longitud);
		
	}
	
	public Comercio(String nombre, Posicion posicion) {
		super(nombre, posicion);
	}
	
	public boolean estaCerca(Posicion posicionActual){
		return estaAMenosDe(posicionActual, rubro.getRangoCercania());
	}
}
