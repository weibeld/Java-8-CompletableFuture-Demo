package org.weibeld.bestprice;

import java.util.concurrent.CompletableFuture;

/* Server
 * API:
 *   static String                    applyDiscount      (Quote quote)
 *   static CompletableFuture<String> applyDiscountAsync (Quote quote) */
public class DiscountService {


  public enum Code {
    NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
    private final int percentage;
    Code(int percentage) {
      this.percentage = percentage;
    }
  }

  public static String applyDiscount(Quote quote) {
    double discountedPrice = apply(quote.getPrice(), quote.getDiscountCode());
    Util.randomDelay();
    return String.format("%s: %.2f", quote.getShopName(), discountedPrice);
  }

  public static CompletableFuture<String> applyDiscountAsync(Quote quote) {
    return CompletableFuture.supplyAsync(() -> applyDiscount(quote));
  }

  private static double apply(double price, Code code) {
    return price * (100-code.percentage) / 100;
  }

}
