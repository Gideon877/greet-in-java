package greet;

import greet.greeter.GreetBuilder;

import java.util.HashMap;
import java.util.Map;

public class GreetPeople {
    Map<String, Integer> names = new HashMap<>();

    StringMethods stringMethods = new StringMethods();
    GreetBuilder builder = new GreetBuilder();

    public String greetPerson(String userName, String language) {
        if(language == null || language.isEmpty()) {
            language = "English";
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

    public Map<String, Integer> getUsersCount() {
        return names;
    }

    public int counter() {
        return names.size();
    }

    public void clearAllUsers() {
        names.clear();
    }

    public void clearUser(String userName) {
        names.remove(userName);
    }
}

