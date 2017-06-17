package org.weibeld.bestprice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.weibeld.bestprice.DiscountService.applyDiscount;

/* Client */
public class BestPriceFinder {

  private final List<Shop> mShops = Arrays.asList(new Shop("BestShop"),
                                                  new Shop("WeRipYouOff"),
                                                  new Shop("Abriss GmbH"),
                                                  new Shop("IKEA"));

  // One thread for each shop (results in total exec time = exec time of 1 shop)
  private final Executor mExec = Executors.newFixedThreadPool(
    Math.min(mShops.size(), 100),
    runnable -> {
      Thread t = new Thread(runnable);
      t.setDaemon(true);
      return t;
    });

  public String findPrice(String shop, String product) {
    return DiscountService.applyDiscount(Quote.parse((new Shop(shop)).getPrice(product)));
  }

  public Future<String> findPriceAsync(String shop, String product) {
    return (new Shop(shop)).getPriceAsync(product)
      .thenApply(Quote::parse)
      .thenCompose(DiscountService::applyDiscountAsync);
  }

  // This solution uses a synchronous API in an asynchronous (non-blocking) way
  public Stream<CompletableFuture<String>> findAllPricesAsync(String product) {
    return mShops.stream()
      .map(shop -> supplyAsync(() -> shop.getPrice(product), mExec))
      .map(f -> f.thenApply(Quote::parse))
      .map(f -> f.thenCompose(q -> supplyAsync(() -> applyDiscount(q), mExec)));
  }

}
