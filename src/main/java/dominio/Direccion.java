package dominio;

public class Direccion {
	
	private String calle;
	private String numero;
	
	public Direccion(){
		
	}
	
	public Direccion (String calle, String numero){
		this.setCalle(calle);
		this.setNumero(numero);
	}
	
	public String getCalle() {
		return calle;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numeroDeLaPalabra) {
		this.numero = numeroDeLaPalabra;
	}
	
	public String dameLaPalabra(String unaDireccionEnString){
	
	char unCaracter;
	int posicion = 0;
	String palabra = "";
	
	while ((unCaracter = unaDireccionEnString.charAt(posicion)) != ' '){
			palabra = (palabra) + (unCaracter);
			posicion ++;
		}
	return palabra;
	}

	public String dameElNumero(String unaDireccionEnString){
	
		char unCaracter;
		int posicion = 0;
		String palabra = "";
		
		while ((unCaracter = unaDireccionEnString.charAt(posicion)) != ' '){
				posicion ++;
			}
		posicion ++;
		int posicionesRestantes = unaDireccionEnString.length();
		for ( ; posicionesRestantes > posicion; posicion ++ ){
			unCaracter = unaDireccionEnString.charAt(posicion);
			palabra = (palabra) + (unCaracter);
			}

		return palabra;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	
	
}
