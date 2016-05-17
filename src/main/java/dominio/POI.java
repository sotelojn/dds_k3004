package dominio;

public class POI {

	private Double latitud;
	private Double longitud;
	private String nombre;
	private String calle;
	private int altura;
	private String descripcion;

	public boolean esValido() {
		return this.getLatitud() != null && this.getLongitud() != null
				&& this.getNombre() != null;
	}

	public boolean estaAMenosDe(POI otroPOI, Double metros) {
		return this.distanciaAPOI(otroPOI) <= metros;
	}
	
	public Double distanciaAPOI(POI punto) {

		final int RADIO = 6371000;

		double dLat = Math.toRadians(punto.getLatitud() - this.getLatitud());
		double dLon = Math.toRadians(punto.getLongitud() - this.getLongitud());

		double latitudPunto = Math.toRadians(punto.getLatitud());
		double miLatitud = Math.toRadians(this.getLatitud());

		double a = haversin(dLat) + Math.cos(latitudPunto)
				* Math.cos(miLatitud) * haversin(dLon);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return RADIO * c;

	}

	public static double haversin(Double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
