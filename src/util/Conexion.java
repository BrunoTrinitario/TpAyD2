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
	
	private DatosConexion dc;
	public void envioEmpleadoAServidor(Object objeto,String mensaje)throws BoxYaRegistradoException {
		 try {
			envioDatosAServidor(objeto,mensaje);
		} catch (DniYaRegistradoException e) {
			e.printStackTrace();
		}
	}
	

	public void envioClienteAServidor(Object objeto,String mensaje) throws DniYaRegistradoException{
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
	            
	            if (dc.in.readLine().equals(Constantes.DNI_YA_REGISTRADO)) {
	            	throw new DniYaRegistradoException(Constantes.DNI_YA_REGISTRADO);
	            }
	            if (dc.in.readLine().equals(Constantes.BOX_YA_REGISTRADO)) {
	            	throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
	            }	            	                   	
	            
	        }catch (UnknownHostException e) {	        	
	        	e.printStackTrace();
	        }catch (IOException e) {
	        	e.printStackTrace();
	        }		 	
		 finally {
			 cerrarConexion(dc);
		 }
	}
	
	
	
	private void abrirConexion(String Ip, int puerto) throws UnknownHostException, IOException {
		Socket socket = new Socket(Ip,puerto);
		this.dc = new DatosConexion(socket);		
		;
	}
	
	
	
	private void enviarDatos(Object objeto, String mensaje) throws IOException {
        this.dc.oos.writeObject(objeto);
        this.dc.out.println(mensaje);
	}
	
	
	private void cerrarConexion(DatosConexion dc) {
		try {
			dc.socket.close();
			dc.oos.close();
			dc.in.close();
			dc.out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
