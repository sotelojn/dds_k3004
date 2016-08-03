package dominio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dominio.exceptions.exceptionPuntoNull;

import java.util.ArrayList;
import java.util.List;

public class LocalTest {

	private Local unLocal;
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
		this.unHorario.setDia("Lunes");
		this.unHorario.setHoraInicio("16:00");
		this.unHorario.setHoraFin("19:00");
//		System.out.println(this.unHorario.getDia());
		this.horarios.add(unHorario);

		this.unLocal = new Local();
		this.unLocal.setNombreFantasia("Neverland");
		this.unLocal.setHorarios(this.horarios);
		
	}
	
	@Test
	public void testLocalDisponibilidad09() {
		Assert.assertFalse(this.unLocal.disponible("09:00","Inversiones"));
	}

	@Test
	public void testLocalDisponibilidad10() {
		Assert.assertFalse(this.unLocal.disponible("22:00","Neverland"));
	}

	@Test
	public void testLocalDisponibilidad13() {
		Assert.assertTrue(this.unLocal.disponible("13:00","Neverland"));
	}

	@Test
	public void testLocalDisponibilidad15() {
		Assert.assertFalse(this.unLocal.disponible("15:00","PEPE"));
	}
	
//	@Test
//	public void testLocalDisponibilidada() {
//		assertFalse("This will succeed.", false);
//	}
//
//	@Test
//	public void testLocalDisponibilidadb() {
//		assertFalse("This will fail!", true);
//	}

}
