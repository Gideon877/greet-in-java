package greet;

import greet.greet.GreetPeople;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetPeopleTest {
    private String userName = "Thabang";
    private String language = "Xhosa";

    @BeforeEach
    void resetDb() throws Exception {
        GreetPeople person = new GreetPeople();
        person.clearAllUsers();
    }

    @Test
    void shouldReturnCountForSpecificUser() throws Exception {
        GreetPeople person = new GreetPeople();

        person.greetPerson("Lebo", null);
        person.greetPerson("John", "Zulu");
        person.greetPerson("Lebo", null);

        assertEquals(person.getGreetCounter("Lebo"), 2);

    }

    @Test
    public void shouldReturnCountOfGreetedNames() throws Exception {
        GreetPeople person = new GreetPeople();

        person.greetPerson("Lebo", null);
        person.greetPerson("John", "Zulu");
        person.greetPerson(userName, "");
        person.greetPerson("Lebo", null);

        assertEquals(person.getUsersCount().size(), 3);

    }

    @Test
    public void shouldGreetPersonInAfrikaans() throws Exception {
        String language = "Afrikaans";
        GreetPeople greet = new GreetPeople();

        assertEquals(greet.greetPerson(userName, language), "Goeie dag, Thabang!");
        assertEquals(greet.getUsersCount().size(), 1);

    }


    // greetCounter update on greet

    @Test
    void shouldClearAllUsers() throws Exception {
        GreetPeople person = new GreetPeople();

        person.greetPerson("Lebo", null);
        person.greetPerson("John", "Zulu");
        person.greetPerson(userName, "");
        person.greetPerson("Lebo", null);

        assertEquals(person.getUsersCount().size(), 3);

        person.clearAllUsers();

        assertEquals(person.getUsersCount().size(), 0);
    }

    @Test
    void shouldClearUser() throws Exception {
        GreetPeople person = new GreetPeople();

        person.greetPerson("Lebo", null);
        person.greetPerson("John", "Zulu");
        person.greetPerson(userName, "");
        person.greetPerson("Lebo", null);

        assertEquals(person.getUsersCount().size(), 3);

        person.clearUser("Lebo");

        assertEquals(person.getUsersCount().size(), 2);
        System.out.println(person.getUsersCount());

    }

}
