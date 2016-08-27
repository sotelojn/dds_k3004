package dominio.entidadesExternasBancos;


import java.util.ArrayList;
import java.util.List;

public class StubBancoExternoDeJson extends BancoExterno{

	@Override
	public List<BancoExterno> buscarBancosExt(String nombreBanco, String Servicio) {

		List<BancoExterno> lista = new ArrayList<BancoExterno> ();
		
		BancoExterno unBanco = new BancoExterno();
		List<String> servicios = new ArrayList<String> ();
		servicios.add("Cobrocheques");
		servicios.add("Depósitos");
		servicios.add("Extracciones");
		servicios.add("Transferencias");
		servicios.add("Créditos");
		
		BancoExterno unBanco2 = new BancoExterno();
		List<String> servicios2 = new ArrayList<String> ();
		servicios2.add("Depósitos");
		servicios2.add("Extracciones");
		servicios2.add("Transferencias");
		servicios2.add("Seguros");
		
		unBanco.setCalle("Galicia");
		unBanco.setNumero("unNumero");
		unBanco.setBanco("BancoDelaPlaza");
		unBanco.setCx(-35.9338322);
		unBanco.setCy(72.348353);
		unBanco.setSucursal("Avellaneda");
		unBanco.setGerente("JavierLoeschbor");
		unBanco.setServicios(servicios);
		
		unBanco2.setCalle("otraCalle");
		unBanco2.setNumero("otroNumero");
		unBanco2.setBanco("BancodelaPlaza");
		unBanco2.setCx(-35.9345681);
		unBanco2.setCy(72.344546);
		unBanco2.setSucursal("Caballito");
		unBanco2.setGerente("FabiánFantaguzzi");
		unBanco2.setServicios(servicios2);
			
		lista.add(unBanco);
		lista.add(unBanco2);             		 
		return lista;
	}

}
