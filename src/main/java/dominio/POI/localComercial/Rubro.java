package dominio.POI.localComercial;

import java.util.List;

public class Rubro {
	private String nombreRubro;
	private Double radio;
	private List<String> palabrasClaves;
	
	public Rubro (String nombre, double radio, List<String> palabras){
		this.setNombreRubro(nombre);
		this.setRadio(radio);
		this.setPalabrasClave(palabras);
		
	}
	
	public String getNombreRubro() {
		return nombreRubro;
	}
	
	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}
	
	public Double getRadio() {
		return radio;
	}
	
	public void setRadio(Double radio) {
		this.radio = radio;
	}
	
	public List<String> getPalabrasClave() {
		return palabrasClaves;
	}
	
	public void setPalabrasClave(List<String> palabrasClave){
		this.palabrasClaves = palabrasClave;
	}

	public boolean esRubroValido() {
		return nombreRubro.length()>0 && radio > 0 && palabrasClaves != null;
	}

	public boolean contenesPalabraClave(String textoLibre) {
		return this.getPalabrasClave().stream().anyMatch(unaPalabra ->
		unaPalabra.equals(textoLibre));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rubro other = (Rubro) obj;
		if (nombreRubro == null) {
			if (other.nombreRubro != null)
				return false;
		} else if (!nombreRubro.equals(other.nombreRubro))
			return false;
		if (radio == null) {
			if (other.radio != null)
				return false;
		} else if (!radio.equals(other.radio))
			return false;
		if (palabrasClaves == null) {
			if (other.palabrasClaves != null)
				return false;
		}else if (!palabrasClaves.stream().allMatch(unaPalabra -> 
		 	other.palabrasClaves.stream().anyMatch(otraPalabra -> unaPalabra.
		 			equals(otraPalabra) ) ) )
				return false;
		return true;
	}

}
