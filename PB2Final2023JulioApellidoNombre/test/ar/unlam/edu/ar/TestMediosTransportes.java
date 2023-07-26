package ar.unlam.edu.ar;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMediosTransportes {

	@Test
	public void queSepuedaCrearMedioTransportePasajero() {
 
		Integer cantidadPasajerosMaximos=50;
		String patente = "AB123CD";
		MedioTransporte pasajero = new TransportePasajero (patente,cantidadPasajerosMaximos);
		
		assertNotNull(pasajero);

	}
	
	@Test
	public void queSepuedaCrearMedioTransporteCarga() {

		Integer cargaMaxima = 1000;
		String patente = "987RT";
		MedioTransporte transporteCarga = new TransporteCarga (patente,cargaMaxima);
		
		assertNotNull(transporteCarga);
	}

	
	@Test
	public void queSepuedaCrearTransporteMixto() {

		Integer cantidadPasajerosMaximos=50;
		Integer cargaMaxima = 1000;
		String patente = "987RT";
		MedioTransporte transporteMixto = new TransporteMixto (patente,cantidadPasajerosMaximos,cargaMaxima);
		
		assertNotNull(transporteMixto);
	}


}
