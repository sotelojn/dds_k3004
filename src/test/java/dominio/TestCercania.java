package dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCercania {

	Posicion posicionActual;
	Posicion posicionLejana;
	Posicion posicionComuna;
	Banco bancoGalicia;
	ParadaColectivo parada60;
	Comercio kiosko;
	CGP cgp;
	Comuna comuna;
	
	@Before
	public void setUp() {
		posicionActual = new Posicion(1d, 1d);
		posicionLejana = new Posicion(100d, 100d);
		posicionComuna = new Posicion(1d, 1d);
		bancoGalicia = new Banco("Galicia", 1d, 1d);
		parada60 = new ParadaColectivo("Panamericana y Marquez", 1d, 1d);
		cgp = new CGP ("cgp",1d,1d);
		kiosko = new Comercio("La petaca feliz", 1d, 1d);
		Rubro golosinas = new Rubro();
		golosinas.setDescripcion("Golosinas");
		golosinas.setRangoCercania(10);
		kiosko.setRubro(golosinas);
		comuna = new Comuna(10d, posicionComuna);
		cgp.setComuna(comuna);
	}
	
	@Test
	public void testBancoEstaCercaDelMismoPunto() {
		assertTrue(bancoGalicia.estaCerca(posicionActual));
	}
	
	@Test
	public void testBancoEstaLejosDeUnaPosicionLejana() {
		assertFalse(bancoGalicia.estaCerca(posicionLejana));
	}
	
	@Test
	public void testKioskoEstaCercaDelMismoPunto() {
		assertTrue(kiosko.estaCerca(posicionActual));
	}
	
	@Test
	public void testKioskoEstaLejosDeUnaPosicionLejana() {
		assertFalse(kiosko.estaCerca(posicionLejana));
	}
	
	@Test
	public void testParadaDeColectivoEstaCercaDelMismoPunto() {
		assertTrue(parada60.estaCerca(posicionActual));
	}
	
	@Test
	public void testParadaDeColectivoEstaLejosDeUnaPosicionLejana() {
		assertFalse(parada60.estaCerca(posicionLejana));
	}
	
	
	@Test
	public void testCgpEstaCercaDelMismoPunto() {
		assertTrue(cgp.estaCerca(posicionActual));
	}
	
	@Test
	public void testCgpEstaLejosDeUnaPosicionLejana() {
		assertFalse(cgp.estaCerca(posicionLejana));
	}
}
