package greet;

public class GreetMenu {
    public static void main(String[] args) throws Exception {
        CommandBuilder command = new CommandBuilder();
        command.menu();
    }
}
//        java -cp .:target/*: greet.GreetMenu
