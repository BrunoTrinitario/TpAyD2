package cliente;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class Cliente implements Serializable {
	private String dni;
	private int intento;
	private LocalTime horaIngreso,horaEgreso;
	
	public Cliente(String dni) {
		super();
		this.dni = dni;
		this.intento=0;
		this.horaIngreso=LocalTime.now();
	}
	public Cliente(String dni,LocalTime horaIngreso,LocalTime horaEgreso, int intento) {
		super();
		this.dni = dni;
		this.intento=intento;
		this.horaIngreso=horaIngreso;
		this.horaEgreso=horaEgreso;
	}

	public String getDni() {
		return this.dni;
	}
	
	public int getIntento() {
		return this.intento;
	}
	
	public void sumarIntento() {
		this.intento++;
	}
	
	public boolean equals(Object obj) {
		 if (this == obj)
	            return true;
	        if (obj == null || getClass() != obj.getClass())
	            return false;
	        Cliente cliente = (Cliente) obj;
	        return Objects.equals(dni, cliente.dni);
	}
	
	public void setHoraAtencion() {
		this.horaEgreso=LocalTime.now();
	}

	public LocalTime getHoraIngreso() {
		return horaIngreso;
	}

	public LocalTime getHoraEgreso() {
		return horaEgreso;
	}
	public String toString() {
		return ("dni: "+this.dni+", hora ingreso: "+this.horaIngreso+", hora egreso: "+this.horaEgreso);
	}
}
