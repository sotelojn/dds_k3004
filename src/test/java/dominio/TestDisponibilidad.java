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
	HorarioDeAtencion horarioAtencion;
	HorarioDeAtencion otroHorarioAtencion;
	LocalTime horarioTarde;
	LocalTime horarioNoche;
	LocalTime horarioTardecita;
	
	
	
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
	horarioAtencion = new HorarioDeAtencion();
	otroHorarioAtencion = new HorarioDeAtencion();
	Set<Dia> diasAtencionKiosco = new HashSet<Dia>();
	Set<HorarioDeAtencion> horariosAtencionKiosco = new HashSet<HorarioDeAtencion>();
	diasAtencionKiosco.add(Dia.LUNES);
	kiosko.setDiasDeAtencion(diasAtencionKiosco);
	horarioAtencion.setHorarioApertura(horarioAPerturaBanco);
	horarioAtencion.setHorarioCierre(horarioCierreBanco);
	horarioTarde =LocalTime.of(18, 00);
	horarioNoche = LocalTime.of(20, 00);
	horarioTardecita = LocalTime.of(19, 00);
	otroHorarioAtencion.setHorarioApertura(horarioTarde);
	otroHorarioAtencion.setHorarioCierre(horarioNoche);
	horariosAtencionKiosco.add(horarioAtencion);
	horariosAtencionKiosco.add(otroHorarioAtencion);
	
	Rubro golosinas = new Rubro();
	golosinas.setDescripcion("Golosinas");
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
	
	/*
	@Test
	public void testDisponibilidadKioscoAbierto() {
		assertFalse(kiosko.estaDisponible(Dia.LUNES,horarioTardecita));
		
	}
	TODO ARREGLAR LOS MULTIPLES HORARIOS
	*/
	
	
}
