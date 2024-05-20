package persistencia;

public class ClienteRegistradoJSON {
	private String dni,fecha,grupo;

	public ClienteRegistradoJSON(String dni, String fecha, String grupo) {
		this.dni = dni;
		this.fecha = fecha;
		this.grupo = grupo;
	}

	public String getDni() {
		return dni;
	}

	public String getFecha() {
		return fecha;
	}

	public String getGrupo() {
		return grupo;
	}
	
}
