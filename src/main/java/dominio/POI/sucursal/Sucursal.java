package dominio.POI.sucursal;

import java.time.DayOfWeek;
import java.util.List;

import dominio.Coordenada;
import dominio.Direccion;
import dominio.PuntoDeInteres;
import dominio.POI.cgp.Servicio;

public class Sucursal extends PuntoDeInteres {

	private String banco;
	private List<Servicio> servicios;

	public Sucursal(Direccion unaDireccion, String unBanco, String unNombre, Coordenada unaCoordenada,
			List<Servicio> listaDeServicios) {
		super(unaDireccion, "Banco", unNombre, unaCoordenada);
		this.setBanco(unBanco);
		this.setServicios(listaDeServicios);

	}

	public Sucursal() {
		super();
	}

	public boolean esValido() {

		return  (this.getBanco().length() > 0)
				//&& this.getDisponibilidad().stream().allMatch(unD -> unD.esHorarioValido())
				&& this.getServicios().stream().allMatch(unS -> unS.esValido());
	}

	public String getBanco() {
		return banco;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	@Override
	public boolean estaCercaDe(Coordenada unaCoordenada) {
		double distancia = this.getCoordenada().distance(unaCoordenada);
		return distancia <= 0.5;
	}

	public Boolean estaDisponible(double hora, String nombreServicio, DayOfWeek dia) {
		return (servicios.stream().anyMatch(servicio -> servicio.estaDisponibleElServicio(nombreServicio, hora, dia)));
	}

	public void setBanco(String unBanco) {
		this.banco = unBanco;
	}

	@Override
	public boolean contenesConsulta (String textoLibre){
		return (this.getBanco().startsWith(textoLibre));
		
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		Sucursal other = (Sucursal) obj;
		
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.getBanco()))
			return false;
		
		if (servicios == null) {
			if (other.servicios != null)
			return false;
		} else if (!other.getServicios().stream().allMatch(unServicio ->
		servicios.stream().anyMatch(otroServicio -> 
		unServicio.equals(otroServicio))) )
			return false;
		return true;
	}
	
	
}
