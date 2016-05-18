package dominio;


import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;



public class TestDisponibilidad {
	

	Banco bancoGalicia;
	ParadaColectivo parada60;
	Comercio kiosko;
	CGP municipalidad;
	Servicio rentas;
	LocalTime horarioAPerturaBanco;
	LocalTime horarioCierreBanco;
	LocalTime unHorario;
	LocalTime otroHorario;

	
	
	
	@Before
	public void setUp() {

	bancoGalicia = new Banco("Galicia", 1d, 1d);
	Set<Dia> diasAtencionBanco = new HashSet<Dia>();
	diasAtencionBanco.add(Dia.LUNES);
	diasAtencionBanco.add(Dia.MARTES);
	diasAtencionBanco.add(Dia.MIERCOLES);
	diasAtencionBanco.add(Dia.JUEVES);
	diasAtencionBanco.add(Dia.VIERNES);
	bancoGalicia.setDiasDeAtencion(diasAtencionBanco);
	horarioAPerturaBanco = LocalTime.of(10, 00);
	horarioCierreBanco = LocalTime.of(15,00);
	unHorario =LocalTime.of(13, 00);
	otroHorario = LocalTime.of(20, 00);
	bancoGalicia.setHorarioApertura(horarioAPerturaBanco);
	bancoGalicia.setHorarioCierre(horarioCierreBanco);
	
	parada60 = new ParadaColectivo("Panamericana y Marquez", 1d, 1d);
	
	kiosko = new Comercio("La petaca feliz", 1d, 1d);
	Rubro golosinas = new Rubro();
	golosinas.setDescripcion("Golosinas");
	golosinas.setRangoCercania(10);
	kiosko.setRubro(golosinas);
	}
	
	@Test
	public void testParadaDeColectivoEstaSiempreDisponibleo() {
		assertTrue(parada60.estaDisponible(Dia.SABADO,horarioCierreBanco));
	}
	@Test
	public void testDisponibilidadBanco() {
		assertTrue(bancoGalicia.estaDisponible(Dia.MARTES,unHorario));
		
	}
	
	@Test
	public void testDisponibilidadBancoCerrado() {
		assertFalse(bancoGalicia.estaDisponible(Dia.MARTES,otroHorario));
		
	}
	
}
