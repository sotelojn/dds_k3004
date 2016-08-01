package ar.edu.utn.d2s.adapter;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.d2s.Coordenada;
import ar.edu.utn.d2s.Direccion;
import ar.edu.utn.d2s.HorarioAtencion;
import ar.edu.utn.d2s.PuntoDeInteres;
import ar.edu.utn.d2s.entidadesExternasBancos.BancoExterno;
import ar.edu.utn.d2s.entidadesExternasBancos.StubBancoExternoDeJson;
import ar.edu.utn.d2s.pdi.cgp.*;
import ar.edu.utn.d2s.pdi.sucursal.Sucursal;

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
	public List<PuntoDeInteres> dameLosPdiExternos(String texto) {
		List<BancoExterno> unaNuevaLista = new ArrayList<>();
		StubBancoExternoDeJson bancoJSon = new StubBancoExternoDeJson();
		
		unaNuevaLista = bancoJSon.buscarBancosExt(texto, ""); 
		return this.aplicarALaListaElAdapterDeBancos(unaNuevaLista);
		
	}

	public List<PuntoDeInteres> aplicarALaListaElAdapterDeBancos(List<BancoExterno> unaNuevaLista) {
		
		List<PuntoDeInteres> listaNuevaPdi = new ArrayList<PuntoDeInteres> ();
		BancoExterno unBancoExterno;
		
		int cantidadDePosiciones = unaNuevaLista.size(); 
		
		for (int inicial = 0; inicial < cantidadDePosiciones; inicial ++){
			PuntoDeInteres pdi = new Sucursal();
			unBancoExterno = unaNuevaLista.get(inicial);
			pdi = this.adaptFromToBanco(unBancoExterno);
			listaNuevaPdi.add( pdi );
			}
		
		return listaNuevaPdi;
	}	
		
}
