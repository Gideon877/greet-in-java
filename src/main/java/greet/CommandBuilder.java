package greet;

import greet.counter.Counter;

import java.sql.SQLException;
import java.util.Scanner;

import static greet.ConsoleColors.*;

public class CommandBuilder {
    Counter db;
//    GreetPeople db;

    StringMethods stringMethods = new StringMethods();

    public CommandBuilder() throws SQLException, ClassNotFoundException {
//        db = new GreetPeople();
        db = new Counter(); }

    public void menu() throws Exception {
        Scanner input =  new Scanner(System.in);

        boolean exitNow = true;

        while(exitNow) {
            System.out.print("Please enter your command:");
            String commandInput = input.nextLine();
            if (commandInput.equals("exit")){
                exitNow = false;
                goodbye();
                return;
            }
            String[] commandParts = commandInput.split(" "); // 'greet lihle english' == ['greet', 'lihle']
            String command = commandParts[0];

            if (command.equalsIgnoreCase("greet")) {
                greet(commandParts);
            } else if(command.equalsIgnoreCase("greeted")) {
                greeted(commandInput);
            } else if(command.equalsIgnoreCase("clear")) {
                clear(commandInput);
            } else if (command.equalsIgnoreCase("counter")) {
                counter();
            } else if(command.equalsIgnoreCase("help")) {
                help();
            } else {
                invalid(command);
            }
        }
    }

    private void greeted(String commandInput) throws Exception {

        String[] commandParts = commandInput.split(" ");

        if(commandParts.length == 1) {
            stringMethods.Format(db.findAllUsers());
        } else if(!commandParts[1].isEmpty()) {
            String name = stringMethods.Capitalize(commandParts[1]);
            int counter = 0;

            if(!db.findUser(name).isEmpty()) {
                counter = db.findUser(name).get(name);
            }
            String countMessage = String.format("%s%s%s have been greeted %s%s%s time(s)!",BLUE_BOLD , name, RESET, CYAN_BOLD ,counter, RESET);
            System.out.println(countMessage);
        }
    }

    public void counter() throws Exception {
        try {
            System.out.println("--------------------------");
            System.out.printf("%20s %s%s%s", "Users greeted:", CYAN_BOLD , db.usersCounter(), RESET);
            System.out.println();
            System.out.println("--------------------------");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void greet(String[] commandParts) throws Exception {
        String message = "", userName = "", language ="";
        try {
            if(commandParts.length == 2) {
                userName = commandParts[1];
                language = null;
            } else if(!commandParts[2].isEmpty()) {
                userName = commandParts[1];
                language = commandParts[2];
            }
            message = db.greetPerson(userName, language); //Language.valueOf(language).getExpression() + ", " + userName;
            System.out.println(message);
        } catch (Exception e) {
            invalid(commandParts[0]);
        }
    }

    private void clear(String commandInput) throws Exception {
        String[] commandParts = commandInput.split(" ");
        try {
            if(commandParts.length == 2) {
                String userName = stringMethods.Capitalize(commandParts[1]);
                db.clearUserByUsername(userName);
            } else if(commandParts.length == 1) {
                db.clearAllUsers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void goodbye() {
        System.out.println("--------------------");
        System.out.printf("%s%15s%s\n", BLUE_BOLD_BRIGHT, "goodbye!", RESET);
        System.out.println("--------------------");
    }

    private static void help() {
        String help, title, greet, greeted, greetedName, counter, clear, clearUsers, exit;

        title = GREEN_BOLD + "Valid commands are:\n\n";
        greet = PURPLE_BOLD + "greet " + RESET + "followed by the " + YELLOW_BOLD + "name"+ RESET + " and the " + CYAN_BOLD + "language" + RESET + " the user is to be greeted in,\n";
        greeted = helpStringBuilder("greeted", "display a list of all users that has been greeted and how many time each user has been greeted,\n");
        greetedName = helpStringBuilderTwo("greeted", "followed by a","username", "returns how many times that username have been greeted,\n");
        clear = helpStringBuilder("clear", "deletes of all users greeted and the reset the greet counter to 0,\n");
        counter = helpStringBuilder("counter", "returns a count of how many unique users has been greeted,\n");
        clearUsers = helpStringBuilderTwo("clear", "followed by a","username","delete the greet counter for the specified user and decrement the greet counter by 1,\n");
        exit = helpStringBuilder("exit", "exits the application,\n");
        help = helpStringBuilder("help", "shows a user an overview of all possible commands.\n\n");

        System.out.printf("%s%s%s%s%s%s%s%s%s%s", title, RESET, greet, greeted, greetedName, counter, clear, clearUsers, exit, help);
    }

    private static String helpStringBuilder(String command, String sentence) {
        return String.format("%s%s%s %s", PURPLE_BOLD, command, RESET, sentence);
    }

    private static String helpStringBuilderTwo(String command, String sentence, String commandTwo, String sentenceTwo) {
        return String.format("%s%s%s %s %s%s%s %s", PURPLE_BOLD, command, RESET, sentence, PURPLE_BOLD, commandTwo, RESET, sentenceTwo);
    }

    private static void invalid(String command) {
        String appName = WHITE_BOLD + "greet-in-java:" + RESET;
        command = RED_UNDERLINED + command + RESET;
        System.out.printf("%s %s%s %s%s %s%s%s ", appName, command, RED, "command not found:", RESET, GREEN ,"type help for all possible commands", RESET);
        System.out.println();
    }
}



//         commandExtractor = new CommandExtractor(commandInput);
//                 commandProcessor = new CommandProcessor(commandExtractor);
//
//
//                 if(!commandProcessor.menu().isEmpty()) {
//                 System.out.println(commandProcessor.menu());
//                 }