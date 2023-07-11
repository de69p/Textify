package com.example.textify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TextifyOperationsTest {

    @Test
    public void testRemoveEmptyLines() {
        List<String> lines = Arrays.asList("Hello", "", "World", "");
        List<String> expected = Arrays.asList("Hello", "World");

        List<String> result = TextifyOperations.removeEmptyLines(lines);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testReplaceAll() {
        List<String> lines = Arrays.asList("Hello", "World", "Hello World");
        List<String> expected = Arrays.asList("Hola", "World", "Hola World");

        List<String> result = TextifyOperations.replaceAll(lines, "Hello", "Hola");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testRemoveWhitespace_All() {
        List<String> lines = Arrays.asList("  Hello  ", "  World  ", "  Hello World  ");
        List<String> expected = Arrays.asList("Hello", "World", "HelloWorld");

        List<String> result = TextifyOperations.removeWhitespace(lines, "all");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testRemoveWhitespace_Leading() {
        List<String> lines = Arrays.asList("  Hello  ", "  World  ", "  Hello World  ");
        List<String> expected = Arrays.asList("Hello  ", "World  ", "Hello World  ");

        List<String> result = TextifyOperations.removeWhitespace(lines, "leading");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testRemoveWhitespace_Trailing() {
        List<String> lines = Arrays.asList("  Hello  ", "  World  ", "  Hello World  ");
        List<String> expected = Arrays.asList("  Hello", "  World", "  Hello World");

        List<String> result = TextifyOperations.removeWhitespace(lines, "trailing");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testReplaceFirst() {
        List<String> lines = Arrays.asList("Hello", "Hello World", "Hello Hello World");
        List<String> expected = Arrays.asList("Hola", "Hola World", "Hola Hello World");

        List<String> result = TextifyOperations.replaceFirst(lines, "Hello", "Hola");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testPadding() {
        List<String> lines = Arrays.asList("Hello", "World", "Textify");
        List<String> expected = Arrays.asList("*****Hello", "*****World", "***Textify");

        List<String> result = TextifyOperations.padding(lines, "*", 10);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testPadding_InvalidPaddingValue() {
        List<String> lines = Arrays.asList("Hello", "World");
        List<String> expected = Arrays.asList("Hello", "World");

        List<String> result = TextifyOperations.padding(lines, "*", -5);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testAddSuffix() {
        List<String> lines = Arrays.asList("Hello", "World", "Textify");
        List<String> expected = Arrays.asList("Hello!", "World!", "Textify!");

        List<String> result = TextifyOperations.addSuffix(lines, "!");

        Assertions.assertEquals(expected, result);
    }
}


