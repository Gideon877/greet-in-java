package greet;

import java.util.HashMap;
import java.util.Map;

public class GreetPeople {
    Map<String, Integer> names = new HashMap<>();

    StringMethods stringMethods = new StringMethods();

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
        return greetBuilder(userName, language);

    }

    public String greetBuilder(String Name, String language) {
        try {
            return String.format("%s, %s!", Language.valueOf(language).getExpression(), Name);
        } catch (IllegalArgumentException e) {
            return String.format("%s, %s!", Language.valueOf("English").getExpression(), Name);
        }
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

