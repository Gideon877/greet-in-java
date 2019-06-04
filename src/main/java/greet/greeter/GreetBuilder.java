package greet.greeter;

import greet.Language;

public class GreetBuilder {
    public GreetPerson greetPerson = (String Name, String language) -> {
        try {
            return String.format("%s, %s!", Language.valueOf(language).getExpression(), Name);
        } catch (IllegalArgumentException e) {
            return String.format("%s, %s!", Language.valueOf("Zulu").getExpression(), Name);
        }
    };
}
