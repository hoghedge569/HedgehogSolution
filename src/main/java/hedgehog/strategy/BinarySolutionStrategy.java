package hedgehog.strategy;

import hedgehog.exception.SolutionException;
import hedgehog.model.Garden;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class BinarySolutionStrategy implements SolutionStrategy {

  public BinarySolutionStrategy() {
    super();
  }

  /**
   * <p>The solution works by virtue of the fact that all paths the hedgehog can take will result in
   * the same number of moves (<code>width + height - 2</code>). Also, at each point on the path the
   * hedgehog has a choice of two possible moves (right or down). This lends itself well to the use
   * of binary numbers (e.g. <code>0101</code>) where in this case, zero means 'move right' and one
   * means 'move down'.</p>
   *
   * <p>Any binary number is a valid path so long as it has the correct number of digits (i.e. the
   * number of moves, <code>width - height - 2</code>), the number of right moves is one less than
   * the width of the garden (<code>width - 1</code>), and the number of down moves is one less than
   * the height of the garden (<code>height - 1</code>).</p>
   *
   * <p>All possible paths are then found by iterating through all numbers less than or equal to the
   * maximum number that can be defined using <code>width - height - 2</code> binary digits (e.g.
   * <code>4</code> digits > <code>1111</code> = <code>15</code>) and by filtering out any invalid
   * paths using the conditions above.</p>
   *
   * <p>Once all possible paths have been determined, the maximum number of apples the hedgehog can
   * collect is calculated by finding the sum of apples on each path and then by selecting the
   * highest value.</p>
   *
   * @param garden An instance of {@link Garden}.
   *
   * @return The maximum number of apples the hedgehog can collect while moving across the garden.
   *
   * @throws SolutionException If there was a problem while calculating the solution.
   * @throws IllegalArgumentException If <b>garden</b> is <code>null</code>.
   *
   * @see Garden
   */
  @Override
  public int solve(final Garden garden) throws SolutionException {

    if (Objects.isNull(garden)) {
      throw new IllegalArgumentException("Illegal argument; 'garden' cannot be 'null'");
    }

    final int numMoves = garden.width() + garden.height() - 2;
    final int binaryMax = IntStream.range(0, numMoves).map(i -> (int) Math.pow(2, i)).sum();

    try {

      return LongStream.rangeClosed(0, binaryMax)
          .mapToObj(Long::toBinaryString)
          .filter(binary -> binary.replaceAll("0", "").length() == garden.height() - 1)
          .map(binary ->
              String.format(
                  "%s%s",
                  IntStream.range(0, numMoves - binary.length())
                      .mapToObj(__ -> "0")
                      .collect(Collectors.joining()),
                  binary))
          .map(binary -> binary.split(""))
          .map(moves -> {

            final AtomicInteger x = new AtomicInteger();
            final AtomicInteger y = new AtomicInteger(garden.height() - 1);
            final AtomicInteger apples = new AtomicInteger(garden.getApples(x.get(), y.get()));

            Arrays.stream(moves)
                .forEach(move -> {

                  if (move.equals("0")) {
                    x.getAndIncrement();
                  } else {
                    y.getAndDecrement();
                  }

                  apples.addAndGet(garden.getApples(x.get(), y.get()));
                });

            return apples.get();
          })
          .max(Comparator.naturalOrder())
          .orElseThrow(() -> new IllegalStateException("Illegal state; unable to find solution."));

    } catch (final Exception e) {
      throw new SolutionException(e);
    }
  }

}
