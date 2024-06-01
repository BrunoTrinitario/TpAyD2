package persistencia;

public class FactoryJSON extends AbstractFactoryArchivo {

	public ILectoEscritura getTipoArchivo(String string) {
		if (string.equals("Lectoescritura"))
			return new LectoGuardadoJSON();
		else return null;
	}

}
