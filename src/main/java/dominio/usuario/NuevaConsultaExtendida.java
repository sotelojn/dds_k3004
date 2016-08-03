package dominio.usuario;

import java.util.List;

import org.joda.time.DateTime;
import dominio.Consulta;
import dominio.PuntoDeInteres;;

public class NuevaConsultaExtendida extends Consulta {
	
	private DateTime fecha;
	
	class ObjetoConFechaYCantidades {
		
		private int cantidad;
	    private DateTime fecha;
	    
	    public void setCantidad(int unaCantidad){
	    	this.cantidad = unaCantidad;
	    }
	    
	    public int getCumplioTiempo(){
	    	return this.cantidad;
	    }

	    public DateTime getFecha(){
	    	return this.fecha;
	    }
	    
	    public void setFecha(DateTime unaFecha){
	    	this.fecha = unaFecha;
	    }
	}
	
	public NuevaConsultaExtendida(String clave, List<PuntoDeInteres> lista, DateTime fechaDInicio) {
		super(clave, lista);
		//hay que pasarle solamente la parte de la fecha
		this.setFecha(fechaDInicio);
	}

	public DateTime getFecha() {
		return fecha;
	}

	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}

	public Object dameFechaYCantidad() {
		ObjetoConFechaYCantidades unObjeto = new ObjetoConFechaYCantidades();
		unObjeto.setFecha(this.getFecha());
		unObjeto.setCantidad(this.getResultados().size());
		return unObjeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NuevaConsultaExtendida other = (NuevaConsultaExtendida) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}
	
}
