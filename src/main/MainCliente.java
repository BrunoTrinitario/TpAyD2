package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import vista.RegistroDni;

public class MainCliente {

	public static void main(String[] args) {
		RegistroDni vista = new RegistroDni();
		
        try {
            Socket socket = new Socket("localhost",124);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.print("DNI: 37550930");
            out.print(" Servicio: servicio 1");
            out.close();
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
