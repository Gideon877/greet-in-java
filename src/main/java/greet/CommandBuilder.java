package greet;

import java.util.Scanner;

public class CommandBuilder {
    StringMethods app = new StringMethods();

    public void menu() throws Exception {
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
            new CommandProcessor(new CommandExtractor(commandInput)).menu();
        }
    }
}
