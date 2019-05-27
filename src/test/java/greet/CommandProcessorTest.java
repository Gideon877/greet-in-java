package greet;

import greet.greeter.Greet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static greet.ConsoleColors.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {
    Greet db = new GreetPeople();
    @Nested
    @DisplayName("process")
    class processGreetCommand {
        @BeforeEach
        void getGreet() throws Exception {
            CommandProcessor commandProcessor = new CommandProcessor(db, new CommandExtractor("greet thabang xhosa"));
            assertEquals("greet", commandProcessor.getCommand());
            assertEquals("Xhosa", commandProcessor.getLanguage());
            assertEquals("Thabang", commandProcessor.getName());
            assertEquals(BLACK_BOLD + "Molo, Thabang!" + RESET, commandProcessor.menu());
            assertEquals(BLACK_BOLD + "Molo, Thabang!" + RESET, commandProcessor.menu());
        }

        @AfterEach
        void cleanUp() throws Exception {
            CommandExtractor commandExtractor = new CommandExtractor("clear thabang");
            CommandProcessor commandProcessor = new CommandProcessor(db, commandExtractor);
            commandProcessor.menu();

        }

        @Test
        void getGreeted() throws Exception{
            CommandProcessor commandProcessor = new CommandProcessor(db, new CommandExtractor("greeted"));
//            assertEquals("greeted", commandProcessor.getCommand());
        }

        @Test
        void getGreetedUser() throws Exception{
            CommandProcessor commandProcessor = new CommandProcessor(db, new CommandExtractor("greeted Thabang"));
            assertEquals("greeted", commandProcessor.getCommand());
            String msg = String.format("%s%s%s have been greeted %s%s%s time(s)!", BLUE_BOLD, commandProcessor.getName(), RESET, CYAN_BOLD , 2, RESET);
            assertEquals(msg, commandProcessor.menu());
        }
    }

}
