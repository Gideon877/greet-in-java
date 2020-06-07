package greet.greeter;

import greet.Language;
import greet.exceptions.LanguageNotFoundException;

import static greet.Colors.*;

public class GreetBuilder {

    public GreetPerson greetPerson = (String name, String language) -> {
        name = name.replaceAll("[^a-zA-Z ]", "").trim();

        language = returnLanguage(language);
        try {
            return greetFormat( BLACK_BOLD, Language.valueOf(language).getExpression(), name);
        } catch (IllegalArgumentException e) {
            if(returnLanguage(language).isEmpty()) {
                return String.format("%s \n%s", greetFormat(RED_BOLD_BRIGHT, Language.valueOf("Zulu").getExpression(), name), new LanguageNotFoundException(language).getMessage());
            }
            return greetFormat( BLACK_BOLD, Language.valueOf(returnLanguage(language)).getExpression(), name);
        }
    };

    public String returnLanguage(String passedLanguage) {
        String validLanguage = "";
        for(Language language: Language.values()) {
            String currentLanguage = language.name().toLowerCase();
            if(passedLanguage.endsWith(currentLanguage.substring(currentLanguage.length() - 4))) {
                validLanguage = language.name();
            }
        }
        return validLanguage;
    }

    private String greetFormat(String color, String language, String userName) {
        return String.format("%s%s, %s!", color, language, userName);
    }
}
