package controlador;

import cliente.NegociosCliente;
import excepciones.DniInvalidoException;
import excepciones.DniYaRegistradoException;

public class ControladorCliente {
	private NegociosCliente nc=new NegociosCliente();
	public void crearCliente(String dni) throws DniInvalidoException,DniYaRegistradoException {
		nc.crearCLiente(dni);
	}

}