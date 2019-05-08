package greet;

public enum Language {
    English("Hello"),
    Tswana("Dumela"),
    Sotho("Dumela"),
    Pedi("Thobela"),
    Xhosa("Molo"),
    Afrikaans("Goeie dag"),
    Tsonga("Avuxeni"),
    Zulu("Sawubona"),
    Swati("Sawubona"),
    Ndebele("Sawubona"),
    Venda("Ndaa/Aa");

    private final String expression;

    Language(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }
}
