import org.junit.Test;
import org.junit.Ignore;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class ShopTest {

  private Shop shop = new Shop("BestShop");
  private final String PRODUCT = "BestProduct";

  @Test
  public void testGetPriceAsync() {
    say("> Calling getPriceAsync");
    long startTime = getTime();
    Future<Double> futurePrice = shop.getPriceAsync(PRODUCT);
    say("< getPriceAsync returns a Future after " + (getTime() - startTime) + " milliseconds");

    say("I can now dow anything I want :)");
    doSomethingElse();

    double price;
    try {
      price = futurePrice.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    say("The Future is ready after " + (getTime() - startTime) + " milliseconds, price is " + round(price));
  }

  @Test
  public void testGetPriceSync() {
    say("> Calling getPrice");
    long startTime = getTime();
    double price = shop.getPrice(PRODUCT);
    say("< getPrice returns after " + (getTime() - startTime) + " milliseconds");
    say("I have been blocked until now :(");
    say("The price is " + round(price));
  }


  private void say(String str) {
    System.out.println(str);
  }

  private long getTime() {
    return System.nanoTime() / 1_000_000;
  }

  private void doSomethingElse() {
    delay();
    say("doodelidum..");
    delay();
    say("doodelidum....");
  }

  private void delay() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private double round(double number) {
    // Round to 2 decimal places
    return Math.round((number * 100)) / 100.0;
  }
}
