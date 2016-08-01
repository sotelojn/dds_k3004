package ar.edu.utn.d2s;

import static org.junit.Assert.*;

import org.junit.Test;

public class testConsultaDTO extends setUp{

	@Test
	public void funcionaElAdapterTrue(){
		EntidadesExternas entidad = new EntidadesExternas();
		assertEquals(entidad.busquedaExterna("").size(), 4);
	}

	@Test
	public void adaptaBienLoExterno(){
		PuntoDeInteres centroNuevo = adaptadorCentroDTO.adaptFromToCGP(cgp3DTO);
		assertTrue(cgp3.equals(centroNuevo));
	}
	
	@Test
	public void cantidadDeElementosDeRepositorioDespuesDelAdapter(){
		EntidadesExternas entidad = new EntidadesExternas();
		unRepo.actualizarListaNueva(entidad.busquedaExterna(""));
		assertEquals(unRepo.getPDIs().size(), 11);
	}
	
	@Test
	public void eliminarUnPDIDelRepositorioPorPdi(){
		unRepo.delete(galiciaDevoto);
		assertEquals(unRepo.getPDIs().size(),6);
	}
	
	@Test
	public void eliminarUnPDIDelRepositorioPorID(){
		unRepo.deletePorId(1);
		assertEquals(unRepo.getPDIs().size(),6);
	}
	
	@Test
	public void eliminarunPDIDelRepositorioPorCoordenadaUnica(){
		unRepo.deletePorCoordenadaUnica(coordenadaGaliciaDevoto);
		assertEquals(unRepo.getPDIs().size(),6);
		
	}
	
}
