package dominio.usuario;

import java.util.List;
import dominio.POI.cgp.Comuna;
import dominio.POI.cgp.Servicio;
import dominio.POI.localComercial.Rubro;
import dominio.acciones.AccionesDeUsuario;
import dominio.POI.colectivo.ParadaDeColectivo;
import dominio.Repositorio;
import dominio.HorarioAtencion;
import dominio.Direccion;
import dominio.Coordenada;


public class UsuarioAdministrador extends UsuarioTerminal {
	

	public UsuarioAdministrador(String unUsuario, Repositorio repositorio, List<AccionesDeUsuario> acciones,
			List<NuevaConsultaExtendida> consultaRealizadas, String unMail) {
		super(unUsuario, repositorio, acciones, consultaRealizadas, unMail);
		// TODO Auto-generated constructor stub
	}

	public void crearBanco(Direccion direccion, String unBanco, String nombre,  Coordenada coordenada, List<Servicio> listaDeServicios)
	{
		repositorio.crearUnaSucursalBancaria (direccion, unBanco, nombre, coordenada, listaDeServicios);
	}

	public void crearCGP(Direccion unaDireccion, Coordenada unaCoordenada, String unNombre, Comuna unaComuna)
	{
		repositorio.crearUnCGP(unaDireccion, unaCoordenada, unNombre, unaComuna);
	}

	public void crearComercio(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, Rubro rubro,List<HorarioAtencion> disponibiidad)
	{
		repositorio.crearUnLocalComercial(unaDireccion, unNombre, unaCoordenada, rubro, disponibiidad);
	}
	
	public void crearParadaColectivo(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, int numeroDeLinea, List<ParadaDeColectivo> paradasDeColectivo)
	{
		repositorio.crearUnColectivo(unaDireccion, unNombre, unaCoordenada, numeroDeLinea, paradasDeColectivo);

	}

	public void eliminarPOI(int id)
	{
		repositorio.deletePorId(id);
	}
 
	
}
