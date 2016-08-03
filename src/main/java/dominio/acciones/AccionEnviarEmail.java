package dominio.acciones;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import dominio.PuntoDeInteres;
import dominio.usuario.UsuarioTerminal;

public class AccionEnviarEmail extends AccionesDeUsuario{

	public AccionEnviarEmail(Boolean permiso) {
		super(permiso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aplicarAccion(DateTime tiempoI, DateTime tiempoF, String textoLibre,
			List<PuntoDeInteres> listaDePOIsBuscados, UsuarioTerminal unUsuario,
			int tiempoDeBusqueda) {
		
		int tiempoDeConsulta = Seconds.secondsBetween(tiempoI, tiempoF).getSeconds();
		
		if (tiempoDeConsulta >= tiempoDeBusqueda){
		System.out.println("mail enviado");}
		
		
	}

}
