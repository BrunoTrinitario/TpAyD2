package cliente;

import excepciones.DniInvalidoException;
import excepciones.DniYaRegistradoException;

public interface IRegistro {
	void crearCLiente(String dni) throws DniInvalidoException, DniYaRegistradoException;
	void enviarClienteAServidor(Cliente cliente) throws DniYaRegistradoException;
}
