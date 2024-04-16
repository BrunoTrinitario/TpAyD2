package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.Cliente;
import empleado.Empleado;

public class Servidor extends Thread {
	private GestorColas gestorcolas=new GestorColas();
	private int puerto=1234;
	@Override
	public void run() {
		try {
            ServerSocket s = new ServerSocket(1234);
            while (true) {

                Socket soc = s.accept();
                PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));             
                ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
                Object objeto = ois.readObject();
                String msg = in.readLine();
                if (objeto instanceof Cliente)
                	this.gestorcolas.registrarCliente((Cliente)objeto);
                else if (objeto instanceof Empleado) {
                	if (msg.equals("agregar"))
                		this.gestorcolas.agregarEmpleadoANoDisponible((Empleado)objeto);
                	//else if (msg.equals("estado"))
                		//aca falta el cambio de estado
                }
                	
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
