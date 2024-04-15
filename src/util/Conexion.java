package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Conexion {
	public void envioAServidor(String mensaje) {
		 try {
	            Socket socket = new Socket("localhost",1234);
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            out.print(mensaje);
	            out.close();
	            socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
