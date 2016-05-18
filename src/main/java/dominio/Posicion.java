package dominio;

public class Posicion {

	private Double latitud;
	private Double longitud;

	public Posicion(Double latitud, Double longitud) {
		setLatitud(latitud);
		setLongitud(longitud);
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

	public Double distanciaA(Posicion unaPosicion) {

		final int RADIO = 6371000;

		double dLat = Math.toRadians(unaPosicion.getLatitud() - this.getLatitud());
		double dLon = Math.toRadians(unaPosicion.getLongitud() - this.getLongitud());

		double latitudPunto = Math.toRadians(unaPosicion.getLatitud());
		double miLatitud = Math.toRadians(this.getLatitud());

		double a = haversin(dLat) + Math.cos(latitudPunto) * Math.cos(miLatitud) * haversin(dLon);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return RADIO * c;

	}

	private double haversin(Double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}

	public boolean esValido() {

		return getLatitud() != null && getLongitud() != null;
	}

}
