package utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPromptUtilsTest {

    @Test
    void stringPrompt_returnsTrimmedUserInput() {
        String simulatedInput = "hello world\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UserPromptUtils.resetScanner();

        String result = UserPromptUtils.stringPrompt("Enter input: ");
        System.out.println();
        System.out.println("Result: " + result);
        assertEquals("hello world", result);
    }
}
