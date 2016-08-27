package dominio.usuario;

import java.util.List;

import dominio.PuntoDeInteres;
import dominio.acciones.AccionesDeUsuario;

public interface Usuario{
	
	public List<PuntoDeInteres> realizarBuquedaDePOI(String textoLibre, int tiempoDeBusqueda);

	public void actualizarListaDeAcciones(List<AccionesDeUsuario> acciones);

	public void agregarConsulta(NuevaConsultaExtendida nuevaConsulta);

	public String getUsuario();

	public List<AccionesDeUsuario> getAcciones();
	
	public String getMail();

}
