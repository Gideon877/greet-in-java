package greet.greeter;

import java.sql.SQLException;
import java.util.Map;

public interface Greeter {
    void clearAllUsers();
    void clearUserByUsername(String userName);
    int usersCounter();
    String greetPerson(String userName, String language) throws SQLException;
    Map<String, Integer> findAllUsers();
    Map<String, Integer> findUser(String name) throws SQLException;
}
