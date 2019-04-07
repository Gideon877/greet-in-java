package greet;

import greet.counter.DbCounter;

import greet.greet.GreetPeople;
import greet.greet.StringMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GreetCounterTest {

    Connection conn;
    final String GREET_DATABASE_URL = "jdbc:h2:./target/greet_db";
    final String userName = "Jan";
    final String language = "pedi";

    public GreetCounterTest() throws Exception {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(GREET_DATABASE_URL, "sa", "");
    }


    @BeforeEach
    void deleteUser() throws SQLException {
        try(Statement statement = (Statement) conn.createStatement()) {
            statement.execute("delete from users where name = 'Jan'");
            statement.execute("insert into users (name, count) values ('Jan', 1)");
        }
    }


    @Test
    void findUserByUsernameTest() throws SQLException{
        DbCounter greetCounter = new DbCounter(userName, conn);
        assertEquals(greetCounter.findUser().get(userName), 1);
    }

    @Test
    void shouldFailToFindUserByUsername() throws SQLException{
        DbCounter greetCounter = new DbCounter("Fortune", conn);
        assertEquals(greetCounter.findUser().get(userName), null);
    }

    @Test
    void updateUserCountTest() throws SQLException{
        DbCounter greetCounter = new DbCounter(userName, conn);

        assertEquals(greetCounter.findAndUpdateUser(), 1);

        Map<String, Integer> user = greetCounter.findUser();

        assertEquals(user.containsKey(userName), true);
        assertEquals(user.get(userName), 2);

    }

    @Test
    void findAllUsersTest() throws Exception {
        DbCounter greetCounter = new DbCounter(userName, conn);
        StringMethods string = new StringMethods();
        assertEquals(1, greetCounter.findAllUsers().get("Jan"));

        GreetPeople b = new GreetPeople();
        b.greetPerson("Jan", null);
        b.greetPerson("Jan", null);
        Map<String, Integer> users = greetCounter.findAllUsers();
        string.Format(users);
        b.clearUser("Jan");
        b.greetPerson("Jan", null);
    }


}
