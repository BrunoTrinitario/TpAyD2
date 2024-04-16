package excepciones;

public class DniInvalidoException extends Exception {
	String mensaje;
	public DniInvalidoException(String mensaje) {
		this.mensaje=mensaje;
	}
	public String getMensaje() {
		return this.mensaje;
	}
}
