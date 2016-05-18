package dominio;

import java.util.Set;
import java.util.stream.Stream;

public class ConjuntoDePOIs {
	public static Set<POI> pois;
	
	public static Stream<POI> buscar (String text) {
		return pois.stream()
				   .filter(poi -> poi.matches(text));
	}
}
