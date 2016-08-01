package ar.edu.utn.d2s;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ar.edu.utn.d2s.exceptions.exceptionPuntoNull;

import java.util.ArrayList;
import java.util.List;

public class ColectivoTest {

	private Colectivo unColectivo;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		// Inicializo Algo muy pesado por unica vez
		this.unColectivo = new Colectivo();
		this.unColectivo.setLinea(101);;
	}
	
	@Test
	public void testColectivoDisponibilidad09() {
		assertTrue(this.unColectivo.disponible("09:00","pepe"));
	}

	@Test
	public void testColectivoDisponibilidad21() {
		assertTrue(this.unColectivo.disponible("10:00","Ventas"));
	}

}
