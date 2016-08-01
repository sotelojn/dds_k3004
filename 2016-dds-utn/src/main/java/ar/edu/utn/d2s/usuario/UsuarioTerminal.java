package ar.edu.utn.d2s.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import ar.edu.utn.d2s.PuntoDeInteres;
import ar.edu.utn.d2s.Repositorio;
import ar.edu.utn.d2s.acciones.AccionesDeUsuario;

public class UsuarioTerminal {

	private String usuario;
	private Repositorio repositorio;
	private List<AccionesDeUsuario> acciones;
	private List<NuevaConsultaExtendida> consultaRealizadas;
	private String mail;

	public String getMail() {
		return mail;
	}

	List<PuntoDeInteres> listaDePdisBuscados = new ArrayList<PuntoDeInteres>();

	public UsuarioTerminal(String unUsuario, Repositorio repositorio, List<AccionesDeUsuario> acciones,
			List<NuevaConsultaExtendida> consultaRealizadas, String unMail) {
		this.setAcciones(acciones);
		this.setConsultaRealizadas(consultaRealizadas);
		this.setRepositorio(repositorio);
		this.setUsuario(unUsuario);
		this.setMail(unMail);
	}

	private void setMail(String unMail) {

		this.mail = unMail;
	}

	public List<PuntoDeInteres> realizarBuquedaDePdi(String textoLibre, int tiempoDeBusqueda) {
		return this.realizarLaBuquedaDePdi(textoLibre, tiempoDeBusqueda);
	}

	public List<PuntoDeInteres> realizarLaBuquedaDePdi(String textoLibre, int tiempoDeBusqueda) {

		DateTime iniciarTiempo = new DateTime();
		listaDePdisBuscados = this.getRepositorio().buscarPDI(textoLibre);
		DateTime finalizarTiempo = new DateTime();

		this.getAcciones().stream().forEach(unaAccion -> unaAccion.puedeAplicarAccion(iniciarTiempo, finalizarTiempo,
				textoLibre, listaDePdisBuscados, this, tiempoDeBusqueda));
		return (listaDePdisBuscados);
	}

	public void agregarConsulta(NuevaConsultaExtendida consultaNueva) {
		this.getConsultaRealizadas().add(consultaNueva);
	}

	public Repositorio getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	public List<AccionesDeUsuario> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionesDeUsuario> acciones) {
		this.acciones = acciones;
	}

	public List<NuevaConsultaExtendida> getConsultaRealizadas() {
		return consultaRealizadas;
	}

	public void setConsultaRealizadas(List<NuevaConsultaExtendida> consultaRealizadas) {
		this.consultaRealizadas = consultaRealizadas;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void mostrarCantidadDeBusquedasPorTerminal() {
		this.getConsultaRealizadas().stream().map(unaBusqueda -> unaBusqueda.dameFechaYCantidad());

	}

	public List<Integer> consultasParcialesDelUsuario() {
		return this.getConsultaRealizadas().stream().map(unaBusqueda -> unaBusqueda.getResultados().size())
				.collect(Collectors.toList());
	}

	public void consultasTotalesDeUnUsuario() {
		System.out.println(this.getUsuario());
		this.cantidadTotalDeBusquedas();
	}

	private int cantidadTotalDeBusquedas() {
		return this.getConsultaRealizadas().size();
	}

}
