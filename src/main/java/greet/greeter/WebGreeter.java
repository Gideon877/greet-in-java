package greet.greeter;

public class WebGreeter {
    private final String input;
    private String username;
    private String language;

    public WebGreeter(String input) {
        this.input = input;
        setInputs();
    }

    private void setInputs() {
        String[] commands = input.split("&");
        String name = commands[0];
        String lang = commands[1];

        this.language = lang.replaceFirst("language=", "");
        this.username = name.replaceFirst("username=", "");
    }

    public boolean isValid() {
        return !username.isEmpty() && !language.isEmpty();
    }

    public String getName() {
        return username;
    }

    public String getLanguage() {
        return language;
    }
}
