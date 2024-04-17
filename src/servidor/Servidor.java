package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;

public class Servidor extends Thread {
	private GestorColas gestorcolas=new GestorColas();
	private int puerto=1234;
	@Override
	public void run() {
		try {
            ServerSocket s = new ServerSocket(1234);
            while (true) {
            	System.out.println("Servidor online");
                Socket soc = s.accept();
                System.out.println("Conexion realizada");
                PrintWriter out = new PrintWriter(soc.getOutputStream(), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));           
                ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
                Object objeto = ois.readObject();
                String msg = in.readLine();
                if (objeto instanceof Cliente)
                	try {
                		this.gestorcolas.registrarCliente((Cliente)objeto);
                		out.println(Constantes.CLIENTE_REGISTRO_OK);
                	}catch(DniYaRegistradoException e) {
                		out.println(e.getMessage());
                	}
                else if (objeto instanceof Empleado) {
                	if (msg.equals("agregar"))
                		try {
                        	this.gestorcolas.agregarEmpleadoANoDisponible((Empleado)objeto);
                		}catch(BoxYaRegistradoException e) {
                			out.println(e.getMessage());
                		}
                }
                	
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
