package dominio;

import static org.junit.Assert.*;

import org.uqbar.geodds.Polygon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dominio.exceptions.exceptionPuntoNull;

import java.util.ArrayList;
import java.util.List;
public class CercaniaTest {

	private Colectivo colectivo114;
	private Coordenada c1;
	private Coordenada c2;
	private Coordenada c3;
	private Parada parada1;
	private Parada parada2;
	private CGP CGP10;
	private Coordenada v1; //-34.664812, -58.469830
	private Coordenada v2; //-34.656710, -58.460401
	private Coordenada v3; //-34.649869, -58.470087
	private Coordenada v4; //-34.657818, -58.478747
	private Polygon zona10;
	private Comuna comuna10;
//	private final String linea = "UnNombreCualquiera";
//	private final Integer latitud = 1;
//	private final Integer longitud = 2;

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
		this.colectivo114 = new Colectivo();
		this.colectivo114.setLinea(114);
		//utn -34.6607211,-58.4667791
		
		this.c1 = new Coordenada(-34.6607211,-58.4667791);
		this.c2 = new Coordenada(-34.660966, -58.467826);
		this.c3 = new Coordenada(-34.66080, -58.4678);
		this.parada1 = new Parada();
		this.parada2 = new Parada();
		this.parada1.setCoordenada(this.c1);
		this.parada2.setCoordenada(this.c2);
		//List<Parada> paradas = new List<Parada>();
		this.colectivo114.addParada(this.parada1);
		this.colectivo114.addParada(this.parada2);
		
		this.CGP10 = new CGP();
		this.v1 = new Coordenada(-34.664812, -58.469830);
		this.v2 = new Coordenada(-34.656710, -58.460401);
		this.v3 = new Coordenada(-34.649869, -58.470087);
		this.v4 = new Coordenada(-34.657818, -58.478747);
		this.zona10 = new Polygon();
		this.zona10.add(v1);
		this.zona10.add(v2);
		this.zona10.add(v3);
		this.zona10.add(v4);
		this.comuna10 = new Comuna();
		this.comuna10.setZona(this.zona10);
		this.CGP10.setComuna(this.comuna10);
		// Notar que el objeto se vuelve a instanciar cada vez que se corre un
		// test
	}

	@After
	public void tearDown() throws Exception {
		// Despues de cada test se ejecuta esto
	}

	@Test
	public void estaCercaColectivo() {
		assertTrue(this.colectivo114.estaCercaDe(c3));
	}

	@Test
	public void estaCercaCGP() {
		assertTrue(this.CGP10.estaCercaDe(c3));
	}
//	
//	@Test
//	public void testLargoNombreErroneo() {
//		this.objetoAProbar.setDireccion("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); 
//		assertFalse(60 > this.objetoAProbar.largoDireccion());
//	}
//	
//	@Test(expected = exceptionPuntoNull.class)
//	public void testSetearPunto1Erroneo() throws Exception {
//		this.objetoAProbar.setLatitud(null);
//	}
//
//	@Test(expected = exceptionPuntoNull.class)
//	public void testSetearPunto2Erroneo() throws Exception {
//		this.objetoAProbar.setLongitud(null);
//	}	
//	
//	@Test(expected = exceptionPuntoNull.class)
//	public void testSetearPuntosErroneosCaso1() throws Exception {
//		this.objetoAProbar.setPuntos(null,null);
//	}	
//	
//	@Test(expected = exceptionPuntoNull.class)
//	public void testSetearPuntosErroneosCaso2() throws Exception {
//		this.objetoAProbar.setPuntos(1,null);
//	}	
//	@Test(expected = exceptionPuntoNull.class)
//	public void testSetearPuntosErroneosCaso3() throws Exception {
//		this.objetoAProbar.setPuntos(null,1);
//	}	
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