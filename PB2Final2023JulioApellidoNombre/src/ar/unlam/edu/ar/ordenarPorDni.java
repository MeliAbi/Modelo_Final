package ar.unlam.edu.ar;

import java.util.Comparator;

public class ordenarPorDni implements Comparator<Pasajero> {

	@Override
	public int compare(Pasajero o1, Pasajero o2) {
		return o2.getDni().compareTo(o1.getDni());
	}

}
