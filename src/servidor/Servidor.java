package servidor;

import java.net.ServerSocket;
import java.util.HashMap;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;
import util.DatosConexion;

public class Servidor extends Thread {
	private GestorColas gestorcolas = new GestorColas();
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
                        	this.gestorcolas.agregarEmpleadoANoAtendiendo((Empleado)objeto);
                        	datosConexion.out.println(Constantes.EMPLEADO_REGISTRO_OK);
                        	System.out.println("Nuevo empleado, empleados actuales: "+empleadosConectados);
                        	
                		}catch(BoxYaRegistradoException e) {
                			datosConexion.out.println(e.getMessage());
                		}
                	}
                	else if (msg.equals(Constantes.EMPLEADO_CAMBIO_ESTADO)) {
                			this.gestorcolas.cambioEstado(empleado);
                	}
                }
                	
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
			
	}

}
