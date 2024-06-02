package persistencia;

import java.io.Serializable;
import java.util.ArrayList;

public class ClienteAXML implements Serializable{
	private String dni,hora,accion;

	public ClienteAXML() {
		
	}
	public ClienteAXML(String dni, String hora, String accion) {
		this.dni = dni;
		this.hora = hora;
		this.accion = accion;
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
	@Override
	public String toString() {
		return "ClienteAXML [dni=" + dni + ", hora=" + hora + ", accion=" + accion + "]";
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
