package greet.exceptions;

import static greet.Colors.RED_BOLD_BRIGHT;
import static greet.Colors.RESET;

public class LanguageNotFoundException extends Exception {
    private final String message;

    public LanguageNotFoundException(String language) {
        this.message = String.format("The language %s%s%s is not available at the moment." ,  RED_BOLD_BRIGHT,  language , RESET);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
