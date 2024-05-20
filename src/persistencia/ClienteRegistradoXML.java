package persistencia;

import java.io.Serializable;

public class ClienteRegistradoXML implements Serializable {
	private String dni,fecha,grupo;
	public ClienteRegistradoXML() {
		
	}
	
	public ClienteRegistradoXML(String dni, String fecha, String grupo) {
		this.dni = dni;
		this.fecha = fecha;
		this.grupo = grupo;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
}
