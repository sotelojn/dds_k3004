
package dominio;

import java.util.Set;
import java.util.stream.Stream;

public class Usuario {

private Posicion posicionUsuario;

public Posicion getPosicionUsuario() {
	return posicionUsuario;
}

public void setPosicionUsuario(Posicion posicionUsuario) {
	this.posicionUsuario = posicionUsuario;
}

public boolean estaCercaDe(POI unPoi)
{
 return	unPoi.estaCerca(this.posicionUsuario);
}

public static Stream<POI> buscar (String text,Set<POI> lista) {
return lista.stream()
			   .filter(poi -> poi.matches(text));
}


}
