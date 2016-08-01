package ar.edu.utn.d2s;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ar.edu.utn.d2s.exceptions.exceptionPuntoNull;

import java.util.ArrayList;
import java.util.List;

public class BancoTest {
	
	private Banco unBanco;
	private HorarioAtencion unHorario;
	private List<HorarioAtencion> horarios;	

	@Before
	public void setUpBeforeClass() throws Exception {
		
		// Inicializo Algo muy pesado por unica vez
		this.horarios = new ArrayList<HorarioAtencion>();

		this.unHorario = new HorarioAtencion();
		this.unHorario.setDia("Lunes");
		this.unHorario.setHoraInicio("10:00");
		this.unHorario.setHoraFin("15:00");
//		System.out.println(this.unHorario.getDia());
		this.horarios.add(unHorario);

		this.unHorario = new HorarioAtencion();
		this.unHorario.setDia("Martes");
		this.unHorario.setHoraInicio("10:00");
		this.unHorario.setHoraFin("15:00");
//		System.out.println(this.unHorario.getDia());
		this.horarios.add(unHorario);

		this.unHorario = new HorarioAtencion();
		this.unHorario.setDia("Miercoles");
		this.unHorario.setHoraInicio("10:00");
		this.unHorario.setHoraFin("15:00");
//		System.out.println(this.unHorario.getDia());
		this.horarios.add(unHorario);

		this.unHorario = new HorarioAtencion();
		this.unHorario.setDia("Jueves");
		this.unHorario.setHoraInicio("10:00");
		this.unHorario.setHoraFin("15:00");
//		System.out.println(this.unHorario.getDia());
		this.horarios.add(unHorario);

		this.unHorario = new HorarioAtencion();
		this.unHorario.setDia("Viernes");
		this.unHorario.setHoraInicio("10:00");
		this.unHorario.setHoraFin("15:00");
//		System.out.println(this.unHorario.getDia());
		this.horarios.add(unHorario);

		this.unBanco = new Banco();
//		this.unBanco.setHorarios(this.horarios);
		
	}
//	
//	@Test
//	public void testBancoDisponibilidad09() {
//		assertFalse(this.unBanco.disponible("09:00","Inversiones"));
//	}
//
//	@Test
//	public void testBancoDisponibilidad10() {
//		assertTrue(this.unBanco.disponible("10:00","Prestamos"));
//	}
//
//	@Test
//	public void testBancoDisponibilidad13() {
//		assertTrue(this.unBanco.disponible("13:00","Atencion Cliente"));
//	}
//
//	@Test
//	public void testBancoDisponibilidad15() {
//		assertTrue(this.unBanco.disponible("15:00","Plazo Fijo"));
//	}
//
//	@Test
//	public void testBancoDisponibilidad16() {
//		assertFalse(this.unBanco.disponible("16:00","Depositos"));
//	}
//
//	@Test
//	public void testBancoDisponibilidad23() {
//		assertFalse(this.unBanco.disponible("23:00","Caja"));
//	}

}
