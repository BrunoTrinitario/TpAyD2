package cliente;

import excepciones.DniInvalidoException;

public interface IRegistro {
	void crearCLiente(String dni) throws DniInvalidoException;
	void enviarClienteAServidor(Cliente cliente);
}
