package io.github.jzdayz.ex;

public class Trys {

  private Trys() {

  }

  public static void doWOE(Action action) {
    try {
      action.run();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public interface Action {

    void run() throws Exception;
  }
}
