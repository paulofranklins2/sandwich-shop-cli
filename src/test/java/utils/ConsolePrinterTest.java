package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsolePrinterTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private enum TestEnum {
        FIRST_OPTION, SECOND_CHOICE
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void printHeader_shouldPrintFormattedHeader() {
        ConsolePrinter.printHeader("Test Header");
        String printed = output.toString();
        assertTrue(printed.contains("Test Header"), "Header should contain the given title");
        assertTrue(printed.lines().count() >= 3, "Header should include top, middle, and bottom lines");
    }

    @Test
    void clearScreen_shouldPrint50EmptyLines() {
        ConsolePrinter.clearScreen();
        long lineCount = output.toString().lines().count();
        assertEquals(100, lineCount, "Should print 50 empty lines");
    }

    @Test
    void capitalizeWords_shouldCapitalizeEachWord() {
        String result = ConsolePrinter.capitalizeWords("hello world from test");
        assertEquals("Hello World From Test", result);
    }

    @Test
    void formatEnum_shouldFormatEnumWithCapitalsAndSpaces() {
        String formatted = ConsolePrinter.formatEnum(TestEnum.FIRST_OPTION);
        assertEquals("First Option", formatted);
    }

    @Test
    void formatEnumList_shouldFormatListOfEnumsAsCommaSeparatedString() {
        List<TestEnum> enumList = List.of(TestEnum.FIRST_OPTION, TestEnum.SECOND_CHOICE);
        String formatted = ConsolePrinter.formatEnumList(enumList);
        assertEquals("First Option, Second Choice", formatted);
    }
}
