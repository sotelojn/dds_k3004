package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class testDeBusquedaPorUsuario extends setUp{
		
	@Test
	public void busquedaDePOIPorUsuario(){
		Boolean cantidad = usuarioTerminal.realizarBuquedaDePOI("3", 5).size() == 2;
		assertTrue(cantidad);
	}
	
	@Test
	public void busquedaDePOIPorUsuarioSinAcciones(){
		Boolean cantidad = usuarioTerminalSinAcciones.realizarBuquedaDePOI("3", 5).size() == 2;
		assertTrue(cantidad);
	}
	
}
