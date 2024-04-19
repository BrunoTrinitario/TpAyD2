package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.util.HashMap;

import cliente.Cliente;
import empleado.Empleado;
import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;
import util.DatosConexion;
import util.EstadoEmpleado;

public class Servidor extends Thread {
	private GestorColas gestorcolas = new GestorColas(this);
	private HashMap <Integer, DatosConexion> empleadosConectados = new HashMap<>();
	private boolean servidorActivo = true;
	
	@Override
	public void run() {
		try {
            ServerSocket s = new ServerSocket(Constantes.PUERTO);
            System.out.println("Servidor online");
            while (servidorActivo) {            	
                
            	DatosConexion datosConexion = new DatosConexion(s.accept());
                Object objeto = datosConexion.ois.readObject();
                String msg = datosConexion.in.readLine();      
                System.out.println("Datos recibidos: "+objeto+" "+msg);
                
                if (objeto instanceof Cliente)
                	try {
                		this.gestorcolas.registrarCliente((Cliente)objeto);;
                		datosConexion.out.println(Constantes.CLIENTE_REGISTRO_OK);
                	}catch(DniYaRegistradoException e) {
                		datosConexion.out.println(e.getMessage());
                	}
                
                else if (objeto instanceof Empleado) {
                	Empleado empleado = (Empleado)objeto;
                	if (msg.equals(Constantes.EMPLEADO_NUEVO)) {
                		try {          			
                			this.empleadosConectados.put(empleado.getBox(), datosConexion);
                        	this.gestorcolas.registrarEmpleado((Empleado)objeto);
                        	datosConexion.out.println(Constantes.EMPLEADO_REGISTRO_OK);
                        	escucharEmpleado((Empleado)objeto,datosConexion);
                        	System.out.println("Nuevo empleado, empleados actuales: "+empleadosConectados);
                        	
                        	
                		}catch(BoxYaRegistradoException e) {
                			datosConexion.out.println(e.getMessage());
                		}
                	}

                }
                else{
                	if (msg.equals(Constantes.SOLICITAR_METRICAS))                	
                	datosConexion.oos.writeObject(gestorcolas.actualizarMetricas());
                }   	
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
				
	}
	
	public void enviarClienteAEmpleado(Empleado empleado, Cliente cliente) {
		try {
			empleadosConectados.get(empleado.getBox()).oos.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void escucharEmpleado(Empleado empleado, DatosConexion datosConexion) {
	        new Thread() {
	            public void run() {
	                try {
	            		while (datosConexion.socket.isConnected()) {
	            			System.out.println("Escuchando a : "+empleado);
	            			Object object = datosConexion.ois.readObject();
	            			String line = datosConexion.in.readLine();
	            			String[] mensajes = line.split(",");
	            			String msg = mensajes[0];
	            			String msg2 = mensajes[1];
	            			System.out.println("Datos recibidos:"+msg+""+msg2);	            			
	            				if (msg.equals(Constantes.EMPLEADO_CAMBIO_ESTADO)) {
	            					if (msg2.equals(EstadoEmpleado.Disponible.toString())){
	            						empleado.cambioEstado(EstadoEmpleado.Disponible);
	            						System.out.println(empleado);
	            					}
	            					if (msg2.equals(EstadoEmpleado.NoDisponible.toString())){
	            						empleado.cambioEstado(EstadoEmpleado.NoDisponible);
	            						System.out.println(empleado);
	            					}
	                        		System.out.println("Solicitud de cambio de estado a "+ msg2);
	                        			gestorcolas.cambioEstado(empleado);           				
	            			}

	                }
	            } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }.start();
			
		}
}
