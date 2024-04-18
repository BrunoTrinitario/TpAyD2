package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class DatosConexion {
    public Socket socket;
    public PrintWriter out;
    public BufferedReader in;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    
    public DatosConexion(Socket socket) {
        this.socket = socket;
        try {
			this.out = new PrintWriter(socket.getOutputStream(), true);
	        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));           
	        this.ois = new ObjectInputStream(socket.getInputStream());
	        this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} 

    }

}
