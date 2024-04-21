package cliente;

import java.io.IOException;

import excepciones.DniInvalidoException;
import excepciones.DniYaRegistradoException;

public interface IRegistro {
	void crearCLiente(String dni) throws DniInvalidoException, DniYaRegistradoException, IOException;
	void enviarClienteAServidor(Cliente cliente) throws DniYaRegistradoException, IOException;
}
