package greet.greeter;

import greet.Language;
import greet.database.PostgresDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static greet.Language.*;
import static org.junit.jupiter.api.Assertions.*;

class GreetBuilderTest {
    String name = "Thabang";
    String language = "Zulu";
    GreetBuilder app;

    @BeforeEach
    void setUp() {
        app = new GreetBuilder();
    }

    @Test
    void shouldGreetPerson() {
        assertEquals("Sawubona, Thabang!", app.greetPerson.greet(name, language));
    }

    @Test
    void shouldGetPersonCounter() {
        PostgresDB db = new PostgresDB();
        assertEquals(0, db.usersCounter());
    }
}