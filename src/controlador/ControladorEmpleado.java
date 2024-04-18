package controlador;

import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import util.EstadoEmpleado;

public class ControladorEmpleado {
	NegociosEmpleado ne=new NegociosEmpleado();
	public void crearEmpleado(String dni,int box) throws BoxYaRegistradoException{
		ne.crearEmpleado(dni, box);
	}

}
