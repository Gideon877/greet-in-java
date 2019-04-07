package greet.main;

import greet.greet.StringMethods;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringMethodsTest {

    @Test
    void ShouldCapitalize() {
        StringMethods stringMethods = new StringMethods();
        final String string = "String";

        assertEquals(string, stringMethods.Capitalize("string"));
        assertEquals(string, stringMethods.Capitalize("String"));
        assertEquals(string, stringMethods.Capitalize("STRING"));
        assertEquals(string, stringMethods.Capitalize("strinG"));
        assertEquals(string, stringMethods.Capitalize("stRIng"));
        assertEquals(string, stringMethods.Capitalize("STRINg"));

        assertEquals(string.equalsIgnoreCase("StrIng"), true);
    }
}