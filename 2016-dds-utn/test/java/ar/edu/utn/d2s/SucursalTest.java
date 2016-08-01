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

public class SucursalTest {

	private Sucursal unaSucursal;
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

		this.unaSucursal = new Sucursal();
		this.unaSucursal.setHorarios(this.horarios);
		
	}
	
	@Test
	public void testSucursalDisponibilidad09() {
		assertFalse(this.unaSucursal.disponible("09:00","Inversiones"));
	}

	@Test
	public void testSucursalDisponibilidad10() {
		assertTrue(this.unaSucursal.disponible("10:00","Prestamos"));
	}

	@Test
	public void testSucursalDisponibilidad13() {
		assertTrue(this.unaSucursal.disponible("13:00","Atencion Cliente"));
	}

	@Test
	public void testSucursalDisponibilidad15() {
		assertTrue(this.unaSucursal.disponible("15:00","Plazo Fijo"));
	}

	@Test
	public void testSucursalDisponibilidad16() {
		assertFalse(this.unaSucursal.disponible("16:00","Depositos"));
	}

	@Test
	public void testSucursalDisponibilidad23() {
		assertFalse(this.unaSucursal.disponible("23:00","Caja"));
	}

}
