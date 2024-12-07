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
            String option = scanner.nextLine();

            if (option.startsWith("letra ")) {
                char c = option.charAt(6);
                int[] counts = analista.quantasOcorrencias(c);
                String outputFileName = "Output/" + c + ".out";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    for (int i = 0; i < 5; i++) {
                        writer.write(i + " " + counts[i]);
                        writer.newLine();
                    }
                    writer.write("5+ " + counts[5]);
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (option.startsWith("ficheiro ")) {
                String[] parts = option.split(" ");
                char c = parts[1].charAt(0);
                String outputFileName = "Output/" + parts[2];

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    analista.listaPalavras(c, writer);
                }
                System.out.println("Ficheiro " + outputFileName + " gravado com sucesso.");
            } else if (option.equals("terminar")) {
                System.out.println("Sessao terminada.");
                break;
            }
        }
        scanner.close();
    }
}
