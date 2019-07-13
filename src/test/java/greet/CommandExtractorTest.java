package greet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandExtractorTest {
    @Nested
    @DisplayName("should get all greet possible command parts")
    class greetCommand {

        @BeforeEach
        void setUp() {

        }

        @Test
        void shouldGetCommandParts_1() {
            String command = "greet Thabang English";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("greet", commandExtractor.getCommand());
            assertEquals("Thabang", commandExtractor.getName());
            assertEquals("English", commandExtractor.getLanguage());
        }

        @Test
        void shouldGetCommandParts_2() {
            String command = "greet Gideon";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("greet", commandExtractor.getCommand());
            assertEquals("Gideon", commandExtractor.getName());
            assertEquals("Zulu", commandExtractor.getLanguage());
            assertEquals(true, commandExtractor.hasName());
        }

        @Test
        void shouldGetCommandParts_3() {
            String command = "greet";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("greet", commandExtractor.getCommand());
            assertEquals("", commandExtractor.getName());
            assertEquals("Zulu", commandExtractor.getLanguage());
            assertEquals(false, commandExtractor.hasName());
        }

    }

    @Nested
    @DisplayName("should get all greeted possible command parts")
    class greetedCommand {
        @Test
        void shouldGetCommandParts_1() {
            String command = "Greeted thabang";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("greeted", commandExtractor.getCommand());
            assertEquals("Thabang", commandExtractor.getName());
        }

        @Test
        void shouldGetCommandParts_2() {
            String command = "greeted";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("greeted", commandExtractor.getCommand());
        }

        @Test
        void shouldGetCommandParts_3() {
            String command = "greeted john and Jonas";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("greeted", commandExtractor.getCommand());
            assertEquals("John", commandExtractor.getName());
        }



    }

    @Nested
    @DisplayName("should get all count, exit and help possible command parts")
    class countAndExitCommand {
        @Test
        void shouldGetCommandParts_1() {
            String command = "count";
            CommandExtractor commandExtractor = new CommandExtractor(command);
            assertEquals("count", commandExtractor.getCommand());
        }

        @Test
        void shouldGetCommandParts_2() {
            String command = "exit";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("exit", commandExtractor.getCommand());
        }

        @Test
        void shouldGetCommandParts_3() {
            String command = "help";
            CommandExtractor commandExtractor = new CommandExtractor(command);
            assertEquals("help", commandExtractor.getCommand());
        }



    }

    @Nested
    @DisplayName("should get all clear possible command parts")
    class clearCommand {
        @Test
        void shouldGetCommandParts_1() {
            String command = "clear Thabang";
            CommandExtractor commandExtractor = new CommandExtractor(command);

            assertEquals("clear", commandExtractor.getCommand());
            assertEquals("Thabang", commandExtractor.getName());
        }

        @Test
        void shouldGetCommandParts_2() {
            String command = "CLEAR";
            CommandExtractor commandExtractor = new CommandExtractor(command);
            assertEquals("clear", commandExtractor.getCommand());
        }

    }
}