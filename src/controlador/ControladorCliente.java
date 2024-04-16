package controlador;

import cliente.NegociosCliente;
import excepciones.DniInvalidoException;

public class ControladorCliente {
	private NegociosCliente nc=new NegociosCliente();
	public void crearCliente(String dni) throws DniInvalidoException {
		nc.crearCLiente(dni);
	}

}
