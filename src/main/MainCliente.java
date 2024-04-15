package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import cliente.NegociosCliente;
import vista.RegistroDni;

public class MainCliente {

	public static void main(String[] args) {
		NegociosCliente nc=new NegociosCliente();
		nc.enviarClienteAServidor("hola");
		
	}

}
