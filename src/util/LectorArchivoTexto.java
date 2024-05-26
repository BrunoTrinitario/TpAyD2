package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorArchivoTexto {
    public static String leerArchivo(String rutaArchivo) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
        StringBuilder contenido = new StringBuilder();
        String linea;

        while ((linea = lector.readLine()) != null) {
            contenido.append(linea);
        }

        lector.close();
        return contenido.toString();
    }
}
