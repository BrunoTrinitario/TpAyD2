package cliente;

public class NegociosCliente implements IRegistro {

	@Override
	public void crearCLiente(String dni) {
		Cliente cliente=new Cliente(dni);
		this.enviarClienteAServidor(cliente);
	}

	@Override
	public void enviarClienteAServidor(Cliente cliente) {
		
	}

}
