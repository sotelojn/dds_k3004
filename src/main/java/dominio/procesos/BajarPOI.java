package dominio.procesos;

import dominio.EntidadesExternas;
import dominio.Repositorio;
import dominio.usuario.UsuarioAdministrador;

public class BajarPOI implements Proceso {

	private EntidadesExternas unaEntidad = new EntidadesExternas();
	private Repositorio unRepositorio;
	
	public BajarPOI(Repositorio unRepositorio, EntidadesExternas unaEntidad){
		this.setRepositorio(unRepositorio);
		this.setEntidades(unaEntidad);
	}
	
	public void setRepositorio(Repositorio u){
		unRepositorio = u;
	}
	
	public void setEntidades(EntidadesExternas u){
		unaEntidad = u;
	}
	
	public Repositorio getRepositorio(){
		return this.unRepositorio;
	}
	
	public String realizate(UsuarioAdministrador unUsuario) {
		String termino;
		termino = this.getEntidadE().dameLosPOIsABajar(this.getRepositorio());
		return termino;
	}

	public EntidadesExternas getEntidadE(){
		return unaEntidad;
	}
}
