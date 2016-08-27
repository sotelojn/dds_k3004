package dominio.entidadesExternasGobierno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StubGobiernoCiudad implements InterfaceGobierno{

	public List<BajaPOI> POIsDeBaja() {
		List<BajaPOI> unaLista = new ArrayList<BajaPOI>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fechaString1 = "28-07-2016";
		Date fecha1;
		try {
			fecha1 = sdf.parse(fechaString1);
		
		BajaPOI busqueda1 = new BajaPOI("Gali", fecha1);
	
		String fechaString2 = "22-07-2016";
		Date fecha2 = sdf.parse(fechaString2);
		BajaPOI busqueda2 = new BajaPOI("3", fecha2);
		
		unaLista.add(busqueda1);
		unaLista.add(busqueda2);
		
		return unaLista;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unaLista;
	}

}
