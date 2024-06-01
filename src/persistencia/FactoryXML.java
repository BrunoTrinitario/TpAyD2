package persistencia;

public class FactoryXML extends AbstractFactoryArchivo {

	@Override
	public ILectoEscritura getTipoArchivo(String string) {
		if (string.equals("Lectoescritura"))
			return new LectoGuardadoXML();
		else return null;
	}

}
