package cliente;

import util.Conexion;

public class NegociosCliente implements IRegistro {
	private Conexion conexion=new Conexion();
	@Override
	public void crearCLiente(String dni) {
		Cliente cliente=new Cliente(dni);
	}

	@Override
	public void enviarClienteAServidor(Cliente cliente) {
		this.conexion.envioAServidor(cliente);
	}

}
