package greet.greeter;

import greet.Language;
import greet.exceptions.LanguageNotFoundException;

import static greet.Colors.*;

public class GreetBuilder {

    public GreetPerson greetPerson = (String name, String language) -> {
        name = name.replaceAll("[^a-zA-Z ]", "").trim();

        try {
            return greetFormat( BLACK_BOLD, Language.valueOf(language).getExpression(), name);
        } catch (IllegalArgumentException e) {
            return String.format("%s %s", greetFormat(RED_BOLD_BRIGHT, Language.valueOf("Zulu").getExpression(), name), new LanguageNotFoundException(language).getMessage());
        }
    };

    private String greetFormat(String color, String language, String userName) {
        return String.format("%s%s, %s!%s", color, language, userName, RESET);
    }
}
