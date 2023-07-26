package ar.unlam.edu.ar;

import java.util.Objects;

public class TicketPasajero extends Ticket{

	private Pasajero pasajero;

	public TicketPasajero(Integer id, Pasajero pasajero) {
		super(id);
		this.pasajero = pasajero;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pasajero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TicketPasajero))
			return false;
		TicketPasajero other = (TicketPasajero) obj;
		return Objects.equals(pasajero, other.pasajero);
	}

	
	
}
