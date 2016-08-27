package dominio.procesos;

import java.util.List;

import dominio.acciones.AccionesDeUsuario;
import dominio.usuario.Usuario;
import dominio.usuario.UsuarioAdministrador;

public class AgregarAccionesAUsuarios implements Proceso {

	private List<AccionesDeUsuario> acciones;
	private Usuario unUsuario;
	
	public AgregarAccionesAUsuarios(List<AccionesDeUsuario> listaDeAcciones) {
		this.setListaDeAcciones(listaDeAcciones);
	}

	public Usuario getUsuario(){
		return this.unUsuario;
	}
	
	public List<AccionesDeUsuario> getAcciones(){
		return this.acciones;
	}
	
	public void setListaDeAcciones(List<AccionesDeUsuario> listaDeAcciones){
		this.acciones = listaDeAcciones;
	}
	
	public void setUsuario(Usuario usuarioAdministrador){
		this.unUsuario = usuarioAdministrador;
	}
	
	@Override
	public String realizate(UsuarioAdministrador usuarioAdministrador) {
		//ASUMO QUE LA LISTA DE ACCIONES DEL USUARIO SE MODIFICA POR COMPLETO, Y NO
		//QUE SE LE AGREGAN ACCIONES A LAS QUE YA ESTABAN SETTEADAS EN EL MISMO
		String termino;
		this.setUsuario(usuarioAdministrador);
		usuarioAdministrador.actualizarListaDeAcciones(this.getAcciones());
		if (!this.interrumpioProceso(usuarioAdministrador) == true){
			termino = "error";
		}else{
			termino = "ok";
		}
		
		return termino;
	}

	public boolean interrumpioProceso(Usuario user){
		return user.getAcciones() == this.getAcciones();
		
	}
	
	public void deshacer(Usuario user){
		user.actualizarListaDeAcciones(this.getUsuario().getAcciones());
		
	}
}
