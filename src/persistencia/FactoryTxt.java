package persistencia;

public class FactoryTxt extends AbstractFactoryArchivo {

	@Override
	public ILectoEscritura getTipoArchivo(String string) {
		if (string.equals("Lectoescritura"))
			return new LectoGuardadoTXT();
		else return null;
	}

}
