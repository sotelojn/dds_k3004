package dominio.POI.localComercial;

import java.time.DayOfWeek;
import java.util.List;

import dominio.Coordenada;
import dominio.Direccion;
import dominio.HorarioAtencion;
import dominio.PuntoDeInteres;

public class Local extends PuntoDeInteres {

	private Rubro rubro;
	private List<HorarioAtencion> disponibilidad;

	public Local(){};
	
	public Local(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, Rubro rubro,
			List<HorarioAtencion> disponibiidad) {
		super(unaDireccion, "locales", unNombre, unaCoordenada);
		this.setRubro(rubro);
		this.setDisponibilidad(disponibiidad);

	}

	public boolean esValido() {
		return this.tieneAtributosDePOIValidos()
				&& this.getDisponibilidad().stream().allMatch(unHorario -> unHorario.esHorarioValido())
				&& this.getRubro().esRubroValido();
	}

	public List<HorarioAtencion> getDisponibilidad() {
		return this.disponibilidad;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro categoria) {
		this.rubro = categoria;
	}

	public void setDisponibilidad(List<HorarioAtencion> unaLista) {
		this.disponibilidad = unaLista;
	}

	public boolean contenesConsulta(String textoLibre) {
		
		return (this.getNombre().startsWith(textoLibre)) ||
				(this.getRubro().getNombreRubro().equals(textoLibre)) ||
				(this.getRubro().contenesPalabraClave(textoLibre));
	}

	@Override
	public boolean estaCercaDe(Coordenada unaCoordenada) {
		double distancia = this.getCoordenada().distance(unaCoordenada);
		return (distancia <= rubro.getRadio());
	}

	public Boolean estaDisponible(double hora, DayOfWeek dia) {

		return (disponibilidad.stream()
				.anyMatch(unaDisponibilidad -> unaDisponibilidad.estaDisponibleElHorario(hora, dia)));

	}

	public Boolean esElNombre(String nombre) {
		return this.getNombre().equals(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		Local other = (Local) obj;
		if (!super.equals(other))
			return false;
		if (disponibilidad == null) {
			if (other.disponibilidad != null)
				return false;
		} else if (!disponibilidad.equals(other.disponibilidad))
			return false;
		if (rubro == null) {
			if (other.rubro != null)
				return false;
		} else if (!rubro.equals(other.rubro))
			return false;
		return true;
	}

	public void reemplazaTusPalabrasClaves(List<String> palabras){
		this.getRubro().setPalabrasClave(palabras);
	}
	
	
}