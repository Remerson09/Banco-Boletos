package com.example.projet_boleto;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class ProcessarBoletos {
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public final void processar(URI caminhoArquivo) {
        final var listaBoletos = new ArrayList<Boleto>();
        try {
            var listaLinhas = Files.readAllLines(Paths.get(caminhoArquivo));
            for (String linha : listaLinhas) {
                if (linha.trim().isEmpty()) continue;
                final String[] vetor = linha.split(",");
                final var boleto = processarLinhaArquivo(vetor);
                if (boleto != null) {
                    listaBoletos.add(boleto);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        System.out.println("Boletos");
        System.out.println("----------------------------------------------------------------------------------");
        for (Boleto boleto : listaBoletos) {
            System.out.println(boleto);
        }
    }

    protected abstract Boleto processarLinhaArquivo(String[] vetor);
}
