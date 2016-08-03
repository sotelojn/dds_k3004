package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class testDeDisponibilidad extends setUp {
	
	@Test
	public void testBancoDisponibilidad1FalsePorServicio() {
		assertFalse(galiciaDevoto.estaDisponible(9.00,"Inversiones", lunes));
	}
	
	@Test
	public void testBancoDisponibilidad1FalsePorHorario() {
		assertFalse(galiciaDevoto.estaDisponible(19.00,"Cheques", lunes));
	}

	@Test
	public void testBancoDisponibilidadTrue() {
		assertTrue(galiciaDevoto.estaDisponible(11.00,"Cheques", lunes));
	}

	@Test
	public void testCGPOIsponibilidadFalsePorServicio(){
		assertFalse(cgp3.estaDisponible(9.00, "localizacion", lunes));
	}
	
	@Test
	public void testCGPOIsponibilidadFalsePorHorario(){
		assertFalse(cgp3.estaDisponible(20.00, "Pension", lunes));
	}
	
	@Test
	public void testCGPOIsponibilidadTrue(){
		assertTrue(cgp117.estaDisponible(12.00, "Pension", lunes));
	}
	
	@Test
	public void testCGPOIsponibilidadDeAlgunServicioConStringVacio(){
		assertTrue(cgp3.estaDisponible(12.00, "", lunes) );
	}
	
	@Test
	public void testCGPOIsponibilidadDeAlgunServicioConVariableNull(){
		assertTrue(cgp3.estaDisponible(12.00, lunes) );
	}
	
	@Test
	public void testColectivoDisponibilidadTrueLunes(){
		assertTrue(colectivo117.estaDisponible(1.00, lunes));
	}
	
	@Test
	public void testColectivoDisponibilidadTrueMartes(){
		assertTrue(colectivo117.estaDisponible(12.00, martes));
	}
	
	@Test
	public void testLocalDisponibilidadTrue(){
		assertTrue(localRamon.estaDisponible(11.00, lunes));
	}
	
	@Test
	public void testLocalDisponibilidadFalsePorHora(){
		assertFalse(localRamon.estaDisponible(18.00, martes));
	}
	
	@Test
	public void testLocalDisponibilidadFalsePorDia(){

		assertFalse(localRamon.estaDisponible(13.00, domingo));
	}

	
}
