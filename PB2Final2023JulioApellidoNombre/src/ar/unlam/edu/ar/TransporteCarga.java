package ar.unlam.edu.ar;

import java.util.ArrayList;
import java.util.List;

public class TransporteCarga  extends MedioTransporte implements iCargable{

	private Integer cargaMaxima;
	private List<Carga> cargas;

	public TransporteCarga(String patente, Integer cargaMaxima) {
		super(patente);
		this.cargaMaxima=cargaMaxima;
		cargas = new ArrayList <Carga>();
	}


	public void setCargaMaxima(Integer cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}
	

	public List<Carga> getCargas() {
		return cargas;
	}


	public void setCargas(Carga carga) {
		cargas.add(carga);
	}
	
	public Integer obtenerCargaActual() {
		Integer ca=0;
		
		for(Carga actual : cargas) {
			ca+=actual.getPeso();
		}
		return ca;
	}


	@Override
	public Integer obtenerCantidadMaximaCarga() {
		return cargaMaxima;
	}

}
