package dominio;

import java.util.Set;

public abstract class POI {

	private String nombre;
	private String calle;
	private int altura;
	private String descripcion;
	private Set<String> tags;
	private Posicion posicion;
	private final static int RANGO_DE_CERCANIA = 5;
	
	public POI(String nombre, Posicion posicion) {
		super();
		this.nombre = nombre;
		this.posicion = posicion;
	}

	public POI(String nombre, Double  latitud, Double longitud) {
		super();
		this.nombre = nombre;
		this.posicion = new Posicion(latitud, longitud);
	}

	public boolean matches(String text) {
		return this.getNombre().contains(text) ||
			   this.getDescripcion().contains(text) ||
			   this.getTags().contains(text);
	}
	
	
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	public boolean esValido() {
		return posicion.esValido() && this.getNombre() != null;
	}

	public boolean estaAMenosDe(Posicion unaPosicion, int metros) {
		return posicion.distanciaA(unaPosicion) <= metros;
	}
	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public boolean estaCerca(Posicion posicionActual){
		return estaAMenosDe(posicionActual, getRangoDeCercania());
	}

	public static int getRangoDeCercania() {
		return RANGO_DE_CERCANIA;
	}
}
