package greet;

import greet.exceptions.NameNotFoundException;
import greet.greeter.Greet;

import java.sql.SQLException;
import java.util.Map;

import static greet.Colors.*;

public class CommandProcessor {
    private final CommandExtractor commandExtractor;
    private final Greet db;
    StringMethods stringMethods = new StringMethods();

    public CommandProcessor(Greet db, CommandExtractor commandExtractor) throws SQLException, ClassNotFoundException {
        this.commandExtractor = commandExtractor;
        this.db = db;
    }

    public String menu() throws Exception {
        if (getCommand().equalsIgnoreCase("greet")) {
            return greet();
        } else if(getCommand().equalsIgnoreCase("greeted")) {
            return greeted();
        } else if(getCommand().equalsIgnoreCase("clear")) {
            return clear();
        } else if (getCommand().equalsIgnoreCase("counter")) {
            return counter();
        } else if(getCommand().equalsIgnoreCase("help")) {
            return stringMethods.help();
        } else if(getCommand().equalsIgnoreCase("x")) {
            return stringMethods.FormatLanguage();
        }else {
            return stringMethods.invalid(getCommand());
        }
    }

    private String greeted() throws Exception {
        if(hasName()) {
            Map<String, Integer> userFound = db.findUser(getName());
            try {
                if(userFound.get(getName()) == null) {
                    throw new NameNotFoundException(getName());
                }
                int counter = userFound.get(getName());
                return String.format("%s%s%s have been greeted %s%s%s time(s)!", BLUE_BOLD, getName(), RESET, CYAN_BOLD , counter, RESET);
            } catch (NameNotFoundException e) {
                return e.getMessage();
            }
        }
        return db.findAllUsers().toString();
    }

    public String counter()  {
        return stringMethods.counter(db.usersCounter());
    }

    public String greet() {
        try {
            return db.greetPerson(getName(), getLanguage()); //Language.valueOf(language).getExpression() + ", " + userName;
        } catch (Exception e) {
            return stringMethods.invalid(getCommand());
        }
    }

    private String clear() {
        try {
            if(hasName()) {
                db.clearUserByUsername(getName());
                return getName() + " have been cleared from database.";
            } else {
                db.clearAllUsers();
                return "Greeted names have been cleared from database.";
            }
        } catch (Exception e) {
            if(hasName()) {
                return "failed to clear name: " + getName();
            } else {
                return "failed to clear all users.";
            }
        }
    }

    public String getCommand() {
        return this.commandExtractor.getCommand();
    }

    public String getName() {
        return this.commandExtractor.getName();
    }

    public String getLanguage() {
        return this.commandExtractor.getLanguage();
    }

    private boolean hasName() {
        return this.commandExtractor.hasName();
    }
}
