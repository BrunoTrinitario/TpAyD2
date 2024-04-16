package cliente;

import excepciones.DniInvalidoException;
import util.Conexion;

public class NegociosCliente implements IRegistro {
	private Conexion conexion=new Conexion();
	@Override
	public void crearCLiente(String dni) throws DniInvalidoException {
		if (dni.length()>=6 && dni.length()<=9) {
			Cliente cliente=new Cliente(dni);
			this.enviarClienteAServidor(cliente);
		}else
			throw new DniInvalidoException("Longitud invalida");
	}

	@Override
	public void enviarClienteAServidor(Cliente cliente) {
		this.conexion.envioAServidor(cliente,"cliente");
	}

}
