package dominio;

public class Comuna {

	private Double radio;
	private Posicion centro;
	
	public boolean estaAdentro(Posicion unaPosicion){
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
