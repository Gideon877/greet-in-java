package greet;

import org.junit.jupiter.api.*;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CommandBuilderTest {
    CommandBuilder command;
    {
        try {
            command = new CommandBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        String commands = "greet Thabo";

        command.greet(commands.split(" "));
        
    }

    @AfterEach
    void tearDown() {

    }

    @Nested
    @DisplayName("when menu")
    class WhenMenu {
        @Test
        @DisplayName("greet person when popped")
        void greetPerson() {
//            command.menu();
            // test (scanner.in)
            assertEquals(true, 1 == 1);

        }

    }

//    public static class CommandAsker {
//        private final Scanner scanner;
//        private final PrintStream out;
//
//        public CommandAsker(InputStream in, PrintStream out) {
//            scanner = new Scanner(in);
//            this.out = out;
//        }
//
//        public String ask(String message) {
//            out.println(message);
//            return scanner.nextLine();
//        }
//    }

}