package dominio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CGPsTest {
	private CentroDTO centroDTO1;
	private ServicioDTO servicio1;
	private ServicioDTO servicio2;
	// private List<ServicioDTO> servicios;
	private RangoServicioDTO rango1;
	private RangoServicioDTO rango2;
	private RangoServicioDTO rango3;
	private RangoServicioDTO rango4;
	private RangoServicioDTO rango5;
	private RangoServicioDTO rango1b;
	private RangoServicioDTO rango2b;
	private RangoServicioDTO rango3b;
	private List<CentroDTO> centrosDTO;
	private Repositorio repoCGP;

	@Before

	public void setUp() throws Exception {

		this.centrosDTO = new ArrayList<CentroDTO>();

		this.centroDTO1 = new CentroDTO();
		this.centroDTO1.setNumeroComuna(3);
		this.centroDTO1.setZonasIncluidas("Balvanera, San Cristóbal");
		this.centroDTO1.setNombreDirector("Juan Gonzales");
		this.centroDTO1.setDomicilio("Junin 2053");
		this.centroDTO1.setTelefono("43750644/45)");
		// this.servicios = new ArrayList<ServicioDTO>();

		this.servicio1 = new ServicioDTO();
		this.servicio1.setNombreServicio("Atencion Ciudadana");
		this.servicio2 = new ServicioDTO();
		this.servicio2.setNombreServicio("Rentas");
		this.centroDTO1.addServicio(servicio1);
		this.centroDTO1.addServicio(servicio2);

		this.rango1 = new RangoServicioDTO();
		this.rango1.setNumeroDia(1);
		this.rango1.setHorarioDesde(9);
		this.rango1.setMinutosDesde(0);
		this.rango1.setHorarioHasta(15);
		this.rango1.setMinutosHasta(30);

		this.rango2 = new RangoServicioDTO();
		this.rango2.setNumeroDia(2);
		this.rango2.setHorarioDesde(9);
		this.rango2.setMinutosDesde(0);
		this.rango2.setHorarioHasta(15);
		this.rango2.setMinutosHasta(30);

		this.rango3 = new RangoServicioDTO();
		this.rango3.setNumeroDia(3);
		this.rango3.setHorarioDesde(9);
		this.rango3.setMinutosDesde(0);
		this.rango3.setHorarioHasta(15);
		this.rango3.setMinutosHasta(30);

		this.rango4 = new RangoServicioDTO();
		this.rango4.setNumeroDia(4);
		this.rango4.setHorarioDesde(9);
		this.rango4.setMinutosDesde(0);
		this.rango4.setHorarioHasta(15);
		this.rango4.setMinutosHasta(30);

		this.rango5 = new RangoServicioDTO();
		this.rango5.setNumeroDia(1);
		this.rango5.setHorarioDesde(9);
		this.rango5.setMinutosDesde(0);
		this.rango5.setHorarioHasta(15);
		this.rango5.setMinutosHasta(30);

		this.rango1b = new RangoServicioDTO();
		this.rango1b.setNumeroDia(1);
		this.rango1b.setHorarioDesde(9);
		this.rango1b.setMinutosDesde(0);
		this.rango1b.setHorarioHasta(15);
		this.rango1b.setMinutosHasta(30);

		this.rango2b = new RangoServicioDTO();
		this.rango2b.setNumeroDia(2);
		this.rango2b.setHorarioDesde(9);
		this.rango2b.setMinutosDesde(0);
		this.rango2b.setHorarioHasta(15);
		this.rango2b.setMinutosHasta(30);

		this.rango3b = new RangoServicioDTO();
		this.rango3b.setNumeroDia(3);
		this.rango3b.setHorarioDesde(9);
		this.rango3b.setMinutosDesde(0);
		this.rango3b.setHorarioHasta(15);
		this.rango3b.setMinutosHasta(30);

		this.servicio1.addRango(rango1);
		this.servicio1.addRango(rango2);
		this.servicio1.addRango(rango3);
		this.servicio1.addRango(rango4);
		this.servicio1.addRango(rango5);

		this.servicio2.addRango(rango1b);
		this.servicio2.addRango(rango2b);
		this.servicio2.addRango(rango3b);

		this.centrosDTO.add(centroDTO1);

		// consultarCGPs(String consulta){
		// return centrosDTO;
		// }

	}

	public List<CentroDTO> buscarPOI(String textoLibre) {
		List<CentroDTO> centrosCumplen = new ArrayList<CentroDTO>();
		List<CGP> CGPs = new ArrayList<CGP>();
		this.repoCGP = new Repositorio();
		for (CentroDTO centro : centrosDTO) {
			if (centro.contenesConsulta(textoLibre)) {
				CGP unCGP = new CGP();
				unCGP = this.crearCGP(centro);
				CGPs.add(unCGP);
				repoCGP.agregarPOI(unCGP);
				centrosCumplen.add(centro);
			}
		}
		return centrosCumplen;
	}

	public CGP crearCGP(CentroDTO uncentro) {
		CGP cgp = new CGP();
		Comuna comuna = new Comuna();
		List<Servicio> servicios = new ArrayList<Servicio>();
		for (ServicioDTO servicioDto : uncentro.getServicios()) {
			Servicio servicioCGP = new Servicio();
			servicioCGP.setTipoServicio(servicioDto.getNombreServicio());
			List<HorarioAtencion> horarios = new ArrayList<HorarioAtencion>();
			for (RangoServicioDTO rangodto : servicioDto.getRangos()) {
				HorarioAtencion horarioCGP = new HorarioAtencion();
				horarioCGP.setDia(String.valueOf(rangodto.getNumeroDia()));
				horarioCGP.setHoraInicio(String.valueOf(rangodto.getHorarioDesde()));
				horarioCGP.setHoraFin(String.valueOf(rangodto.getHorarioHasta()));
				horarios.add(horarioCGP);
			}
			servicioCGP.setHorarios(horarios);
			servicios.add(servicioCGP);

		}
		comuna.setServicios(servicios);
		comuna.setNumeroComuna(uncentro.getNumeroComuna());
		cgp.setComuna(comuna);
		return cgp;

	}

	// @Test
	// public List<CentroDTO> consultarCGPs ()
	@Test
	public void consultarCGPsRepo() {
		List<CentroDTO> centrosCumplen = this.buscarPOI("Balvanera");
		assertTrue(centrosCumplen.contains(this.centroDTO1));
	}

}
