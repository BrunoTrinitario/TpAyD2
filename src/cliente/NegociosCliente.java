package cliente;

import java.io.IOException;

import conexion.Conexion;
import excepciones.DniInvalidoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;

public class NegociosCliente implements IRegistro {
	private Conexion conexion=new Conexion();
	@Override
	public void crearCLiente(String dni) throws DniInvalidoException,DniYaRegistradoException, IOException {
		if (dni.length()>=6 && dni.length()<=9) {
			Cliente cliente=new Cliente(dni);
			this.enviarClienteAServidor(cliente);
		}else
			throw new DniInvalidoException(Constantes.DNI_INCORRECTO);
	}

	@Override
	public void enviarClienteAServidor(Cliente cliente) throws DniYaRegistradoException, IOException{
		this.conexion.envioClienteAServidor(cliente,"cliente");
	}

}
