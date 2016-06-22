package dominio;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestRepoPOIS {

	Posicion posicionActual;
	Posicion posicionLejana;
	Posicion posicionComuna;
	POI bancoGalicia;
	POI parada60;
	POI cgp;
	Comuna comuna;
	Administrador roman;
	Set<String> listaTagGalicia = new HashSet<String>();
	Set<String> listaTagCGP8 = new HashSet<String>();
	LocalTime horarioAPerturaBanco;
	LocalTime horarioCierreBanco;
	Set<POI> listaPois = new HashSet<POI>();
	LocalTime horarioAperturaCGP;
	LocalTime horarioCierreCGP;
	Servicio rentas;
	Set<Servicio> serviciosCGP8= new HashSet<Servicio>();
	Set<String> tag60Parada = new HashSet<String>();
	LocalTime horarioManiana;
	LocalTime horarioNoche;

	

	@Before
	public void setUp() {
		roman = new Administrador();
		bancoGalicia = roman.crearBanco("Banco Galicia",1d,1d, "Av.Rivadavia", 2300, "Banco Galicia",listaTagGalicia, horarioAPerturaBanco, horarioCierreBanco);
		posicionActual = new Posicion(1d, 1d);
		listaTagGalicia.add("grande");
		listaTagGalicia.add("equina");
		horarioAPerturaBanco = LocalTime.of(10, 00);
		horarioCierreBanco = LocalTime.of(15,00);
		listaPois.add(bancoGalicia);

		cgp = roman.crearCGP("CGP",1d,1d,"Sarmiento", 1800, "CGP 8", listaTagCGP8, horarioAperturaCGP, horarioCierreCGP, comuna, rentas, serviciosCGP8);
		listaTagCGP8.add("Amplio");
		horarioAperturaCGP = LocalTime.of(8,00);
		horarioCierreCGP = LocalTime.of(18,00);	
		listaPois.add(cgp);
		parada60 = roman.crearParadaColectivo("Parada 60", 1d, 1d, "Panamericana y Marquez", 1000, "Parada 60", tag60Parada, horarioManiana, horarioNoche);
		horarioManiana =LocalTime.of(07, 00);
		horarioNoche = LocalTime.of(23, 30);
		listaPois.add(parada60);
	}
	
	@Test
	public void testHayBancoCreado() {
		assertTrue(listaPois.contains(bancoGalicia));
	}

	@Test
	public void testHayTresElementosEnLaListaPOIS() {
	assertTrue(listaPois.size()== 3);
}
	@Test
	public void borreElCGP() {
		roman.elimnarPOI(cgp,listaPois);
		assertFalse(listaPois.contains(cgp));
	}
}

