package greet;

public enum Language {
    Afrikaans("Goeie dag"),
    Chinese("Nín hao"),
    English("Hello"),
    French("Bonjour"),
    Hindi("Namasté"),
    Italian("Buongiorno"),
    Ndebele("Sawubona"),
    Pedi("Thobela"),
    Sotho("Dumela"),
    Spanish("Ola"),
    Swati("Sawubona"),
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
