package persistencia;

import java.io.IOException;

import util.Constantes;
import util.LectorArchivoTexto;

public class FactoryDeFactorys {

	public static AbstractFactoryArchivo crearFactory(String contenido) {
		AbstractFactoryArchivo factory = null;
       switch (contenido) {
        
        case "TXT":
        	factory = new FactoryTxt();
        	break;
        case "XML":
        	factory = new FactoryXML();
        	break;
        case "JSON":
        	factory = new FactoryJSON();
        	break;
        }        	         
        System.out.println("Se creo "+ factory);
		return factory;
	}
}


