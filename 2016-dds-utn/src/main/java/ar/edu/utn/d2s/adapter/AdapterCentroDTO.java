package ar.edu.utn.d2s.adapter;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.d2s.Coordenada;
import ar.edu.utn.d2s.Direccion;
import ar.edu.utn.d2s.HorarioAtencion;
import ar.edu.utn.d2s.PuntoDeInteres;
import ar.edu.utn.d2s.entidadesExternasCDTO.CentroDTO;
import ar.edu.utn.d2s.entidadesExternasCDTO.RangoServicioDTO;
import ar.edu.utn.d2s.entidadesExternasCDTO.ServicioDTO;
import ar.edu.utn.d2s.entidadesExternasCDTO.StubCentroDTO;
import ar.edu.utn.d2s.pdi.cgp.CGP;
import ar.edu.utn.d2s.pdi.cgp.Comuna;
import ar.edu.utn.d2s.pdi.cgp.Servicio;

public class AdapterCentroDTO implements InterfaceAdapter{
	
		public PuntoDeInteres adaptFromToCGP(CentroDTO cgp) {
			
			int numeroComuna = cgp.getNumeroComuna();
			String unNombre = cgp.getNombre();
			org.uqbar.geodds.Polygon unPoligono = cgp.getPoligono();
			List<ServicioDTO> servicio = cgp.getServicios();
			Coordenada coordenadas = cgp.getCoordenada();
			String unaDireccion = cgp.getDireccion();
			
			Direccion direccion = this.pasameloADireccion(unaDireccion);
			List<Servicio> listaDeServicios = this.pasameloAServicio(servicio); 
			Comuna tipoComuna = this.pasameloAComuna(numeroComuna, unPoligono, listaDeServicios);
			PuntoDeInteres cgpNuevo = new CGP (direccion, coordenadas, unNombre, tipoComuna);
			return (cgpNuevo);
		
		}

		private Comuna pasameloAComuna(int numeroComuna, org.uqbar.geodds.Polygon unPoligono, List<Servicio> listaDeServicios) {
			
			Comuna unaComuna = new Comuna(numeroComuna, unPoligono, listaDeServicios);
			return unaComuna;
		}

		private Direccion pasameloADireccion(String unaDireccion) {
			
			Direccion direccion = new Direccion();
			String primeraPalabra;
			String numeroDeLaPalabra;
			
			primeraPalabra = direccion.dameLaPalabra(unaDireccion);
			numeroDeLaPalabra = direccion.dameElNumero(unaDireccion);
			
			direccion.setCalle(primeraPalabra);
			direccion.setNumero(numeroDeLaPalabra);
			
			return direccion;
		}

		private List<Servicio> pasameloAServicio (List<ServicioDTO> unaLista){
			
			List<Servicio> listaNuevaDeServicios = new ArrayList<Servicio>();
			int cont = unaLista.size();
			for (int initial = 0; initial < cont; initial ++){
				
				List<HorarioAtencion> listaDeNuevosHorarios = new ArrayList<HorarioAtencion>();
				ServicioDTO unSerDTO = unaLista.get(initial);
				String unNombre = unSerDTO.getNombreServicio();
				List<RangoServicioDTO> horarios = unSerDTO.getRangos();
			
				int otroCont = horarios.size();
				for (int otroInitial = 0; otroInitial < otroCont; otroInitial ++){
					RangoServicioDTO unHorario = horarios.get(otroInitial);
					listaDeNuevosHorarios.add(this.pasameloAMiTipoDeHorario(unHorario));
				}
				
				Servicio unServicio = new Servicio (unNombre, listaDeNuevosHorarios);
				listaNuevaDeServicios.add( unServicio );
			}
			
			return listaNuevaDeServicios;
		}

		private double pasameLaHoraDeIntADouble(int hora, int minutos){
			double nuevoMin = minutos * (0.01);
			double horaDefinitiva = hora + nuevoMin;
			return horaDefinitiva;
		}
		
		private HorarioAtencion pasameloAMiTipoDeHorario(RangoServicioDTO unHorario){
			
			int numeroDia = unHorario.getNumeroDia();
			int minI = unHorario.getMinutosDesde();
			int horaI = unHorario.getHorarioDesde();
			int minH = unHorario.getMinutosHasta();
			int horaH = unHorario.getHorarioHasta();

			HorarioAtencion nuevoHorario = new HorarioAtencion(DayOfWeek.of(numeroDia),
					this.pasameLaHoraDeIntADouble(horaI, minI),
					this.pasameLaHoraDeIntADouble(horaH, minH));
		
			return nuevoHorario;
			
		}

		@Override
		public List<PuntoDeInteres> dameLosPdiExternos(String texto) {

				List<CentroDTO> unaNuevaLista = new ArrayList<>();
				StubCentroDTO centroDTO = new StubCentroDTO();
				unaNuevaLista = centroDTO.buscarCentroDTO(texto); 
				return this.aplicarALaListaElAdapterCentroDTO(unaNuevaLista);
				}
		
		public List<PuntoDeInteres> aplicarALaListaElAdapterCentroDTO(List<CentroDTO> unaNuevaLista) {
			
			List<PuntoDeInteres> listaNuevaPdi = new ArrayList<PuntoDeInteres> ();
			CentroDTO unCentroDTO;
			
			int cantidadDePosiciones = unaNuevaLista.size(); 
			
			for (int inicial = 0; inicial < cantidadDePosiciones; inicial ++){
				PuntoDeInteres pdi = new CGP();
				unCentroDTO = unaNuevaLista.get(inicial);
				pdi = this.adaptFromToCGP(unCentroDTO);
				listaNuevaPdi.add( pdi );
			}
			
			return listaNuevaPdi;

		}
		

}
