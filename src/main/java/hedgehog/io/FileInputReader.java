package hedgehog.io;

import hedgehog.exception.InputReadException;
import hedgehog.model.Garden;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileInputReader implements InputReader {

  private static final String DELIMITER = " ";

  public FileInputReader() {
    super();
  }

  @Override
  public Garden read(final String inputFileLocation) throws InputReadException {

    if (Objects.isNull(inputFileLocation)) {
      throw new IllegalArgumentException("Illegal argument; 'inputFileLocation' cannot be 'null'.");
    }

    if (inputFileLocation.trim().isEmpty()) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; 'inputFileLocation' cannot be empty or whitespace only. "
                  + "[inputFileLocation == %s]",
              inputFileLocation));
    }

    final InputStream inputStream = getClass().getResourceAsStream(inputFileLocation);

    if (Objects.isNull(inputStream)) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; file cannot be found at 'inputFileLocation'. "
                  + "[inputFileLocation == %s]",
              inputFileLocation));
    }

    final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

    try (final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

      final Garden.Builder builder = new Garden.Builder();

      String line = bufferedReader.readLine();
      final String[] splitLine = line.split(DELIMITER);

      builder
          .width(Integer.parseInt(splitLine[0]))
          .height(Integer.parseInt(splitLine[1]));

      while (Objects.nonNull(line = bufferedReader.readLine())) {
        builder.appleRow(
            Arrays.stream(line.split(DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
      }

      return builder.build();

    } catch (final Exception e) {
      throw new InputReadException(e);
    }
  }

}
