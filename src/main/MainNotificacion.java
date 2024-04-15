package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor.Servidor;
import vista.Notificacion;

public class MainNotificacion {

	public static void main(String[] args) {

		Notificacion vista = new Notificacion();
		Thread servidor=new Thread();
		servidor.start();
	}

}
