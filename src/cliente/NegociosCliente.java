package cliente;

import excepciones.DniInvalidoException;
import excepciones.DniYaRegistradoException;
import util.Conexion;

public class NegociosCliente implements IRegistro {
	private Conexion conexion=new Conexion();
	@Override
	public void crearCLiente(String dni) throws DniInvalidoException,DniYaRegistradoException {
		if (dni.length()>=6 && dni.length()<=9) {
			Cliente cliente=new Cliente(dni);
			this.enviarClienteAServidor(cliente);
		}else
			throw new DniInvalidoException("Longitud invalida");
	}

	@Override
	public void enviarClienteAServidor(Cliente cliente) throws DniYaRegistradoException{
		this.conexion.envioClienteAServidor(cliente,"cliente");
	}

}
