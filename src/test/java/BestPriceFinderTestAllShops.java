package org.weibeld.bestprice;

import org.junit.Test;
import org.junit.Ignore;
import java.util.List;
import java.util.stream.Stream;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.weibeld.bestprice.TestHelpers.getTime;
import static org.weibeld.bestprice.TestHelpers.say;

public class BestPriceFinderTestAllShops {

  private BestPriceFinder mFinder = new BestPriceFinder();
  private String mProduct = "BestProduct";  // "NA"

  @Test
  public void runFindAllPricesAsync() {
    say("> Calling findAllPricesAsync");
    long startTime = getTime();
    Stream<CompletableFuture<ShopPrice>> fut = mFinder.findAllPricesAsync(mProduct);
    say("< findAllPricesAsync returns after " + (getTime() - startTime) + " milliseconds");

    CompletableFuture[] futArr = fut.map(f -> f.thenAccept(price ->
      System.out.println(String.format("%s (%d milliseconds)", price, (getTime() - startTime)))
      ))
      .toArray(size -> new CompletableFuture[size]);
    CompletableFuture.allOf(futArr).join();
    say("All shops returned results after " + (getTime() - startTime) + " milliseconds");
  }

}
