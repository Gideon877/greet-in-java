package greet.greeter;

import greet.Language;

import static greet.Colors.*;

public class GreetBuilder {

    public GreetPerson greetPerson = (String name, String language) -> {
        String Name = name.replaceAll("[^a-zA-Z ]", "").trim();
        if(Name == null || Name.isEmpty()) {
            Name = "World";
        }
        try {
            return greetFormat( BLACK_BOLD, Language.valueOf(language).getExpression(), Name);
        } catch (IllegalArgumentException e) {
            return greetFormat(RED_BOLD_BRIGHT, Language.valueOf("Zulu").getExpression(), Name);
        }
    };

    private String greetFormat(String color, String language, String userName) {
        return String.format("%s%s, %s!%s", color, language, userName, RESET);
    }
}
