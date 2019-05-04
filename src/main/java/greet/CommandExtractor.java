package greet;

import greet.StringMethods.*;

public class CommandExtractor {
    private final String command;
    private final String name;
    private final String language;
    private final String defaultLanguage = "Zulu";

    StringMethods stringMethods;

    public CommandExtractor(String command) {
        String[] commandParts = command.split(" ");
        stringMethods = new StringMethods(); // Java classes that I created to do simple methods like Capitalize a word.

        this.command = commandParts[0].toLowerCase(); // setting first command to lowercase => e.g 'Greet' => 'greet'

        //Get user name and setting an empty string if is not provided
        if(commandParts.length >= 2) {
            this.name = stringMethods.Capitalize(commandParts[1]);
        } else {
            this.name = "";
        }
        //Get user entered language and setting it to a default language if is not provided
        if(commandParts.length == 3) {
            this.language = stringMethods.Capitalize(commandParts[2]);
        } else {
            this.language = defaultLanguage;
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
