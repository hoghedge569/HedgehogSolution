package hedgehog.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class GardenTest {

  private static final int WIDTH = 3; // 'N': Number of columns
  private static final int HEIGHT = 4; // 'M': Number of rows

  @Test
  public void garden_zeroWidth_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new Garden(0, HEIGHT, appleTable()));
  }

  @Test
  public void garden_negativeWidth_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new Garden(-1, HEIGHT, appleTable()));
  }

  @Test
  public void garden_zeroHeight_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new Garden(WIDTH, 0, appleTable()));
  }

  @Test
  public void garden_negativeHeight_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new Garden(WIDTH, -1, appleTable()));
  }

  @Test
  public void garden_nullAppleTable_shouldThrowIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> new Garden(WIDTH, HEIGHT, null));
  }

  @Test
  public void garden_appleTableTooFewRows_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable(WIDTH, HEIGHT - 1)));
  }

  @Test
  public void garden_appleTableTooManyRows_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable(WIDTH, HEIGHT + 1)));
  }

  @Test
  public void garden_appleTableTooFewColumns_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable(WIDTH - 1, HEIGHT)));
  }

  @Test
  public void garden_appleTableTooManyColumns_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable(WIDTH + 1, HEIGHT)));
  }

  @Test
  public void garden_appleTableNegativeApples_shouldThrowIllegalArgument() {
    final int[][] appleTable = appleTable();
    appleTable[1][1] = -appleTable[1][1];
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable));
  }

  @Test
  public void getApples_negativeX_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable()).getApples(-1, 0));
  }

  @Test
  public void getApples_xEqualToWidth_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable()).getApples(WIDTH, 0));
  }

  @Test
  public void getApples_xGreaterThanWidth_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable()).getApples(WIDTH + 1, 0));
  }

  @Test
  public void getApples_negativeY_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable()).getApples(0, -1));
  }

  @Test
  public void getApples_yEqualToHeight_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable()).getApples(0, HEIGHT));
  }

  @Test
  public void getApples_yGreaterThanHeight_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Garden(WIDTH, HEIGHT, appleTable()).getApples(0, HEIGHT + 1));
  }

  @Test
  public void getApples_happyPathTests() {
    final Garden garden = new Garden(WIDTH, HEIGHT, appleTable());
    assertEquals(0, garden.getApples(0, 0));
    assertEquals(11, garden.getApples(1, 1));
    assertEquals(22, garden.getApples(2, 2));
    assertEquals(32, garden.getApples(2, 3));
  }

  @Test
  public void builder_nullWidth_shouldThrowIllegalState() {
    final Garden.Builder builder = new Garden.Builder().height(HEIGHT);
    Arrays.stream(appleTable())
        .map(appleRow -> Arrays.stream(appleRow).boxed().collect(Collectors.toList()))
        .forEach(builder::appleRow);
    assertThrows(IllegalStateException.class, () -> builder.build());
  }

  @Test
  public void builder_nullHeight_shouldThrowIllegalState() {
    final Garden.Builder builder = new Garden.Builder().width(WIDTH);
    Arrays.stream(appleTable())
        .map(appleRow -> Arrays.stream(appleRow).boxed().collect(Collectors.toList()))
        .forEach(builder::appleRow);
    assertThrows(IllegalStateException.class, () -> builder.build());
  }

  @Test
  public void builder_nullAppleTable_shouldThrowIllegalState() {
    assertThrows(
        IllegalStateException.class,
        () -> new Garden.Builder().width(WIDTH).height(HEIGHT).build());
  }

  /**
   * <p>Returns an example <code>appleTable</code> (<code>int[][]<code>).</p>
   *
   * <p>For any given <code>appleRow</code> (<code>int[]</code>) the number of apples will increment
   * by one, from zero to N-1</p>
   *
   * <p><b>For example</b>;<br>
   * <code>[0, 1, 2, ... N-1]</code></p>
   *
   * <p>The first number of apples in each <code>appleRow</code> (<code>int[]</code>) when
   * considered as a sequence (e.g. an <code>appleColumn</code>) will decrement by ten, from
   * (M-1)x10 to zero.</p>
   *
   * <p><b>For example</b>;<br>
   * <code>
   * [(M-1)x10, ...<br>
   * ...<br>
   * [20, ...<br>
   * [10, ...<br>
   * [0, ...
   * </code></p>
   *
   * <p>An example end result for a width of three and a height of four would be as follows;</p>
   *
   * <p><code>
   * [30, 31, 32]<br>
   * [20, 21, 22]<br>
   * [10, 11, 12]<br>
   * [0, 1, 2]
   * </code></p>
   *
   * @return an example <code>appleTable</code>.
   */
  private int[][] appleTable(final int width, final int height) {
    final int[][] appleTable = new int[height][width];
    for (int rowIndex = 0; rowIndex < appleTable.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < appleTable[rowIndex].length; columnIndex++) {
        appleTable[rowIndex][columnIndex] = (appleTable.length - 1 - rowIndex) * 10 + columnIndex;
      }
    }
    return appleTable;
  }

  private int[][] appleTable() {
    return appleTable(WIDTH, HEIGHT);
  }

}
