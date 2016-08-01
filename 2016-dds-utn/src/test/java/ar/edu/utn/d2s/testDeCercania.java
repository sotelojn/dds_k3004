package ar.edu.utn.d2s;

import static org.junit.Assert.*;

import org.junit.Test;

public class testDeCercania extends setUp{
	
	@Test
	public void cercaniaDeSucursalFalse(){
		assertFalse (galiciaDevoto.estaCercaDe(coordenadaLejana));
	}
	
	@Test
	public void cercaniaDeSucursalTrue(){
		assertTrue (galiciaDevoto.estaCercaDe(coordenadaCercanaParaSucursalGalicia));
	}
	
	@Test
	public void cercaniaDeCGP3False(){
		assertFalse (cgp3.estaCercaDe(coordenadaLejana));
	}
	
	@Test
	public void cercaniaDeCGP3True(){
		assertTrue (cgp3.estaCercaDe(coordenadaCercanaDeCGP3));
	}
	
	@Test
	public void cercaniaDeParadaDeColectivo117CosquinFalse(){
		assertFalse (colectivo117.estaCercaDe(coordenadaLejana));
	}

	@Test
	public void cercaniaDeParadaDeColectivo117CosquinNuevaFuncionTrue(){
		assertTrue (colectivo117.estaCercaDe());
	}
	
	@Test
	public void cercaniaDeParadaDeColectivo117CosquinTrue(){
		assertTrue (colectivo117.estaCercaDe(coordenadaCercanaDeParadaDeColectivo117DeCosquin));
	}

	@Test
	public void cercaniaDeLocalFalse(){
		assertFalse (localRamon.estaCercaDe(coordenadaLejana));
	}
	
	@Test
	public void cercaniaDeLocalTrue(){
		assertTrue (localRamon.estaCercaDe(cordenadaCercanaLocalRamon));
	}
	
}
