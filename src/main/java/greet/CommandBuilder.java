package greet;

import greet.counter.Counter;
import greet.greeter.Greet;

import java.util.Scanner;

public class CommandBuilder {
    StringMethods app = new StringMethods();
    public void menu() throws Exception {
        Greet db = new GreetPeople();
        Scanner input =  new Scanner(System.in);

        boolean exitNow = true;

        while(exitNow) {
            System.out.print("Please enter your command:");
            String commandInput = input.nextLine();
            if (commandInput.equals("exit")){
                exitNow = false;
                app.goodbye();
                return;
            }
            System.out.println(new CommandProcessor(db, new CommandExtractor(commandInput)).menu());
        }
    }
}
