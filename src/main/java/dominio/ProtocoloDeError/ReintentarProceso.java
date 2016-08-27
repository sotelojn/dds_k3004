package dominio.ProtocoloDeError;

import dominio.procesos.Proceso;
import dominio.usuario.UsuarioAdministrador;

public class ReintentarProceso implements ProtocoloDeError {

	private int cantidad;
	
	public ReintentarProceso(int cant) {
		this.setCantidad(cant);
	}
	
	public void setCantidad(int cant){
		this.cantidad = cant;
	}
	
	public int getCantidad(){
		return this.cantidad;
	}
	
	@Override
	public void implementarProtocolo(UsuarioAdministrador user, Proceso proceso) {
		int contador = 0;
		
		while (contador < this.getCantidad()){
			
			user.realizarProceso(proceso, user);
			contador ++;
			
		}
		
	}


}
