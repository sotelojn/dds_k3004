package dominio.acciones;

import java.util.List;

import org.joda.time.DateTime;

import dominio.PuntoDeInteres;
import dominio.usuario.NuevaConsultaExtendida;
import dominio.usuario.Usuario;

public class AccionGuardarConsultas extends AccionesDeUsuario{

	public AccionGuardarConsultas(Boolean permiso) {
		super(permiso);
	}

	@Override
	public void aplicarAccion(DateTime tiempoI, DateTime tiempoF, String textoLibre,
			List<PuntoDeInteres> listaDePOIsBuscados, Usuario unUsuario,
			int tiempoDeBusqueda) {

		NuevaConsultaExtendida nuevaConsulta = new NuevaConsultaExtendida(textoLibre,
				listaDePOIsBuscados, tiempoI);
		unUsuario.agregarConsulta(nuevaConsulta);

		
	}

}
