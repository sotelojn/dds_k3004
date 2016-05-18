package dominio;

public class Comuna {

	private Double radio;
	private Posicion centro;

	public Comuna() {
	};

	public Comuna(Double radio, Posicion centro) {
		super();
		this.radio = radio;
		this.centro = centro;
	}

	public Comuna(Double radio, Double latitud, Double longitud) {
		super();
		this.radio = radio;
		this.centro = new Posicion(latitud, longitud);
	}

	public boolean estaAdentro(Posicion unaPosicion) {
		return centro.distanciaA(unaPosicion) <= getRadio();
	}

	public Double getRadio() {
		return radio;
	}

	public void setRadio(Double radio) {
		this.radio = radio;
	}

	public Posicion getCentro() {
		return centro;
	}

	public void setCentro(Posicion centro) {
		this.centro = centro;
	}

}
