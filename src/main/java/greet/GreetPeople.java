package greet;

import greet.greeter.GreetBuilder;
import greet.greeter.Greet;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static greet.Language.Zulu;

public class GreetPeople implements Greet {
    Map<String, Integer> names = new HashMap<>();

    StringMethods stringMethods = new StringMethods();
    GreetBuilder builder = new GreetBuilder();

    @Override
    public String greetPerson(String userName, String language) {
        if(language == null || language.isEmpty()) {
            language = Zulu.toString();
        }

        language = stringMethods.Capitalize(language);
        userName = stringMethods.Capitalize(userName);

        if(names.containsKey(userName)) {
            names.put(userName, names.get(userName) + 1);
        } else {
            names.put(userName, 1);
        }
        return builder.greetString(userName, language);

    }

    public int getGreetCounter(String userName) {
        try {
            return names.get(userName);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public Map<String, Integer> findAllUsers() {
        return names;
    }

    @Override
    public Map<String, Integer> findUser(String name) throws SQLException {
        Map<String, Integer> user = new HashMap<>();
        if(names.get(name) == null) {
            return user;
        }
        user.put(name, names.get(name));
        return user;
    }

    @Override
    public int usersCounter() {
        return names.size();
    }

    @Override
    public void clearAllUsers() {
        names.clear();
    }

    @Override
    public void clearUserByUsername(String userName) {
        names.remove(userName);
    }
}

