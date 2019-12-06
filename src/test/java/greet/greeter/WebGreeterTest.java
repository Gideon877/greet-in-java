package greet.greeter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
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

    @Test
    void asciiTable() {
        char a = '1';
        System.out.println(a);
    }

    @Test
    void dateAndTime(){
        LocalDateTime localDateTime = LocalDateTime.of(2000, Month.AUGUST, 31, 10, 20, 30);
        System.out.println(localDateTime);
    }
}