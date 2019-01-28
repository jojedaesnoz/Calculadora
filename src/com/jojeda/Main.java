package com.jojeda;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static util.Constantes.CLIENTE;
import static util.Constantes.MAX_CONEXIONES;
import static util.Constantes.SERVIDOR;

public class Main {

    public static void main(String[] args) {
        // Crear el process builder
        ProcessBuilder pb = new ProcessBuilder()
                .directory(new File(System.getProperty("user.dir")))
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            // SERVIDOR
            pb.command("java", "-jar", SERVIDOR);
            Process servidor = pb.start();

            // CLIENTES
            pb.command("java", "-jar", CLIENTE);
            ArrayList<Process> clientes = new ArrayList<>();
            for (int i = 0; i < MAX_CONEXIONES; i++) {
                clientes.add(pb.start());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
