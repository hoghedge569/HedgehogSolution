package hedgehog.io;

import hedgehog.exception.OutputWriteException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FileOutputWriter implements OutputWriter {

  public FileOutputWriter() {
    super();
  }

  @Override
  public void write(final int result, final String outputFileLocation) throws OutputWriteException {

    if (Objects.isNull(outputFileLocation)) {
      throw new IllegalArgumentException(
          "Illegal argument; 'outputFileLocation' cannot be 'null'.");
    }

    if (outputFileLocation.trim().isEmpty()) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; 'outputFileLocation' cannot be empty or whitespace only. "
                  + "[outputFileLocation == %s]",
              outputFileLocation));
    }

    final FileWriter fileWriter;

    try {
      fileWriter = new FileWriter(outputFileLocation);
    } catch (final IOException e) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; file cannot be written to 'outputFileLocation'. "
                  + "[outputFileLocation == %s]",
              outputFileLocation),
          e);
    }

    try (final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

      bufferedWriter.append(Integer.toString(result));

    } catch (final Exception e) {
      throw new OutputWriteException(e);
    }
  }

}
