package greet.greeter;

import org.junit.jupiter.api.*;

import static greet.Colors.BLACK_BOLD;
import static greet.Colors.RESET;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetBuilderTest {

    GreetBuilder greeter;
    @Nested
    @DisplayName("Should greet [name] when:")
    class shouldGreetSomeone {
        @BeforeEach
        void setUp() {
            greeter = new GreetBuilder();
        }

        @Test
        @DisplayName("a [name] is passed.")
        void shouldGreetSomeone_1() {
            assertEquals(BLACK_BOLD + "Hello, Thabang!", greeter.greetPerson.greet("Thabang", "English"));
        }

        @Test
        @DisplayName("name and an empty space is passed.")
        void shouldGreetSomeone_2() {
            assertEquals(BLACK_BOLD + "Hello, Thabang!", greeter.greetPerson.greet("  Thabang       ", "English"));
        }

        @Test
        @DisplayName("name and characters are passed.")
        void shouldGreetSomeone_3() {
            assertEquals(BLACK_BOLD + "Hello, Thabang!", greeter.greetPerson.greet(".;':<>!Thabang@#$%^&*()`", "English"));
        }
    }

    @Nested
    @DisplayName("Should greet World when:")
    class shouldGreetWorld {
        @BeforeEach
        void setUp() {
            greeter = new GreetBuilder();
        }

        @Test
        @DisplayName("an empty String is passed.")
        void shouldGreetWorld_1() {
            assertEquals(BLACK_BOLD + "Hello, !" , greeter.greetPerson.greet("", "English"));
        }

        @Test
        @DisplayName("an empty space String is passed.")
        void shouldGreetWorld_2() {
            assertEquals(BLACK_BOLD + "Hello, !" , greeter.greetPerson.greet("         ", "English"));
        }

        @Test
        @DisplayName("characters are passed.")
        void shouldGreetWorld_3() {
            assertEquals(BLACK_BOLD + "Hello, !" , greeter.greetPerson.greet(".;':<>!@#$%^&*()`", "English"));
        }
    }




}
