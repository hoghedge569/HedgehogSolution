package hedgehog;

import hedgehog.exception.InputReadException;
import hedgehog.exception.OutputWriteException;
import hedgehog.exception.SolutionException;
import hedgehog.io.FileOutputWriter;
import hedgehog.io.InputReader;
import hedgehog.io.FileInputReader;
import hedgehog.io.OutputWriter;
import hedgehog.model.Garden;
import hedgehog.strategy.BinarySolutionStrategy;
import hedgehog.strategy.SolutionStrategy;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HedgehogSolution {

  private final InputReader inputReader;
  private final OutputWriter outputWriter;
  private final SolutionStrategy solutionStrategy;

  private static final Logger LOGGER = Logger.getLogger(HedgehogSolution.class.getSimpleName());

  public HedgehogSolution() {
    super();
    inputReader = new FileInputReader();
    outputWriter = new FileOutputWriter();
    solutionStrategy = new BinarySolutionStrategy();
  }

  public void execute(final String inputFileLocation, final String outputFileLocation) {

    LOGGER.log(
        Level.INFO,
        String.format(
            "execute[inputFileLocation == %s, outputFileLocation == %s]",
            inputFileLocation,
            outputFileLocation));

    read(inputFileLocation)
        .flatMap(this::solve)
        .ifPresent(solution -> write(solution, outputFileLocation));
  }

  private Optional<Garden> read(final String inputFileLocation) {

    LOGGER.log(Level.INFO, String.format("read[inputFileLocation == %s]", inputFileLocation));

    Garden garden = null;

    try {
      garden = inputReader.read(inputFileLocation);
    } catch (final InputReadException e) {
      LOGGER.log(
          Level.SEVERE,
          String.format(
              "InputReadException caught while attempting to read input file. "
                  + "[inputFileLocation == %s]",
              inputFileLocation),
          e);
    }

    return Optional.ofNullable(garden);
  }

  private Optional<Integer> solve(final Garden garden) {

    LOGGER.log(Level.INFO, String.format("solve[garden == %s]", garden));

    Integer solution = null;

    try {
      solution = solutionStrategy.solve(garden);
    } catch (final SolutionException e) {
      LOGGER.log(
          Level.SEVERE,
          String.format(
              "SolutionException caught while attempting to solve. [garden == %s]",
              garden),
          e);
    }

    return Optional.ofNullable(solution);
  }

  private void write(final int solution, final String outputFileLocation) {

    LOGGER.log(
        Level.INFO,
        String.format(
            "write[solution == %d, outputFileLocation == %s]",
            solution,
            outputFileLocation));

    try {
      outputWriter.write(solution, outputFileLocation);
    } catch (final OutputWriteException e) {
      LOGGER.log(
          Level.SEVERE,
          String.format(
              "OutputWriteException caught while attempting to write output file. [solution == %d, "
                  + "outputFileLocation == %s]",
              solution,
              outputFileLocation),
          e);
    }
  }

}
