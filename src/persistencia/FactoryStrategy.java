package persistencia;

import java.io.IOException;

import servidor.IOrdenAtencion;
import servidor.OrdenPorGrupoAfinidad;
import servidor.OrdenPorGrupoEtario;
import servidor.OrdenPorLlegada;
import util.Constantes;
import util.LectorArchivoTexto;

public class FactoryStrategy {

	
	public static IOrdenAtencion getOrdenAtencion() {
		IOrdenAtencion strategy = null;
		String contenido = null;

        try {
             contenido = LectorArchivoTexto.leerArchivo(Constantes.ARCHIVO_STRATEGY);
        } catch (IOException e) {
            e.printStackTrace();
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
