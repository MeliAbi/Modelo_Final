package ar.unlam.edu.ar;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class TestComaniaTransporte {

	@Test
	public void queSePuedaRegistrarUnViaje() {

		// No modificar este test.
		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cantidadPasajerosMaximos = 50;
		MedioTransporte medioTransporte = new TransportePasajero(Patente, cantidadPasajerosMaximos);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);

		empresa.registrarViaje(viaje);

		Boolean valorObtenido = empresa.getViajes().containsValue(viaje);
		assertTrue(valorObtenido);

	}

	@Test
	public void queSePuedaRegistrarUnTicketDePasajeroAUnViaje() 
			throws CantidadPasajeroSobrepasadaException,TipoTicketInvalidoExcption {

		// No modificar este test.
		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cantidadPasajerosMaximos = 2;
		MedioTransporte medioTransporte = new TransportePasajero(Patente, cantidadPasajerosMaximos);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);

		empresa.registrarViaje(viaje);

		Integer numeroViaje = 1;
		Integer dni = 2222;
		String apellido = "perez";
		Pasajero pasajero = new Pasajero(dni, apellido);
		try {
			empresa.registrarTicketPasajero(numeroViaje, pasajero);
		} catch (TipoTicketInvalidoExcption | CantidadPasajeroSobrepasadaException e) {
			if(e instanceof CantidadPasajeroSobrepasadaException) {
				throw new CantidadPasajeroSobrepasadaException(e.getMessage());
			}else {
				throw new TipoTicketInvalidoExcption(e.getMessage());
			}
		}

		// Completar Test
		assertEquals(1,empresa.buscarViaje(numeroViaje).getTickets().size());

	}

	@Test (expected= TipoTicketInvalidoExcption.class)
	public void queAlRegistrarUnTicketDePasajeroAUnViajeConMedioDeTransporteDeCargaLanceUnaException() 
			throws CantidadPasajeroSobrepasadaException,TipoTicketInvalidoExcption{

		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cargaMaxima = 50;
		MedioTransporte medioTransporte = new TransporteCarga(Patente, cargaMaxima);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);

		empresa.registrarViaje(viaje);

		Integer numeroViaje = 1;

		Integer dni = 2222;
		String apellido = "perez";
		Pasajero pasajero = new Pasajero(dni, apellido);
		try {
			empresa.registrarTicketPasajero(numeroViaje, pasajero);
		} catch (TipoTicketInvalidoExcption | CantidadPasajeroSobrepasadaException e) {
			throw new TipoTicketInvalidoExcption(e.getMessage());
		}

		// Completar test
		
	}

	@Test
	public void queSePuedaRegistrarUnTicketDeCargaAUnViaje() throws CapacidadExcedidaException,TipoTicketInvalidoExcption{

		// No modificar este test.
		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cargaMaxima = 50;
		MedioTransporte medioTransporte = new TransporteCarga(Patente, cargaMaxima);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);
		
		empresa.registrarViaje(viaje);

		Integer numeroViaje = 1;
		
		try {
			empresa.registrarTicketcarga(numeroViaje, new Carga(1, 10));
		} catch (CapacidadExcedidaException | TipoTicketInvalidoExcption e) {
			if(e instanceof CapacidadExcedidaException) {
				throw new CapacidadExcedidaException(e.getMessage());
			}else {
				throw new TipoTicketInvalidoExcption(e.getMessage());
			}
		}

		assertEquals(1,empresa.buscarViaje(numeroViaje).getTickets().size());
		assertEquals((Integer)10,((TransporteCarga)empresa.buscarViaje(numeroViaje).getMedioTransporte()).obtenerCargaActual());

	}

	@Test
	public void queSePuedaRegistrarUnTicketMixtoAUnViaje() throws CantidadPasajeroSobrepasadaException, TipoTicketInvalidoExcption, CapacidadExcedidaException{

		// No modificar este test.
		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cargaMaxima = 20;

		Integer cantidadPasajerosMaximos = 3;

		MedioTransporte medioTransporte = new TransporteMixto(Patente, cantidadPasajerosMaximos,cargaMaxima);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);

		empresa.registrarViaje(viaje);

		Integer numeroViaje = 1;
		Integer dni = 2222;
		String apellido = "perez";
		
		Pasajero pasajero = new Pasajero(dni, apellido);
		Carga carga=new Carga(1, 10);
		
		
		try {
			empresa.registrarTicketMixto(numeroViaje, pasajero,carga);
		} catch (CantidadPasajeroSobrepasadaException | TipoTicketInvalidoExcption | CapacidadExcedidaException e) {
			if(e instanceof CantidadPasajeroSobrepasadaException) {
				throw new CantidadPasajeroSobrepasadaException(e.getMessage());
				
			}else if(e instanceof CapacidadExcedidaException){
				throw new CapacidadExcedidaException(e.getMessage());
				
			}else {
				throw new TipoTicketInvalidoExcption(e.getMessage());
			}
		}
		// Completar Test
		assertEquals(1,empresa.buscarViaje(numeroViaje).getTickets().size());
		assertEquals((Integer)10,((TransporteMixto)empresa.buscarViaje(numeroViaje).getMedioTransporte()).obtenerCargaActual());
		assertEquals((Integer)1,((TransporteMixto)empresa.buscarViaje(numeroViaje).getMedioTransporte()).obtenerCantidadPasajerosABordo());
	}

	@Test (expected = CapacidadExcedidaException.class)
	public void queAlRegistrarUnticketYExcedalaCargaMaximaDelTransporteLanceUnaExceptionCapacidadExcedidaException() throws CantidadPasajeroSobrepasadaException, CapacidadExcedidaException, TipoTicketInvalidoExcption {
		// No modificar este test.
		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cargaMaxima = 20;

		Integer cantidadPasajerosMaximos = 3;

		MedioTransporte medioTransporte = new TransporteMixto(Patente, cantidadPasajerosMaximos,cargaMaxima);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);

		empresa.registrarViaje(viaje);

		Integer numeroViaje = 1;
		Integer dni = 2222;
		String apellido = "perez";
		
		Pasajero pasajero = new Pasajero(dni, apellido);
		Carga carga=new Carga(1, 1000);
		
		
		try {
			empresa.registrarTicketMixto(numeroViaje, pasajero,carga);
		} catch (CantidadPasajeroSobrepasadaException | TipoTicketInvalidoExcption | CapacidadExcedidaException e) {
			if(e instanceof CantidadPasajeroSobrepasadaException) {
				throw new CantidadPasajeroSobrepasadaException(e.getMessage());
				
			}else if(e instanceof CapacidadExcedidaException){
				throw new CapacidadExcedidaException(e.getMessage());
				
			}else {
				throw new TipoTicketInvalidoExcption(e.getMessage());
			}
		}
		// Completar Test
		assertEquals(1,empresa.buscarViaje(numeroViaje).getTickets().size());
		assertEquals((Integer)10,((TransporteMixto)empresa.buscarViaje(numeroViaje).getMedioTransporte()).obtenerCargaActual());
		assertEquals((Integer)1,((TransporteMixto)empresa.buscarViaje(numeroViaje).getMedioTransporte()).obtenerCantidadPasajerosABordo());

	}

	@Test
	public void queSePuedaObtenerUnaListaPasajeroDeUnViajeOrdenadoPorDNIDescendiente() throws CantidadPasajeroSobrepasadaException, TipoTicketInvalidoExcption, TipoViajeInvalidoExcption {
		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cantidadPasajerosMaximos = 3;
		MedioTransporte medioTransporte = new TransportePasajero(Patente, cantidadPasajerosMaximos);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);

		empresa.registrarViaje(viaje);

		Integer numeroViaje = 1;
		Integer dni = 2223;
		String apellido = "perez";
		Pasajero pasajero = new Pasajero(dni, apellido);
		
		Integer dni1 = 2221;
		String apellido1 = "perez";
		Pasajero pasajero1 = new Pasajero(dni1, apellido1);
		
		Integer dni2 = 2222;
		String apellido2 = "perez";
		Pasajero pasajero2 = new Pasajero(dni2, apellido2);
		
		try {
			empresa.registrarTicketPasajero(numeroViaje, pasajero);
			empresa.registrarTicketPasajero(numeroViaje, pasajero1);
			empresa.registrarTicketPasajero(numeroViaje, pasajero2);
		} catch (TipoTicketInvalidoExcption | CantidadPasajeroSobrepasadaException e) {
			if(e instanceof CantidadPasajeroSobrepasadaException) {
				throw new CantidadPasajeroSobrepasadaException(e.getMessage());
			}else {
				throw new TipoTicketInvalidoExcption(e.getMessage());
			}
		}
		try {
			System.out.println(empresa.obtenerListaPasajeroOrdenadosPorDNIDescendiente(numeroViaje));
		} catch (TipoViajeInvalidoExcption e) {
			throw new TipoViajeInvalidoExcption(e.getMessage());
		}
		assertEquals(3,empresa.buscarViaje(numeroViaje).getTickets().size());

	}

	@Test
	public void queSePuedaObtenerElTotalDeCargasTransportada() throws CapacidadExcedidaException, TipoTicketInvalidoExcption {
		// No modificar este test.
		Empresa empresa = new Empresa("UnlamTravel");

		LocalDateTime salida = LocalDateTime.of(2023, 07, 20, 19, 00);
		LocalDateTime llegada = LocalDateTime.of(2023, 07, 21, 0, 30);
		String origen = "Buenos Aires";
		String destino = "Mardel Plata";

		String Patente = "ac111jr";

		Integer cargaMaxima = 50;
		MedioTransporte medioTransporte = new TransporteCarga(Patente, cargaMaxima);

		Viaje viaje = new Viaje(salida, llegada, origen, destino, medioTransporte);
		
		empresa.registrarViaje(viaje);

		Integer numeroViaje = 1;
		
		try {
			empresa.registrarTicketcarga(numeroViaje, new Carga(1, 10));
			empresa.registrarTicketcarga(numeroViaje, new Carga(2, 10));
			empresa.registrarTicketcarga(numeroViaje, new Carga(3, 20));
		} catch (CapacidadExcedidaException | TipoTicketInvalidoExcption e) {
			if(e instanceof CapacidadExcedidaException) {
				throw new CapacidadExcedidaException(e.getMessage());
			}else {
				throw new TipoTicketInvalidoExcption(e.getMessage());
			}
		}

		System.out.println(((TransporteCarga)empresa.buscarViaje(numeroViaje).getMedioTransporte()).obtenerCargaActual());
		System.out.println(((TransporteCarga)empresa.buscarViaje(numeroViaje).getMedioTransporte()).getCargas());
		
		assertEquals(3,empresa.buscarViaje(numeroViaje).getTickets().size());
		assertEquals((Integer)40,((TransporteCarga)empresa.buscarViaje(numeroViaje).getMedioTransporte()).obtenerCargaActual());


	}
}
