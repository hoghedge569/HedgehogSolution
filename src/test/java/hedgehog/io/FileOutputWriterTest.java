package hedgehog.io;

import static org.junit.jupiter.api.Assertions.assertThrows;

import hedgehog.exception.OutputWriteException;
import org.junit.jupiter.api.Test;

public class FileOutputWriterTest {

  @Test
  public void write_nullLocation_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new FileOutputWriter().write(1, null));
  }

  @Test
  public void write_emptyLocation_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new FileOutputWriter().write(1, ""));
  }

  @Test
  public void write_whitespaceLocation_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new FileOutputWriter().write(1, " "));
  }

  @Test
  public void write_directory_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new FileOutputWriter().write(1, "./"));
  }

  @Test
  public void write_happyPathTest() throws OutputWriteException {
    new FileOutputWriter().write(1, "./test-output.txt");
  }

}
