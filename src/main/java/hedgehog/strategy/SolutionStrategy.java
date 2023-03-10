package hedgehog.strategy;

import hedgehog.exception.SolutionException;
import hedgehog.model.Garden;

public interface SolutionStrategy {

  int solve(Garden garden) throws SolutionException;

}
