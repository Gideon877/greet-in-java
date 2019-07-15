package greet.greeter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WebGreeterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUserInputs() {
        String input = "username=Thabang&language=English";
        Map<String, String> users = new HashMap<>();

        WebGreeter wg = new WebGreeter(input);

        assertEquals(wg.isValid(), true);

        assertEquals("Thabang", wg.getName());
        assertEquals("English", wg.getLanguage());
    }
}