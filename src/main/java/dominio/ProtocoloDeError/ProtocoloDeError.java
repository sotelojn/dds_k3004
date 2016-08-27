package dominio.ProtocoloDeError;

import dominio.procesos.Proceso;
import dominio.usuario.UsuarioAdministrador;

public interface ProtocoloDeError {

	public abstract void implementarProtocolo(UsuarioAdministrador user, Proceso proceso);
	
}
