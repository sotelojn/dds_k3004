package dominio.adapter;

import dominio.Repositorio;

public class ObjetoDeAyuda {
	
	private boolean finalizo;
	private Repositorio unRepo;
	
	public ObjetoDeAyuda(){}

	public Repositorio getUnRepo() {
		return unRepo;
	}

	public void setFinalizo(boolean finalizo) {
		this.finalizo = finalizo;
	}

	public void setUnRepo(Repositorio unRepo) {
		this.unRepo = unRepo;
	}

	public boolean getFinalizo() {
		return finalizo;
	}
	
}
