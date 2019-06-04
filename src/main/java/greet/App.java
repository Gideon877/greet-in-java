package greet;

public class App {
    public static void main(String[] args) throws Exception {
        CommandRunner command = new CommandRunner();
        command.menu();
    }
}

//        mvn dependency:copy-dependencies
//        java -cp .:target/*: greet.App
