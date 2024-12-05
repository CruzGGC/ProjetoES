package com.CruzGGC;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalistaTest {

    @Test
    void testQuantasOcorrenciasExemploFicha() throws IOException {
        Analista analista = new Analista("test.txt"); // Contém o exemplo da ficha
        int[] counts = analista.quantasOcorrencias('e');

        assertEquals(11, counts[0], "Palavras sem 'e'");
        assertEquals(3, counts[1], "Palavras com 1 'e'");
        assertEquals(3, counts[2], "Palavras com 2 'e'");
        assertEquals(0, counts[3], "Palavras com 3 'e'");
        assertEquals(0, counts[4], "Palavras com 4 'e'");
        assertEquals(0, counts[5], "Palavras com 5+ 'e'");
    }


    @Test
    void testListaPalavras() throws IOException {
        Analista analista = new Analista("test.txt");
        analista.listaPalavras('e', "output.txt");

        List<String> lines = Files.readAllLines(Path.of("output.txt"));
        assertEquals("E 1", lines.get(0));
        assertEquals("EXISTE 2", lines.get(1));
    }
}
