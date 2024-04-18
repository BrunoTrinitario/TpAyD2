package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;

public class Conexion {
	
	private Socket socket;
	private ObjectOutputStream oos;
	private BufferedReader in;           
	private PrintWriter out;
	
	public void envioEmpleadoAServidor(Object objeto,String mensaje)throws BoxYaRegistradoException {
		 try {
			envioDatosAServidor(objeto,mensaje);
		} catch (DniYaRegistradoException e) {
			e.printStackTrace();
		}
	}
	

	public void envioClienteAServidor(Object objeto, String mensaje) throws DniYaRegistradoException{
			 try {
				 envioDatosAServidor(objeto,mensaje);			    	
			} catch (BoxYaRegistradoException e) {
				e.printStackTrace();
			}
	}
	
	
	public void envioDatosAServidor(Object objeto,String mensaje) throws DniYaRegistradoException, BoxYaRegistradoException{
		 try {
			 
	            abrirConexion(Constantes.IP, Constantes.puerto);	            
	            enviarDatos(objeto,mensaje);
	            String msg = in.readLine();
	            if (msg.equals(Constantes.DNI_YA_REGISTRADO)) {
	            	throw new DniYaRegistradoException(Constantes.DNI_YA_REGISTRADO);
	            }else if (msg.equals(Constantes.BOX_YA_REGISTRADO)) {
	            	throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
	            }	            	                   	
	        }catch (UnknownHostException e) {	        	
	        	e.printStackTrace();
	        }catch (IOException e) {
	        	e.printStackTrace();
	        }		 	
		 finally {
			cerrarConexion();
		 }
	}
	
	
	
	private void abrirConexion(String Ip, int puerto) throws UnknownHostException, IOException {
		System.out.println("Abriendo conexion en " +Ip+":"+puerto);
		this.socket = new Socket(Ip,puerto);
    	this.oos = new ObjectOutputStream(socket.getOutputStream()); 
    	System.out.println("Bufer de entrada");
    	this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
    	System.out.println(in);
    	this.out = new PrintWriter(socket.getOutputStream(),true);	
		System.out.println("Conexion abierta");
	}
	
	
	
	private void enviarDatos(Object objeto, String mensaje) throws IOException {
		System.out.println("Enviando datos: "+ objeto+ " " + mensaje);
		oos.writeObject(objeto);
        out.println(mensaje);
        System.out.println("datos enviados");
	}
	
	
	private void cerrarConexion() {
		try {
			socket.close();
			oos.close();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
