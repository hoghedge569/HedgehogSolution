package hedgehog.io;

import hedgehog.exception.InputReadException;
import hedgehog.model.Garden;

public interface InputReader {

  Garden read(String location) throws InputReadException;

}
