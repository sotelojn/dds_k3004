package dominio;

public abstract class PuntoDeInteres {

	private int ID;
	private Direccion direccion;
	private Coordenada coordenada;
	private String nombre;
	
	private String icono;

	
	public abstract boolean estaCercaDe(Coordenada unaCoordenada);
	public abstract boolean contenesConsulta (String textoLibre);
	public abstract boolean esValido();
	
	public PuntoDeInteres(){
		
	}
	
	public PuntoDeInteres (Direccion unaDireccion, String unIcono, String unNombre, Coordenada unaCoordenada){
		this.setDireccion(unaDireccion);
		this.setIcono(unIcono);
		this.setNombre(unNombre);
		this.setCoordenada(unaCoordenada);
	}
	
	public boolean tieneAtributosDePOIValidos(){
		return tieneNombreVaido() && this.tieneIdValido() &&
				this.tieneIconoValido() && this.tieneCoordenadaValida() ;
	}
	
	
	public Coordenada getCoordenada() {
		return coordenada;
	}	
	
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public int largoDireccion() {
		return this.direccion.getCalle().length();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
        if (obj instanceof PuntoDeInteres) {
        PuntoDeInteres POI = (PuntoDeInteres) obj;
        		return (this.getDireccion().equals(POI.getDireccion()) &&
        				this.getCoordenada().equals(POI.getCoordenada()) &&
        				this.getNombre().equals(POI.getNombre()) &&
        				this.getIcono().equals(POI.getIcono()) );
        }
        return false;
	}

	public boolean tieneNombreVaido() {
		return ((this.nombre).length() > 0);
	}
	
	public boolean tieneIdValido(){
		return this.getId() >= 0;
	}
	
	public boolean tieneIconoValido(){
		return this.getIcono().length() > 0;
	}
	
	public boolean tieneCoordenadaValida(){
		return this.getCoordenada().tieneCoordenadasValidas();
	}
	
	public int getId() {
		return this.ID;
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public void setId(int unId) {
		this.ID = unId;
	}
	
}