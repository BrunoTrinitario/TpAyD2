package servidor;

import java.util.ArrayList;

import empleado.Empleado;

public interface iMetrica {
	void consultarRendimiento();
	void buscaMetricas(ArrayList<Empleado> listaEmpleado,ArrayList<Metrica>listaMetricas);
}
