package dominio;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import dominio.RepoDePOIs;
import dominio.Administrador;
import dominio.Comuna;
import dominio.POI;
import dominio.Posicion;
import dominio.Servicio;

public class TestRepoPOIS {

	Posicion posicionActual;
	Posicion posicionLejana;
	Posicion posicionComuna;
	POI parada60;
	Comuna comuna;
	Administrador roman;
	Set<String> listaTagGalicia = new HashSet<String>();
	Set<String> listaTagCGP8 = new HashSet<String>();
	LocalTime horarioAPerturaBanco;
	LocalTime horarioCierreBanco;
	LocalTime horarioAperturaCGP;
	LocalTime horarioCierreCGP;
	Servicio rentas;
	Set<Servicio> serviciosCGP8= new HashSet<Servicio>();
	Set<String> tag60Parada = new HashSet<String>();
	LocalTime horarioManiana;
	LocalTime horarioNoche;
	RepoDePOIs repositorio = new RepoDePOIs();
	

	@Before
	public void setUp() {
		roman = new Administrador();
		posicionActual = new Posicion(1d, 1d);
		listaTagGalicia.add("grande");
		listaTagGalicia.add("equina");
		horarioAPerturaBanco = LocalTime.of(10, 00);
		horarioCierreBanco = LocalTime.of(15,00);
		roman.crearBanco("Banco Galicia",1d,1d, "Av.Rivadavia", 2300, "Banco Galicia", listaTagGalicia, horarioAPerturaBanco, horarioCierreBanco, null,repositorio);

		listaTagCGP8.add("Amplio");
		listaTagCGP8.add("Amplio");
		horarioAperturaCGP = LocalTime.of(8,00);
		horarioCierreCGP = LocalTime.of(18,00);	
		roman.crearCGP("CGP",1d,1d,"Sarmiento", 1800, "CGP 8", listaTagCGP8, horarioAperturaCGP, horarioCierreCGP, comuna, rentas, serviciosCGP8, repositorio);

		horarioManiana =LocalTime.of(07, 00);
		horarioNoche = LocalTime.of(23, 30);
		roman.crearParadaColectivo("Parada 60", 1d, 1d, "Panamericana y Marquez", 1000, "Parada 60", tag60Parada, horarioManiana, horarioNoche,repositorio);


	}
	
	@Test
	public void testHayBancoCreado() {
	assertTrue(repositorio.existePOI("Banco Galicia"));
	}

	@Test
	public void testHayTresElementosEnLaListaPOIS() {
	
	assertTrue(repositorio.cantEnLista()== 3);
}
	@Test
	public void borraElCGP() {
		roman.eliminarPOI("Parada 60", repositorio);
		roman.eliminarPOI("Banco Galicia", repositorio);

		assertTrue(repositorio.cantEnLista()== 0);
	//	assertFalse(repositorio.existePOI("Banco Galicia"));

	}
}

