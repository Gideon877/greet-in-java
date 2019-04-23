package greet;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GreetPeopleTest {
    GreetPeople greeter = new GreetPeople();
    @Disabled("need bug fix")
    @Nested
    @DisplayName("when command is greet")
    class GreetPerson {
        GreetPeople greet;
        final String userOne = "Dan";
        final String zulu = "Zulu";

        @BeforeEach
        void setClass() {
            greet = new GreetPeople();
        }

        @Test
        @DisplayName("greet person with default language")
        void greetPerson() {
            assertEquals("Hello, Dan!" , greet.greetPerson(userOne, null));
        }

        @Test
        @DisplayName("greet person in Zulu")
        void greetPersonInZulu() {
            assertEquals("Sawubona, Dan!" , greet.greetPerson(userOne, zulu));
        }
    }

    @Disabled("need bug fix")
    @Nested
    @DisplayName("when greetBuilder is executed")
    class greetBuilder {
        GreetPeople greet;

        @BeforeEach
        void setClass() {
            greet = new GreetPeople();
        }

        @Test
        @DisplayName("greet person in Sotho")
        void greetPersonInSotho() {
            assertEquals("Dumela, Dan!" , greet.greetPerson("Dan", "Sotho"));
        }
        @Test
        @DisplayName("greet person with default language")
        void greetPerson() {
            assertEquals("Hello, Dan!" , greet.greetPerson("Dan", null));
        }

    }

    @Test
    void getGreetCounter() {
        assertEquals(0, greeter.getGreetCounter("John"));
        greeter.greetPerson("John", null);
        assertEquals(1, greeter.getGreetCounter("John"));


        List<Language> somethingList = Arrays.asList(Language.values());
        System.out.println(somethingList);
    }

    @Test
    void getUsersCount() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);
        greeter.greetPerson("Vince", null);

        assertEquals("{Vince=1, John=1, Jonas=1}", greeter.getUsersCount().toString());
    }

    @Test
    void counter() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);

        assertEquals(2, greeter.counter());
    }

    @Test
    void clearAllUsers() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);

        assertEquals(2, greeter.counter());
        greeter.clearAllUsers();
        assertEquals(0, greeter.counter());
    }

    @Test
    void clearUser() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);
        greeter.greetPerson("Vince", null);

        assertEquals(3, greeter.counter());

        greeter.clearUser("Jonas");
        assertEquals(2, greeter.counter());

    }
}