package ar.edu.utn.d2s.acciones;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.utn.d2s.PuntoDeInteres;
import ar.edu.utn.d2s.usuario.NuevaConsultaExtendida;
import ar.edu.utn.d2s.usuario.UsuarioTerminal;

public class AccionGuardarConsultas extends AccionesDeUsuario{

	public AccionGuardarConsultas(Boolean permiso) {
		super(permiso);
	}

	@Override
	public void aplicarAccion(DateTime tiempoI, DateTime tiempoF, String textoLibre,
			List<PuntoDeInteres> listaDePdisBuscados, UsuarioTerminal unUsuario,
			int tiempoDeBusqueda) {

		NuevaConsultaExtendida nuevaConsulta = new NuevaConsultaExtendida(textoLibre,
				listaDePdisBuscados, tiempoI);
		unUsuario.agregarConsulta(nuevaConsulta);

		
	}

}
