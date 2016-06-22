package dominio;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Stream;

public class RepoDePOIs {
	public static Set<POI> pois;
	
	public static Stream<POI> buscar (String text) {
		return pois.stream()
				   .filter(poi -> poi.matches(text));
	}
	
	
	public static POI agregarBanco (String nombre, Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Set<Dia> diasDeAtencion) {
		Banco nuevoBanco;
		nuevoBanco = new Banco(nombre, lat, lon);
		nuevoBanco.setCalle(calle);
		nuevoBanco.setAltura(altura);
		nuevoBanco.setDescripcion(descripcion);
		nuevoBanco.setTags(tags);
		nuevoBanco.setHorarioApertura(horarioApertura);
		nuevoBanco.setHorarioCierre(horarioCierre);
		nuevoBanco.setDiasDeAtencion(diasDeAtencion);
		return nuevoBanco;
		
	}
	
	public static POI agregarCGP (String nombre, Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Comuna comuna, Servicio servicio, Set<Servicio> servicios) {
		CGP nuevoCGP;
		nuevoCGP = new CGP(nombre, lat, lon);
		nuevoCGP.setCalle(calle);
		nuevoCGP.setAltura(altura);
		nuevoCGP.setDescripcion(descripcion);
		nuevoCGP.setTags(tags);
		nuevoCGP.setHorarioApertura(horarioApertura);
		nuevoCGP.setHorarioCierre(horarioCierre);
		nuevoCGP.setComuna(comuna);
		nuevoCGP.setServicio(servicio);
		nuevoCGP.setServicios(servicios);
		return nuevoCGP;
	}
	
	public static POI agregarComercio (String nombre,  Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Rubro rubro, Set<HorarioDeAtencion> horarios, Set<Dia> diasDeAtencion) {
		Comercio nuevoComercio;
		nuevoComercio = new Comercio(nombre, lat, lon);
		nuevoComercio.setCalle(calle);
		nuevoComercio.setAltura(altura);
		nuevoComercio.setDescripcion(descripcion);
		nuevoComercio.setTags(tags);
		nuevoComercio.setHorarioApertura(horarioApertura);
		nuevoComercio.setHorarioCierre(horarioCierre);
		nuevoComercio.setRubro(rubro);
		nuevoComercio.setHorarios(horarios);
		nuevoComercio.setDiasDeAtencion(diasDeAtencion);
		return nuevoComercio;
	}
	
	public static POI agregarParadaColectivo (String nombre,  Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre) {
		ParadaColectivo nuevaParadaColectivo;
		nuevaParadaColectivo = new ParadaColectivo(nombre, lat, lon);
		nuevaParadaColectivo.setCalle(calle);
		nuevaParadaColectivo.setAltura(altura);
		nuevaParadaColectivo.setDescripcion(descripcion);
		nuevaParadaColectivo.setTags(tags);
		nuevaParadaColectivo.setHorarioApertura(horarioApertura);
		nuevaParadaColectivo.setHorarioCierre(horarioCierre);
		return nuevaParadaColectivo;
	}
	
	public static void borrarPOI (POI poi) {
		pois.remove(poi);
	}
	
	public static boolean existePOI (POI poi) {
		return pois.contains(poi);
	}
}