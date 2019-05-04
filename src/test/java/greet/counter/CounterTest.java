package greet.counter;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static greet.ConsoleColors.*;
import static org.junit.jupiter.api.Assertions.*;

class CounterTest {
    static Counter counterTest;

    static {
        try {
            counterTest = new Counter();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    final String user1 = "Thabang";
    final String user2 = "Jonas";
    final String user3 = "Sandiso";

    @BeforeAll
    static void enroll() {
        System.out.println(counterTest.findAllUsers());
        counterTest.clearAllUsers();
    }

    @BeforeEach
    void setUp() {
        counterTest.addNewUser(user3);
        counterTest.findAndUpdateUser(user3);
    }

    @AfterEach
    void tearDown() {
//        System.out.println(counterTest.usersCounter());
        counterTest.clearUserByUsername(user1);
        counterTest.clearUserByUsername(user2);
        counterTest.clearUserByUsername(user3);
    }

    @Test
    void shouldClearAllUsers() throws SQLException {
        assertEquals(1, counterTest.usersCounter());
        counterTest.clearAllUsers();
        assertEquals(0, counterTest.usersCounter());
    }

    @Test
    void shouldAddNewUser() {
//        add second user
        counterTest.addNewUser(user1);
        assertEquals(2, counterTest.usersCounter());

        //add third user
        counterTest.addNewUser(user2);
        assertEquals(3, counterTest.usersCounter());

    }

    @Test
    void shouldFindUser() throws SQLException {
        assertEquals("{Sandiso=2}", counterTest.findUser(user3).toString());
    }

    @Test
    void shouldFindAndUpdateUser() throws SQLException {
        assertEquals("{Sandiso=2}", counterTest.findUser(user3).toString());

        counterTest.findAndUpdateUser(user3);
        assertEquals("{Sandiso=3}", counterTest.findUser(user3).toString());
    }

    @Test
    void shouldFindAllUsers() {
        counterTest.addNewUser(user1);
        assertEquals("{Sandiso=2, Thabang=1}", counterTest.findAllUsers().toString());
    }

    @Test
    void shouldClearUserByUsername() throws SQLException {
        assertEquals("{Sandiso=2}", counterTest.findAllUsers().toString());

        counterTest.clearUserByUsername(user3);
        assertEquals("{}", counterTest.findUser(user3).toString());
    }

    @Test
    void shouldGetUsersCounter() {
        assertEquals(1, counterTest.usersCounter());

        counterTest.addNewUser(user1);
        counterTest.addNewUser(user2);

        assertEquals(3, counterTest.usersCounter());
    }

    //greet
    @Test
    void shouldGreetPerson() throws SQLException {
        assertEquals(BLACK_BOLD + "Dumela, Thabang!" + RESET , counterTest.greetPerson(user1, "sotho"));
        assertEquals(2, counterTest.usersCounter());
    }

    @Test
    void shouldGreetPersonWithDefault() throws SQLException {
        assertEquals(BLACK_BOLD + "Hello, Jonas!" + RESET , counterTest.greetPerson(user2, null));
        assertEquals(2, counterTest.usersCounter());
    }

    @Test
    void shouldGreetPersonWithDefaultIfNoLanguageFound() throws SQLException {
        assertEquals(RED_BOLD_BRIGHT + "Hello, Jonas!" + RESET , counterTest.greetPerson(user2, "french"));
        assertEquals(2, counterTest.usersCounter());

    }
}