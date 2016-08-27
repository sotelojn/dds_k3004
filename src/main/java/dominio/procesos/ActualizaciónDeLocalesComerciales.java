package dominio.procesos;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import dominio.Coordenada;
import dominio.Direccion;
import dominio.HorarioAtencion;
import dominio.Repositorio;
import dominio.POI.localComercial.Local;
import dominio.POI.localComercial.Rubro;
import dominio.usuario.UsuarioAdministrador;

public class ActualizaciónDeLocalesComerciales implements Proceso {

	private FileReader unArchivo;
	private Repositorio unRepositorio;
	
	public ActualizaciónDeLocalesComerciales(FileReader arch, Repositorio unRepo){
		this.setRepositorio(unRepo);
		this.setArchivo(arch);
	}
	
	public void setRepositorio(Repositorio c){
		this.unRepositorio = c;
	}
	
	public void setArchivo(FileReader arch){
		this.unArchivo = arch;
	}
	
	public Repositorio getRepositorio(){
		return this.unRepositorio;
	}
	
	public List<String> dameLasPalabrasClaves(FileReader unArchivo) throws IOException {
		char unCaracter;
		String palabra = "";
		List<String> listaDePalabras = new ArrayList<String>();
		@SuppressWarnings("unused")
		int contador = 0;
		
		while((unCaracter = (String.valueOf(unArchivo.read())).charAt(0)) != ';' &&
				((unCaracter != '\n' ) || (unCaracter != '.') ))
			contador ++;
		while((unCaracter = (String.valueOf(unArchivo.read())).charAt(0)) != '.'){
			if (unCaracter == ' '){
				listaDePalabras.add(palabra);
			}else{
				palabra = unCaracter + palabra;
			}
		}
		return listaDePalabras;
	}

	public String dameElNombreFantasia(FileReader unArchivo) throws IOException {
		String nombre = "";
		String unCaracter;
		
		while((unCaracter = String.valueOf(unArchivo.read())) != ";" && (unCaracter != null)){
			nombre = nombre + unCaracter;
		}
		
		return nombre;
	}

	public void actualizarArchivo(FileReader arch){
		unArchivo = arch;
	}

	public FileReader getArchivo(){
		return unArchivo;
	}

	@Override
	public String realizate(UsuarioAdministrador usuarioAdministrador) {
		Local unLocal = new Local();
		List<String> palabras = new ArrayList<String>();
		String termino;
		String nombre;
		FileReader archivo = this.getArchivo();
		
		try {
			nombre = this.dameElNombreFantasia(archivo);
			while (!nombre.equals(null)){
				unLocal = (Local) this.getRepositorio().buscarLocalComercial(nombre);
				palabras = this.dameLasPalabrasClaves(archivo);
			if (unLocal != null){
				unLocal.reemplazaTusPalabrasClaves(palabras);
			}else{
				Direccion unaDirec = new Direccion();
				Coordenada coordenada = new Coordenada(0,0);
				List<HorarioAtencion> disponibilidad = new ArrayList<HorarioAtencion>(); 
				Rubro unRubro = new Rubro("", 0, palabras);
				getRepositorio().crearUnLocalComercial(unaDirec, nombre, coordenada, unRubro, disponibilidad);
				}
			}
			
			termino = "ok";
			return termino;
		} 
		catch (IOException e) {
			termino = "error";
			e.printStackTrace();
			return termino;
		} 		
	}
	
}
