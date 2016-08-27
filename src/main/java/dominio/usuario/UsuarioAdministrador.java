package dominio.usuario;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import dominio.POI.cgp.Comuna;
import dominio.POI.cgp.Servicio;
import dominio.POI.localComercial.Rubro;
import dominio.acciones.AccionesDeUsuario;
import dominio.POI.colectivo.ParadaDeColectivo;
import dominio.Repositorio;
import dominio.HorarioAtencion;
import dominio.Direccion;
import dominio.Coordenada;
import dominio.procesos.DataProces;
import dominio.procesos.Proceso;
import dominio.ProtocoloDeError.ProtocoloDeError;


public class UsuarioAdministrador extends UsuarioTerminal {
	
	private ProtocoloDeError unProtocolo;
	private List<DataProces> listaDeProcesos = new ArrayList<DataProces>(); 
	
	public UsuarioAdministrador(String unUsuario, Repositorio repositorio, List<AccionesDeUsuario> acciones,
			List<NuevaConsultaExtendida> consultaRealizadas, String unMail,
			ProtocoloDeError proto) {
		super(unUsuario, repositorio, acciones, consultaRealizadas, unMail);
		this.setProtocolo(proto);
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
 
	
	public List<DataProces> getListaDeProcesos(){
		return listaDeProcesos;
		}
	
	public void realizarProceso(Proceso unProceso,UsuarioAdministrador unUsuario){
		String termino = " ";
		DateTime iniciarTiempo = new DateTime();
		
		termino = unProceso.realizate(unUsuario);
		if (termino.equals("ok")){
			DateTime finalizarTiempo = new DateTime();
			this.agregarAlDataProces(iniciarTiempo,finalizarTiempo,
					"Actualizacion de Locales Comerciales",termino);
		}else{
			this.getProtocolo().implementarProtocolo(this, unProceso);
		}if(termino.equals("error")){
			DateTime finalizarTiempo = new DateTime();
			this.agregarAlDataProces(iniciarTiempo,finalizarTiempo,
					"Actualizacion de Locales Comerciales",termino);
		}
		
	}
	
	public void setProtocolo(ProtocoloDeError proto){
		this.unProtocolo = proto;
	}
	
	public ProtocoloDeError getProtocolo(){
		return this.unProtocolo;
	}
	
	public void agregarAlDataProces(DateTime iniciarTiempo, DateTime finalizarTiempo,
			String unProceso, String termino){
		DataProces unaData = new DataProces (iniciarTiempo,finalizarTiempo,
				unProceso, this.getUsuario(), termino);
		this.getListaDeProcesos().add(unaData);
	}
	
	public int procesosRealizadosPorUnUsuario(){
		return this.getListaDeProcesos().size();
	}
	
}

