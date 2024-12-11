package com.CruzGGC;

import java.io.*;
import java.util.*;

public class Analista {
    private final Map<String, Integer> wordMap;

    public Analista(String fileName) throws IOException {
        this(new FileReader(fileName));
    }

    public Analista(Reader reader) throws IOException {
        wordMap = new HashMap<>();
        Leitor(reader);
    }

    private void Leitor(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] palavras = linha.toUpperCase().replaceAll("[^A-Z ]", "").split("\\s+");
            for (String palavra : palavras) {
                wordMap.put(palavra, wordMap.getOrDefault(palavra, 0) + 1);
            }
        }
        bufferedReader.close();
    }

    public int[] calcularLetras(char letra) {
        int[] contagem = new int[6];
        letra = Character.toUpperCase(letra);

        int totalPalavras = 0;

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            int ocorrencias = entry.getValue();
            totalPalavras += ocorrencias;

            int frequency = 0;
            for (char caracter : word.toCharArray()) {
                if (caracter == letra) {
                    frequency++;
                }
            }
            if (frequency >= 5) {
                contagem[5] += ocorrencias;
            } else {
                contagem[frequency] += ocorrencias;
            }
        }

        contagem[0] = totalPalavras - (contagem[1] + contagem[2] + contagem[3] + contagem[4] + contagem[5]);
        return contagem;
    }

    public void calcularPalavras(char letra, Writer writer) throws IOException {
        letra = Character.toUpperCase(letra);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (String word : wordMap.keySet()) {
            if (word.charAt(0) == letra) {
                bufferedWriter.write(word + " " + wordMap.get(word));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
    }
}
