package hedgehog.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import hedgehog.exception.InputReadException;
import hedgehog.model.Garden;
import org.junit.jupiter.api.Test;

public class FileInputReaderTest {

  @Test
  public void read_nullLocation_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new FileInputReader().read(null));
  }

  @Test
  public void read_emptyLocation_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new FileInputReader().read(""));
  }

  @Test
  public void read_whitespaceLocation_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new FileInputReader().read(" "));
  }

  @Test
  public void read_fileNotFound_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new FileInputReader().read("/fileNotFound.txt"));
  }

  @Test
  public void read_noWidth_shouldThrowNumberFormat() {
    try {
      new FileInputReader().read("/noWidth.txt");
      fail("'read(\"/noWidth.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(NumberFormatException.class, e.getCause().getClass(), e.getCause().getMessage());
    }
  }

  @Test
  public void read_zeroWidth_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/zeroWidth.txt");
      fail("'read(\"/zeroWidth.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_negativeWidth_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/negativeWidth.txt");
      fail("'read(\"/negativeWidth.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_characterWidth_shouldThrowNumberFormat() {
    try {
      new FileInputReader().read("/characterWidth.txt");
      fail("'read(\"/characterWidth.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          NumberFormatException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_zeroHeight_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/zeroHeight.txt");
      fail("'read(\"/zeroHeight.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_negativeHeight_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/negativeHeight.txt");
      fail("'read(\"/negativeHeight.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_characterHeight_shouldThrowNumberFormat() {
    try {
      new FileInputReader().read("/characterHeight.txt");
      fail("'read(\"/characterHeight.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          NumberFormatException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_noRows_shouldThrowIllegalState() {
    try {
      new FileInputReader().read("/noRows.txt");
      fail("'read(\"/noRows.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(IllegalStateException.class, e.getCause().getClass(), e.getCause().getMessage());
    }
  }

  @Test
  public void read_tooFewRows_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/tooFewRows.txt");
      fail("'read(\"/tooFewRows.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_tooManyRows_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/tooManyRows.txt");
      fail("'read(\"/tooManyRows.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_tooFewColumns_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/tooFewColumns.txt");
      fail("'read(\"/tooFewColumns.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_tooManyColumns_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/tooManyColumns.txt");
      fail("'read(\"/tooManyColumns.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_negativeApples_shouldThrowIllegalArgument() {
    try {
      new FileInputReader().read("/negativeApples.txt");
      fail("'read(\"/negativeApples.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          IllegalArgumentException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_characterApples_shouldThrowNumberFormat() {
    try {
      new FileInputReader().read("/characterApples.txt");
      fail("'read(\"/characterApples.txt\")' should throw an InputReadException.");
    } catch (final InputReadException e) {
      assertEquals(
          NumberFormatException.class,
          e.getCause().getClass(),
          e.getCause().getMessage());
    }
  }

  @Test
  public void read_happyPathTests() throws InputReadException {
    final Garden garden = new FileInputReader().read("/happyPath.txt");
    assertEquals(0, garden.getApples(0, 0));
    assertEquals(11, garden.getApples(1, 1));
    assertEquals(22, garden.getApples(2, 2));
    assertEquals(32, garden.getApples(2, 3));
  }

}
