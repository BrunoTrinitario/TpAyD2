package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import cliente.Cliente;
import empleado.Empleado;
import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import servidor.Metrica;

public class Conexion {
	
	private Socket socket;
	private ObjectOutputStream oos;
	private BufferedReader in;           
	private PrintWriter out;
	private ObjectInputStream ois;
	
	public void envioEmpleadoAServidor(NegociosEmpleado negociosEmpleado, Empleado empleado,String mensaje)throws BoxYaRegistradoException {
		String msg = envioDatosAServidor(empleado,mensaje);
			escucharServidor(negociosEmpleado);
    	if (msg.equals(Constantes.BOX_YA_REGISTRADO)) {
    		throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
    	}	
	}
	

	public void envioClienteAServidor(Object objeto, String mensaje) throws DniYaRegistradoException{
			String msg = envioDatosAServidor(objeto,mensaje);
            if (msg.equals(Constantes.DNI_YA_REGISTRADO)) {
            	throw new DniYaRegistradoException(Constantes.DNI_YA_REGISTRADO);
            }
			cerrarConexion();
		 }
	
	public String envioDatosAServidor(Object objeto,String mensaje){
		String msg = null; 
		try {			 	
			 	System.out.println("Abriendo conexion...");
	            abrirConexion(Constantes.IP, Constantes.PUERTO);System.out.println("Conexion abierta");       
	            System.out.println("Enviando datos...");   
	            enviarDatos(objeto,mensaje);System.out.println("Datos enviados");   
	            System.out.println("Leyendo respuesta"); 
	            msg = in.readLine(); System.out.println("Respuesta recibida: " +msg);             	                   	
		}catch(Exception e) {
			msg=e.getMessage();
		}
		return msg;

	}
	
	public Metrica solicitudDeActulizacionMetricas(Object objeto,String mensaje) {
		Object metrica=null;
			String msg = envioDatosAServidor(objeto,mensaje);
				try {
					System.out.println(msg); 
					System.out.println("Intentando leer metricas"); 
					metrica=this.ois.readObject();
					System.out.println("Metrica leida: "+metrica); 
				} catch (Exception e) {
					
				}
				finally {
					cerrarConexion();
				}
				return (Metrica)metrica;				
}
	
	private void abrirConexion(String Ip, int puerto) throws UnknownHostException, IOException {
		this.socket = new Socket(Ip,puerto);
    	this.oos = new ObjectOutputStream(socket.getOutputStream()); 
    	this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); ;
    	this.out = new PrintWriter(socket.getOutputStream(),true);
    	this.ois = new ObjectInputStream(socket.getInputStream());
	}
	
	
	
	private void enviarDatos(Object objeto, String mensaje) throws IOException {
		oos.writeObject(objeto);
        out.println(mensaje);
		System.out.println("Datos enviados: Objeto: "+objeto+", mensaje: "+mensaje); 
	}
	
	
	private void escucharServidor(NegociosEmpleado negociosEmpleado) {
        new Thread() {
            public void run() {
                try {
            		while (socket.isConnected()) {
            			Object objeto = ois.readObject();
            			if (objeto instanceof Cliente) {
            				negociosEmpleado.enviarClienteAEmpleado((Cliente)objeto);            				
            			}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
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

	public void cambioEstadoEmpleado(Empleado empleado, String mensaje) {
		try {
			mensaje=mensaje+","+empleado.getEstado().toString();
			Empleado aux = empleado;
			enviarDatos(aux,mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
