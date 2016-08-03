package dominio;

import org.junit.Test;

import dominio.adapter.AdapterBancoJSon;

import static org.junit.Assert.*;

public class testConsultaBancoExt extends setUp{

	@Test
	public void adaptaBienElBanco (){
		PuntoDeInteres bancoNuevo = adaptadorJSon.adaptFromToBanco(objetoJSon);
		assertTrue(bancoAdaptado.equals(bancoNuevo));
	}
	
	@Test
	public void buscarPorBancoTrue(){
		AdapterBancoJSon entidad = new AdapterBancoJSon();
		Boolean cantidad = entidad.dameLosPOIExternos("").size() == 2;
		assertTrue(cantidad);
	}
	
	@Test
	public void cantidadDeElementosDeRepositorioDespuesDelAdapter(){
		EntidadesExternas entidad = new EntidadesExternas();
		unRepo.actualizarListaNueva(entidad.busquedaExterna(""));
		assertEquals(unRepo.getPOIs().size(), 11);
	}
	
	@Test
	public void elminiarUnBancoExternoPorId(){
		EntidadesExternas entidad = new EntidadesExternas();
		unRepo.actualizarListaNueva(entidad.busquedaExterna(""));
		unRepo.deletePorId(10);
		assertEquals(unRepo.getPOIs().size(), 10);
	}
	
}
