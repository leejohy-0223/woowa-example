package racing.exception;

public class InvalidInputException extends IllegalStateException {

    private static final String HEADER = "[ERROR] ";
    private String message = null;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return HEADER + message;
    }
}
