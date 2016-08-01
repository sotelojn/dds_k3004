package ar.edu.utn.d2s;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import ar.edu.utn.d2s.exceptions.*;
import ar.edu.utn.d2s.pdi.cgp.CGP;
import ar.edu.utn.d2s.pdi.cgp.Comuna;
import ar.edu.utn.d2s.pdi.cgp.Servicio;
import ar.edu.utn.d2s.pdi.colectivo.Colectivo;
import ar.edu.utn.d2s.pdi.colectivo.ParadaDeColectivo;
import ar.edu.utn.d2s.pdi.localComercial.Local;
import ar.edu.utn.d2s.pdi.localComercial.Rubro;
import ar.edu.utn.d2s.pdi.sucursal.Sucursal;


public class Repositorio {
	private final static Logger log = Logger.getLogger(Repositorio.class);
	private List<PuntoDeInteres> PDIs;
	private List<Consulta> cache;
	private EntidadesExternas todasLasEntidades;
	
	
	public Repositorio(){
		PDIs = new ArrayList<PuntoDeInteres>();
		cache = new ArrayList<Consulta>();
		todasLasEntidades = new EntidadesExternas();
	}
	
	public void crearUnColectivo(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, int numeroDeLinea, List<ParadaDeColectivo> paradasDeColectivo){
	
		Colectivo unPdi = new Colectivo(unaDireccion, unNombre, unaCoordenada, numeroDeLinea, paradasDeColectivo);
		this.agregarPDIConId(unPdi);
		
	}
	
	public void crearUnCGP(Direccion unaDireccion, Coordenada unaCoordenada, String unNombre, Comuna unaComuna){
		
		CGP unPdi = new CGP(unaDireccion,unaCoordenada,unNombre,unaComuna);
		this.agregarPDIConId(unPdi);

	}
	public List<PuntoDeInteres> getPDIs() {
		return PDIs;
	}

	public void setPDIs(List<PuntoDeInteres> pDIs) {
		PDIs = pDIs;
	}

	public void crearUnaSucursalBancaria(Direccion unaDireccion, String unBanco, 
			String unNombre, Coordenada unaCoordenada, List<Servicio> listaDeServicios){
	
		Sucursal unPdi = new Sucursal (unaDireccion, unBanco, unNombre, unaCoordenada, listaDeServicios);
		this.agregarPDIConId(unPdi);
	}
	
	public void crearUnLocalComercial(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, Rubro rubro,
			List<HorarioAtencion> disponibiidad){
		
		Local unPdi = new Local(unaDireccion, unNombre, unaCoordenada, rubro, disponibiidad);
		this.agregarPDIConId(unPdi);
	
	}
	
	public void guardarConsulta(Consulta consulta){
		this.getCache().add(consulta);
	}


	public void LoguearConsulta(DateTime tiempoI, DateTime tiempoF, String textoConsulta, int cantidadResultados){
		Seconds TiempoConsulta = Seconds.secondsBetween(tiempoI, tiempoF);
		 log.info("Nombre Buscado : " + textoConsulta);
		 log.info("Cantidad Resultados : " + cantidadResultados);
		 log.info("Tiempo de Busqueda : " + TiempoConsulta);
	}
	

	public List<PuntoDeInteres> buscarPDI (String textoLibre){
		List<PuntoDeInteres> resultPdis = new ArrayList<PuntoDeInteres> ();
		
		if (this.getCache().stream().anyMatch(consulta -> 
		consulta.getConsulta().equals(textoLibre)) ){
			resultPdis = this.dameLaConsultaQueBusco(textoLibre);
		
		}else{
			this.actualizarListaNueva(this.getEntidadesExternas().busquedaExterna(textoLibre));
			resultPdis = this.getPDIs().stream().filter(unPdi -> unPdi.contenesConsulta(textoLibre))
					.collect(Collectors.toList());
			Consulta nuevaConsult = new Consulta(textoLibre, resultPdis);
			this.guardarConsulta(nuevaConsult);
		}
		return resultPdis;
	}

	public void actualizarListaNueva(List<PuntoDeInteres> nuL){
		int cantidad = nuL.size();
		PuntoDeInteres unPdi;
		for (int initial = 0; initial < cantidad; initial ++){
			unPdi = nuL.get(initial);
			this.update(unPdi);
		}
	}

	private EntidadesExternas getEntidadesExternas() {
		return this.todasLasEntidades;
	}

	private List<PuntoDeInteres> dameLaConsultaQueBusco(String textoLibre) {
		return (this.getCache().stream().filter(unaConsulta -> 
		unaConsulta.getConsulta().equals(textoLibre))
		.collect(Collectors.toList()) ).get(0).getResultados();
		
	}

	public void create(PuntoDeInteres unPdi) {
		this.verificarValidez(unPdi);
		this.agregarPDIConId(unPdi);
	}
	
	public void agregarPDIConId(PuntoDeInteres unPdi){
		unPdi.setId(this.PDIs.size() + 1);
		this.PDIs.add(unPdi);
	}

	public void delete(PuntoDeInteres unPdi) {
		this.PDIs.remove(unPdi);
	}

	public void update(PuntoDeInteres unPdi) {
		this.verificarValidez(unPdi);
		this.verificarExistenciaEnElRepo(unPdi);

	}
	
	public  void agregarLoteSinRepetidos (List<PuntoDeInteres> loteDePDIs) {
				loteDePDIs.stream().forEach(unPdi -> this.update(unPdi));
	
	}

	// Busqueda por coordenadas
	public PuntoDeInteres searchByCoord(Coordenada otrasCoordenadas) {
		
		return 	this.selectByCoordenada(otrasCoordenadas);
	}
	
	public PuntoDeInteres selectByCoordenada(Coordenada coordenada){
		int posiciones = PDIs.size();
		PuntoDeInteres unPdi = new Sucursal();
		
		for (int initial = 0; initial < posiciones; initial ++){
			
			unPdi = PDIs.get(initial);
			double lat1= unPdi.getCoordenada().latitude() ;
			double lat2 = coordenada.latitude();
			double lon1= unPdi.getCoordenada().longitude() ;
			double lon2 = coordenada.longitude();
			
			if (Double.compare(lat1, lat2) == 0){
				if (Double.compare(lon1, lon2) == 0 ){
					return unPdi;
				}
			}
		}
		return null;
	}
		
	// Busqueda por ID
	public PuntoDeInteres searchById(int id) {
		return this.selectById(id);
	}

	public PuntoDeInteres selectById(int id){
		int posiciones = PDIs.size();
		PuntoDeInteres unPdi = new Sucursal();
		
		for (int initial = 0; initial < posiciones; initial ++){
			
			unPdi = PDIs.get(initial);
			
			if (unPdi.getId() == id){
				return unPdi;
				}
		}
		return null;
	}
	
	public List<PuntoDeInteres> allInstances() {
		return this.PDIs;
	}

	private void verificarValidez(PuntoDeInteres unPdi) {
		if (!unPdi.esValido()) {
			throw new PDIInvalidoException();
		}
	}
	
	public boolean estaEnElRepositorio (PuntoDeInteres unPdi) {
		return (this.getPDIs().stream().anyMatch(otroPdi -> otroPdi.equals(unPdi)));
	}
	
	public void verificarExistenciaEnElRepo(PuntoDeInteres unPdi) {
		if (!this.estaEnElRepositorio(unPdi)) {
			//throw new NoExisteElPOIEnElRepositorioException();
			this.agregarPDIConId(unPdi);	
			
		}else{
			PuntoDeInteres otroPdi = this.searchByCoord(unPdi.getCoordenada());
			int nuevoId = otroPdi.getId();
			this.delete(otroPdi);
			unPdi.setId(nuevoId);
			PDIs.add(unPdi);
			
		}

	}

	public Boolean tenesAEsteColectivo(String textoLibre) {

		return PDIs.stream().filter(unPdi -> unPdi.getIcono().equals("ParaDeColectivo"))
				.anyMatch(unPdi -> unPdi.getNombre().equals(textoLibre));
	}

	public void deletePorId(int i) {

		this.delete(this.selectById(i));		
	}

	public void deletePorCoordenadaUnica(Coordenada coordenada) {
		
		this.delete(this.searchByCoord(coordenada));
	}

	public List<Consulta> getCache(){
		return this.cache;
	}

	public Consulta dameElUltimoAgregadoEnLaCache(String texto) {
		return this.getCache().stream().filter(unaConsulta -> unaConsulta.getConsulta().equals(texto))
				.collect(Collectors.toList()).get(0);
	}
	
}
