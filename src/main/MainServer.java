package main;

import servidor.Servidor;
import util.Constantes;

public class MainServer {

	public static void main(String[] args) {
		
		Servidor servidor = new Servidor(Constantes.PUERTO);
		servidor.start();

	}
}
