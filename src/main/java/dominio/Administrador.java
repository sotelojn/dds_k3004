package dominio;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Administrador extends Usuario {

private Set<Dia> setearDiasAtencionBancos()
	{
		Set<Dia> diasAtencionBanco = new HashSet<Dia>();
		diasAtencionBanco.add(Dia.LUNES);
		diasAtencionBanco.add(Dia.MARTES);
		diasAtencionBanco.add(Dia.MIERCOLES);
		diasAtencionBanco.add(Dia.JUEVES);
		diasAtencionBanco.add(Dia.VIERNES);
		return diasAtencionBanco;
	}	
	
public POI crearBanco(String nombre, Double lat, Double lon, String calle ,int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Set<Dia> diasDeAtencion)
{
POI banco;
return banco = RepoDePOIs.agregarBanco(nombre, lat, lon, calle, altura, descripcion, tags, horarioApertura, horarioCierre, diasDeAtencion);
}

public POI crearCGP(String nombre,  Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Comuna comuna, Servicio servicio, Set<Servicio> servicios)
{
POI cgp;
return cgp = RepoDePOIs.agregarCGP(nombre, lat, lon, calle, altura, descripcion, tags, horarioApertura, horarioCierre, comuna, servicio,servicios);
}

public POI crearComercio(String nombre,  Double lat, Double lon, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Rubro rubro, Set<HorarioDeAtencion> horarios, Set<Dia> diasDeAtencion)
{
POI comercio;
return comercio = RepoDePOIs.agregarComercio(nombre, lat, lon, calle, altura, descripcion, tags, horarioApertura, horarioCierre, rubro, horarios, diasDeAtencion);
}

public POI crearParadaColectivo(String nombre,  Double lat, Double lon,String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre)
{
POI paradaC;
return paradaC = RepoDePOIs.agregarParadaColectivo(nombre, lat, lon, calle, altura, descripcion, tags, horarioApertura, horarioCierre);
}

public void elimnarPOI(POI unPoi)
{
	RepoDePOIs.borrarPOI(unPoi);
}

}
