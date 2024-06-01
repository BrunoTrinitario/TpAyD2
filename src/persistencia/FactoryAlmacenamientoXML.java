package persistencia;

public class FactoryAlmacenamientoXML extends AbstractFactoryArchivo{

	@Override
	public ILectoEscritura getTipoArchivo() {
		return new LectoGuardadoXML();
	}

}
