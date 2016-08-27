package dominio;

import java.util.ArrayList;
import java.util.List;

import dominio.adapter.AdapterBancoJSon;
import dominio.adapter.AdapterCentroDTO;
import dominio.adapter.InterfaceAdapter;
import dominio.adapter.AdapterGobiernoDeLaCiudad;
import dominio.adapter.ObjetoDeAyuda;

public class EntidadesExternas {
	
	private List<InterfaceAdapter> adapters;
	private AdapterGobiernoDeLaCiudad adapterGob;
	
	public List<InterfaceAdapter> getAdapters(){
		return this.adapters;
		}
	
	public void setAdapters(){
		this.adapters = new ArrayList<InterfaceAdapter>();
	}
	
	public void setAdapterGob(AdapterGobiernoDeLaCiudad unAdap){
		this.adapterGob = unAdap;
	}
	
	public void setteoDeAdapters(){
		this.setAdapters();
		this.getAdapters().add(new AdapterCentroDTO());
		this.getAdapters().add(new AdapterBancoJSon());
		this.setAdapterGob(new AdapterGobiernoDeLaCiudad());
	}
	
	public List<PuntoDeInteres> busquedaExterna(String texto){
		List<PuntoDeInteres> nuevaListaExterna = new ArrayList<PuntoDeInteres>();
		
		this.setteoDeAdapters();
		
		int cantidadDeAdapters = this.getAdapters().size();
		
		for (int initial = 0; initial < cantidadDeAdapters; initial++){
			nuevaListaExterna.addAll(this.getAdapters().get(initial).
					dameLosPOIExternos(texto) );
			}
		
		return nuevaListaExterna;
		}
	
	public String dameLosPOIsABajar(Repositorio unRepo) {
		String termino;
		this.setteoDeAdapters();
		ObjetoDeAyuda obj = new ObjetoDeAyuda();
		obj = (this.getAdapterDeGobierno().dameDeBajaLaLista(unRepo));
		
		if (obj.getFinalizo() == true){
			termino = "ok";
		}else{
			termino = "error";
			unRepo = obj.getUnRepo();
		};
		return termino;
	}
	
	public AdapterGobiernoDeLaCiudad getAdapterDeGobierno(){
		return adapterGob;
	}
}