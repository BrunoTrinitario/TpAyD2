package cliente;

import java.io.Serializable;

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
	
}
