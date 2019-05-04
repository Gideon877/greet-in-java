package greet;

import greet.counter.Counter;

import java.sql.SQLException;

import static greet.ConsoleColors.*;

public class CommandProcessor {
    private final CommandExtractor commandExtractor;
    Counter db;
    StringMethods stringMethods;
//    GreetPeople db;

    public CommandProcessor(CommandExtractor commandExtractor) throws SQLException, ClassNotFoundException {
        this.commandExtractor = commandExtractor;
        db = new Counter();
        stringMethods = new StringMethods();
//        db = new GreetPeople();
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
        } else {
            return stringMethods.invalid(getCommand());
        }
    }

    private String greeted() throws Exception {
        System.out.println(getName().isEmpty());
        if(getName().isEmpty()) {
            stringMethods.Format(db.findAllUsers());
            return db.findAllUsers().toString();
        } else  {
            int counter = 0;

            System.out.println(db.findUser(getName()) + " greeted ");
            if(!db.findUser(getName()).equals(null)) {
                counter = db.findUser(getName()).get(getName());
            }
            return String.format("%s%s%s have been greeted %s%s%s time(s)!", BLUE_BOLD, getName(), RESET, CYAN_BOLD ,counter, RESET);
        }
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
            if(getName().isEmpty()) {
                db.clearAllUsers();
            } else {
                db.clearUserByUsername(getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
