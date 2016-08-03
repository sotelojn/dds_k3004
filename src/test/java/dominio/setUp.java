package dominio;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Polygon;
import dominio.Repositorio;
import dominio.acciones.AccionLoggear;
import dominio.acciones.AccionesDeUsuario;
import dominio.adapter.AdapterBancoJSon;
import dominio.adapter.AdapterCentroDTO;
import dominio.entidadesExternasBancos.BancoExterno;
import dominio.entidadesExternasCDTO.CentroDTO;
import dominio.entidadesExternasCDTO.RangoServicioDTO;
import dominio.entidadesExternasCDTO.ServicioDTO;
import dominio.POI.cgp.CGP;
import dominio.POI.cgp.Comuna;
import dominio.POI.cgp.Servicio;
import dominio.POI.colectivo.Colectivo;
import dominio.POI.colectivo.ParadaDeColectivo;
import dominio.POI.localComercial.Local;
import dominio.POI.localComercial.Rubro;
import dominio.POI.sucursal.Sucursal;
import dominio.usuario.NuevaConsultaExtendida;
import dominio.usuario.UsuarioTerminal;

public class setUp {
	
	DayOfWeek lunes = DayOfWeek.MONDAY;
	DayOfWeek martes = DayOfWeek.TUESDAY;
	DayOfWeek miercoles = DayOfWeek.WEDNESDAY;
	DayOfWeek jueves = DayOfWeek.THURSDAY;
	DayOfWeek viernes = DayOfWeek.FRIDAY;
	DayOfWeek sabado = DayOfWeek.SATURDAY;
	DayOfWeek domingo = DayOfWeek.SUNDAY;

	
	//GENERA UN BANCO
	
		HorarioAtencion Lunes = new HorarioAtencion(lunes, 10.00, 15.00);
		HorarioAtencion Martes = new HorarioAtencion(martes, 10.00, 15.00);
		HorarioAtencion Miercoles = new HorarioAtencion(miercoles, 10.00, 15.00);
		HorarioAtencion Jueves = new HorarioAtencion(jueves, 10.00, 15.00);
		HorarioAtencion Viernes = new HorarioAtencion(viernes, 10.00, 15.00);
		
		List<HorarioAtencion> horariosDeTodaLaSemana = new ArrayList<HorarioAtencion>();{
			horariosDeTodaLaSemana.add(Lunes);
			horariosDeTodaLaSemana.add(Martes);
			horariosDeTodaLaSemana.add(Miercoles);
			horariosDeTodaLaSemana.add(Jueves);
			horariosDeTodaLaSemana.add(Viernes);
		}
		
		Servicio cobroCheques = new Servicio("CobroCheques", horariosDeTodaLaSemana);
		Servicio depositos = new Servicio("Depósito", horariosDeTodaLaSemana);
		Servicio extracciones = new Servicio("Extracciones", horariosDeTodaLaSemana);
		Servicio transferencias = new Servicio("Transferencias", horariosDeTodaLaSemana);
		Servicio creditos = new Servicio("Créditos", horariosDeTodaLaSemana);
		
		List<Servicio> serviciosNuevo = new ArrayList<Servicio> ();{
		serviciosNuevo.add(cobroCheques);
		serviciosNuevo.add(depositos);
		serviciosNuevo.add(extracciones);
		serviciosNuevo.add(transferencias);
		serviciosNuevo.add(creditos);}
		
		Direccion direccionNuevo = new Direccion("unaCalle", "unNumero");
		
		Coordenada coordenadaNuevo = new Coordenada(-35.9338322, 72.348353);
		
		Sucursal bancoAdaptado = new Sucursal(direccionNuevo, "BancoDeLaPlaza", 
				"Avellaneda", coordenadaNuevo, serviciosNuevo);
	//OTRO BANCO
	Direccion direccionGalicia = new Direccion ("Av. Francisco Beiro", "5289");
		
	Coordenada coordenadaGaliciaDevoto = new Coordenada (-34.613752, -58.524815);
	HorarioAtencion horariosDeChequesLM = new HorarioAtencion(lunes, 10.00, 15.00);
	HorarioAtencion horariosDeChequesLT = new HorarioAtencion(miercoles, 10.00, 15.00);
	HorarioAtencion horariosDeChequesV = new HorarioAtencion(viernes, 10.00, 15.00);
	
	List<HorarioAtencion> horariosParaCheques = new ArrayList<HorarioAtencion>();
	{horariosParaCheques.add(horariosDeChequesLM);
	horariosParaCheques.add(horariosDeChequesLT);
	}
	List<Servicio> listaDeServicios = new ArrayList<Servicio>();
	Servicio cheques = new Servicio ("Cheques", horariosParaCheques);
	
	{
	listaDeServicios.add(cheques);
	}
	
	Sucursal galiciaDevoto = new Sucursal(direccionGalicia, "Galicia", 
			"galiciaDevoto", coordenadaGaliciaDevoto, listaDeServicios);

	
	//GENERA UN CGP
		//CGP3
		Direccion direccionCGP3 = new Direccion ("Junín", "521");
		Direccion direccionCGP117 = new Direccion ("Bacacay","968");
	
		Coordenada coordenadaCGP3 = new Coordenada (-34.603302, -58.397160);
		Coordenada punto1 = new Coordenada(-34.601192, -58.399927);
		Coordenada punto2 = new Coordenada(-34.600627, -58.386752);
		Coordenada punto3 = new Coordenada(-34.607384, -58.400626);
		Coordenada punto4 = new Coordenada(-34.606407, -58.386340);
	
		Coordenada coordenadaCGP117 = new Coordenada (-34.63215,-58.4846846);
		Coordenada punto5 = new Coordenada(-34.6323054,-58.4837008);
		Coordenada punto6 = new Coordenada(-34.631909,-58.4848569);
		Coordenada punto7 = new Coordenada(-34.6321369,-58.4847907);
		Coordenada punto8 = new Coordenada(-34.6321617,-58.4846703);
	
		Polygon poligonoCGP3 = new Polygon();
		{poligonoCGP3.add(punto1);
		poligonoCGP3.add(punto2);
		poligonoCGP3.add(punto3);
		poligonoCGP3.add(punto4);}
		
		Polygon poligonoCGP117 = new Polygon();
		{poligonoCGP3.add(punto5);
		poligonoCGP3.add(punto6);
		poligonoCGP3.add(punto7);
		poligonoCGP3.add(punto8);}
		
		List<HorarioAtencion> horariosDePension = new ArrayList<HorarioAtencion>();
		{horariosDePension.add(horariosDeChequesLM);
		horariosDePension.add(horariosDeChequesLT);
		}
		
		List<HorarioAtencion> horariosDeRentas = new ArrayList<HorarioAtencion>();
		{horariosDeRentas.add(horariosDeChequesLM);
		horariosDeRentas.add(horariosDeChequesLT);
		horariosDeRentas.add(horariosDeChequesV);}
		
		HorarioAtencion lunesJubM = new HorarioAtencion(lunes, 8.00, 12.45);
		HorarioAtencion lunesJubT = new HorarioAtencion(lunes, 14.00, 18.30);
		
		List<HorarioAtencion> horariosDeJubilacion = new ArrayList<HorarioAtencion>();{
			horariosDeJubilacion.add(lunesJubM);
			horariosDeJubilacion.add(lunesJubT);
		}
		
		Servicio pension = new Servicio ("Pension", horariosDePension);
		Servicio jubilacion = new Servicio("Jubilacion", horariosDeJubilacion);
		Servicio rentas = new Servicio ("Rentas", horariosDeRentas);
		
		List<Servicio> listaDeServiciosDeCGP = new ArrayList<Servicio>();
		{listaDeServiciosDeCGP.add(cheques);
		listaDeServiciosDeCGP.add(jubilacion);}
		
		List<Servicio> listaDeServiciosDeCGP117 = new ArrayList<Servicio>();
		{listaDeServiciosDeCGP117.add(rentas);
		listaDeServiciosDeCGP117.add(pension);}
		
		Comuna comunaCGP3 = new Comuna (3, poligonoCGP3, listaDeServiciosDeCGP);
		Comuna comunaCGP117 = new Comuna(117, poligonoCGP117, listaDeServiciosDeCGP117);
		
		CGP cgp3 = new CGP (direccionCGP3, coordenadaCGP3, "cgp3", comunaCGP3);
		
		CGP cgp117 =  new CGP (direccionCGP117, coordenadaCGP117, "cgp117", comunaCGP117);
		
	//GENERA UNA PARADA DE COLECTIVO
		//PARADA DE COLECTIVO 117
		Direccion direccion117Cosquin = new Direccion("Cosquin", "302");
		Coordenada coordenada117Cosquin = new Coordenada (-34.642732, -58.523251);
		ParadaDeColectivo parada117Cosquin = new ParadaDeColectivo(direccion117Cosquin, coordenada117Cosquin);
		List<ParadaDeColectivo> paradasDeColectivo117 = new ArrayList<ParadaDeColectivo>();
		{paradasDeColectivo117.add(parada117Cosquin);}
		
		//PARADA DE COLECTIVO 2
		Direccion direccion2Rivadavia = new Direccion("Av.Rivadavia", "11376");
		Coordenada coordenada2Rivadavia = new Coordenada(-34.639007, -58.524632);
		ParadaDeColectivo parada2Rivadavia = new ParadaDeColectivo(direccion2Rivadavia,coordenada2Rivadavia);
		List<ParadaDeColectivo> paradasDeColectivo2 = new ArrayList<ParadaDeColectivo>();
		{paradasDeColectivo2.add(parada2Rivadavia);}
	
		//COLECTIVO 117
		Direccion direccion117 = null;
		Coordenada coordenadaColectivo117 = new Coordenada(-34.643178, -58.523200);
		Colectivo colectivo117 = new Colectivo (direccion117, "117", coordenadaColectivo117, 117, paradasDeColectivo117);
		
		//COLECTIVO 2
		Direccion direccion2 = null;
		Coordenada coordenadaColectivo2 = new Coordenada(-34.635231, -58.487350);
		Colectivo colectivo2 = new Colectivo (direccion2, "2", coordenada2Rivadavia, 2, paradasDeColectivo2);
		
	//GENERA UN LOCAL
	Direccion direccionRamon = new Direccion ("Cnel. Ramon Falcon", "7051");
	Coordenada coordenadaRamon = new Coordenada (-34.640128, -58.525601);
	List<String> palabrasClaves = new ArrayList <String>();
	{
	palabrasClaves.add("remeras");
	palabrasClaves.add("pantalones");
	palabrasClaves.add("polleras");
	
	}
	
	Rubro comercial = new Rubro ("venta de ropa", 0.5, palabrasClaves);
	
	HorarioAtencion horariosLAM = new HorarioAtencion(lunes, 10.00, 12.30);
	HorarioAtencion horariosLPM = new HorarioAtencion(lunes, 16.00, 20.00);
	HorarioAtencion horariosMTot = new HorarioAtencion(martes, 10.00, 16.00);
	List<HorarioAtencion> disponibiidadRamon = new ArrayList <HorarioAtencion>();
	{
	disponibiidadRamon.add(horariosLAM);
	disponibiidadRamon.add(horariosLPM);
	disponibiidadRamon.add(horariosMTot);}
	
	Local localRamon = new Local(direccionRamon, "Los Ramones", coordenadaRamon, comercial, disponibiidadRamon);

	//GENERACION DE REPOSITORIO
		Repositorio unRepo = new Repositorio();{
		unRepo.crearUnaSucursalBancaria(direccionGalicia, "Galicia", "galiciaDevoto", coordenadaGaliciaDevoto, listaDeServicios);
		unRepo.crearUnColectivo(direccion117, "117", coordenadaColectivo117, 117, paradasDeColectivo117);
		unRepo.crearUnColectivo(direccion2, "2", coordenada2Rivadavia, 2, paradasDeColectivo2);
		unRepo.crearUnCGP(direccionCGP3, coordenadaCGP3, "cgp3", comunaCGP3);
		unRepo.crearUnCGP(direccionCGP117, coordenadaCGP117, "cgp117", comunaCGP117);
		unRepo.crearUnLocalComercial(direccionRamon, "Los Ramones", coordenadaRamon, comercial, disponibiidadRamon);
		unRepo.agregarPOIConId(galiciaDevoto);}
	
	//COORDENADAS PARA LA CERCANIA
	//CoordenadaLejana
	Coordenada coordenadaLejana = new Coordenada (-34.737688, -58.486905);
	//CoordenadaCercana para la sucursal
	Coordenada coordenadaCercanaParaSucursalGalicia = new Coordenada (-34.613620, -58.524611);
	//CoordenadaCercana para la CGP3
	Coordenada coordenadaCercanaDeCGP3 = new Coordenada(-34.607384, -58.400626);
	//coordenadaCercana para la parada de colecctivo del 117 de cosquin
	Coordenada coordenadaCercanaDeParadaDeColectivo117DeCosquin = new Coordenada (-34.642472, -58.523283);
	//coordenadaCercana para el Local Ramon
	Coordenada cordenadaCercanaLocalRamon = new Coordenada (-34.640188, -58.526242);

	//Creacion del adapter
	AdapterBancoJSon adaptadorJSon = new AdapterBancoJSon();
	AdapterCentroDTO adaptadorCentroDTO = new AdapterCentroDTO();
	
	//CGP3 DTO
	RangoServicioDTO lunesManana = new RangoServicioDTO (1 , 8, 0, 12, 45);
	RangoServicioDTO lunesTarde = new RangoServicioDTO(1, 14, 0, 18, 30);
	
	List<RangoServicioDTO> listaDeDias = new ArrayList<>();
	{listaDeDias.add(lunesManana);
	listaDeDias.add(lunesTarde);}
	
	ServicioDTO pensionNueva = new ServicioDTO ("Pension", listaDeDias);
	
	ServicioDTO chequeNuevo = new ServicioDTO("Cheque", listaDeDias);

	List<ServicioDTO> serviciosQueOfrece = new ArrayList<> ();
	{serviciosQueOfrece.add(pensionNueva);
	serviciosQueOfrece.add(chequeNuevo);}
	
	Coordenada coordenadasCGP3 = new Coordenada(-34.603302, -58.397160);
												
	String barriosQueIncluye = "Balvanera, San Cristobal";

	CentroDTO cgp3DTO = new CentroDTO (3, "cgp3", barriosQueIncluye, "Perez Ricardo", "Junín 521", "4375-0644/45", serviciosQueOfrece, coordenadasCGP3, poligonoCGP3);

	//BANCO EXTERNO
	
	List<String> listaDeStringDeServicios = new ArrayList<String>();
	{listaDeStringDeServicios.add("Cobrocheques");
	listaDeStringDeServicios.add("Depósitos");
	listaDeStringDeServicios.add("Extracciones");
	listaDeStringDeServicios.add("Transferencias");
	listaDeStringDeServicios.add("Créditos");		
	}
	
	BancoExterno objetoJSon = new BancoExterno("unaCalle", "unNumero", "BancoDeLaPlaza"
			, -35.9338322, 72.348353, "Avellaneda", "JavierLoeschbor", listaDeStringDeServicios);
	
	//CREACION DE USUARIOS
		//CREAR ACCION
		AccionLoggear accionLoggear = new AccionLoggear(true);
			//CREACION DE LA LISTA DE ACCIONES ADMINISTRADOR
			List<AccionesDeUsuario> listaAccionesAdm = new ArrayList<AccionesDeUsuario>(); 
			{listaAccionesAdm.add(accionLoggear);}
			//CREACION DE LA LISTA DE ACCIONES TERMINAL
			List<AccionesDeUsuario> listaAccionesTerm = new ArrayList<AccionesDeUsuario>(); 
			{accionLoggear.setPermiso(false);
			listaAccionesTerm.add(accionLoggear);}
			//CREACION DE LA LISTA DE ACCIONES VACIA
			List<AccionesDeUsuario> listaAccionesVacia = new ArrayList<AccionesDeUsuario>();
		//CREAR LISTA DE CONSULTAS
			List<NuevaConsultaExtendida> consultaRealizadasAdm = new ArrayList<NuevaConsultaExtendida> ();
			List<NuevaConsultaExtendida> consultaRealizadasTerm = new ArrayList<NuevaConsultaExtendida> ();
		//TERMINAL
			//SIN PERMISOS
			UsuarioTerminal usuarioTerminalSinAcciones = new UsuarioTerminal("elJuniorUsuarioConPermiso",
					unRepo,listaAccionesVacia, consultaRealizadasTerm, "");
			//CON PERMISO 
			UsuarioTerminal usuarioTerminal = new UsuarioTerminal("elJuniorUsuarioConPermiso",
					unRepo,listaAccionesTerm, consultaRealizadasTerm, "");	
}