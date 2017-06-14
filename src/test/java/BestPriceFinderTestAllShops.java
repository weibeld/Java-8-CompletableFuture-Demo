package org.weibeld.bestprice;

import org.junit.Test;
import org.junit.Ignore;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.weibeld.bestprice.TestHelpers.getTime;
import static org.weibeld.bestprice.TestHelpers.say;

public class BestPriceFinderTestAllShops {

  private BestPriceFinder mFinder = new BestPriceFinder();
  private String mProduct = "BestProduct";  // "NA"

  @Test
  public void runFindAllPrices() {
    say("> Calling findAllPrices");
    long startTime = getTime();
    List<String> prices = mFinder.findAllPrices(mProduct);
    say("< findAllPrices returns after " + (getTime() - startTime) + " milliseconds");
    prices.stream().forEach(TestHelpers::say);
  }

  @Test
  public void runFindAllPricesParallel() {
    say("> Calling findAllPricesParallel");
    long startTime = getTime();
    List<String> prices = mFinder.findAllPrices(mProduct);
    say("< findAllPricesParallel returns after " + (getTime() - startTime) + " milliseconds");
    prices.stream().forEach(TestHelpers::say);
  }

  @Test
  public void runFindAllPricesAsync() {
    say("> Calling findAllPricesAsync");
    long startTime = getTime();
    List<String> prices = mFinder.findAllPricesAsync(mProduct);
    say("< findAllPricesAsync returns after " + (getTime() - startTime) + " milliseconds");
    prices.stream().forEach(TestHelpers::say);
  }

}
