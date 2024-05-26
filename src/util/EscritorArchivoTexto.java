package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorArchivoTexto {
    public static void escribirArchivo(String contenido, String rutaArchivo) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo));
        escritor.write(contenido);
        escritor.close();
    }
}