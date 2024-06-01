package persistencia;

public class FactoryTxt extends AbstractFactoryArchivo {

	@Override
	public ILectoEscritura getTipoArchivo() {
		
		return new LectoGuardadoTXT();
	}

}
