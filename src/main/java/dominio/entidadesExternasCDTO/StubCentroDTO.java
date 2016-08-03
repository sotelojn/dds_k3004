package dominio.entidadesExternasCDTO;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Polygon;

import dominio.Coordenada;


public class StubCentroDTO implements InterfaceCentroDTO{

	@Override
	public List<CentroDTO> buscarCentroDTO(String TextoLibre) {
		
		RangoServicioDTO lunesMañana = new RangoServicioDTO (1 , 8, 0, 12, 45);
		RangoServicioDTO lunesTarde = new RangoServicioDTO(1, 14, 0, 18, 30);
		
		List<RangoServicioDTO> listaDeDias = new ArrayList<>();
		listaDeDias.add(lunesMañana);
		listaDeDias.add(lunesTarde);
		
		ServicioDTO pension = new ServicioDTO ("Pension", listaDeDias);
		
		ServicioDTO jubilacion = new ServicioDTO("Jubilacion", listaDeDias);

		List<ServicioDTO> serviciosQueOfrece = new ArrayList<> ();
		serviciosQueOfrece.add(pension);
		serviciosQueOfrece.add(jubilacion);
		
		Coordenada coordenadasCGP3 = new Coordenada(-34.603302, -58.397160);
		
		String barriosQueIncluye = "Balvanera, San Cristobal";

		Coordenada coordenadasCGP8 = new Coordenada (-34.686048, -58.455480);
			
		Coordenada v1 = new Coordenada(-34.664812, -58.469830);
		Coordenada v2 = new Coordenada(-34.656710, -58.460401);
		Coordenada v3 = new Coordenada(-34.649869, -58.470087);
		Coordenada v4 = new Coordenada(-34.657818, -58.478747);
		Polygon zona = new Polygon();
		zona.add(v1);
		zona.add(v2);
		zona.add(v3);
		zona.add(v4);
		
		CentroDTO cgp3 = new CentroDTO (3, "cgp3", barriosQueIncluye, "Perez Ricardo", "Junín 521", "4375-0644/45", serviciosQueOfrece, coordenadasCGP3, zona);
		CentroDTO cgp8 = new CentroDTO (8, "cgp8", barriosQueIncluye, "Gonzalez Alberto", "Calle Falsa 123", "4123-234", serviciosQueOfrece, coordenadasCGP8, zona);
		
		List<CentroDTO> lista = new ArrayList<CentroDTO>();
		lista.add(cgp3);
		lista.add(cgp8);
		
		return lista;
	}
	
}
