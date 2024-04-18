package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DatosConexion {
	public Socket socket = new Socket("localhost",1234);
	public ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	public BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));            
    public PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
    
    public DatosConexion(Socket socket) throws UnknownHostException, IOException {

    	this.oos = new ObjectOutputStream(socket.getOutputStream());
    	this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));            
    	this.out = new PrintWriter(socket.getOutputStream(),true);
    }
}
