package dominio;

import org.uqbar.geodds.Point;

public class Coordenada extends Point {
	
	public Coordenada(double aX, double aY) {
		super(aX, aY);
	}
	
	public boolean tieneCoordenadasValidas() {
	
		return (this.toDegree(this.latitude()) >= -90 || this.toDegree(this.longitude()) <= 90) && 
				(this.toDegree(this.longitude()) >= -180 || this.toDegree(this.latitude()) <= 180);
	}
	
	@Override
	public boolean equals(Object anObject){
		Coordenada other = (Coordenada) anObject;
		return (this.latitude() == other.latitude() && 
				this.longitude() == other.longitude() );
	}
	
}
