package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class testConsultaDTO extends setUp{

	@Test
	public void funcionaElAdapterTrue(){
		assertEquals(entidad.busquedaExterna("").size(), 4);
	}

	//@Test
	//public void adaptaBienLoExterno(){
	//	PuntoDeInteres centroNuevo = adaptadorCentroDTO.adaptFromToCGP(cgp3DTO);
	//	assertTrue(cgp3.equals(centroNuevo));
	//}
	
	@Test
	public void cantidadDeElementosDeRepositorioDespuesDelAdapter(){
		unRepo.actualizarListaNueva(entidad.busquedaExterna(""));
		assertEquals(unRepo.getPOIs().size(), 11);
	}
	
	@Test
	public void eliminarPOI(){
		unRepo.actualizarListaNueva(entidad.busquedaExterna(""));
		unRepo.delete(cgp3);
		assertTrue(unRepo.getPOIs().size() == 10);
	}
	
	@Test
	public void eliminarUnPOIDelRepositorioPorPOI(){
		unRepo.delete(galiciaDevoto);
		assertEquals(unRepo.getPOIs().size(),6);
	}
	
	@Test
	public void eliminarUnPOIDelRepositorioPorID(){
		unRepo.deletePorId(1);
		assertEquals(unRepo.getPOIs().size(),6);
	}
	
	@Test
	public void eliminarunPOIDelRepositorioPorCoordenadaUnica(){
		unRepo.deletePorCoordenadaUnica(coordenadaGaliciaDevoto);
		assertEquals(unRepo.getPOIs().size(),6);
		
	}
	
}
