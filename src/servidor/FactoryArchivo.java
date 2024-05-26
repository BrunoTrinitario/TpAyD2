package servidor;

import java.io.IOException;

import persistencia.ILectoEscritura;
import persistencia.LectoGuardadoJSON;
import persistencia.LectoGuardadoTXT;
import persistencia.LectoGuardadoXML;
import util.Constantes;
import util.LectorArchivoTexto;

public class FactoryArchivo {

	
	public static ILectoEscritura getTipoArchivo() {
		ILectoEscritura tipoArchivo = null;
		String contenido = null;

        try {
             contenido = LectorArchivoTexto.leerArchivo(Constantes.ARCHIVO_CONFIGURACION);
            System.out.println("Contenido leído del archivo:");
            System.out.println(contenido);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }
        
        switch (contenido) {
        
        case "TXT":
        	tipoArchivo = new LectoGuardadoTXT();
        	break;
        case "XML":
        	tipoArchivo = new LectoGuardadoXML();
        	break;
        case "JSON":
        	tipoArchivo = new LectoGuardadoJSON();
        	break;
        }        	         
        System.out.println("Se creo "+ tipoArchivo);
		return tipoArchivo;
	}
}
