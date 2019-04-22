package greet.greeter;

import greet.Language;

import static greet.ConsoleColors.*;

public class GreetBuilder {

    public String greetString(String Name, String language) {
        try {
            return greetFormat( BLACK_BOLD, Language.valueOf(language).getExpression(), Name);
        } catch (IllegalArgumentException e) {
            return greetFormat(RED_BOLD_BRIGHT, Language.valueOf("English").getExpression(), Name);
        }
    }
    private String greetFormat(String color, String language, String userName) {
        return String.format("%s%s, %s!%s", color, language, userName, RESET);
    }
}
