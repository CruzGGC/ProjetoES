package com.CruzGGC;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class RunAnalista {
    public static void main(String[] args) throws IOException {
        Path outputDir = Paths.get("Output");
        if (!Files.exists(outputDir)) {
            Files.createDirectory(outputDir);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o nome do ficheiro: ");
        String fileName = scanner.nextLine();
        Analista analista = new Analista("Output/" + fileName);

        while (true) {
            System.out.print("Opcao desejada: ");
            String opcao = scanner.nextLine();

            if (opcao.startsWith("letra ")) {
                char letra = opcao.charAt(6);
                int[] contagem = analista.calcularLetras(letra);
                String outputFileName = "Output/" + letra + ".out";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    for (int i = 0; i < 5; i++) {
                        writer.write(i + " " + contagem[i]);
                        writer.newLine();
                    }
                    writer.write("5+ " + contagem[5]);
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (opcao.startsWith("ficheiro ")) {
                String[] parte = opcao.split(" ");
                char letra = parte[1].charAt(0);
                String outputFileName = "Output/" + parte[2];

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    analista.calcularPalavras(letra, writer);
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (opcao.equals("terminar")) {
                System.out.println("Sessao terminada.");
                break;
            }
        }
        scanner.close();
    }
}
