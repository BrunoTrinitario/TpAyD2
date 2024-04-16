package cliente;

import util.Conexion;

public class NegociosCliente implements IRegistro {
	private Conexion conexion=new Conexion();
	@Override
	public void crearCLiente(String dni) {
		Cliente cliente=new Cliente(dni);
		this.enviarClienteAServidor(cliente);
	}

	@Override
	public void enviarClienteAServidor(Cliente cliente) {
		this.conexion.envioAServidor(cliente,"cliente");
	}

}
