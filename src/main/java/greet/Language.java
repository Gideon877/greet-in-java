package greet;

public enum Language {
    Afrikaans("Goeie dag"),
    English("Hello"),
    Ndebele("Sawubona"),
    Pedi("Thobela"),
    Sotho("Dumela"),
    Swati("Sawubona"),
    Spanish("Ola"),
    Tsonga("Avuxeni"),
    Tswana("Dumela"),
    Venda("Ndaa/Aa"),
    Xhosa("Molo"),
    Zulu("Sawubona");

    private final String expression;

    Language(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }
}
