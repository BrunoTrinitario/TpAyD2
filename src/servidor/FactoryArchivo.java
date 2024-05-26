package servidor;

import java.io.IOException;

import util.Constantes;
import util.LectorArchivoTexto;

public class FactoryArchivo {

	
	public static IStrategyOrdenAtencion getOrdenAtencion() {
		IStrategyOrdenAtencion strategy = null;
		String contenido = null;

        try {
             contenido = LectorArchivoTexto.leerArchivo(Constantes.ARCHIVO_STRATEGY);
            System.out.println("Contenido leído del archivo:");
            System.out.println(contenido);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }
        
        switch (contenido) {
        
        case Constantes.ORDEN_DE_LLEGADA:
        	strategy = new OrdenPorLlegada();
        	break;
        case Constantes.ORDEN_GRUPO_AFINIDAD:
        	strategy = new OrdenPorGrupoAfinidad();
        	break;
        case Constantes.ORDEN_GRUPO_ETARIO:
        	strategy = new OrdenPorGrupoEtario();
        	break;
        }        	         
        System.out.println("Se creo "+ strategy);
		return strategy;
	}
}
