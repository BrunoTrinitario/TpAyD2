package cliente;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
	private String dni;
	private int intento;
	
	public Cliente(String dni) {
		super();
		this.dni = dni;
		this.intento=0;
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
	
}
