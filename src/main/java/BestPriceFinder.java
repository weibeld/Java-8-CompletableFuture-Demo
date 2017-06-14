package org.weibeld.bestprice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinder {

  private final List<Shop> mShops = Arrays.asList(new Shop("BestShop"),
                                                  new Shop("WeRipYouOff"),
                                                  new Shop("Abriss GmbH"),
                                                  new Shop("IKEA"));

  // One thread for each shop (results in total exec time = exec time of 1 shop)
  private final Executor mExecutor = Executors.newFixedThreadPool(
    Math.min(mShops.size(), 100),
    runnable -> {
      Thread t = new Thread(runnable);
      t.setDaemon(true);
      return t;
    });

  public String findPrice(String shop, String product) {
    return (new Shop(shop)).getPrice(product);
  }

  public Future<String> findPriceAsync(String shop, String product) {
    return (new Shop(shop)).getPriceAsync(product);
  }

  public List<String> findAllPrices(String product) {
    return mShops.stream()
      .map(shop -> shop.getPrice(product))
      .collect(Collectors.toList());
  }

  public List<String> findAllPricesParallel(String product) {
    // Note that this stream is not guaranteed to be parallel
    return mShops.parallelStream()
      .map(shop -> shop.getPrice(product))
      .collect(Collectors.toList());
  }

  public List<String> findAllPricesAsync(String product) {
    List<CompletableFuture<String>> futures = mShops.stream()
      .map(s -> CompletableFuture.supplyAsync(() -> s.getPrice(product), mExecutor))
      .collect(Collectors.toList());

    // Can do something else here

    return futures.stream()
      .map(CompletableFuture::join)  // blocking...
      .collect(Collectors.toList());
  }

}
