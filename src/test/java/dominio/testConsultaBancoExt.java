package dominio;

import org.junit.Test;

import static org.junit.Assert.*;

public class testConsultaBancoExt extends setUp{

	@Test
	public void adaptaBienElBanco (){
		PuntoDeInteres bancoNuevo = adaptadorJSon.adaptFromToBanco(objetoJSon);
		assertTrue(bancoAdaptado.equals(bancoNuevo));
	}
	
	@Test
	public void buscarPorBancoTrue(){
		Boolean cantidad = adaptadorJSon.dameLosPOIExternos("").size() == 2;
		assertTrue(cantidad);
	}
	
	@Test
	public void cantidadDeElementosDeRepositorioDespuesDelAdapter(){
		unRepo.actualizarListaNueva(entidad.busquedaExterna(""));
		assertEquals(unRepo.getPOIs().size(), 11);
	}
	
	@Test
	public void elminiarUnBancoExternoPorId(){
		unRepo.actualizarListaNueva(entidad.busquedaExterna(""));
		unRepo.deletePorId(10);
		assertEquals(unRepo.getPOIs().size(), 10);
	}
	
}
