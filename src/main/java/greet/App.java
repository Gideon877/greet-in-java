package greet;

import greet.greeter.GreetBuilder;
import greet.greeter.GreetPerson;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        GreetPerson app = new GreetBuilder().greetPerson;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter name: ");
        String name = scanner.next();

        System.out.println(app.greet(name, "Pedi"));
    }
}
