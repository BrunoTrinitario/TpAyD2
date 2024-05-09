package main;

import servidor.Servidor;
import util.Constantes;

public class MainServer2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Servidor servidor = new Servidor(Constantes.PUERTO2);
		servidor.start();
	}

}
