package test.java.dominio;


import dominio.RepoDePOIs;
import dominio.Administrador;
import dominio.Comuna;
import dominio.POI;
import dominio.Posicion;
import static org.junit.Assert.*;


public class TestFiltro {
	
	RepoDePOIs conjuntoInicial;
	POI colectivo114;
	
	@Before
	public void setUp() {
		colectivo114 = new POI("Parada 114","Monroe",2900,"Parada del colectivo 114 (esquina Cramer)",("Colectivo","144","Parada"),(1d,1d));
		colectivo152 = new POI("Parada 152","Cabildo",2500,"Parada del colectivo 152 (esquina Monroe)",("Colectivo","152","Parada"),(100d,100d));
		conjuntoInicial = new RepoDePOIs();
		
		conjuntoInicial.pois.add(colectivo114);
		conjuntoInicial.pois.add(colectivo152);
		
	}
	
	@Test
		public void testFlitroPor114() {
		AssertEquals(conjuntoInicial.buscar("114").count(), 1);
		}
	
}
