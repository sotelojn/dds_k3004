
package dominio;

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

}
