package dominio.POI.colectivo;

import java.time.DayOfWeek;
import java.util.List;

import dominio.Coordenada;
import dominio.Direccion;
import dominio.PuntoDeInteres;

public class Colectivo extends PuntoDeInteres{

	private int linea;
	private List<ParadaDeColectivo> paradasDeColectivo;
	
	public Colectivo(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, int numeroDeLinea,List<ParadaDeColectivo> paradasDeColectivo){
		super(unaDireccion, "Colectivo", unNombre, unaCoordenada);
		this.setLinea(numeroDeLinea);
		this.setParadasDeColectivo(paradasDeColectivo);
	}
	
	public void setParadasDeColectivo(List<ParadaDeColectivo> paradasDeColectivo) {
		this.paradasDeColectivo = paradasDeColectivo;
	}
	
	private List<ParadaDeColectivo> getParadasDeColectivo(){
		return this.paradasDeColectivo;
	
	}
	
	@Override
	public boolean esValido(){
		return (this.tieneAtributosDePOIValidos() &&
				this.tieneLineaValida()	);
	}

	private boolean tieneLineaValida() {
		return this.getLinea()>0;
	}

	public int getLinea() {
		return linea;
	}
	
	public void setLinea(int linea) {
		this.linea = linea;
	}

	public boolean estaCercaDe(Coordenada unaCoordenada){
		return ((this.getParadasDeColectivo()).stream().anyMatch(unaParada -> (unaParada.dameCoordenada().distance(unaCoordenada) ) <= 0.1) );
	}
	
	//Creo que es esta la funcion que se debería utilizar, pero a verdad es que nose
	public Boolean estaCercaDe(){
		return ((this.getParadasDeColectivo().stream().anyMatch(unaParada -> (unaParada.dameCoordenada().distance(this.getCoordenada()) ) <= 0.1)));
	}
	
	public Boolean estaDisponible(double hora, DayOfWeek dia){
		return true;
	}
	
	@Override
	public boolean contenesConsulta(String textoLibre) {
		return(this.getNombre().equals(textoLibre));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		Colectivo other = (Colectivo) obj;
		if (linea != other.linea)
			return false;
		if (!super.equals(other))
			return false;
		if (paradasDeColectivo == null) {
			if (other.paradasDeColectivo != null)
				return false;
		} else if (!paradasDeColectivo.equals(other.paradasDeColectivo))
			return false;
		return true;
	}
	
	
	
}
