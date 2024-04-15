package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import util.Conexion;

public class NegociosCliente implements IRegistro {
	private Conexion conexion=new Conexion();
	@Override
	public void crearCLiente(String dni) {
		Cliente cliente=new Cliente(dni);
		this.enviarClienteAServidor(dni);
	}

	@Override
	public void enviarClienteAServidor(String dni) {
		this.conexion.envioAServidor("Cliente."+dni);
	}

}
