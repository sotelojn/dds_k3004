package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import dominio.exceptions.*;
import dominio.POI.cgp.CGP;
import dominio.POI.cgp.Comuna;
import dominio.POI.cgp.Servicio;
import dominio.POI.colectivo.Colectivo;
import dominio.POI.colectivo.ParadaDeColectivo;
import dominio.POI.localComercial.Local;
import dominio.POI.localComercial.Rubro;
import dominio.POI.sucursal.Sucursal;


public class Repositorio {
	private final static Logger log = Logger.getLogger(Repositorio.class);
	private List<PuntoDeInteres> POIs;
	private List<Consulta> cache;
	private EntidadesExternas todasLasEntidades;
	
	public int tamanio(){
		return this.POIs.size();
	}
	
	public Repositorio(){
		POIs = new ArrayList<PuntoDeInteres>();
		cache = new ArrayList<Consulta>();
		todasLasEntidades = new EntidadesExternas();
	}
	
	public void crearUnaSucursalBancaria(Direccion unaDireccion, String unBanco, 
			String unNombre, Coordenada unaCoordenada, List<Servicio> listaDeServicios){
	
		Sucursal unPOI = new Sucursal (unaDireccion, unBanco, unNombre, unaCoordenada, listaDeServicios);
		this.agregarPOIConId(unPOI);
	}
	
	public void crearUnLocalComercial(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, Rubro rubro,
			List<HorarioAtencion> disponibiidad){
		
		Local unPOI = new Local(unaDireccion, unNombre, unaCoordenada, rubro, disponibiidad);
		this.agregarPOIConId(unPOI);
	
	}
		
	public void crearUnColectivo(Direccion unaDireccion, String unNombre, Coordenada unaCoordenada, int numeroDeLinea, List<ParadaDeColectivo> paradasDeColectivo){
	
		Colectivo unPOI = new Colectivo(unaDireccion, unNombre, unaCoordenada, numeroDeLinea, paradasDeColectivo);
		this.agregarPOIConId(unPOI);
		
	}
	
	public void crearUnCGP(Direccion unaDireccion, Coordenada unaCoordenada, String unNombre, Comuna unaComuna){
		
		CGP unPOI = new CGP(unaDireccion,unaCoordenada,unNombre,unaComuna);
		this.agregarPOIConId(unPOI);

	}
	
	public List<PuntoDeInteres> getPOIs() {
		return POIs;
	}

	public void setPOIs(List<PuntoDeInteres> POIs) {
		POIs = POIs;
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
	
	public List<PuntoDeInteres> buscarPOI (String textoLibre){
		List<PuntoDeInteres> resultPOIs = new ArrayList<PuntoDeInteres> ();
		
		if (this.getCache().stream().anyMatch(consulta -> 
		consulta.getConsulta().equals(textoLibre)) ){
			resultPOIs = this.dameLaConsultaQueBusco(textoLibre);
		
		}else{
			this.actualizarListaNueva(this.getEntidadesExternas().busquedaExterna(textoLibre));
			resultPOIs = this.getPOIs().stream().filter(unPOI -> unPOI.contenesConsulta(textoLibre))
					.collect(Collectors.toList());
			Consulta nuevaConsult = new Consulta(textoLibre, resultPOIs);
			this.guardarConsulta(nuevaConsult);
		}
		return resultPOIs;
	}

	public void actualizarListaNueva(List<PuntoDeInteres> nuL){
		int cantidad = nuL.size();
		PuntoDeInteres unPOI;
		for (int initial = 0; initial < cantidad; initial ++){
			unPOI = nuL.get(initial);
			this.update(unPOI);
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

	public void create(PuntoDeInteres unPOI) {
		this.verificarValidez(unPOI);
		this.agregarPOIConId(unPOI);
	}
	
	public void agregarPOIConId(PuntoDeInteres unPOI){
		unPOI.setId(this.POIs.size() + 1);
		this.POIs.add(unPOI);
	}

	public void delete(PuntoDeInteres unPOI) {
		this.POIs.remove(unPOI);
	}

	public void update(PuntoDeInteres unPOI) {
		this.verificarValidez(unPOI);
		this.verificarExistenciaEnElRepo(unPOI);

	}
	
	public  void agregarLoteSinRepetidos (List<PuntoDeInteres> loteDePOIs) {
				loteDePOIs.stream().forEach(unPOI -> this.update(unPOI));
	
	}

	public PuntoDeInteres searchByCoord(Coordenada otrasCoordenadas) {
		return 	this.selectByCoordenada(otrasCoordenadas);
	
	}
	
	public PuntoDeInteres selectByCoordenada(Coordenada coordenada){
		int posiciones = POIs.size();
		PuntoDeInteres unPOI = new Sucursal();
		
		for (int initial = 0; initial < posiciones; initial ++){
			
			unPOI = POIs.get(initial);
			double lat1= unPOI.getCoordenada().latitude() ;
			double lat2 = coordenada.latitude();
			double lon1= unPOI.getCoordenada().longitude() ;
			double lon2 = coordenada.longitude();
			
			if (Double.compare(lat1, lat2) == 0){
				if (Double.compare(lon1, lon2) == 0 ){
					return unPOI;
				}
			}
		}
		return null;
	}
		
	public PuntoDeInteres searchById(int id) {
		return this.selectById(id);

	}

	public PuntoDeInteres selectById(int id){
		int posiciones = POIs.size();
		PuntoDeInteres unPOI = new Sucursal();
		
		for (int initial = 0; initial < posiciones; initial ++){
			
			unPOI = POIs.get(initial);
			
			if (unPOI.getId() == id){
				return unPOI;
				}
		}
		return null;
	}
	
	public List<PuntoDeInteres> allInstances() {
		return this.POIs;
	}

	private void verificarValidez(PuntoDeInteres unPOI) {
		if (!unPOI.esValido()) {
			throw new POIInvalidoException();
		}
	}
	
	public boolean estaEnElRepositorio (PuntoDeInteres unPOI) {
		return (this.getPOIs().stream().anyMatch(otroPOI -> otroPOI.equals(unPOI)));
	}
	
	public void verificarExistenciaEnElRepo(PuntoDeInteres unPOI) {
		if (!this.estaEnElRepositorio(unPOI)) {
			this.agregarPOIConId(unPOI);	
			
		}else{
			PuntoDeInteres otroPOI = this.searchByCoord(unPOI.getCoordenada());
			int nuevoId = otroPOI.getId();
			this.delete(otroPOI);
			unPOI.setId(nuevoId);
			POIs.add(unPOI);
			
		}

	}

	public Boolean tenesAEsteColectivo(String textoLibre) {

		return POIs.stream().filter(unPOI -> unPOI.getIcono().equals("ParaDeColectivo"))
				.anyMatch(unPOI -> unPOI.getNombre().equals(textoLibre));
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
	
	public PuntoDeInteres buscarLocalComercial(String unNombre){
		Local aux = new Local();
		
		return this.getPOIs().stream().filter(unPOI ->
			unPOI.getClass()== aux.getClass() && unPOI.getNombre() == unNombre)
			.collect(Collectors.toList()).get(0);
		
	}
	
}
