package greet.exceptions;

import static greet.Colors.*;

public class NameNotFoundException extends Exception {
    private final String message;

    public NameNotFoundException(String name) {
        this.message = BLUE_BOLD + name + RESET + " have not been greeted. Please greet them first to get a counter.";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
