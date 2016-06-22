package main.java.dominio;

import java.util.Set;
import java.util.stream.Stream;

public class RepoDePOIs {
	public static Set<POI> pois;
	
	public static Stream<POI> buscar (String text) {
		return pois.stream()
				   .filter(poi -> poi.matches(text));
	}
	
	
	public static void agregarPOI (POI nombre) {
		pois.add(nombre);
	}
	
	public static void borrarPOI (POI nombre) {
		pois.remove(nombre);
	}
}