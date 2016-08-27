package dominio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class testDeBusqueda extends setUp{

	@Test
	public void seEncuentraEnLaCache(){
		List<PuntoDeInteres> listaDePOIs = new ArrayList<PuntoDeInteres>();
		listaDePOIs.add(galiciaDevoto);
		listaDePOIs.add(galiciaDevoto);
		Consulta consulta = new Consulta("Gali", listaDePOIs);
		unRepo.getCache().add(consulta);
		int cant = unRepo.buscarPOI("Gali").size();
		assertTrue ( cant == 2);
	}
	
	@Test
	public void seEncuentraLaSucursalEnLaBusquedaTrue(){
		Boolean encuentraSucursal = unRepo.buscarPOI("Gali").size() == 2;
		assertTrue(encuentraSucursal);
	}
	
	@Test
	public void seEncuentraLaSucursalEnLaBusquedaFalse(){
		Boolean NoencuentraSucursal = unRepo.buscarPOI("Frances").size() >= 1;
		assertFalse(NoencuentraSucursal);
	}
	
	@Test
	public void seEncuentraElCGP3EnLaBusquedaTrue(){
		Boolean seEncuentraCGP3 = unRepo.buscarPOI("3").size() == 2;
		assertTrue(seEncuentraCGP3);
	}
	
	public void seEncuentraElCGP117ConServicioRentasTrue(){
		Boolean seEncuentraCGP117ServicioRentas = unRepo.buscarPOI("rentas").size() == 1;
		assertTrue(seEncuentraCGP117ServicioRentas);
	}
	
	@Test
	public void encuentraCGPyColectivo117True(){
		Boolean seEncuentranCGPyColectivo117 = unRepo.buscarPOI("117").size() == 2;
		assertTrue(seEncuentranCGPyColectivo117);
	}
	
	@Test
	public void seEncuentraLaParadaDeColectivo2CosquinEnLaBusquedaTrue(){
		Boolean n = unRepo.buscarPOI("2").size() == 1;
		
		assertTrue(n);
		
	}

	@Test
	public void seEncuentraLaParadaDeColectivo114CosquinEnLaBusquedaFalse(){
		Boolean n = unRepo.buscarPOI("114").size() == 1;
		assertFalse(n);
	}
	
	@Test
	public void seEncuentraElLocalRamon3EnLaBusquedaTrue(){
		Boolean n = unRepo.buscarPOI("polleras").size() == 1;		
		assertTrue(n);
	}
	
	@Test
	public void seEncuentraElLocalRamon3EnLaBusquedaFalse(){
		Boolean n = unRepo.buscarPOI("camisas").size() == 1;		
		assertFalse(n);
	}
	
	
	
}
