package greet.greeter;

import greet.Language;

public class GreetBuilder {
    public GreetPerson greetPerson = (String name, String language) -> {
        try {
            return String.format("%s, %s!", Language.valueOf(language).getExpression(), name);
        } catch (IllegalArgumentException e) {
            return String.format("%s, %s!", Language.valueOf("Zulu").getExpression(), name);
        }
    };
}
