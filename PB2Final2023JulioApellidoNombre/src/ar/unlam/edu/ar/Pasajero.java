package ar.unlam.edu.ar;

import java.util.Objects;

public class Pasajero implements Comparable <Pasajero>{

	
	private Integer dni; 
	private String apellido;
	
	
	public Pasajero(Integer dni, String apellido) {
		super();
		this.dni = dni;
		this.apellido = apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pasajero))
			return false;
		Pasajero other = (Pasajero) obj;
		return Objects.equals(dni, other.dni);
	}
	@Override
	public int compareTo(Pasajero o) {
		return o.getDni().compareTo(dni);
	}
	
	public String toString() {
		return "Dni: " + dni;
	}

}
