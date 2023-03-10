package hedgehog.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hedgehog.exception.SolutionException;
import hedgehog.model.Garden;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BinarySolutionStrategyTest {

  @Test
  public void solve_nullGarden_shouldThrowIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new BinarySolutionStrategy().solve(null));
  }

  @Test
  public void solve_happyPathTest() throws SolutionException {

    assertEquals(
        12,
        new BinarySolutionStrategy()
            .solve(
                new Garden.Builder()
                    .width(3)
                    .height(3)
                    .appleRow(Arrays.asList(1, 2, 3))
                    .appleRow(Arrays.asList(1, 3, 3))
                    .appleRow(Arrays.asList(1, 2, 3))
                    .build()));

    assertEquals(
        156,
        new BinarySolutionStrategy()
            .solve(
                new Garden.Builder()
                    .width(12)
                    .height(13)
                    .appleRow(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
                    .appleRow(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1))
                    .appleRow(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2))
                    .appleRow(Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3))
                    .appleRow(Arrays.asList(5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4))
                    .appleRow(Arrays.asList(6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5))
                    .appleRow(Arrays.asList(7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6))
                    .appleRow(Arrays.asList(8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7))
                    .appleRow(Arrays.asList(9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8))
                    .appleRow(Arrays.asList(10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9))
                    .appleRow(Arrays.asList(11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
                    .appleRow(Arrays.asList(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
                    .appleRow(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
                    .build()));
  }

}
