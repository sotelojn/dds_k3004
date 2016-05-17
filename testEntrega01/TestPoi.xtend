package dds.grupo06.testEntrega01

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import dds.grupo06.poi.ParadaColectivo
import dds.grupo06.poi.SucursalBancaria
import dds.grupo06.poi.CGP
import dds.grupo06.poi.ServicioCGP
import java.util.List
import dds.grupo06.poi.POI
import java.util.ArrayList
import org.uqbar.geodds.Point
import org.uqbar.geodds.Polygon
import dds.grupo06.poi.Ubicacion
import java.time.LocalTime
import dds.grupo06.poi.Dia
import dds.grupo06.poi.LocalComercial
import dds.grupo06.poi.Rubro
import dds.grupo06.domain.Repositorio
import org.json.JSONObject
import dds.grupo06.stubs.StubRepoBanco
import dds.grupo06.stubs.StubRepoCGP
import dds.grupo06.adapters.AdapterCGP

class TestPoi {
	LocalTime momento
	LocalTime momentoDos
	LocalTime momentoTres
	LocalTime momentoCuatro
	LocalTime momentoCinco
	Dia dia
	Dia dia2
	Dia dia3

	// variables e instancias para punto3
	Repositorio unRepositorio
	ParadaColectivo unaParadaColectivo
	ParadaColectivo otraParadaColectivo
	SucursalBancaria unBanco
	SucursalBancaria otroBanco
	CGP unCGP
	CGP otroCGP
	Rubro unRubro
	Rubro otroRubro
	String nombreRubro
	String etiquetaColectivo
	// String etiquetaBanco = "banco"
	String nombreServicio
	String otroNombreServicio
	ServicioCGP unServicio
	ServicioCGP otroServicio
	List<POI> listadoDeRetorno
	// fin variables e instancias para punto3
	ParadaColectivo otraParada
	Point vertice1
	Point vertice2
	Point vertice3
	Point vertice4
	List<Point> listadoDeCoordenadas
	StubRepoBanco unStub
	StubRepoCGP unStubRepoCGP
	List<JSONObject> unaLista
	AdapterCGP adapterCGP

	@Before
	def void setUp() {
		unaLista = new ArrayList()
		adapterCGP = new AdapterCGP(unStubRepoCGP)
		unStubRepoCGP = new StubRepoCGP()
		unStub = new StubRepoBanco(unaLista)
		// Inicio de juego de datos para punto2
		momento = LocalTime.of(15, 44, 0, 0) // 15:44hs
		dia = Dia.LUNES
		momentoDos = LocalTime.of(10, 0, 0, 0) // 10:00hs
		dia2 = Dia.MIERCOLES
		momentoTres = LocalTime.of(15, 0, 0, 0) // 15:00hs
		dia3 = Dia.SABADO
		momentoCuatro = LocalTime.of(20, 0, 0, 0) // 20:00hs
		momentoCinco = LocalTime.of(22, 0, 0, 0) // 22:00hs
		unRubro = new Rubro("carrousel")
		unRubro.agregarHorarioAtencion(dia, momentoDos, momentoCuatro) // carrousel abierto de 10 a 20hs
		nombreRubro = "carrousel"
		// Fin de juego de datos para punto2
		// Inicio de juego de datos para punto3
		unRepositorio = new Repositorio()
		unRepositorio.servicioBanco = unStub
		unRepositorio.servicioCGP = unStubRepoCGP
		unaParadaColectivo = new ParadaColectivo("114")
		otraParadaColectivo = new ParadaColectivo("26")
		otraParadaColectivo.etiquetas.add("parque chacabuco") // <-- Agrego etiqueta general
		unBanco = new SucursalBancaria("santander")
		//unBanco.nombreBanco = "santander"
		otroBanco = new SucursalBancaria("frances")
		//otroBanco.nombreBanco = "frances"
		unCGP = new CGP()
		otroCGP = new CGP()
		unServicio = new ServicioCGP("plaza publica")
		unServicio.agregarHorarioAtencion(dia2, momentoDos, momento)
		otroServicio = new ServicioCGP("centro civico")
		unServicio.agregarHorarioAtencion(Dia.JUEVES, momentoDos, momentoTres)
		listadoDeRetorno = new ArrayList()
		nombreServicio = "plaza"
		otroNombreServicio = "centro civico"
		etiquetaColectivo = "114"
		unCGP.servicios.add(unServicio)
		otroCGP.servicios.add(otroServicio)
		unRepositorio.listadoPois.add(unBanco)
		unRepositorio.listadoPois.add(unCGP)
		unRepositorio.listadoPois.add(unaParadaColectivo)
		unRepositorio.listadoPois.add(otraParadaColectivo)
		unRepositorio.listadoPois.add(otroCGP)
		// Fin Juego de datos para punto3 
		// - Comienza Juego de datos para el punto 1
		otroRubro = new Rubro("kioskoDiarios")
		otroRubro.radioDeCercania = 0.2 // cada rubro define su radio de cercania, para los kioskos de diarios son 2 cuadras
		otroRubro.coordenadasRubro = new Point(1.2,1.2)
		otraParada = new ParadaColectivo("7")
		otraParada.coordenadas = new Point(2.0, 1.0)
		unCGP.coordenadas = new Point(1.0, 1.0)
		otroCGP.coordenadas = new Point(7.0, 14.0)
		unBanco.coordenadas = new Point(0.0, 0.0)
		otroBanco.coordenadas = new Point(1.1, 1.1)
		// Creo e inicializo otro Poi cualquiera para probar el CGP,la comuna es un cuadrado de lado 3
		unaParadaColectivo.coordenadas = new Point(2.0, 2.0)
		otraParadaColectivo.coordenadas = new Point (1.0001, 1.0001)
		otraParada.direccion = new Ubicacion()
		// los vertices de la comuna
		vertice1 = new Point(0.0, 0.0)
		vertice2 = new Point(3.0, 0.0)
		vertice3 = new Point(3.0, 3.0)
		vertice4 = new Point(0.0, 3.0)
		listadoDeCoordenadas = new ArrayList<Point>()
		listadoDeCoordenadas.add(vertice1)
		listadoDeCoordenadas.add(vertice2)
		listadoDeCoordenadas.add(vertice3)
		listadoDeCoordenadas.add(vertice4)
		otraParada.direccion.comuna = new Polygon(listadoDeCoordenadas)
	// Fin Juego de datos para punto 1
	}

	@Test // test un kiosko de diarios esta cerca si esta a 2 cuadras
	def void estoyCercaSoyUnKioskoDiarios() {
		Assert.assertEquals(false, otroRubro.estaCerca(otroBanco))
	}
	
	@Test // test un kiosko de diarios esta lejos si esta a mas de 2 cuadras
	def void estoyLejosSoyUnKioskoDiarios() {
		Assert.assertEquals(false, otroRubro.estaCerca(otroCGP))
	}

	@Test // Test punto1, se debe comportar genericamente porque no hay excepciones
	def void noEstaCercaSoyUnBanco() {
		Assert.assertEquals(false, unBanco.estaCerca(unaParadaColectivo))
	}
	
	@Test // Testeo que un banco en el (0.0) este en el borde del poligono y se considere lejos por no estar dentro del mismo
	def void estoyLejosSoyUnBanco(){
		Assert.assertEquals(false, unBanco.estaCerca(otraParada))
	}
		

	@Test // Test punto 1a
	def void noEstaCercaSoyUnaParadaDeBondi() {
		Assert.assertEquals(false, unaParadaColectivo.estaCerca(unCGP))
	}
	
	@Test // Test una parada a menos de una cuadra de un CGP se considera cercana
	def void estoyCercaSoyUnaParadaAMenosDeUnaCuadra(){
		Assert.assertEquals(true, otraParadaColectivo.estaCerca(unCGP))
	}

	@Test // Test 1b CGP
	def void estoyCercaSoyUnCGP() {
		Assert.assertEquals(true, unCGP.estaCerca(otraParada))
	}

	@Test // otro CGP que esta fuera del radio de la comuna	
	def void estoyLejosSoyOtroCGP() {
		Assert.assertEquals(false, otroCGP.estaCerca(otraParada))
	}

	@Test // Test punto 2a. El coletivo siempre esta disponible, asi tendria que dar true en cualquier caso
	def void elBondiSiempreEstaDisponible() {
		Assert.assertEquals(true, unaParadaColectivo.estaDisponible(momento, dia))
	}

	@Test // Test punto 2b. Pruebo en un horario que el banco esta abierto
	def void elBancoEstaAbierto() {
		Assert.assertEquals(true, unBanco.estaDisponible(momentoDos, dia))

	}

	@Test // Test punto 2c. Pruebo en un horario donde justo el banco esta cerrado 15:00
	def void elBancoEstaCerrado() {
		Assert.assertEquals(false, unBanco.estaDisponible(momentoTres, dia3))

	}

	@Test // Test punto 2d. Pruebo en un horario que un CGP esta abierto
	def void elCgpEstaAbierto() {
		Assert.assertEquals(true, unCGP.estaDisponible(momentoDos, dia2))

	}

	@Test // Test punto 2e. Pruebo en un horario que un CGP esta cerrado
	def void elCgpEstaCerrado() {
		Assert.assertEquals(false, unCGP.estaDisponible(momento, dia))

	}

	@Test // Test punto 2f. El Local Comercial esta abierto de 10 a 20hs, pruebo si esta abierto a las 15:00
	def void elLocalComercialEstaAbierto() {
		Assert.assertEquals(true, unRubro.estaDisponible(momentoTres, dia))
	}

	@Test // Test punto 2g. El Local Comercial deberia estar cerrado a las 22:00
	def void elLocalComercialEstaCerrado() {
		Assert.assertEquals(false, unRubro.estaDisponible(momentoCinco, dia))

	}

	@Test // Test punto3 a
	def void testEtiquetaColectivo() {
		Assert.assertEquals(unaParadaColectivo.matcheaBusqueda(etiquetaColectivo), true)
	}

	@Test // Test punto3 b
	def void testEtiquetaBanco() {
		Assert.assertEquals(unBanco.matcheaBusqueda("santander"), true)
	}

	@Test // Test punto3 c  envio "plaza" y encuentra servicio "plaza publica"
	def void testEtiquetaCGP() {
		Assert.assertEquals(unCGP.matcheaBusqueda(nombreServicio), true)
	}

	@Test // Test punto3 d
	def void testDevuelveObjetoQueCoincide() {
		Assert.assertEquals((unRepositorio.buscarCoincidenciasCon("114")).head.identityEquals(unaParadaColectivo), true)
	}

	@Test // Test punto3 e
	def void testDevuelveObjetoQueCoincide2() {
		Assert.assertEquals((unRepositorio.buscarCoincidenciasCon("plaza publica")).size, 1)
	}

	@Test // usuario encuentra lo que busca en etiquetas generales
	def void testUsuarioEncuentraEnEtiquetaGeneral() {
		Assert.assertEquals((unRepositorio.buscarCoincidenciasCon("parque chacabuco")).head.identityEquals(otraParadaColectivo), true)
	}

	@Test // usuario no encuentra lo que busca en etiquetas generales ni en particulares
	def void testUsuarioNoEncuentraEnEtiquetaGeneralNiParticular() {
		Assert.assertEquals((unRepositorio.buscarCoincidenciasCon("hospital")).size, 0)
	}
	
	@Test // usuario busca un cine y no espera una parada de colectivo
	def void testUsuarioNoRecibeRespuestaErronea() {
		Assert.assertEquals((unRepositorio.buscarCoincidenciasCon("cine")).head.identityEquals(otraParadaColectivo), false)
	}
}
