package dominio.adapter;

import java.util.ArrayList;
import java.util.List;

import dominio.Coordenada;
import dominio.Direccion;
import dominio.HorarioAtencion;
import dominio.PuntoDeInteres;
import dominio.entidadesExternasBancos.BancoExterno;
import dominio.entidadesExternasBancos.StubBancoExternoDeJson;
import dominio.POI.cgp.*;
import dominio.POI.sucursal.Sucursal;

public class AdapterBancoJSon implements InterfaceAdapter{
	
	public PuntoDeInteres adaptFromToBanco(BancoExterno unBanco){
	
		Coordenada unaCoordenada = new Coordenada(Double.parseDouble(unBanco.getCx()) , Double.parseDouble(unBanco.getCy()));
		Direccion unaDireccion = new Direccion(unBanco.getCalle(), unBanco.getNumero());
		List<Servicio> servicios = this.pasameloAServicioDesdeString(unBanco.getServicios());
		Sucursal unBancoNuevo = new Sucursal(unaDireccion, unBanco.getBanco(), unBanco.getSucursal(), 
			unaCoordenada,servicios);
		return unBancoNuevo;
	}
		
	private List<Servicio> pasameloAServicioDesdeString (List<String> unaLista){
		List<Servicio> listaNuevaDeServicios = new ArrayList<Servicio>();
		HorarioAtencion unHorario = new HorarioAtencion();
		
		int cont = unaLista.size();
		for (int initial = 0; initial < cont; initial ++){
			String unNombre = unaLista.get(initial);
			Servicio unServicio = new Servicio (unNombre, unHorario.generarHorariosParaTodaLaSemana(10.00, 15.00));
			listaNuevaDeServicios.add( unServicio );
		}
			
		return listaNuevaDeServicios;
		}

	@Override
	public List<PuntoDeInteres> dameLosPOIExternos(String texto) {
		List<BancoExterno> unaNuevaLista = new ArrayList<>();
		StubBancoExternoDeJson bancoJSon = new StubBancoExternoDeJson();
		
		unaNuevaLista = bancoJSon.buscarBancosExt(texto, ""); 
		return this.aplicarALaListaElAdapterDeBancos(unaNuevaLista);
		
	}

	public List<PuntoDeInteres> aplicarALaListaElAdapterDeBancos(List<BancoExterno> unaNuevaLista) {
		
		List<PuntoDeInteres> listaNuevaPOI = new ArrayList<PuntoDeInteres> ();
		BancoExterno unBancoExterno;
		
		int cantidadDePosiciones = unaNuevaLista.size(); 
		
		for (int inicial = 0; inicial < cantidadDePosiciones; inicial ++){
			PuntoDeInteres POI = new Sucursal();
			unBancoExterno = unaNuevaLista.get(inicial);
			POI = this.adaptFromToBanco(unBancoExterno);
			listaNuevaPOI.add( POI );
			}
		
		return listaNuevaPOI;
	}	
		
}
