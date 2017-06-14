package org.weibeld.bestprice;

public class TestHelpers {

  public static void say(String str) {
    System.out.println(str);
  }

  public static long getTime() {
    return System.nanoTime() / 1_000_000;
  }

  public static void doSomethingElse() {
    delay();
    say("doodelidum..");
    delay();
    say("doodelidum....");
  }

  public static void delay() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
