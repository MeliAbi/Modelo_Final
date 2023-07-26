package ar.unlam.edu.ar;

import java.util.ArrayList;
import java.util.List;

public class TransporteMixto extends MedioTransporte implements iCargable, ITransportable{
	/*
	 * No se pueden agregar mas Atributos
	 */
	private Integer cantidadPasajerosMaximos=0;
	private Integer cantidadPasajerosABordo=0;
	private Integer cargaMaxima=0;
	private List<Carga> cargas;
 

	public TransporteMixto(String patente, Integer cantidadPasajerosMaximos2, Integer cargaMaxima2) {
		super(patente);
		this.cargaMaxima=cargaMaxima2;
		this.cantidadPasajerosMaximos=cantidadPasajerosMaximos2;
		cargas = new ArrayList<Carga>();
	}


	public void setCantidadPasajerosMaximos(Integer cantidadPasajerosMaximos) {
		this.cantidadPasajerosMaximos = cantidadPasajerosMaximos;
	}


	public void setCargaMaxima(Integer cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}

	@Override
	public Integer obtenerCantidadMaximaPasajero() {
		return cantidadPasajerosMaximos;
	}


	@Override
	public Integer obtenerCantidadMaximaCarga() {
		return cargaMaxima;
	}


	@Override
	public Integer obtenerCantidadPasajerosABordo() {
		return cantidadPasajerosABordo;
	}

	public void setCantidadPasajerosABordo(Integer ca) {
		this.cantidadPasajerosABordo=ca;
	}
	
	@Override
	public Integer obtenerCargaActual() {
		Integer ca=0;
		
		for(Carga actual : cargas) {
			ca+=actual.getPeso();
		}
		return ca;
	}

	public List<Carga> getCargas() {
		return cargas;
	}


	public void setCargas(Carga carga) {
		cargas.add(carga);
	}

}
