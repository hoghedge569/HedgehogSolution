package hedgehog.exception;

public class InputReadException extends Exception {

  private static final String MESSAGE = "Exception while attempting to read input.";

  public InputReadException(final Throwable cause) {
    super(MESSAGE, cause);
  }

}
