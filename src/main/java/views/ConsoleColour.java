package views;

public enum ConsoleColour {
    RESET("\033[0m"),
    RED("\033[0;31m"),
    GREEN("\033[0;92m"),
    BLUE("\033[0;34m"),
    YELLOW("\033[0;93m"),
    WHITE("\033[0;97m");

    private final String code;

    ConsoleColour(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}