package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
