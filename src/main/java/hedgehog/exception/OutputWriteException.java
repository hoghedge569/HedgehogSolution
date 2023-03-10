package hedgehog.exception;

public class OutputWriteException extends Exception {

  private static final String MESSAGE = "Exception while attempting to write output.";

  public OutputWriteException(final Throwable cause) {
    super(MESSAGE, cause);
  }

}
