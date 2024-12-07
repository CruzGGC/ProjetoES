package com.CruzGGC;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class AnalistaTest {

    // Testes de Partição do Domínio de Entrada

    @Test
    void testEntradaVazia() throws Exception {
        // Entrada vazia
        String input = "";

        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('a');

        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, counts, "Resultado com entrada vazia deve ser zero.");
    }

    @Test
    void testSemCaracterAnalisado() throws Exception {
        String input = """
            TEST TESTING UNIT TESTS.
            """;

        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('a');

        assertArrayEquals(new int[]{4, 0, 0, 0, 0, 0}, counts, "Todas as palavras devem estar em count[0].");
    }

    @Test
    void testComFrequenciasVariadas() throws Exception {
        String input = """
            aaa a aa aaaaaa.
            """;

        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('a');

        assertArrayEquals(new int[]{0, 1, 1, 1, 0, 1}, counts, "Verifica as frequências de 'a' nas palavras.");
    }

    // Testes Estruturais

    @Test
    void testCriacaoArquivoSaidaEmMemoria() throws Exception {
        String input = """
            O Pai Natal nao existe.
            Mas o coelho da Pascoa existe
            (e a Fada dos Dentes tambem).
            """;

        Analista analista = new Analista(new StringReader(input));

        StringWriter writer = new StringWriter();
        analista.listaPalavras('e', writer);

        String output = writer.toString();
        assertTrue(output.contains("EXISTE 2"), "A saída deve conter 'EXISTE 2'.");
        assertTrue(output.contains("E 1"), "A saída deve conter 'E 1'.");
    }

    @Test
    void testPalavrasComCaractereEspecifico() throws Exception {
        String input = """
            Mas mais Mas mesmo.
            """;

        Analista analista = new Analista(new StringReader(input));

        StringWriter writer = new StringWriter();
        analista.listaPalavras('m', writer);

        String output = writer.toString();
        assertTrue(output.contains("MAS 2"), "A saída deve conter 'MAS 2'.");
        assertTrue(output.contains("MESMO 1"), "A saída deve conter 'MESMO 1'.");
    }

    @Test
    void testExclusaoCaracteresEspeciais() throws Exception {
        String input = """
            @Te5te! Testando. t&ste..
            """;

        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('e');

        assertEquals(0, counts[0], "Deve haver uma palavra sem 'e'.");
        assertEquals(2, counts[1], "Duas palavras têm 1 'e'.");
        assertEquals(1, counts[2], "Duas palavras têm 2 'e'.");
    }

    // Testes Unitários

    @Test
    void testQuantasOcorrencias() throws Exception {
        String input = """
            O Pai Natal nao existe.
            Mas o coelho da Pascoa existe
            (e a Fada dos Dentes tambem).
            """;

        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('e');

        assertEquals(11, counts[0], "Palavras sem 'e'");
        assertEquals(3, counts[1], "Palavras com 1 'e'");
        assertEquals(3, counts[2], "Palavras com 2 'e'");
    }

    @Test
    void testListaPalavras() throws Exception {
        String input = """
            O Pai Natal nao existe.
            Mas o coelho da Pascoa existe
            (e a Fada dos Dentes tambem).
            """;

        Analista analista = new Analista(new StringReader(input));

        StringWriter writer = new StringWriter();
        analista.listaPalavras('e', writer);

        String output = writer.toString();
        assertTrue(output.contains("EXISTE 2"), "A saída deve conter 'EXISTE 2'.");
        assertTrue(output.contains("E 1"), "A saída deve conter 'E 1'.");
    }

    // Testes Lógicos

    @Test
    void testPalavrasComMaisDeCincoOcorrencias() throws Exception {
        String input = """
            eeeeeeeeeee eeeeeeeee eeeeeeeeeeeeee
            """;

        Analista analista = new Analista(new StringReader(input));
        int[] counts = analista.quantasOcorrencias('e');

        assertEquals(0, counts[0], "Nenhuma palavra sem 'e'.");
        assertEquals(0, counts[1], "Nenhuma palavra com 1 'e'.");
        assertEquals(0, counts[4], "Nenhuma palavra com menos de 5 'e'.");
        assertEquals(3, counts[5], "Todas as palavras têm 5 ou mais 'e'.");
    }

    @Test
    void testPalavrasIniciandoComLetrasDiferentes() throws Exception {
        String input = """
            Mas mais Mais Outro. mesmo
            """;

        Analista analista = new Analista(new StringReader(input));

        StringWriter writer = new StringWriter();
        analista.listaPalavras('m', writer);

        String output = writer.toString();
        assertTrue(output.contains("MAS 1"), "A saída deve conter 'MAS 1'.");
        assertTrue(output.contains("MESMO 1"), "A saída deve conter 'MESMO 1'.");
    }
}
