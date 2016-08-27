package dominio.ProtocoloDeError;

import dominio.procesos.Proceso;
import dominio.usuario.UsuarioAdministrador;

public class EnviarMail implements ProtocoloDeError {

	public EnviarMail() {}
	
	@Override
	public void implementarProtocolo(UsuarioAdministrador user, Proceso proceso){
		String mail;
		mail = user.getMail();
		System.out.println("mail enviado a " + mail);
		
	}


}
