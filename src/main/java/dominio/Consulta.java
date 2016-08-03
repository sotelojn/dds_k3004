package dominio;

import java.util.List;

public class Consulta {
	
	private String consulta;
	private List<PuntoDeInteres> resultados;
	
	public Consulta(String clave, List<PuntoDeInteres> lista) {
		this.setConsulta(clave);
		this.setResultados(lista);
	}
	
	public String getConsulta() {
		return consulta;
	}
	
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	public List<PuntoDeInteres> getResultados() {
		return resultados;
	}
	
	public void setResultados(List<PuntoDeInteres> resultados) {
		this.resultados = resultados;
	}
	
}
