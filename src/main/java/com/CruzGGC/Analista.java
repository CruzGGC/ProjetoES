package com.CruzGGC;

import java.io.*;
import java.util.*;

public class Analista {
    // Mapa que armazena cada palavra do texto e o número de vezes que ela aparece
    private final Map<String, Integer> wordMap;

    // Construtor que aceita o nome de um ficheiro físico e inicializa o Analista
    public Analista(String fileName) throws IOException {
        this(new FileReader(fileName)); // Chama o outro construtor com um FileReader
    }

    // Construtor que aceita um StringReader, permitindo leitura da memória ( Vai ser usado para os testes )
    public Analista(Reader reader) throws IOException {
        wordMap = new HashMap<>();
        readFromReader(reader); // Carrega as palavras do StringReader no mapa
    }

    // Metodo para ler palavras do texto a partir de um Reader
    private void readFromReader(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Remove letras não alfabéticas e separa palavras por espaço ( ex: "Hello, World!" -> "HELLO WORLD" )
            String[] words = line.toUpperCase().replaceAll("[^A-Z ]", "").split("\\s+");
            for (String word : words) {
                // Incrementa a contagem de cada palavra no mapa (ou adiciona se não existir)
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
        }
        bufferedReader.close(); // Fecha o Reader
    }

    // Metodo para calcular o número de palavras com diferentes frequências de uma letra específica
    public int[] quantasOcorrencias(char c) {
        int[] counts = new int[6]; // Array para armazenar as contagens (0 a 5+)
        c = Character.toUpperCase(c); // Garante que a letra analisada seja maiúscula

        int totalWords = 0;

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            int occurrences = entry.getValue();
            totalWords += occurrences; // Conta o número total de palavras no texto

            int frequency = 0;
            // Conta o número de vezes que c aparece na palavra
            for (char ch : word.toCharArray()) {
                if (ch == c) {
                    frequency++;
                }
            }
            // Atualiza a contagem no array (5+ vai na última posição)
            if (frequency >= 5) {
                counts[5] += occurrences;
            } else {
                counts[frequency] += occurrences;
            }
        }

        // Calcula o número de palavras que não contêm a letra especificada
        counts[0] = totalWords - (counts[1] + counts[2] + counts[3] + counts[4] + counts[5]);
        return counts;
    }

    // Metodo para listar palavras que começam com uma letra específica
    public void listaPalavras(char c, Writer writer) throws IOException {
        c = Character.toUpperCase(c); // Garante que a letra analisada seja maiúscula
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (String word : wordMap.keySet()) {
            if (word.charAt(0) == c) { // Verifica se a palavra começa com a letra especificada
                // Escreve a palavra e sua frequência no Writer
                bufferedWriter.write(word + " " + wordMap.get(word));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush(); // Garante que todos os dados sejam escritos
    }
}
