package greet;

import greet.greeter.GreetBuilder;
import greet.greeter.GreetPerson;

public class App {
    public static void main(String[] args) throws Exception {

        GreetPerson greetBuilder = new GreetBuilder().greetPerson;

        System.out.println(greetBuilder.greet("Lefatshe", "Pedi"));
    }
}
