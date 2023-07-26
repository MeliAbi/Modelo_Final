package ar.unlam.edu.ar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Empresa {

	private String nombre;
	private Map<Integer, Viaje> viajes;
	private Integer autoincremental =0;


	public Empresa(String nombre) {
		viajes= new TreeMap<Integer, Viaje>();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Map<Integer, Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Map<Integer, Viaje> viajes) {
		this.viajes = viajes;
	}

	public void registrarViaje(Viaje viaje) {
		autoincremental++;
		viajes.put(autoincremental,viaje);
	}

	/*
	 * Registra Un ticket para carga TicketCarga.class 
	 * Si el viaje no admite Carga lanza TipoTicketInvalidoExcption
	 *  si supera El peso maximo que soporta el   medioTransprte Lanza Una exception CapacidadExcedidaException
	 */

	public Viaje buscarViaje(Integer numeroViaje) {
		return viajes.get(numeroViaje);

	}
	
	public void registrarTicketcarga(Integer numeroViaje, Carga carga) throws CapacidadExcedidaException, TipoTicketInvalidoExcption {

		Viaje local = buscarViaje(numeroViaje);
		MedioTransporte mt = local.getMedioTransporte();
	
		if(mt instanceof TransporteCarga) {
			Integer ca = ((TransporteCarga)mt).obtenerCargaActual();
			
			if(((TransporteCarga)mt).obtenerCantidadMaximaCarga() > ca && ((TransporteCarga)mt).obtenerCantidadMaximaCarga() >= (ca + carga.getPeso())) {
				
				TicketCarga tc = new TicketCarga(numeroViaje,carga);
				((TransporteCarga)mt).setCargas(carga);
				local.setTickets(tc);
				
			}else {
				throw new CapacidadExcedidaException("Cantidad de Carga Sobrepasada");
			}
		}else
		{
			throw new TipoTicketInvalidoExcption("El tipo de ticket es invalido");
		}

	}

	/*
	 * Se registra un TicketPasajero TicketPasajero 
	 * Si el viaje no admite pasajeros lanza TipoTicketInvalidoExcption - OK
	 * si supera la cantidad de pasajero que soporta el medioTransprte Lanza Una exception CantidadPasajeroSobrepasadaException - OK
	 */

	public void registrarTicketPasajero(Integer numeroViaje, Pasajero pasajero) throws TipoTicketInvalidoExcption,CantidadPasajeroSobrepasadaException {
		
		Viaje local = buscarViaje(numeroViaje);
		MedioTransporte mt = local.getMedioTransporte();
	
		if(mt instanceof TransportePasajero) {
			Integer cant = ((TransportePasajero)mt).obtenerCantidadPasajerosABordo();
			
			if(((TransportePasajero)mt).obtenerCantidadMaximaPasajero() > cant) {
				
				TicketPasajero tp = new TicketPasajero(numeroViaje,pasajero);
				((TransportePasajero)mt).setCantidadPasajerosABordo(cant+1);
				local.setTickets(tp);
			}else {
				throw new CantidadPasajeroSobrepasadaException("Cantidad de Pasajeros Sobrepasada");
			}
		}else
		{
			throw new TipoTicketInvalidoExcption("El tipo de ticket es invalido");
		}
		
	}
	

	/*
	 * Se registra un TicketMixto TicketMixto.class  
	 * si supera la cantidad de pasajero que soporta el medioTransprte Lanza Una exception CantidadPasajeroSobrepasadaException
	 * si supera El peso maximo que soporta el   medioTransprte Lanza Una exception CapacidadExcedidaException
	 */

	public void registrarTicketMixto(Integer numeroViaje, Pasajero pasajero,Carga carga) 
			throws CantidadPasajeroSobrepasadaException, TipoTicketInvalidoExcption, CapacidadExcedidaException {
		
		Viaje local = buscarViaje(numeroViaje);
		MedioTransporte mt = local.getMedioTransporte();
	
		if(mt instanceof TransporteMixto) {
			Integer cant = ((TransporteMixto)mt).obtenerCantidadPasajerosABordo();
			Integer ca = ((TransporteMixto)mt).obtenerCargaActual();
			
			if(((TransporteMixto)mt).obtenerCantidadMaximaPasajero() > cant) {
				
				if(((TransporteMixto)mt).obtenerCantidadMaximaCarga() > ca && ((TransporteMixto)mt).obtenerCantidadMaximaCarga() >= (ca + carga.getPeso())) {
					Ticket tm = new TicketMixto(numeroViaje,pasajero,carga);
					((TransporteMixto)mt).setCantidadPasajerosABordo(cant+1);
					((TransporteMixto)mt).setCargas(carga);
					local.setTickets(tm);
					
				}else {
					throw new CapacidadExcedidaException("Cantidad de Carga Sobrepasada");
				}
			}else {
				throw new CantidadPasajeroSobrepasadaException("Cantidad de Pasajeros Sobrepasada");
			}
		}else
		{
			throw new TipoTicketInvalidoExcption("El tipo de ticket es invalido");
		}

	}

	
	/*
	 * retorna la lista de pasajero enforma Descendiente Lanza una exception si el
	 * viaje no existe o si el tipo de viaje No es compatible para trnssporte de
	 * pasajero 
	 */

	public TreeSet<Pasajero> obtenerListaPasajeroOrdenadosPorDNIDescendiente(Integer numeroViaje) throws TipoViajeInvalidoExcption {
		Viaje viaje = buscarViaje(numeroViaje);
		
		MedioTransporte mp = viaje.getMedioTransporte();
		
		if((mp instanceof TransportePasajero || mp instanceof TransporteMixto) && mp!=null) {

			List<Pasajero> pasajeros = new ArrayList<Pasajero>();
			TreeSet<Pasajero> pasajerosordenados = new TreeSet<Pasajero>(new ordenarPorDni());
			
			for(Ticket actual : viaje.getTickets()) {
				if(actual instanceof TicketPasajero) {
					pasajeros.add(((TicketPasajero)actual).getPasajero());
				}else if(actual instanceof TicketMixto){
					pasajeros.add(((TicketMixto)actual).getPasajero());
				}
			}
			pasajerosordenados.addAll(pasajeros);
			return pasajerosordenados;
			
		}else {
			throw new TipoViajeInvalidoExcption("El viaje no fue encontrado o no es valido");
		}
	}

	public Double obtenerELTotalDeCargaTransportadaEnTodosLosViajes() {

		return null;
	}

}
