package persistencia;

public class FactoryXML extends AbstractFactoryArchivo {

	@Override
	public ILectoEscritura getTipoArchivo() {
		return new LectoGuardadoXML();
	}

}
