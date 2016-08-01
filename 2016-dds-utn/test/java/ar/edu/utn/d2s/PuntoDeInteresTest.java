package ar.edu.utn.d2s;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ar.edu.utn.d2s.exceptions.exceptionPuntoNull;

public class PuntoDeInteresTest {

	private PuntoDeInteres objetoAProbar;
	private final String nombrePrincipal = "UnNombreCualquiera";
	private final Integer latitud = 1;
	private final Integer longitud = 2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Inicializo Algo muy pesado por unica vez
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Cierro recursos por unica vez
	}

	@Before
	public void setUp() throws Exception {
		// Antes de cada test se ejecuta esto
		this.objetoAProbar = new Colectivo();
		this.objetoAProbar.setDireccion(this.nombrePrincipal);
		this.objetoAProbar.setLatitud(this.latitud);
		this.objetoAProbar.setLongitud(this.longitud);
		// Notar que el objeto se vuelve a instanciar cada vez que se corre un
		// test
	}

	@After
	public void tearDown() throws Exception {
		// Despues de cada test se ejecuta esto
	}

	@Test
	public void testLargoNombre() {
		assertTrue(60 > this.objetoAProbar.largoDireccion());
	}

	@Test
	public void testNombreNoNulo() {
		assertNotNull(this.objetoAProbar.getDireccion());
	}
	
	@Test
	public void testLargoNombreErroneo() {
		this.objetoAProbar.setDireccion("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); 
		assertFalse(60 > this.objetoAProbar.largoDireccion());
	}
	
	@Test(expected = exceptionPuntoNull.class)
	public void testSetearPunto1Erroneo() throws Exception {
		this.objetoAProbar.setLatitud(null);
	}

	@Test(expected = exceptionPuntoNull.class)
	public void testSetearPunto2Erroneo() throws Exception {
		this.objetoAProbar.setLongitud(null);
	}	
	
	@Test(expected = exceptionPuntoNull.class)
	public void testSetearPuntosErroneosCaso1() throws Exception {
		this.objetoAProbar.setPuntos(null,null);
	}	
	
	@Test(expected = exceptionPuntoNull.class)
	public void testSetearPuntosErroneosCaso2() throws Exception {
		this.objetoAProbar.setPuntos(1,null);
	}	
	@Test(expected = exceptionPuntoNull.class)
	public void testSetearPuntosErroneosCaso3() throws Exception {
		this.objetoAProbar.setPuntos(null,1);
	}	
	//	@Test
	//public void testEquals() {
	//		UnaClaseMuySimpatica otroObjetoQueDeberiaSerIgual = new UnaClaseMuySimpatica();
	//		otroObjetoQueDeberiaSerIgual.setNombre(this.nombrePrincipal);
	//		assertTrue("El equals no anda, poner siempre estas aclaraciones",
	//			this.objetoAProbar.equals(otroObjetoQueDeberiaSerIgual));
	//}

//	@Test
//	public void testQueTodaviaNoHice() {s
//		fail("Not yet implemented, no entreguen test asi, como mucho @Ignore");
//	}

//	@Test
//	@Ignore
//	public void testQueIgnoroPorqueEstoyEnDuda() {
//		fail("Not yet implemented");
//	}
// probando git
}