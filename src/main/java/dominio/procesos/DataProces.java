package dominio.procesos;

import org.joda.time.DateTime;

public class DataProces {

	private DateTime tiempoInicio;
	private DateTime tiempoFin;
	private String proceso;
	private String nombreUsuario;
	private String comoTermino;
	
	public String getUsuario() {
		return nombreUsuario;
	}
	
	public DateTime getTiempoInicio() {
		return tiempoInicio;
	}

	public DateTime getTiempoFin() {
		return tiempoFin;
	}

	public String getProceso() {
		return proceso;
	}

	public String getComoTermino() {
		return comoTermino;
	}

	public DataProces (DateTime iniciarTiempo, DateTime finalizarTiempo,
				String unProceso, String unNombreUsuario, String termino){
		this.setTiempoInicio(iniciarTiempo);
		this.setTiempoFin(finalizarTiempo);
		this.setProceso(unProceso);
		this.setNombreUsuario(unNombreUsuario);
		this.setComoTermino(termino);
	}
	
	public void setTiempoInicio(DateTime ini){
		tiempoInicio = ini;
	}
	
	public void setTiempoFin(DateTime fin){
		tiempoFin = fin;
	}
	
	public void setProceso(String unProc){
		proceso = unProc;
	}
	
	public void setNombreUsuario(String unUsu){
		nombreUsuario = unUsu;
	}
	
	public void setComoTermino(String term){
		comoTermino = term;
	}

}
