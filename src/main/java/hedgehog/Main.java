package hedgehog;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

  private static final String DEFAULT_INPUT_FILE_LOCATION = "/input.txt";
  private static final String DEFAULT_OUTPUT_FILE_LOCATION = "./output.txt";

  private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

  public static void main(final String... args) {

    LOGGER.log(Level.INFO, String.format("main [args == %s]", Arrays.toString(args)));

    new HedgehogSolution()
        .execute(
            args.length > 0 ? args[0] : DEFAULT_INPUT_FILE_LOCATION,
            args.length > 1 ? args[1] : DEFAULT_OUTPUT_FILE_LOCATION);
  }

}
