package greet.greet;

import greet.Language;
import greet.counter.GreetCounter;

import java.util.*;

public class GreetPeople {

    GreetCounter greetCounter = new GreetCounter();
    StringMethods stringMethods = new StringMethods();
    ConsoleColors consoleColors = new ConsoleColors();

    public GreetPeople() throws Exception {
    }


    public String greetPerson(String userName, String language) throws Exception {
        if(language == null || language.isEmpty()) {
            language = "English";
        }
        language = stringMethods.Capitalize(language);
        userName = stringMethods.Capitalize(userName);

        if(greetCounter.findUserByUsername(userName).isEmpty()) {
            greetCounter.addNewUserData(userName);
        } else {
            greetCounter.updateUserData(userName);
        }

        return greetBuilder(userName, language);
    }

    private String greetBuilder(String Name, String language) {
        try {
            return greetFormat( consoleColors.BLACK_BOLD, Language.valueOf(language).getExpression(), Name);
        } catch (IllegalArgumentException e) {
            return greetFormat(consoleColors.RED_BOLD_BRIGHT, Language.valueOf("English").getExpression(), Name);
        }
    }

    private String  greetFormat(String color, String language, String userName) {
        return String.format("%s%s, %s!%s", color, language, userName, consoleColors.RESET);
    }


    public int getGreetCounter(String userName) throws Exception {
        userName = stringMethods.Capitalize(userName);
        Map<String, Integer> user = greetCounter.findUserByUsername(userName);

        if(user.isEmpty() || user.get(userName) == null) {
            return 0;
        }

        return user.get(userName);
    }

    public Map<String, Integer> getUsersCount() throws Exception {
        return greetCounter.getUsersCount();
    }


    public int counter() throws Exception {
        return greetCounter.findUsersCount();
    }

    public void clearAllUsers() throws Exception {
        greetCounter.clearAllUsers();
    }

    public void clearUser(String userName) {
        userName = stringMethods.Capitalize(userName);
        greetCounter.clearUserByUsername(userName);
    }
}
