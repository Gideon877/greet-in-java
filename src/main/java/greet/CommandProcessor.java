package greet;

import greet.counter.Counter;
import greet.greeter.Greet;

import java.sql.SQLException;
import java.util.Map;

import static greet.ConsoleColors.*;

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
            clear();
            return "";
        } else if (getCommand().equalsIgnoreCase("counter")) {
            counter();
            return "";
        } else if(getCommand().equalsIgnoreCase("help")) {
            stringMethods.help();
            return "";
        } else if(getCommand().equalsIgnoreCase("x")) {
            stringMethods.FormatLanguage();
            return "";
        }else {
            return stringMethods.invalid(getCommand());
        }
    }

    private String greeted() throws Exception {
        if(hasName()) {
            Map<String, Integer> userFound = db.findUser(getName());
            try {
                int counter = userFound.get(getName());
                String greetedMessage = String.format("%s%s%s have been greeted %s%s%s time(s)!", BLUE_BOLD, getName(), RESET, CYAN_BOLD , counter, RESET);
                return greetedMessage;
            } catch (NullPointerException e) {
                String greetedMessage = String.format("%s%s%s have been greeted %s%s%s time(s)!", BLUE_BOLD, getName(), RESET, CYAN_BOLD , 0, RESET);
                return greetedMessage;
            }
        }
//        stringMethods.Format(db.findAllUsers());
        return db.findAllUsers().toString();
    }

    public void counter()  {
        stringMethods.counter(db.usersCounter());
    }

    public String greet() {
        try {
            return db.greetPerson(getName(), getLanguage()); //Language.valueOf(language).getExpression() + ", " + userName;
        } catch (Exception e) {
            return stringMethods.invalid(getCommand());
        }
    }

    private void clear() {
        try {
            if(hasName()) {
                db.clearUserByUsername(getName());
            } else {
                db.clearAllUsers();
            }
        } catch (Exception e) {
            if(hasName()) {
                System.out.println("failed to clear name: " + getName());
            } else {
                System.out.println("failed to clear all users");
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
