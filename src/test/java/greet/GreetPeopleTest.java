package greet;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static greet.ConsoleColors.*;
import static org.junit.jupiter.api.Assertions.*;

class GreetPeopleTest {
    GreetPeople greeter = new GreetPeople();

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
            assertEquals(BLACK_BOLD + "Sawubona, Dan!" + RESET , greet.greetPerson(userOne, null));
        }

        @Test
        @DisplayName("greet person in English")
        void greetPersonInZulu() {
            assertEquals(BLACK_BOLD + "Hello, Dan!" + RESET , greet.greetPerson(userOne, "English"));
//            assertEquals(BLACK_BOLD + "Hello, Dan!" + RESET , greet.greetPerson(userOne, "english"));
        }
    }


    @Test
    void getGreetCounter() {
        assertEquals(0, greeter.getGreetCounter("John"));
        greeter.greetPerson("John", null);
        assertEquals(1, greeter.getGreetCounter("John"));


//        List<Language> somethingList = Arrays.asList(Language.values());
//        System.out.println(somethingList);
    }

    @Test
    void getUsersCount() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);
        greeter.greetPerson("Vince", null);

        assertEquals("{Vince=1, John=1, Jonas=1}", greeter.findAllUsers().toString());
    }

    @Test
    void counter() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);

        assertEquals(2, greeter.usersCounter());
    }

    @Test
    void clearAllUsers() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);

        assertEquals(2, greeter.usersCounter());
        greeter.clearAllUsers();
        assertEquals(0, greeter.usersCounter());
    }

    @Test
    void clearUser() {
        greeter.greetPerson("John", null);
        greeter.greetPerson("Jonas", null);
        greeter.greetPerson("Vince", null);

        assertEquals(3, greeter.usersCounter());

        greeter.clearUserByUsername("Jonas");
        assertEquals(2, greeter.usersCounter());

    }

    @Test
    void builderTest() {
        StringBuilder sb = new StringBuilder();

        sb.append("------------\n");
        sb.append("GoodBye\n");
        sb.append("------------\n");

        System.out.println(sb);
    }
}