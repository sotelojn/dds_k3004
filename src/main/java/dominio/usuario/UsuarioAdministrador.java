package dominio.usuario;

import java.util.List;
import dominio.POI.cgp.Comuna;
import dominio.POI.cgp.Servicio;
import dominio.POI.localComercial.Rubro;
import dominio.POI.colectivo.ParadaDeColectivo;
import dominio.Repositorio;
import dominio.HorarioAtencion;
import dominio.Direccion;
import dominio.Coordenada;


public class UsuarioAdministrador {
	
	public void crearBanco(Direccion direccion, String unBanco, String nombre,  Coordenada coordenada, List<Servicio> listaDeServicios, Repositorio repo)
	{
		repo.crearUnaSucursalBancaria (direccion, unBanco, nombre, coordenada, listaDeServicios);
	}

	public void crearCGP(Direccion unaDireccion, Coordenada unaCoordenada, String unNombre, Comuna unaComuna,Repositorio repo)
	{
		repo.crearUnCGP(unaDireccion, unaCoordenada, unNombre, unaComuna);
	}

	public void crearComercio(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, Rubro rubro,List<HorarioAtencion> disponibiidad, Repositorio repo)
	{
		repo.crearUnLocalComercial(unaDireccion, unNombre, unaCoordenada, rubro, disponibiidad);
	}
	
	public void crearParadaColectivo(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, int numeroDeLinea, List<ParadaDeColectivo> paradasDeColectivo, Repositorio repo)
	{
		repo.crearUnColectivo(unaDireccion, unNombre, unaCoordenada, numeroDeLinea, paradasDeColectivo);

	}

	public void eliminarPOI(int id, Repositorio repo )
	{
		repo.deletePorId(id);
	}
 
	
}
