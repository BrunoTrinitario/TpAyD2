package empleado;

import java.io.IOException;

import cliente.Cliente;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.EstadoEmpleado;

public interface IActualizar {
	void informaEstado(Empleado empleado);
	void clienteAusente() throws DniYaRegistradoException;
	int crearEmpleado(String nombre,int box) throws BoxYaRegistradoException, IOException;
	void informarAcceso(Empleado empleado) throws BoxYaRegistradoException, IOException;
	void cambioEstado(EstadoEmpleado estado);
	void finalizarAtencion();

}
