package hedgehog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record Garden(int width, int height, int[][] appleTable) {

  public Garden {

    if (width <= 0) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; 'width' cannot be less than or equal to zero. [width == %d]",
              width));
    }

    if (height <= 0) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; 'height' cannot be less than or equal to zero. [height == %d]",
              height));
    }

    if (Objects.isNull(appleTable)) {
      throw new IllegalArgumentException("Illegal argument; 'appleTable' cannot be 'null'.");
    }

    if (appleTable.length != height) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; 'appleTable' must have a length equal to the specified 'height'. "
                  + "[appleTable.length == %d, height == %d]",
              appleTable.length,
              height));
    }

    for (int rowIndex = 0; rowIndex < appleTable.length; rowIndex++) {
      if (appleTable[rowIndex].length != width) {
        throw new IllegalArgumentException(
            String.format(
                "Illegal argument; 'appleTable[%d]' must have a length equal to the specified "
                    + "'width'. [appleTable[%d].length == %d, width == %d]",
                rowIndex, rowIndex,
                appleTable[rowIndex].length,
                width));
      }

      for (int columnIndex = 0; columnIndex < appleTable[rowIndex].length; columnIndex++) {
        if (appleTable[rowIndex][columnIndex] < 0) {
          throw new IllegalArgumentException(
              String.format(
                  "Illegal argument; 'appleTable[%d][%d]' cannot be less than zero. "
                      + "[appleTable[%d][%d] == %d]",
                  rowIndex, columnIndex, rowIndex, columnIndex,
                  appleTable[rowIndex][columnIndex],
                  width));
        }
      }
    }
  }

  /**
   * <p>Returns the number of apples for the location specified by <code>x</code> and <code>y</code>
   * where <code>x</code> denotes the column and <code>y</code> the row.</p>
   *
   * <p>The <code>x</code> axis starts at zero which indicates the first (or leftmost) element in an
   * <code>appleRow</code>. The <code>y</code> also starts at zero but this denotes the last (or
   * bottom) element in an <code>appleColumn</code>.</p>
   *
   * <p><b>For example</b>;<br>
   * Given a `appleTable` of <code>[[30, 31, 32], [20, 21, 22], [10, 11, 12], [0, 1, 2]]</code>,
   * this can be visualised on a 2D axis as follows;</p>
   *
   * <p><code>
   * ^ [ [30, 31, 32],<br>
   * | &nbsp; [20, 21, 22],<br>
   * y &nbsp; [10, 11, 12],<br>
   * | &nbsp; [ 0, &nbsp;1, &nbsp;2] ]<br>
   * o--------x-------->
   * </code></p>
   *
   * <p>Then, when <code>x</code> is equal to 0 and <code>y</code> is equal to zero, this method
   * would return <code>0</code>. Likewise, when <code>x=1</code> and <code>y=2</code> then this
   * method would return <code>21</code></p>
   *
   * @param x The desired column
   * @param y The desired row
   *
   * @return the number of apples for the location specified by <code>x</code> and <code>y</code>.
   *
   * @throws IllegalArgumentException if <code>x</code> is less than zero or greater than or equal
   * to the specified width or if <code>y</code> is less than zero of greater than or equal to the
   * specified height.
   */
  public int getApples(final int x, final int y) {

    if (x < 0) {
      throw new IllegalArgumentException(
          String.format("Illegal argument; 'x' cannot be less than zero. [x == %d]", x));
    }

    if (x >= width) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; 'x' cannot be greater than or equal to the specified 'width'. "
                  + "[x == %d, width == %d]",
              x,
              width));
    }

    if (y < 0) {
      throw new IllegalArgumentException(
          String.format("Illegal argument; 'y' cannot be less than zero. [y == %d]", y));
    }

    if (y >= height) {
      throw new IllegalArgumentException(
          String.format(
              "Illegal argument; 'y' cannot be greater than or equal to the specified 'height'. "
                  + "[y == %d, height == %d]",
              y,
              height));
    }

    return appleTable[appleTable.length - 1 - y][x];
  }
  
  @Override
  public String toString() {
    return String.format(
        "%s[width=%d, height=%d, appleTable=%s]",
        Garden.class.getSimpleName(),
        width,
        height,
        "[...]");
  }

  public static class Builder {

    private Integer width;
    private Integer height;
    private List<List<Integer>> appleTable;

    public Builder() {
      super();
    }

    public Builder width(final Integer width) {
      this.width = width;
      return this;
    }

    public Builder height(final Integer height) {
      this.height = height;
      return this;
    }

    public Builder appleRow(final List<Integer> appleRow) {
      if (Objects.isNull(appleTable)) {
        appleTable = new ArrayList<>();
      }
      appleTable.add(appleRow);
      return this;
    }

    public Garden build() {

      if (Objects.isNull(width)) {
        throw new IllegalStateException(
            "Illegal state; cannot build Garden, 'width' not specified.");
      }

      if (Objects.isNull(height)) {
        throw new IllegalStateException(
            "Illegal state; cannot build Garden, 'height' not specified.");
      }

      if (Objects.isNull(this.appleTable)) {
        throw new IllegalStateException(
            "Illegal state; cannot build Garden, 'appleTable' not specified.");
      }

      final Object[] appleTableObj =
          this.appleTable.stream()
              .filter(Objects::nonNull)
              .map(appleRow ->
                  appleRow.stream()
                      .filter(Objects::nonNull)
                      .collect(Collectors.toList())
                      .toArray())
              .collect(Collectors.toList())
              .toArray();

      final int[][] appleTable =
          new int[appleTableObj.length][((Object[]) appleTableObj[0]).length];

      for (int i = 0; i < appleTableObj.length; i++) {
        for (int j = 0; j < ((Object[]) appleTableObj[i]).length; j++) {
          appleTable[i][j] = (int) ((Object[]) appleTableObj[i])[j];
        }
      }

      return new Garden(width, height, appleTable);
    }

  }

}
