package greet.proxy;

public class Main {
    public static void main(String[] args) {
        GreetInterface proxy = GreetProxy.newInstance(new Greet());
        proxy.greetPerson("Thabang");
    }
}
