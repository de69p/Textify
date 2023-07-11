package com.example.textify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    @Test
    public void testReadLinesFromFile() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".txt");
        List<String> expectedLines = Arrays.asList("Hello", "World", "Textify");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
            for (String line : expectedLines) {
                writer.write(line);
                writer.newLine();
            }
        }

        List<String> result = Main.readLinesFromFile(tempFile.toString());

        Files.delete(tempFile);

        Assertions.assertEquals(expectedLines, result);
    }

    @Test
    public void testPrintLines() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> lines = Arrays.asList("Hello", "World", "Textify");
        String expectedOutput = "Hello\nWorld\nTextify\n";

        Main.printLines(lines);

        System.setOut(System.out);

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testSaveToFile() throws IOException {
        Path tempFile = Files.createTempFile("temp", ".txt");
        String filename = tempFile.toString();

        List<String> lines = Arrays.asList("Hello", "World", "Textify");

        Main.saveToFile(lines, filename);

        List<String> result = Files.readAllLines(tempFile);

        Files.delete(tempFile);

        Assertions.assertEquals(lines, result);
    }
}
