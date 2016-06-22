package main.java.dominio;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Stream;

public class RepoDePOIs {
	public static Set<POI> pois;
	
	public static Stream<POI> buscar (String text) {
		return pois.stream()
				   .filter(poi -> poi.matches(text));
	}
	
	
	public static POI agregarBanco (String nombre, Posicion posicion, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Set<Dia> diasDeAtencion) {
		Banco nuevoBanco;
		nuevoBanco = new Banco(nombre, posicion);
		nuevoBanco.setCalle(calle);
		nuevoBanco.setAltura(altura);
		nuevoBanco.setDescripcion(descripcion);
		nuevoBanco.setTags(tags);
		nuevoBanco.setHorarioApertura(horarioApertura);
		nuevoBanco.setHorarioCierre(horarioCierre);
		nuevoBanco.setDiasDeAtencion(diasDeAtencion);
		pois.add(nuevoBanco);
		return nuevoBanco;
		
	}
	
//	public static POI agregarCGP (String nombre, Posicion posicion, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Servicio servicio, Set<Servicio> servicios) {
//		CGP nuevoCGP;
//		nuevoCGP = new CGP(nombre, posicion);
		
//	}
	
	public static void borrarPOI (POI nombre) {
		pois.remove(nombre);
	}
}