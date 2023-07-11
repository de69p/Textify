package com.example.textify;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.err.println("Usage: textify [OPTION] FILE");
        System.out.println();
        System.out.println("Available commands:");
        System.out.println("-x: Removes empty lines from the input file");
        System.out.println("-g: Replaces all occurrences of substring old in each line with string new");
        System.out.println("-w spacing: Removes whitespace from lines based on the specified interval [all], [leading] or [trailing]");
        System.out.println("-r old new: Replaces the first instance of substring old in each line with string new");
        System.out.println("-p ch num: Pads the beginning of a line with the specified character until the line length reaches the specified number");
        System.out.println("-s suffix: Adds the specified suffix to the end of each line");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter textify command: ");
        String input = scanner.nextLine();

        String[] commandArgs = input.split(" ");
        if (commandArgs.length < 2 || !commandArgs[0].equals("textify")) {
            System.err.println("Usage: textify [ -x | -g | -w spacing | -r old new | -p ch num | -s suffix ] FILE");
            scanner.close();
            return;
        }

        String filename = commandArgs[commandArgs.length - 1];

        List<String> lines = readLinesFromFile(filename);

        if (lines.isEmpty()) {
            scanner.close();
            return;
        }

        List<String> transformedLines = new ArrayList<>(lines);
        boolean modifyLines = false;
        boolean replaceAll = false;

        for (int i = 1; i < commandArgs.length - 1; i++) {
            String option = commandArgs[i];

            switch (option) {
                case "-x" -> {
                    transformedLines = TextifyOperations.removeEmptyLines(transformedLines);
                    modifyLines = true;
                }
                case "-g" -> replaceAll = true;
                case "-w" -> {
                    transformedLines = TextifyOperations.removeWhitespace(transformedLines, commandArgs[++i]);
                    modifyLines = true;
                }
                case "-r" -> {
                    String oldString = commandArgs[++i];
                    String newString = commandArgs[++i];
                    transformedLines = replaceAll
                            ? TextifyOperations.replaceAll(transformedLines, oldString, newString)
                            : TextifyOperations.replaceFirst(transformedLines, oldString, newString);
                    modifyLines = true;
                }
                case "-p" -> {
                    transformedLines = TextifyOperations.padding(transformedLines, commandArgs[++i], Integer.parseInt(commandArgs[++i]));
                    modifyLines = true;
                }
                case "-s" -> {
                    transformedLines = TextifyOperations.addSuffix(transformedLines, commandArgs[++i]);
                    modifyLines = true;
                }
                default -> {
                }
            }
        }

        printLines(modifyLines ? transformedLines : lines);

        System.out.print("Do you want to save the output file? (Yes/No): ");
        String saveOption = scanner.nextLine();

        if (saveOption.equalsIgnoreCase("Yes")) {
            System.out.print("Enter the output filename: ");
            String outfile = scanner.nextLine();
            saveToFile(transformedLines, outfile);
            System.out.println("Output file saved successfully.");
        }

        scanner.close();
    }

    static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            lines = reader.lines().toList();
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }

        return lines;
    }

    static void printLines(List<String> lines) {
        lines.forEach(System.out::println);
    }

    static void saveToFile(List<String> lines, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            lines.forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException e) {
                    System.err.println("Error saving file: " + filename);
                }
            });
        } catch (IOException e) {
            System.err.println("Error saving file: " + filename);
        }
    }
}
