package hedgehog.exception;

public class SolutionException extends Exception {

  private static final String MESSAGE = "Exception while attempting to solve.";

  public SolutionException(final Throwable cause) {
    super(MESSAGE, cause);
  }

}
