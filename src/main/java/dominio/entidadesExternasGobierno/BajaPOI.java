package dominio.entidadesExternasGobierno;

import java.util.Date;
import java.util.List;

public class BajaPOI implements InterfaceGobierno{

	private String valorDeBusqueda;
	private Date fechaDeBaja;
	
	public BajaPOI (){};
	
	public BajaPOI (String valor, Date fecha){
		this.setFechaDeBaja(fecha);
		this.setValorDeBusqueda(valor);
	};
	
	public String getValorDeBusqueda() {
		return valorDeBusqueda;
	}

	public void setValorDeBusqueda(String valorDeBusqueda) {
		this.valorDeBusqueda = valorDeBusqueda;
	}

	public Date getFechaDeBaja() {
		return fechaDeBaja;
	}

	public void setFechaDeBaja(Date fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
	}

	public List<BajaPOI> POIsDeBaja() {
		return null;
	}

}
