package test.java.dominio;

import static org.junit.Assert.*;

import main.java.dominio.ConjuntoDePOIs;
import main.java.dominio.POI;

public class TestFiltro {
	
	ConjuntoDePOIs conjuntoInicial;
	POI colectivo114;
	POI colectivo152;
	
	@Before
	public void setUp() {
		colectivo114 = new POI("Parada 114","Monroe",2900,"Parada del colectivo 114 (esquina Cramer)",("Colectivo","144","Parada"),(1d,1d));
		colectivo152 = new POI("Parada 152","Cabildo",2500,"Parada del colectivo 152 (esquina Monroe)",("Colectivo","152","Parada"),(100d,100d));
		conjuntoInicial = new ConjuntoDePOIs();
		
		conjuntoInicial.pois.add(colectivo114);
		conjuntoInicial.pois.add(colectivo152);
		
	}
	
	@Test
		public void testFlitroPor114() {
		AssertEquals(conjuntoInicial.buscar("114").count(), 1);
		}
	
}
