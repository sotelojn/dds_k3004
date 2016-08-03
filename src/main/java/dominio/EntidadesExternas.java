package dominio;

import java.util.ArrayList;
import java.util.List;

import dominio.adapter.AdapterBancoJSon;
import dominio.adapter.AdapterCentroDTO;
import dominio.adapter.InterfaceAdapter;

public class EntidadesExternas {
	
	private List<InterfaceAdapter> adapters;
	
	public List<InterfaceAdapter> getAdapters(){
		return this.adapters;
		}
	
	public void setAdapters(){
		this.adapters = new ArrayList<InterfaceAdapter>();
	}
	
	public void setteoDeAdapters(){
		this.setAdapters();
		this.getAdapters().add(new AdapterCentroDTO());
		this.getAdapters().add(new AdapterBancoJSon());
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
}
