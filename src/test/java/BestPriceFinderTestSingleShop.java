package org.weibeld.bestprice;

import org.junit.Test;
import org.junit.Ignore;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.weibeld.bestprice.TestHelpers.getTime;
import static org.weibeld.bestprice.TestHelpers.say;
import static org.weibeld.bestprice.TestHelpers.doSomethingElse;

public class BestPriceFinderTestSingleShop {

  private BestPriceFinder mFinder = new BestPriceFinder();
  private String mProduct = "BestProduct";  // "NA"
  private String mShop = "BestShop";

  @Test
  public void runFindPrice() {
    say("> Calling findPrice");
    long startTime = getTime();
    String price = mFinder.findPrice(mShop, mProduct);
    say("< findPrice returns after " + (getTime() - startTime) + " milliseconds");
    say("I have been blocked until now :(");
    say(price);
  }

  @Test
  public void runFindPriceAsync() {
    say("> Calling findPriceAsync");
    long startTime = getTime();
    Future<String> futurePrice = mFinder.findPriceAsync(mShop, mProduct);
    say("< findPriceAsync returns a Future after " + (getTime() - startTime) + " milliseconds");

    say("I can now dow anything I want :)");
    doSomethingElse();

    String price;
    try {
      price = futurePrice.get(10, TimeUnit.SECONDS);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    say("The Future is ready after " + (getTime() - startTime) + " milliseconds");
    say(price);
  }

}
