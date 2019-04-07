package greet;

import greet.greet.GreetPeopleHashMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GreetPeopleHashMapTest {

    private final String userName = "Gideon";
    private final String language = "tswana";

    @Test
    void shouldGreetPerson() {
        GreetPeopleHashMap person = new GreetPeopleHashMap();
        assertEquals(person.greetPerson(userName, "zulu"), "Sawubona, Gideon!");
    }

    @Test
    void shouldGreetPersonWithDefaultLanguage() {
        GreetPeopleHashMap person = new GreetPeopleHashMap();
        assertEquals(person.greetPerson(userName, ""), "Hello, Gideon!");
        assertEquals(person.greetPerson(userName, null), "Hello, Gideon!");
    }

    @Test
    void shouldGetGreetCounter() {
        GreetPeopleHashMap person = new GreetPeopleHashMap();
        person.greetPerson(userName, language);
        person.greetPerson("John", language);
        person.greetPerson(userName, language);

        assertEquals(person.getGreetCounter(userName), 2);

    }

    @Test
    void shouldGetUsersCount() {
        GreetPeopleHashMap person = new GreetPeopleHashMap();
        person.greetPerson(userName, language);
        person.greetPerson("John", null);

        Map<String, Integer> map = person.getUsersCount();

        assertEquals(map.get("John"), 1);
    }

    @Test
    void shouldReturnCounter() {
        GreetPeopleHashMap person = new GreetPeopleHashMap();

        person.greetPerson("Ross", language);
        person.greetPerson("John", null);
        person.greetPerson(userName, null);
        person.greetPerson("John", null);

        assertEquals(person.counter(), 3);

    }

    @Test
    void shouldClearAllUsers() {
        GreetPeopleHashMap person = new GreetPeopleHashMap();

        person.greetPerson("Ross", language);
        person.greetPerson("John", null);

        assertEquals(person.counter(), 2);

        person.clearAllUsers();

        assertEquals(person.counter(), 0);


    }

    @Test
    void shouldClearUserCount() {
        GreetPeopleHashMap person = new GreetPeopleHashMap();

        person.greetPerson("Ross", language);
        person.greetPerson("Ross", language);
        person.greetPerson("John", null);

        assertEquals(person.counter(), 2);

        person.clearUser("Ross");
        assertEquals(person.counter(), 1);
    }
}