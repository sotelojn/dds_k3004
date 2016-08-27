package dominio.adapter;

import java.util.ArrayList;
import java.util.List;

import dominio.PuntoDeInteres;
import dominio.Repositorio;
import dominio.entidadesExternasGobierno.*;

public class AdapterGobiernoDeLaCiudad {

	private StubGobiernoCiudad unStubPOI = new StubGobiernoCiudad();
	private BajaPOI unPOI;
	
	public AdapterGobiernoDeLaCiudad(){
		BajaPOI POI = new BajaPOI();
		this.setUnPOI(POI);
	}
	
 	public void setUnPOI(BajaPOI unPOI){
		this.unPOI = unPOI;
	}
	
 	public BajaPOI getUnPOI(){
 		return unPOI;
 	}
 	
	public StubGobiernoCiudad getUnStubPOI(){
		return unStubPOI;
	}
	
	public ObjetoDeAyuda dameDeBajaLaLista(Repositorio unRepo) {
		List<List<PuntoDeInteres>> listaAEliminar = new ArrayList<List<PuntoDeInteres>>();
		ObjetoDeAyuda obj = new ObjetoDeAyuda();
		obj.setUnRepo(unRepo);
		
		List<BajaPOI> lista = this.getUnStubPOI().POIsDeBaja();
		lista.forEach(unaBaja -> listaAEliminar.add(unRepo
				.buscarPOI(unaBaja.getValorDeBusqueda())));
		int c = unRepo.getPOIs().size();
		listaAEliminar.forEach(unaLista -> unaLista.forEach(unPOI -> unRepo.delete(unPOI)));
		
		
		obj.setFinalizo(c > unRepo.getPOIs().size());
		return (obj);
	}

}
