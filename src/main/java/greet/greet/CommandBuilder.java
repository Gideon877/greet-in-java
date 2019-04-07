package greet.greet;

import java.util.Scanner;

public class CommandBuilder {

    public void menu() throws Exception {
        Scanner input =  new Scanner(System.in);

        boolean exitNow = false;

        while(!exitNow) {
            System.out.print("Please enter your command:");
            String commandInput = input.nextLine();
            if (commandInput.equals("exit")){
                exitNow = true;
                goodbye();
                return;
            }

            String[] commandParts = commandInput.split(" ");
            String command = commandParts[0];

            if (command.equalsIgnoreCase("greet")) {
                greet(commandInput);
            } else if(command.equalsIgnoreCase("greeted")) {
                greeted(commandInput);
            } else if(command.equalsIgnoreCase("clear")) {
                clear(commandInput);
            } else if (command.equalsIgnoreCase("counter")) {
                counter();
            } else if(command.equalsIgnoreCase("help")) {
                help();
            } else {
                invalid();
            }
        }
    }

    private static void greeted(String commandInput) throws Exception {
        StringMethods stringMethods = new StringMethods();
        GreetPeople greet = new GreetPeople();

        String[] commandParts = commandInput.split(" ");
        String countMessage = "";

        if(commandParts.length == 1) {
            stringMethods.Format(greet.getUsersCount());
        } else if(!commandParts[1].isEmpty()) {
            String name = stringMethods.Capitalize(commandParts[1]);
            countMessage = String.format("%s people have been greeted %s time(s)!", name, greet.getGreetCounter(name));
            System.out.println(countMessage);
        }
    }

    private static void counter() throws Exception {
        GreetPeople greet = new GreetPeople();
        try {
            String countMessage;
            countMessage =  String.format("greeted users: %s!", greet.counter());
            System.out.println(countMessage);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void greet(String commandInput) throws Exception {
        String[] commandParts = commandInput.split(" ");
        String message = "", userName = "", language ="";
        GreetPeople greet = new GreetPeople();

        try {
            if(commandParts.length == 2) {
                userName = commandParts[1];
                language = null;
            } else if(!commandParts[2].isEmpty()) {
                userName = commandParts[1];
                language = commandParts[2];
            }
            message = greet.greetPerson(userName, language);
            System.out.println(message);
        } catch (Exception e) {
        }
    }

    private static void clear(String commandInput) throws Exception {
        String[] commandParts = commandInput.split(" ");
        GreetPeople greet = new GreetPeople();
        try {
            if(commandParts.length == 2) {
                String userName = commandParts[1];
                greet.clearUser(userName);
            } else if(commandParts.length == 1) {
                greet.clearAllUsers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void goodbye() {
        System.out.println("--------------------");
        System.out.printf("%15s","goodbye!");
        System.out.println();
        System.out.println("--------------------");
    }

    public static void help() {
        String menu = "Valid commands are:\n\ngreet followed by the [name] and the [language] the user is to be greeted in,\ngreeted display a list of all users that has been greeted and how many time each user has been greeted,\ngreeted followed by a [username] returns how many times that username have been greeted,\ncounter returns a count of how many unique users has been greeted,\nclear deletes of all users greeted and the reset the greet counter to 0,\nclear followed by a username delete the greet counter for the specified user and decrement the greet counter by 1,\nexit exits the application,\nhelp shows a user an overview of all possible commands.\n";
        System.out.println(menu);

    }

    public static void invalid() {
        System.out.println("Invalid command! Type help for all possible commands.\n");
    }

}
