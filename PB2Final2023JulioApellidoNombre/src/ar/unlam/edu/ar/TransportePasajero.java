package ar.unlam.edu.ar;

public class TransportePasajero extends MedioTransporte implements ITransportable{

	private Integer cantidadPasajerosMaximos=0;
	private Integer cantidadPasajerosABordo=0;

	public TransportePasajero(String patente, Integer cantidadPasajerosMaximos) {
		super(patente);
		this.cantidadPasajerosMaximos=cantidadPasajerosMaximos;
	}

	public void setCantidadPasajerosMaximos(Integer cantidadPasajerosMaximos) {
		this.cantidadPasajerosMaximos = cantidadPasajerosMaximos;
	}

	@Override
	public Integer obtenerCantidadMaximaPasajero() {
		return cantidadPasajerosMaximos;
	}

	@Override
	public Integer obtenerCantidadPasajerosABordo() {
		return cantidadPasajerosABordo;
	}

	public void setCantidadPasajerosABordo(Integer cant) {
		this.cantidadPasajerosABordo=cant;
		
	}
}
