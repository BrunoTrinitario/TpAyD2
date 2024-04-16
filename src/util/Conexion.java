package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import excepciones.DniYaRegistradoException;

public class Conexion {
	public void envioAServidor(Object objeto,String mensaje) {
		 try {
	            Socket socket = new Socket("localhost",1234);
	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));             
	            oos.writeObject(objeto);
	            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
	            out.print(mensaje);
	            oos.close();
	            out.close();
	            socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	public void envioClienteAServidor(Object objeto,String mensaje) throws DniYaRegistradoException{
		 try {
	            Socket socket = new Socket("localhost",1234);
	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));             
	            oos.writeObject(objeto);
	            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
	            out.print(mensaje);
	            if (in.readLine().equals("yaRegistrado")) {
	            	throw new DniYaRegistradoException("yaRegistrado");
	            }
	            oos.close();
	            out.close();
	            socket.close();
	        }catch(DniYaRegistradoException e) {
	        	throw new DniYaRegistradoException("yaRegistrado");
	        }
		 	catch (Exception e) {
	        	
	        }
	}

}
