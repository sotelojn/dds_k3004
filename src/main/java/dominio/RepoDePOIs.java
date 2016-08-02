package dominio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RepoDePOIs {

	private List<POI> pois;
	public RepoDePOIs ()
	{
		pois = new ArrayList<POI>();
	}
	public int cantEnLista()
	{
		int cantidad = this.pois.size();
		return cantidad;
	}
	public void agregarBanco(String nombre, Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Set<Dia> diasDeAtencion) {
		Banco nuevoBanco;
		nuevoBanco = new Banco(nombre, lat, lon);
		nuevoBanco.setCalle(calle);
		nuevoBanco.setAltura(altura);
		nuevoBanco.setDescripcion(descripcion);
		nuevoBanco.setTags(tags);
		nuevoBanco.setHorarioApertura(horarioApertura);
		nuevoBanco.setHorarioCierre(horarioCierre);
		nuevoBanco.setDiasDeAtencion(diasDeAtencion);
		this.pois.add(nuevoBanco);
	}
	
	public void agregarCGP (String nombre, Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Comuna comuna, Servicio servicio, Set<Servicio> servicios) {
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
		this.pois.add(nuevoCGP);
		
	}
	
	public POI agregarComercio (String nombre,  Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Rubro rubro, Set<HorarioDeAtencion> horarios, Set<Dia> diasDeAtencion) {
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
	
	public void agregarParadaColectivo (String nombre,  Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre) {
		ParadaColectivo nuevaParadaColectivo = new ParadaColectivo(nombre, lat, lon);
		nuevaParadaColectivo.setCalle(calle);
		nuevaParadaColectivo.setAltura(altura);
		nuevaParadaColectivo.setDescripcion(descripcion);
		nuevaParadaColectivo.setTags(tags);
		nuevaParadaColectivo.setHorarioApertura(horarioApertura);
		nuevaParadaColectivo.setHorarioCierre(horarioCierre);
		this.pois.add(nuevaParadaColectivo);
	}
	

	public boolean existePOI ( String nombre) {
		for (POI poi : pois) {
			if (poi.getNombre() == nombre) {
	        	return true;
     }
}
    return false;
}
	public void borrarPOI (String nombre) {
		for (POI poi : pois) {
	        if (poi.getNombre().contains(nombre))  {
	        	this.pois.remove(poi);
	     }
		}
	}
//		return pois.contains(poi.getNombre(nombre));
//	}
}
