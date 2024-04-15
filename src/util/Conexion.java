package util;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Conexion {
	public void envioAServidor(Object mensaje) {
		 try {
	            Socket socket = new Socket("localhost",1234);
	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	            oos.writeObject(mensaje);
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            out.print(mensaje);
	            out.close();
	            socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
