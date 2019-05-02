package greet;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static greet.ConsoleColors.BLACK_BOLD;
import static greet.ConsoleColors.RESET;
import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {
    @Nested
    @DisplayName("process")
    class processGreetCommand {
        @Test
        void getGreet() throws Exception {
            CommandProcessor commandProcessor = new CommandProcessor(new CommandExtractor("greet thabang xhosa"));
            assertEquals("greet", commandProcessor.getCommand());
            assertEquals("Xhosa", commandProcessor.getLanguage());
            assertEquals("Thabang", commandProcessor.getName());
            assertEquals(BLACK_BOLD + "Molo, Thabang!" + RESET, commandProcessor.menu());
        }

        @Test
        @Disabled("database error")
        void getGreeted() throws Exception{
            CommandProcessor commandProcessor = new CommandProcessor(new CommandExtractor("greeted"));
            assertEquals("greeted", commandProcessor.getCommand());
            assertEquals("{Thabang=1}", commandProcessor.menu());
        }

        @Test
        @Disabled("database error")
        void getGreetedUser() throws Exception{
            CommandProcessor commandProcessor = new CommandProcessor(new CommandExtractor("greeted Thabang"));
            assertEquals("greeted", commandProcessor.getCommand());
            assertEquals("1", commandProcessor.menu());
        }
    }

}