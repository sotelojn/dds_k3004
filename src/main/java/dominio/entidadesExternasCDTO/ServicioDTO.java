package dominio.entidadesExternasCDTO;


import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {
	
	private String nombreServicio;
	private List<RangoServicioDTO> rangos;
	
	public ServicioDTO (String nombre, List<RangoServicioDTO> lista){
		this.setNombreServicio(nombre);
		this.setRangos(lista);
	}
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public List<RangoServicioDTO> getRangos() {
		return rangos;
	}
	public void setRangos(List<RangoServicioDTO> rangos) {
		this.rangos = rangos;
	}
	public ServicioDTO(){
		rangos = new ArrayList<RangoServicioDTO>();
	}
	public boolean addRango(RangoServicioDTO rango){
		return this.rangos.add(rango);
	}

}
