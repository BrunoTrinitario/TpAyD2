package main;

import cliente.Cliente;
import cliente.NegociosCliente;

public class MainCliente {

	public static void main(String[] args) {
		Cliente cliente = new Cliente("12335548");
				
		NegociosCliente nc=new NegociosCliente();
		
		nc.enviarClienteAServidor(cliente);
		
	}

}
