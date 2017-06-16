package org.weibeld.bestprice;

public class TestHelpers {

  public static void say(String str) {
    System.out.println(str);
  }

  public static long getTime() {
    return System.nanoTime() / 1_000_000;
  }

  public static void doSomethingElse() {
    //Util.delay(500L);
    say("doodelidum..");
    //Util.delay(500L);
    //say("doodelidum....");
  }

}
