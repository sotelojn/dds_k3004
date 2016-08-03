package dominio.entidadesExternasCDTO;


import java.util.List;

import org.uqbar.geodds.Polygon;

import dominio.Coordenada;

public class CentroDTO implements InterfaceCentroDTO{

	private Polygon poligono;
	private int numeroComuna;
	private String nombre;
	private String zonasIncluidas;
	private String nombreDirector;
	private String direccion;
	private String telefono;
	private List<ServicioDTO> servicios;
	private Coordenada coordenada;
	private String icono;
	
	public CentroDTO(){
		
	};
	
	public CentroDTO(int numeroComuna, String nombreCGP, String zonasIncluidas, String nombreDirector, String unaDireccion, String telefono,
			List<ServicioDTO> servicios, Coordenada coordenada, Polygon unPoligono) {
		this.numeroComuna = numeroComuna;
		this.nombre = nombreCGP;
		this.zonasIncluidas = zonasIncluidas;
		this.nombreDirector = nombreDirector;
		this.direccion = unaDireccion;
		this.telefono = telefono;
		this.servicios = servicios;
		this.coordenada = coordenada;
		this.poligono = unPoligono;
	}

	public List<ServicioDTO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}

	public int getNumeroComuna() {
		return numeroComuna;
	}

	public Coordenada getCoordenada(){
		return this.coordenada;
	}
	
	public String getZonasIncluidas() {
		return zonasIncluidas;
	}
	
	public String getNombreDirector() {
		return nombreDirector;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getIcono(){
		return icono;
	}
	
	public void setNumeroComuna(int numeroComuna) {
		this.numeroComuna = numeroComuna;
	}

	public void setZonasIncluidas(String zonasIncluidas) {
		this.zonasIncluidas = zonasIncluidas;
	}

	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}

	public void setDomicilio(String unaDireccion) {
		this.direccion = unaDireccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setIcono(String unIcono){
		this.icono = unIcono;
	}
	
	public boolean addServicio(ServicioDTO servicio) {
		return this.servicios.add(servicio);
	}

	public String getNombre() {
		return this.nombre;
	}

	public Polygon getPoligono() {
		return this.poligono;
	}

	@Override
	public List<CentroDTO> buscarCentroDTO(String TextoLibre) {
		return null;
	}

}
