package greet;

import greet.StringMethods.*;

public class CommandExtractor {
    private final String command;
    private final String name;
    private final String language;

    StringMethods stringMethods;

    public CommandExtractor(String command) {
        String[] commandParts = command.split(" ");
        stringMethods = new StringMethods();

        this.command = commandParts[0].toLowerCase();

        if(commandParts.length >= 2) {
            this.name = stringMethods.Capitalize(commandParts[1]);
        } else {
            this.name = "";
        }

        if(commandParts.length == 3) {
            this.language = stringMethods.Capitalize(commandParts[2]);
        } else {
            this.language = "Zulu";
        }
    }

    public String getCommand() {
        return command;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }
}
