package controlador;

import cliente.Cliente;
import empleado.Empleado;
import vista.VistaNotificacion;

public class ControladorNotificaciones implements IVista {
	private VistaNotificacion nt=new VistaNotificacion();
	@Override
	public void agregarCliente(Cliente cliente,Empleado empleado) {
		nt.agregar(cliente,empleado);
	}



}
