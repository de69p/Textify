package com.example.textify;

import java.util.List;

public class TextifyOperations {

    // -x
    public static List<String> removeEmptyLines(List<String> lines) {
        return lines.stream()
                .filter(line -> !line.isEmpty())
                .toList();
    }

    // -g
    public static List<String> replaceAll(List<String> lines, String oldString, String newString) {
        return lines.stream()
                .map(line -> line.replace(oldString, newString))
                .toList();
    }

    // -w <spacing>
    public static List<String> removeWhitespace(List<String> lines, String spacing) {
        return lines.stream()
                .map(line -> switch (spacing.toLowerCase()) {
                    case "all" -> line.replaceAll("\\s", "");
                    case "leading" -> line.replaceAll("^\\s+", "");
                    case "trailing" -> line.replaceAll("\\s+$", "");
                    default -> line;
                })
                .toList();
    }


    // -r <old> <new>
    public static List<String> replaceFirst(List<String> lines, String oldString, String newString) {
        return lines.stream()
                .map(line -> line.replaceFirst(oldString, newString))
                .toList();
    }

    // -p <symbol> <padding>
    public static List<String> padding(List<String> lines, String symbol, int padding) {
        if (padding < 1 || padding > 100) {
            System.err.println("Padding value must be between 1 and 100.");
            return lines;
        }

        return lines.stream()
                .map(line -> {
                    if (line.length() < padding) {
                        StringBuilder paddedLine = new StringBuilder(line);
                        while (paddedLine.length() < padding) {
                            paddedLine.insert(0, symbol);
                        }
                        return paddedLine.toString();
                    } else {
                        return line;
                    }
                })
                .toList();
    }

    // -s <suffix>
    public static List<String> addSuffix(List<String> lines, String suffix) {
        return lines.stream()
                .map(line -> line + suffix)
                .toList();
    }
}
