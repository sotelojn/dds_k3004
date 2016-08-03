package dominio.entidadesExternasBancos;

import java.util.List;

public class BancoExterno implements InterfaceBancoExt{
	
	private String calle;
	private String numero;
	private String banco;
    private String cx;
    private String cy;
    private String sucursal;
    private String gerente;
    private List<String> servicios;

    public BancoExterno(){};
    
    public BancoExterno(String calle, String numero, String banco, Double cx,
    		Double cy, String sucursal, String gerente, List<String> servicios){
    	this.setCalle(calle);
    	this.setNumero(numero);
    	this.setBanco(banco);
    	this.setCx(cx);
    	this.setCy(cy);
    	this.setSucursal(sucursal);
    	this.setServicios(servicios);
    };
      
    public List<BancoExterno> buscarBancosExt(String nombreBanco, String Servicio){
    	return null;
    }
    
    public void setCalle(String calle){
    	this.calle = calle;
    }
    
    public String getCalle(){
    	return this.calle;
    }
    
    public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
    
    public void setBanco(String nombre){
    	this.banco = nombre;
    }
    
    public String getBanco() {
		return banco;
	}

    public void setCx(double coordenada){
    	this.cx = Double.toString(coordenada);
    }
    
	public String getCx() {
		return cx;
	}

	public void setCy(double coordenada){
		this.cy = Double.toString(coordenada);
	}
	
	public String getCy() {
		return cy;
	}

	public void setSucursal (String sucursales){
		this.sucursal = sucursales;
	}
	
	public String getSucursal() {
		return sucursal;
	}

	public void setGerente(String gerente){
		this.gerente = gerente;
	}
	
	public String getGerente() {
		return gerente;
	}

	public void setServicios(List<String> servicios){
		this.servicios = servicios;
	}
	
	public List<String> getServicios() {
		return servicios;
	}

//	private String consulta="/home/dds/2016-dds-utn-k3154-g10/2016-dds-utn-k3154-g10/json.txt";
//	private BufferedReader bf;
	//private String strjson;
	
//	public List<PuntoDeInteres> buscarBancosExt(String TextoLibre){
//		List<PuntoDeInteres> POISuc = new ArrayList<PuntoDeInteres>();
//		this.repoSuc = new Repositorio();
//		Sucursal unaSucursal;
//		String strjason = this.muestraContenido(consulta);
//		final Gson gson = new Gson();
//		final java.lang.reflect.Type tipoListaSucursales = new TypeToken<List<InterfaceBanco>>(){}.getType();
//		final List<InterfaceBanco> sucursales = gson.fromJson(strjason, tipoListaSucursales);
//		for (InterfaceBanco unaSuc : sucursales) { 
//			unaSucursal = this.crearSucursal(unaSuc);
//			this.repoSuc.agregarPOI(unaSucursal);
//			POISuc.add(unaSucursal);
//		}
//		return POISuc;
//	}


//@Test
//public void debeDevolerLaRepresentacionJSONEnUnaListaDeObjetos() {
//	final String empleado1JSON = "{\"id\":46,\"nombre\":\"Miguel\",\"empresa\":\"Autentia\"}";
//	final String empleado2JSON = "{\"id\":76,\"nombre\":\"CR7\",\"empresa\":\"Real Madrid C.F\"}";
//	final String empleadosJSON = "[" + empleado1JSON + "," + empleado2JSON + "]";
//	final Gson gson = new Gson();
//	final Type tipoListaEmpleados = new TypeToken<List<Empleado>>(){}.getType();
//	final List<Empleado> empleados = gson.fromJson(empleadosJSON, tipoListaEmpleados);
//	assertNotNull(empleados);
//	assertEquals(2, empleados.size());
//	final Empleado empleado1 = empleados.get(0);
//	final Empleado empleado2 = empleados.get(1);
//	assertEquals(46, empleado1.getId());
//	assertEquals("Miguel", empleado1.getNombre());
//	assertEquals("Autentia", empleado1.getEmpresa());
//	assertEquals(76, empleado2.getId());
//	assertEquals("CR7", empleado2.getNombre());
//	assertEquals("Real Madrid C.F", empleado2.getEmpresa());
//}
//    public String muestraContenido(String archivo){
//        String cadena = "";
//        
//        try{
//        	String temp = "";
//        	String bfRead = "";
//        	bf = new BufferedReader(new FileReader(archivo));
//        while((bfRead = bf.readLine())!=null) {
//        	temp = temp + bfRead;
//        }
//        cadena = temp;
//       
//    }catch(Exception e){
//    	System.err.print("No se encontro archivo");
//    }
//        return cadena;
//    }
    
//    public List<PuntoDeInteres> pasarPOI (String unPOI){
//    	Sucursal sucExt = new Sucursal();
//		String strjson= "";
//		strjson= this.muestraContenido(consulta);
//		
//		
//		return POIExternos;
//	}

	//	public Sucursal crearSucursal(BancoExterno unaSuc) {
//		Sucursal suc = new Sucursal();
//		List<Servicio> servicios = new ArrayList<Servicio>();
//		for (String servicio : unaSuc.getServicios()) {
//			Servicio servicioSucursal = new Servicio();
//			servicioSucursal.setTipoServicio(servicio);
//			servicios.add(servicioSucursal);
//
//		}
//		suc.setServicios(servicios);
//		suc.setBanco(unaSuc.getBanco());
//		Coordenada c1 = new Coordenada(unaSuc.getCx(),unaSuc.getCy());
//		suc.setCoordenada(c1);
//		return suc;
//}

}
