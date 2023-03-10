package hedgehog.io;

import hedgehog.exception.OutputWriteException;

public interface OutputWriter {

  void write(int result, String location) throws OutputWriteException;

}
