package com.CruzGGC;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class AnalistaTest {

    @Test
    void testQuantasOcorrencias() throws IOException {
        String input = """
                O Pai Natal nao existe.
                Mas o coelho da Pascoa existe
                (e a Fada dos Dentes tambem).
                """;

        // Criar Analista com entrada em memória
        Analista analista = new Analista(new StringReader(input));
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
        String input = """
        O Pai Natal nao existe.
        Mas o coelho da Pascoa existe
        (e a Fada dos Dentes tambem).
        """;

        // Criar Analista com entrada em memória
        Analista analista = new Analista(new StringReader(input));

        // Criar saída em memória
        StringWriter writer = new StringWriter();
        analista.listaPalavras('e', writer);

        // Verificar saída
        String output = writer.toString();
        assertTrue(output.contains("EXISTE 2"), "A saída deve conter 'EXISTE 2'.");
        assertTrue(output.contains("E 1"), "A saída deve conter 'E 1'.");
    }

    @Test
    void testEntradaVazia() throws IOException {
        // Entrada vazia
        String input = "";

        // Criar Analista com entrada vazia
        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('a');

        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, counts, "Resultado com entrada vazia deve ser zero.");
    }

    @Test
    void testSemCaracterAnalisado() throws IOException {
        String input = """
        TEST TESTING UNIT TESTS.
        """;

        // Criar Analista com entrada sem 'a'
        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('a');

        assertArrayEquals(new int[]{4, 0, 0, 0, 0, 0}, counts, "Todas as palavras devem estar em count[0].");
    }
}