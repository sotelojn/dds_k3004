package ar.edu.utn.d2s;

import static org.junit.Assert.*;

import org.junit.Test;

public class testDeBusquedaPorUsuario extends setUp{
		
	@Test
	public void busquedaDePdiPorUsuario(){
		Boolean cantidad = usuarioTerminal.realizarBuquedaDePdi("3", 5).size() == 2;
		assertTrue(cantidad);
	}
	
	@Test
	public void busquedaDePdiPorUsuarioSinAcciones(){
		Boolean cantidad = usuarioTerminalSinAcciones.realizarBuquedaDePdi("3", 5).size() == 2;
		assertTrue(cantidad);
	}
	
}
