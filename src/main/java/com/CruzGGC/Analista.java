package com.CruzGGC;

import java.io.*;
import java.util.*;

public class Analista {
    private final Map<String, Integer> wordMap;

    public Analista(String fileName) throws IOException {
        wordMap = new HashMap<>();
        readFile(fileName);
    }

    private void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.toUpperCase().replaceAll("[^A-Z ]", "").split("\\s+");
            for (String word : words) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
        }
        reader.close();
    }

    public int[] quantasOcorrencias(char c) {
        int[] counts = new int[6];
        c = Character.toUpperCase(c);

        int totalWords = 0;

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            int occurrences = entry.getValue();
            totalWords += occurrences;

            int frequency = 0;
            for (char ch : word.toCharArray()) {
                if (ch == c) {
                    frequency++;
                }
            }
            if (frequency >= 5) {
                counts[5] += occurrences;
            } else {
                counts[frequency] += occurrences;
            }
        }

        // Ajuste para palavras sem o caractere `c`
        counts[0] = totalWords - (counts[1] + counts[2] + counts[3] + counts[4] + counts[5]);

        return counts;
    }


    public void listaPalavras(char c, String fileName) throws IOException {
        c = Character.toUpperCase(c);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (String word : wordMap.keySet()) {
            if (word.charAt(0) == c) {
                writer.write(word + " " + wordMap.get(word));
                writer.newLine();
            }
        }
        writer.close();
    }
}
