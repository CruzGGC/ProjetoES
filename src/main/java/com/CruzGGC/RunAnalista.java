package com.CruzGGC;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class RunAnalista {
    public static void main(String[] args) throws IOException {
        // Verifica se a pasta "Output" existe, caso contrário, cria a pasta
        Path outputDir = Paths.get("Output");
        if (!Files.exists(outputDir)) {
            Files.createDirectory(outputDir);
        }

        Scanner scanner = new Scanner(System.in); // Lê as entradas do utilizador
        System.out.print("Introduza o nome do ficheiro: ");
        String fileName = scanner.nextLine(); // Nome do ficheiro a ser processado
        Analista analista = new Analista("Output/" + fileName); // Inicializa o Analista com o ficheiro fornecido

        while (true) {
            System.out.print("Opcao desejada: ");
            String option = scanner.nextLine(); // Lê a opção do utilizador

            if (option.startsWith("letra ")) {
                // Cria um ficheiro com a contagem de palavras com a letra especificada
                char c = option.charAt(6); // Obtém a letra especificada
                int[] counts = analista.quantasOcorrencias(c); // Calcula as frequências de c
                String outputFileName = "Output/" + c + ".out";

                // Escreve os resultados em um arquivo de saída
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    for (int i = 0; i < 5; i++) {
                        writer.write(i + " " + counts[i]); // Contagens de 0 a 4
                        writer.newLine();
                    }
                    writer.write("5+ " + counts[5]); // Contagem para 5 ou mais ocorrências
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (option.startsWith("ficheiro ")) {
                // Caso "ficheiro <letra> nome": lista palavras que começam com a letra especificada e salva no ficheiro de saída fornecido
                String[] parts = option.split(" "); // Divide a opção para extrair parâmetros
                char c = parts[1].charAt(0); // Letra especificada
                String outputFileName = "Output/" + parts[2]; // Nome do ficheiro de saída

                // Escreve a lista de palavras no arquivo de saída
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    analista.listaPalavras(c, writer);
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (option.equals("terminar")) {
                // Caso "terminar": encerra o programa
                System.out.println("Sessao terminada.");
                break;
            }
        }
        scanner.close(); // Fecha o Scanner
    }
}
