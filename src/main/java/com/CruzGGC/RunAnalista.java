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
            String opcao = scanner.nextLine(); // Lê a opção do utilizador

            if (opcao.startsWith("letra ")) {
                // Cria um ficheiro com a contagem de palavras com a letra especificada
                char letra = opcao.charAt(6); // Obtém a letra especificada
                int[] contagem = analista.calcularLetras(letra); // Calcula as frequências de c
                String outputFileName = "Output/" + letra + ".out";

                // Escreve os resultados em um arquivo de saída
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    for (int i = 0; i < 5; i++) {
                        writer.write(i + " " + contagem[i]); // Contagens de 0 a 4
                        writer.newLine();
                    }
                    writer.write("5+ " + contagem[5]); // Contagem para 5 ou mais ocorrências
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (opcao.startsWith("ficheiro ")) {
                // Caso "ficheiro <letra> nome": lista palavras que começam com a letra especificada e salva no ficheiro de saída fornecido
                String[] parte = opcao.split(" "); // Divide a opção para extrair parâmetros
                char letra = parte[1].charAt(0); // Letra especificada
                String outputFileName = "Output/" + parte[2]; // Nome do ficheiro de saída

                // Escreve a lista de palavras no arquivo de saída
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    analista.calcularPalavras(letra, writer);
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (opcao.equals("terminar")) {
                // Caso "terminar": encerra o programa
                System.out.println("Sessao terminada.");
                break;
            }
        }
        scanner.close(); // Fecha o Scanner
    }
}
