package persistencia;

import java.util.ArrayList;

public class ClienteAJson {
	private String dni,hora,accion;

	public ClienteAJson(String dni, String hora, String accion) {
		this.dni = dni;
		this.hora = hora;
		this.accion = accion;
	}
	public String toJson() {
		return String.format("{\"hora\": \"%s\", \"dni\": \"%s\", \"accion\": \"%s\"}",hora, dni, accion);
	}
	public String getDni() {
		return dni;
	}
	public String getHora() {
		return hora;
	}
	public String getAccion() {
		return accion;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
