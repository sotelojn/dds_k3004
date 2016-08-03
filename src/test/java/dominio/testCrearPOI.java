package dominio;

import static org.junit.Assert.*;


import org.junit.Test;

public class testCrearPOI extends setUp{

//	unRepo.crearUnaSucursalBancaria(direccionGalicia, "Galicia", "galiciaDevoto", coordenadaGaliciaDevoto, listaDeServicios);
//	unRepo.crearUnColectivo(direccion117, "117", coordenadaColectivo117, 117, paradasDeColectivo117);
//	unRepo.crearUnColectivo(direccion2, "2", coordenada2Rivadavia, 2, paradasDeColectivo2);
//	unRepo.crearUnCGP(direccionCGP3, coordenadaCGP3, "cgp3", comunaCGP3);
//	unRepo.crearUnCGP(direccionCGP117, coordenadaCGP117, "cgp117", comunaCGP117);
//	unRepo.crearUnLocalComercial(direccionRamon, "Los Ramones", coordenadaRamon, comercial, disponibiidadRamon);
//	unRepo.agregarPOIConId(galiciaDevoto);}
	
	
	@Test
	public void usuarioCreaNuevoPOI(){
		roman.crearBanco(direccionGalicia, "Galicia", "galiciaDevoto", coordenadaGaliciaDevoto, listaDeServicios);
		roman.crearCGP(direccionCGP3, coordenadaCGP3, "cgp3", comunaCGP3);
		assertTrue(unRepo2.tamanio()==2);
	}
	
	@Test
	public void seEncuentraLaSucursalEnLaBusquedaFalse(){
		Boolean NoencuentraSucursal = unRepo.buscarPOI("Frances").size() >= 1;
		assertFalse(NoencuentraSucursal);
	}

	
	
	
}
