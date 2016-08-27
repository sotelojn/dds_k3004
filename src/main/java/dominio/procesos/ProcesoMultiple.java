package dominio.procesos;

import java.util.List;

import dominio.usuario.UsuarioAdministrador;

public class ProcesoMultiple implements Proceso {

	private List<Proceso> listaDeProcesos;
	
	public ProcesoMultiple (List<Proceso> procesos){
		this.setListaDeProcesos(procesos);
	}
	
	public void setListaDeProcesos(List<Proceso> procesos) {
		this.listaDeProcesos = procesos;
	}
	
	public List<Proceso> getListaProcesos(){
		return this.listaDeProcesos;
	}
	
	@Override
	public String realizate(UsuarioAdministrador usuarioAdministrador) {
		String termino;
		int cant = usuarioAdministrador.procesosRealizadosPorUnUsuario() + 2;
		this.getListaProcesos().forEach(action -> usuarioAdministrador.
				realizarProceso(action,usuarioAdministrador));
		int cantTotal = usuarioAdministrador.procesosRealizadosPorUnUsuario();
		if (cant == cantTotal){
			termino = "ok";
		}else{
			termino = "error";
		}
		return termino;
	}
}
