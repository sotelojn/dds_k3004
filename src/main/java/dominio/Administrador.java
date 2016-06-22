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
	
public void crearBanco(String nombre,Posicion posicion, String calle,int altura,String descripcion,Set<String> tags,LocalTime horarioApertura,LocalTime horarioCierre)
{
Set<Dia> diasDeAtencion = this.setearDiasAtencionBancos();
RepoDePOIs.agregarBanco(nombre, posicion, calle, altura, descripcion, tags, horarioApertura, horarioCierre, diasDeAtencion);
}

public void crearCGP(String nombre, Posicion posicion, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Comuna comuna, Servicio servicio, Set<Servicio> servicios)
{
RepoDePOIs.agregarCGP(nombre, posicion, calle, altura, descripcion, tags, horarioApertura, horarioCierre, comuna, servicio,servicios);
}

public void crearComercio(String nombre, Posicion posicion, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre, Rubro rubro, Set<HorarioDeAtencion> horarios, Set<Dia> diasDeAtencion)
{
RepoDePOIs.agregarComercio(nombre, posicion, calle, altura, descripcion, tags, horarioApertura, horarioCierre, rubro, horarios, diasDeAtencion);
}

public void crearParadaColectivo(String nombre, Posicion posicion, String calle, int altura, String descripcion, Set<String> tags, LocalTime horarioApertura, LocalTime horarioCierre)
{
RepoDePOIs.agregarParadaColectivo(nombre, posicion, calle, altura, descripcion, tags, horarioApertura, horarioCierre);
}

public void elimnarPOI(POI unPoi)
{
RepoDePOIs.borrarPOI(unPoi);
}

}
